package gq.fokia.smartaide.bussiness;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gq.fokia.smartaide.MainActivity;
import gq.fokia.smartaide.R;

/**
 * Created by archie on 8/27/17.
 */

public class LoginFragment extends Fragment implements LoginContract.View {

    @BindView(R.id.login_progress)
    ProgressBar mLoginProgress;

    @BindView(R.id.email)
    AutoCompleteTextView mEmailView;

    @BindView(R.id.password)
    EditText mPasswordView;

    @BindView(R.id.login_form)
    ScrollView mLoginForm;

    @BindView(R.id.email_sign_in_button)
    Button signButton;

    private LoginContract.Presenter mPresenter;

    public static LoginFragment newInstance(String id){
        Bundle args = new Bundle();
        args.putString("fragment_id", id);
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
        //模拟自动登录
        //signButton.performClick();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public String getUserEmail() {
        return mEmailView.getText().toString();
    }

    @Override
    public String getPassword() {
        return mPasswordView.getText().toString();
    }

    @Override
    public boolean isEmailValid(String email) {
        return email.contains("@");
    }

    @Override
    public boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    @Override
    public void showLoginProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginForm.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginProgress.animate()
                    .setDuration(shortAnimTime)
                    .alpha(show ? 1 : 0)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            mLoginProgress.setVisibility(show ? View.VISIBLE : View.GONE);
                            mLoginForm.setVisibility(show ? View.GONE : View.VISIBLE);
                        }
                    });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mLoginProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginForm.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public boolean setEmailError(String error) {
        mEmailView.setError(error);
        if(!TextUtils.isEmpty(error)){
            mEmailView.requestFocus();
        }
        return true;
    }

    @Override
    public boolean setPasswordError(String error) {
        mPasswordView.setError(error);
        if(!TextUtils.isEmpty(error)){
            mPasswordView.requestFocus();
        }
        return true;
    }

    @Override
    public void resetEditView() {
        mEmailView.setText("");
        mPasswordView.setText("");
    }

    @Override
    public void toMainAct() {
        Toast.makeText(getActivity(), "Sign in Success!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void showFailedError() {
        Toast.makeText(getActivity(), "Sign in Failed!", Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.email_sign_in_button, R.id.email_reset_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.email_sign_in_button:
                mPresenter.login();
                break;

            case R.id.email_reset_button:
                mPresenter.reset();
                break;
        }
    }
}
