package gq.fokia.smartaide.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import gq.fokia.smartaide.model.User;
import gq.fokia.smartaide.data.UserDataSource;

/**
 * Created by archie on 8/27/17.
 */

public class UserLocalDataSource {

    private static UserLocalDataSource INSTANCE = null;

    private AppDbHelper mAppDbHelper;

    public UserLocalDataSource(@NonNull Context context) {
        mAppDbHelper = new AppDbHelper(context);
    }

    public static UserLocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null){
            INSTANCE = new UserLocalDataSource(context);
        }
        return INSTANCE;
    }

    public void saveUserInfo(User user) {
        if (user == null)
            return;
        SQLiteDatabase db = mAppDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UsersPersistenceContract.UserEntry.COLUMN_NAME_ENTRY_ID, user.getId());
        values.put(UsersPersistenceContract.UserEntry.COLUMN_NAME_EMAIL, user.getName());
        //values.put(UsersPersistenceContract.UserEntry.COLUMN_NAME_PWD, user.getPassword()); 默认情况下不存储密码
        values.put(UsersPersistenceContract.UserEntry.COLUMN_NAME_AVATAR, user.getAvatar());
        values.put(UsersPersistenceContract.UserEntry.COLUMN_NAME_IS_MERCHANT, user.getIs_merchant());

        db.insert(UsersPersistenceContract.UserEntry.TABLE_NAME, null, values);
        db.close();

    }

}
