package gq.fokia.smartaide.data;

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
