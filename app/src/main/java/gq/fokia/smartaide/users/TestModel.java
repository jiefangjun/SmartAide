package gq.fokia.smartaide.users;

import java.util.ArrayList;
import java.util.List;

import gq.fokia.smartaide.data.User;

/**
 * Created by archie on 7/29/17.
 */

public class TestModel implements QueueUsersContract.Model {

    private static TestModel mTestModel;
    private List<User> mUserList = new ArrayList<>();

    public static TestModel getInstance(){
        if (mTestModel == null){
            mTestModel = new TestModel();
        }
        return mTestModel;
    }

    @Override
    public void doData(List<User> users) {
        mUserList.addAll(users);
    }


    @Override
    public List<User> getData() {
        return mUserList;
    }

}
