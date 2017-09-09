package gq.fokia.smartaide.units;


import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import java.util.List;

import gq.fokia.smartaide.data.UnitDataSource;
import gq.fokia.smartaide.data.remote.UnitsRemoteDataSource;
import gq.fokia.smartaide.model.Unit;


import static gq.fokia.smartaide.units.UnitsFragment.mUnitList;

/**
 * Created by archie on 7/29/17.
 */

public class UnitsPresenter implements UnitsContract.Presenter {

    private UnitsContract.View mView;
    private UnitsRemoteDataSource mUnitsRemoteDataSource;

    public UnitsPresenter(UnitsRemoteDataSource unitsRemoteDataSource, UnitsContract.View view) {
        mUnitsRemoteDataSource = unitsRemoteDataSource;
        mView = view;
    }

    @Override
    public void start() {
    }

    @Override
    public void loadData() {

        mUnitsRemoteDataSource.getUnits(new UnitDataSource.LoadUnitsCallback() {
            @Override
            public void onUnitsLoaded(List<Unit> units) {
                //暂时暴力解决。
                if (mUnitList.size() != 0){
                    mUnitList.clear();
                }
                for (Unit unit: units) {
                    mUnitList.add(unit);
                }
            }

            @Override
            public void onDataNotAvailable() {
                mView.showInfo();
            }
        });
        mView.showData();

    }

    @Override
    public List<Unit> getData() {
        return mUnitList;
    }


}
