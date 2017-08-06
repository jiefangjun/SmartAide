package gq.fokia.queueaide.QueueUsers;

import java.util.List;
import java.util.concurrent.ExecutionException;

import gq.fokia.queueaide.BasePresenter;
import gq.fokia.queueaide.BaseView;
import gq.fokia.queueaide.data.User;

/**
 * Created by archie on 7/29/17.
 */

public interface QueueUsersContract {

    interface View extends BaseView<Presenter>{
        void showData();
    }

    interface Presenter extends BasePresenter{
        List<User> loadData();
    }

    interface Model{
        List<User> getData();
    }

}
