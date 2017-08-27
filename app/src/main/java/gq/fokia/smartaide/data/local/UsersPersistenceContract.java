package gq.fokia.smartaide.data.local;

import android.provider.BaseColumns;

/**
 * Created by archie on 8/27/17.
 */

public final class UsersPersistenceContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public UsersPersistenceContract() {}

    /* Inner class that defines the table contents */
    public static abstract class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PWD = "password";
    }
}
