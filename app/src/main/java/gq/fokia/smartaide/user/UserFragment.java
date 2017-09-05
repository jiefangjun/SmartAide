package gq.fokia.smartaide.user;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import gq.fokia.smartaide.R;

/**
 * Created by archie on 8/26/17.
 */

public class UserFragment extends Fragment implements UserContract.View {
    private ImageView userAvatar;
    private TextView userName;
    private UserContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        userAvatar = (ImageView) view.findViewById(R.id.user_avatar);
        userName = (TextView) view.findViewById(R.id.user_name);
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
