package gq.fokia.smartaide.login;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gq.fokia.smartaide.model.User;
import gq.fokia.smartaide.data.UserRepository;
import gq.fokia.smartaide.data.remote.QueueFetcher;
import gq.fokia.smartaide.utils.HttpUtil;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by archie on 8/27/17.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    private final UserRepository mUserRepository;
    private final LoginContract.View mLoginView;
    private final Context mContext;


    private UserLoginTask mAuthTask = null;
    private UserRegister mRegisterTask = null;


    public LoginPresenter(Context context, UserRepository userRepository, LoginContract.View loginView) {
        this.mContext = context;
        this.mUserRepository = userRepository;
        this.mLoginView = loginView;

        mLoginView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void login() {
        attemptLogin();
    }

    @Override
    public void register() {

        String email = mLoginView.getUserEmail();
        String password = mLoginView.getPassword();

        boolean cancel = false;
        if (!cancel) {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            mLoginView.showLoginProgress(true);
            mRegisterTask = new UserRegister(email, password);
            mRegisterTask.execute((Void) null);

        }


    }

    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mLoginView.setEmailError(null);
        mLoginView.setPasswordError(null);

        // Store values at the time of the login attempt.
        String email = mLoginView.getUserEmail();
        String password = mLoginView.getPassword();

        boolean cancel = false;

        // Check for a valid mEmailView address & password.
        /*if (TextUtils.isEmpty(email)) {
            cancel = mLoginView.setEmailError(mContext.getString(R.string.error_field_required));
        } else if (!mLoginView.isEmailValid(email)) {
            cancel = mLoginView.setEmailError(mContext.getString(R.string.error_invalid_email));
        } else if (TextUtils.isEmpty(password)) {
            cancel = mLoginView.setPasswordError(mContext.getString(R.string.error_field_required));
        } else if (!mLoginView.isPasswordValid(password)) {
            cancel = mLoginView.setPasswordError(mContext.getString(R.string.error_invalid_password));
        }*/

        if (!cancel) {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            mLoginView.showLoginProgress(true);
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);

        }
    }

    public class UserRegister extends AsyncTask<Void, Void, Boolean>{

        private final String mEmail;
        private final String mPassword;

        UserRegister(String email, String password){
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            User user = new User(mEmail, mPassword, "", "0");

            HttpUtil.sendUserPostRequest("http://10.61.42.58:8080/users", user, new okhttp3.Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    mLoginView.showFailedError();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                }
                });

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (success){
                mLoginView.showLoginProgress(false);
                mLoginView.toMainAct();
                Toast.makeText(mContext, "注册成功", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(mContext, "注册失败", Toast.LENGTH_LONG).show();
            }
        }
    }

    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;
        private List<User> mUserList = new ArrayList<>();

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                mUserList = new QueueFetcher().getUsers("http://10.61.42.58:8080/users");

            } catch (IOException e){
                return false;
            }

            for (User user : mUserList){
                if (user.getName().equals(mEmail)){
                    return user.getPassword().equals(mPassword);
                }
            }

            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            mLoginView.showLoginProgress(false);

            if (success) {
                mLoginView.toMainAct();
                if (mUserRepository != null) {
                    mUserRepository.saveUserInfo(new User(mLoginView.getUserEmail(), mLoginView.getPassword(), "avatar", "0"));
                }
            } else {
                mLoginView.showFailedError();
            }
        }
    }
}
