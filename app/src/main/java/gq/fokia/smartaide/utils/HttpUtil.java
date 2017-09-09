package gq.fokia.smartaide.utils;

import gq.fokia.smartaide.model.Unit;
import gq.fokia.smartaide.model.User;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by archie on 9/3/17.
 */

public class HttpUtil {

    public static void sendUserGetRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void sendUserPostRequest(String address, User user, okhttp3.Callback callback){
        RequestBody requestBody = new FormBody.Builder()
                .add("name", user.getName())
                .add("password", user.getPassword())
                .add("avatar", user.getAvatar())
                .add("is_merchant", user.getIs_merchant())
                .build();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);

    }

    public static void sendUnitPostRequest(String address, Unit unit, Callback callback){
        RequestBody requestBody = new FormBody.Builder()
                .add("user_avatar", unit.getUserAvatar())
                .add("restaurant_name", unit.getRestaurantName())
                .add("user_name", unit.getUserName())
                .add("product_name", unit.getProductName())
                .build();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void sendUnitPutRequest(String address, Unit unit, Callback callback) {
        RequestBody requestBody = new FormBody.Builder()
                .add("user_avatar", unit.getUserAvatar())
                .add("restaurant_name", unit.getRestaurantName())
                .add("user_name", unit.getUserName())
                .add("product_name", unit.getProductName())
                .build();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .put(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void sendUnitDeleteRequest(String address, Unit unit, Callback callback) {
        RequestBody requestBody = new FormBody.Builder()
                .add("user_avatar", unit.getUserAvatar())
                .add("restaurant_name", unit.getRestaurantName())
                .add("user_name", unit.getUserName())
                .add("product_name", unit.getProductName())
                .build();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .delete(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
