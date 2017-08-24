package gq.fokia.smartaide.data.remote;

import com.alibaba.fastjson.JSON;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import gq.fokia.smartaide.data.Group;
import gq.fokia.smartaide.data.User;

/**
 * Created by archie on 7/22/17.
 */

public class QueueFetcher {

    private String jsonString;

    public byte[] getUrlBytes(String urlSpec) throws IOException{
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try{
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            if(connection.getResponseCode() != HttpURLConnection.HTTP_OK){
                throw new IOException(connection.getResponseMessage() +
                ": with "+
                urlSpec);
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0){
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        }finally {
            connection.disconnect();
        }
    }

    public String getJsonString(String urlSpec) throws IOException{
        return jsonString = new String(getUrlBytes(urlSpec));
    }

    public List<User> getUrlString(String urlSpec) throws IOException{
        jsonString = new String(getUrlBytes(urlSpec));
        return fetchItems();
    }

    private void parseItems(List<User> items)throws IOException, JSONException{
        Group group = JSON.parseObject(jsonString, Group.class);
        items.addAll(group.getUsers());
    }

    public List<User> fetchItems(){
        List<User> items = new ArrayList<>();
        try{
            parseItems(items);
        }catch (JSONException je){
            je.printStackTrace();
        }catch (IOException ie){
            ie.printStackTrace();
        }
        return items;
    }
}
