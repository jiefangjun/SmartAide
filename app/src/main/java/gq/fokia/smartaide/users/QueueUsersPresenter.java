package gq.fokia.smartaide.users;

import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;

import java.io.IOException;
import java.util.List;

import gq.fokia.smartaide.data.User;
import gq.fokia.smartaide.data.remote.QueueFetcher;

/**
 * Created by archie on 7/29/17.
 */

public class QueueUsersPresenter implements QueueUsersContract.Presenter {

    private TestModel mModel;
    private QueueUsersContract.View mView;
    private FetchItemsTask mFetchItemsTask;

    public QueueUsersPresenter(TestModel model, QueueUsersContract.View view) {
        mModel = model;
        mView = view;
    }

    @Override
    public void start() {
        //loadData();
    }

    @Override
    public void loadData(final SwipeRefreshLayout swipeRefreshLayout) {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                new FetchItemsTask().execute("http://192.168.1.8:8080/Users?method=get");
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    @Override
    public List<User> getData() {
        return mModel.getData();
    }

    class FetchItemsTask extends AsyncTask<String, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                mModel.doData(new QueueFetcher().getUrlString(params[0]));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mView.showData();
        }

    }


}
