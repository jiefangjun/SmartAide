package gq.fokia.smartaide.data;

import java.util.List;

import gq.fokia.smartaide.model.User;

/**
 * Created by archie on 8/27/17.
 */

public interface UserDataSource {

    /*
    * 在这里定义一些回调接口
    * */

    public interface OnLoginListener {

        void loginSuccess(User user);

        void loginFailed();
    }

    interface LoadUsersCallback{

        void onUsersLoaded(List<User> users);

        void onDataNotAvailable();
    }

    void getUsers(LoadUsersCallback callback);

    void postUser(User user);

    void putUser(User user);

    void deleteUser(User user);

}
