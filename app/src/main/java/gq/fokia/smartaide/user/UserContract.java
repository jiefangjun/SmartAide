package gq.fokia.smartaide.user;

import android.widget.ImageView;

import gq.fokia.smartaide.BasePresenter;
import gq.fokia.smartaide.BaseView;

/**
 * Created by archie on 8/26/17.
 */

public interface UserContract {
    interface View extends BaseView<Presenter>{
        void showAvatar();
        void showNAme();
    }

    interface Presenter extends BasePresenter{
        void loadAvatar();
        void loadName();
    }


}
