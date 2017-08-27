package gq.fokia.smartaide.bussiness;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import gq.fokia.smartaide.R;
import gq.fokia.smartaide.data.UserRepository;
import gq.fokia.smartaide.data.local.UserLocalDataSource;

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

        // Create the view
        if (loginFragment == null) {
            loginFragment = LoginFragment.newInstance("LOGIN_FRAGMENT");
        }
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.contentFrame, loginFragment);
        transaction.commit();

        // Create the presenter
        /*new LoginPresenter(getApplicationContext(),
                UserRepository.getInstance(UserLocalDataSource.getInstance(getApplicationContext()),
                loginFragment);*/
        new LoginPresenter(getApplicationContext(),
                UserRepository.getInstance(UserLocalDataSource.getInstance(this)),
                loginFragment);

    }
}
