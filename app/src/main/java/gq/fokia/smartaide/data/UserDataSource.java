package gq.fokia.smartaide.data;

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

    void saveUserInfo(User user);
}
