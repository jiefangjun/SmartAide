package gq.fokia.smartaide.login;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import gq.fokia.smartaide.R;

/**
 * Created by archie on 8/27/17.
 */

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginFragment loginFragment = (LoginFragment) getFragmentManager().
                findFragmentById(R.id.contentFrame);

        if (loginFragment == null) {
            loginFragment = LoginFragment.newInstance("LOGIN_FRAGMENT");
        }
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.contentFrame, loginFragment);
        transaction.commit();
        new LoginPresenter(getApplicationContext(), loginFragment);

    }
}
