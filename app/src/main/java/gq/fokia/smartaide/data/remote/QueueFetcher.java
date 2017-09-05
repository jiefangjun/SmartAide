package gq.fokia.smartaide.data.remote;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gq.fokia.smartaide.model.Unit;
import gq.fokia.smartaide.model.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by archie on 7/22/17.
 */

public class QueueFetcher {

    private String jsonString;
    private List<User> mUserList = new ArrayList<>();

    private List<Unit> mUnitList = new ArrayList<>();

    public List<User> getUsers(String urlSpec) throws IOException{
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(urlSpec)
                .build();
        Response response = client.newCall(request).execute();
        jsonString = response.body().string();
        Gson gson = new Gson();
        mUserList = gson.fromJson(jsonString, new TypeToken<List<User>>(){}.getType());
        return mUserList;
    }

    public List<Unit> getUnits(String urlSpec) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(urlSpec)
                .build();
        Response response = client.newCall(request).execute();
        jsonString = response.body().string();
        Gson gson = new Gson();
        mUnitList = gson.fromJson(jsonString, new TypeToken<List<Unit>>(){}.getType());
        return mUnitList;
    }



}
