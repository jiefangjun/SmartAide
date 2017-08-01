package gq.fokia.queueaide.QueueUsers;

import java.util.List;

import gq.fokia.queueaide.data.User;

/**
 * Created by archie on 7/29/17.
 */

public interface QueueUsersContract {

    interface View{
        void showData(String users);
    }

    interface Presenter{
        List<User> getData();
    }

    interface Model{
        String doData();
    }


}
