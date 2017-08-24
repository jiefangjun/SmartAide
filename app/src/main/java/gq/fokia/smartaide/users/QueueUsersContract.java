package gq.fokia.smartaide.users;

import java.util.List;

import gq.fokia.smartaide.BasePresenter;
import gq.fokia.smartaide.BaseView;
import gq.fokia.smartaide.data.User;

/**
 * Created by archie on 7/29/17.
 */

public interface QueueUsersContract {

    interface View extends BaseView<Presenter>{
        void showData();
        void showInfo();
    }

    interface Presenter extends BasePresenter{
        void loadData();
        List<User> getData();
    }

    interface Model{
        List<User> getData();
        void doData(List<User> users);
    }

}
