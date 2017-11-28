package gq.fokia.smartaide.data.remote;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gq.fokia.smartaide.data.UnitDataSource;
import gq.fokia.smartaide.model.Unit;
import gq.fokia.smartaide.utils.HttpUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by archie on 9/6/17.
 */

public class UnitsRemoteDataSource implements UnitDataSource{

    public static UnitsRemoteDataSource INSTANCE;

    private List<Unit> mUnitList = new ArrayList<>();

    private static final String URL = "http://172.20.205.241:8080/SmartAide/units";

    private UnitsRemoteDataSource(){}

    public static UnitsRemoteDataSource getInstance() {
        if (INSTANCE == null ){
            INSTANCE = new UnitsRemoteDataSource();
        }
        return INSTANCE;
    }


    @Override
    public void getUnits(final LoadUnitsCallback callback) {

        HttpUtil.sendGetRequest(URL, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onDataNotAvailable();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonString = response.body().string();
                Gson gson = new Gson();
                mUnitList = gson.fromJson(jsonString, new TypeToken<List<Unit>>(){}.getType());
                callback.onUnitsLoaded(mUnitList);
            }
        });
    }

    @Override
    public void postUnit(Unit unit) {
        HttpUtil.sendUnitPostRequest(URL, unit, new Callback() {
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
    public void putUnit(Unit unit) {
        HttpUtil.sendUnitPutRequest(URL, unit, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    @Override
    public void deleteUnit(Unit unit) {
        HttpUtil.sendUnitDeleteRequest(URL, unit, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }
}
