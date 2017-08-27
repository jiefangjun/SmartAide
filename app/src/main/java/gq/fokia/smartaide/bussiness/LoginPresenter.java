package gq.fokia.smartaide.bussiness;


import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;

import gq.fokia.smartaide.R;
import gq.fokia.smartaide.data.User;
import gq.fokia.smartaide.data.UserRepository;

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
    public void reset() {
        mLoginView.resetEditView();
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
        if (TextUtils.isEmpty(email)) {
            cancel = mLoginView.setEmailError(mContext.getString(R.string.error_field_required));
        } else if (!mLoginView.isEmailValid(email)) {
            cancel = mLoginView.setEmailError(mContext.getString(R.string.error_invalid_email));
        } else if (TextUtils.isEmpty(password)) {
            cancel = mLoginView.setPasswordError(mContext.getString(R.string.error_field_required));
        } else if (!mLoginView.isPasswordValid(password)) {
            cancel = mLoginView.setPasswordError(mContext.getString(R.string.error_invalid_password));
        }

        if (!cancel) {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            mLoginView.showLoginProgress(true);
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }
    }

    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the mPasswordView matches.
                    return pieces[1].equals(mPassword);
                }
            }

            // TODO: register the new account here.
            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            mLoginView.showLoginProgress(false);

            if (success) {
                mLoginView.toMainAct();
                if (mUserRepository != null) {
                    mUserRepository.saveUserInfo(new User(mLoginView.getUserEmail()));
                }
            } else {
                mLoginView.showFailedError();
            }
        }
    }
}
