package gq.fokia.smartaide.data.remote;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gq.fokia.smartaide.data.UserDataSource;
import gq.fokia.smartaide.model.User;
import gq.fokia.smartaide.utils.HttpUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by archie on 10/6/17.
 */

public class UserRemoteDataSource implements UserDataSource {

    public static UserRemoteDataSource INSTANCE;

    private List<User> mUserList = new ArrayList<>();

    private static final String URL = "http://192.168.1.6:8080/SmartAide/users";

    private UserRemoteDataSource(){}

    public static UserRemoteDataSource getInstance() {
        if (INSTANCE == null ){
            INSTANCE = new UserRemoteDataSource();
        }
        return INSTANCE;
    }


    @Override
    public void getUsers(final LoadUsersCallback callback) {
        HttpUtil.sendGetRequest(URL, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onDataNotAvailable();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonString = response.body().string();
                Gson gson = new Gson();
                mUserList = gson.fromJson(jsonString, new TypeToken<List<User>>(){}.getType());
                callback.onUsersLoaded(mUserList);
            }
        });
    }

    @Override
    public void postUser(User user) {
        HttpUtil.sendUserPostRequest(URL, user, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                response.body().string();
            }
        });
    }

    @Override
    public void putUser(User user) {
        HttpUtil.sendUserPutRequest(URL, user, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                response.body().string();
            }
        });

    }

    @Override
    public void deleteUser(User user) {
        HttpUtil.sendUserDeleteRequest(URL, user, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                response.body().string();
            }
        });
    }

}
