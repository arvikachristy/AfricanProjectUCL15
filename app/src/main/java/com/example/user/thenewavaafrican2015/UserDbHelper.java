package com.example.user.thenewavaafrican2015;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
/**
 * Created by User on 17/03/2015.
 */
import com.example.user.thenewavaafrican2015.UserContract.UserEntry;

public class UserDbHelper extends SQLiteOpenHelper
{
    public static final int DATABASE_VER = 4;
    public static final String DATABASE_NAME = "User.db";
    public static UserDbHelper instance;
    public static SQLiteDatabase users;

    public UserDbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + UserEntry.TABLE_NAME + " ("
        + UserEntry.COLUMN_NAME_ID + " INTEGER AUTO_INCREMENT,"
        + UserEntry.COLUMN_NAME_NAME + " VARCHAR(30) PRIMARY KEY, "
        + UserEntry.COLUMN_NAME_AGE + " INTEGER, "
        + UserEntry.COLUMN_NAME_PASS + " VARCHAR(60),"
        + UserEntry.COLUMN_NAME_INFECTED + " INTEGER, "
        + UserEntry.COLUMN_NAME_LAST_ACCESS + " TIMESTAMP)");

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME);
        onCreate(db);
    }
    public static UserDbHelper getInstance(Context context)
    {
        if(instance == null)
        {
            instance = new UserDbHelper(context);
        }
        return instance;
    }
}
