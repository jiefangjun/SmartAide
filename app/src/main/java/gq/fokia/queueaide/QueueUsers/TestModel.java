package gq.fokia.queueaide.QueueUsers;
import android.os.AsyncTask;

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
    private List<User> mUserList = new ArrayList<>();

    public static TestModel getInstance(){
        if (mTestModel == null){
            mTestModel = new TestModel();
        }
        return mTestModel;
    }

    @Override
    public void doData(List<User> users) {
        mUserList.addAll(users);
    }


    @Override
    public List<User> getData() {
        return mUserList;
    }

}
