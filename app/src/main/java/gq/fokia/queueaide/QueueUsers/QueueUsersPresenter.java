package gq.fokia.queueaide.QueueUsers;

import android.os.AsyncTask;

import junit.framework.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import gq.fokia.queueaide.data.User;
import gq.fokia.queueaide.data.remote.QueueFetcher;

/**
 * Created by archie on 7/29/17.
 */

public class QueueUsersPresenter implements QueueUsersContract.Presenter {

    private TestModel mModel;
    private QueueUsersContract.View mView;

    public QueueUsersPresenter(TestModel model, QueueUsersContract.View view) {
        mModel = model;
        mView = view;
    }

    @Override
    public void start() {
        mView.showData();
    }

    @Override
    public List<User> loadData() {
        return mModel.getData();
    }


}
