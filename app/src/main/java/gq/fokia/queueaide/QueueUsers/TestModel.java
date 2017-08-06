package gq.fokia.queueaide.QueueUsers;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import gq.fokia.queueaide.data.User;
import gq.fokia.queueaide.data.remote.QueueFetcher;

/**
 * Created by archie on 7/29/17.
 */

public class TestModel implements QueueUsersContract.Model {

    private static TestModel mTestModel;
    private List<User> mUserList;
    private FetchItemsTask mFetchItemsTask;

    public static TestModel getInstance(){
        if (mTestModel == null){
            mTestModel = new TestModel();
        }
        return mTestModel;
    }

    @Override
    public List<User> getData() {
        mFetchItemsTask = new FetchItemsTask();
        try {
            mUserList = mFetchItemsTask.execute("http://192.168.123.153:8080/listusers").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return mUserList;
    }

    class FetchItemsTask extends AsyncTask<String, Integer, List<User>> {

        @Override
        protected List<User> doInBackground(String... params) {
            try {
                mUserList = new QueueFetcher().getUrlString(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return mUserList;
        }


    }
}
