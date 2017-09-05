package gq.fokia.smartaide.units;

import java.util.ArrayList;
import java.util.List;

import gq.fokia.smartaide.model.Unit;
import gq.fokia.smartaide.model.User;

/**
 * Created by archie on 7/29/17.
 */

public class TestModel implements UnitsContract.Model {

    //真正的model应该是dataRepository
    private static TestModel mTestModel;
    private List<Unit> mUnitList = new ArrayList<>();

    public static TestModel getInstance(){
        if (mTestModel == null){
            mTestModel = new TestModel();
        }
        return mTestModel;
    }

    @Override
    public void doData(List<Unit> units) {
        //为啥总喜欢用暴力手段。。
        mUnitList.clear();
        mUnitList.addAll(units);
    }


    @Override
    public List<Unit> getData() {
        return mUnitList;
    }

}
