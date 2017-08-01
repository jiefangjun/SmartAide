package gq.fokia.queueaide.QueueUsers;

import java.util.ArrayList;
import java.util.List;

import gq.fokia.queueaide.data.User;

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
    public String doData() {
        return "Hello mvp";
    }

    private void initData(){
        for (int i = 0; i< 20; i++){
            String a = i+"";
            mUserList.add(new User(a, a, a, a, a, true));
        }
    }

    public List<User> getUserList(){
        initData();
        return mUserList;
    }
}
