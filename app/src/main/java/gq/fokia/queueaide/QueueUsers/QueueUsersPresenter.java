package gq.fokia.queueaide.QueueUsers;

import junit.framework.Test;

import java.util.List;

import gq.fokia.queueaide.data.User;

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
    public List<User> getData() {
        return mModel.getUserList();
    }

    public void showView(){
        mView.showData(mModel.doData());
    }
}
