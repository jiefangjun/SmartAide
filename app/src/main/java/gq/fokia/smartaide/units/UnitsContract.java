package gq.fokia.smartaide.units;

import android.support.v4.widget.SwipeRefreshLayout;

import java.util.List;

import gq.fokia.smartaide.BasePresenter;
import gq.fokia.smartaide.BaseView;
import gq.fokia.smartaide.model.Unit;
import gq.fokia.smartaide.model.User;

/**
 * Created by archie on 7/29/17.
 */

public interface UnitsContract {

    interface View extends BaseView<Presenter>{
        void showData();
        void showInfo();
    }

    interface Presenter extends BasePresenter{
        void loadData(SwipeRefreshLayout swipeRefreshLayout);
        List<Unit> getData();
    }

    interface Model{
        List<Unit> getData();
        void doData(List<Unit> units);
    }

}
