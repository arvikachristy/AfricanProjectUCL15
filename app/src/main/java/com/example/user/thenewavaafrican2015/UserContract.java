package com.example.user.thenewavaafrican2015;

import android.provider.BaseColumns;

/**
 * Created by User on 17/03/2015.
 */
public final class UserContract
{
    public UserContract(){}
    public static abstract class UserEntry implements BaseColumns
    {
        //Table def
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_AGE = "age";
        public static final String COLUMN_NAME_INFECTED = "infected";
        public static final String COLUMN_NAME_LAST_ACCESS = "lastAccess";
    }
}
