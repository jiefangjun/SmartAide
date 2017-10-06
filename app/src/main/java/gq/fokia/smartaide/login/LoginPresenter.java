package gq.fokia.smartaide.login;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gq.fokia.smartaide.data.remote.UserRemoteDataSource;
import gq.fokia.smartaide.model.User;
import gq.fokia.smartaide.utils.HttpUtil;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by archie on 8/27/17.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private static final String URL = "http://192.168.1.6:8080/SmartAide/users";
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };

    private final LoginContract.View mLoginView;
    private final Context mContext;


    private UserLoginTask mAuthTask = null;
    private UserRegister mRegisterTask = null;


    public LoginPresenter(Context context, LoginContract.View loginView) {
        this.mContext = context;
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

            //UserRemoteDataSource.getInstance().postUser(user);
            RequestBody requestBody = new FormBody.Builder()
                    .add("name", user.getName())
                    .add("password", user.getPassword())
                    .add("avatar", user.getAvatar())
                    .add("is_merchant", user.getIs_merchant())
                    .build();
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(URL)
                    .post(requestBody)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (success){
                mLoginView.showLoginProgress(false);
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

        /*
        *Async已经是异步了，所以这里采用同步方法*/
        @Override
        protected Boolean doInBackground(Void... params) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(URL)
                    .build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String jsonString = null;
            try {
                jsonString = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Gson gson = new Gson();
            mUserList = gson.fromJson(jsonString, new TypeToken<List<User>>(){}.getType());

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
            } else {
                mLoginView.showFailedError();
            }
        }
    }
}
