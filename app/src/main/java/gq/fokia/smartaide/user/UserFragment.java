package gq.fokia.smartaide.user;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.allen.library.SuperTextView;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import gq.fokia.smartaide.R;

/**
 * Created by archie on 8/26/17.
 */

public class UserFragment extends Fragment implements UserContract.View {

    private UserContract.Presenter mPresenter;

    @BindView(R.id.user)
    SuperTextView userView;

    @BindView(R.id.favorite)
    SuperTextView favoriteView;

    @BindView(R.id.help)
    SuperTextView helpView;

    @BindView(R.id.about)
    SuperTextView aboutView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_superview, container, false);
        return view;
    }

    @Override
    public void setPresenter(UserContract.Presenter presenter) {

    }

    @Override
    public void showAvatar() {
        mPresenter.loadAvatar();
    }

    @Override
    public void showNAme() {
        mPresenter.loadName();
    }

}
