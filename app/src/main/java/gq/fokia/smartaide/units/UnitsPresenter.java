package gq.fokia.smartaide.units;

import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;

import java.io.IOException;
import java.util.List;

import gq.fokia.smartaide.model.Unit;
import gq.fokia.smartaide.model.User;
import gq.fokia.smartaide.data.remote.QueueFetcher;

/**
 * Created by archie on 7/29/17.
 */

public class UnitsPresenter implements UnitsContract.Presenter {

    private TestModel mModel;
    private UnitsContract.View mView;

    public UnitsPresenter(TestModel model, UnitsContract.View view) {
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
                new FetchItemsTask().execute("http://10.61.42.85:8080/units");
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    @Override
    public List<Unit> getData() {
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
                mModel.doData(new QueueFetcher().getUnits(params[0]));
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
