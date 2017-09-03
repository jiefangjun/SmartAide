package gq.fokia.smartaide.data;

import android.support.annotation.NonNull;

import java.util.LinkedHashMap;
import java.util.Map;

import gq.fokia.smartaide.data.local.UserLocalDataSource;

/**
 * Created by archie on 8/27/17.
 */

public class UserRepository implements UserDataSource {

    private static UserRepository INSTANCE = null;

    private final UserDataSource mLocalDataSource;

    Map<String, User> mCachedUser;

    private UserRepository(@NonNull UserDataSource localData) {
        this.mLocalDataSource = localData;
    }

    public static UserRepository getInstance(@NonNull UserDataSource localData) {
        if (INSTANCE == null){
            INSTANCE = new UserRepository(localData);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void saveUserInfo(User user) {
        if (user == null)
            return;
        mLocalDataSource.saveUserInfo(user);
        // Do in memory cache update to keep the app UI up to date
        if (mCachedUser == null) {
            mCachedUser = new LinkedHashMap<>();
        }
        mCachedUser.put(user.getName(), user);
    }
}
