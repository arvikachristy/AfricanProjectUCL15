package com.example.user.thenewavaafrican2015;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Aidan on 23/04/2015. Forgive us for we have sinned.
 */
//I'm well aware this breaks encapsulation, but time was tight and spending hours coding up boiler plates for parceleable wasn't an option
public class Globals
{
    public static User curUsr;
    public static void restoreState(Context context, String name)
    {
            UserDbHelper mDbHelper = UserDbHelper.getInstance(context);
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            MedicineDbHelper medDbHelper =  MedicineDbHelper.getInstance(context);
            SQLiteDatabase db2 =  medDbHelper.getWritableDatabase();
            //Creating a database cursor with an SQL command
            Cursor c = db.rawQuery("SELECT * FROM " + UserContract.UserEntry.TABLE_NAME + " WHERE " + UserContract.UserEntry.COLUMN_NAME_NAME + " =?", new String[]{name});
            if(c.moveToNext()) {
                curUsr = new User(c.getString(1), c.getInt(2), c.getInt(3), c.getString(4));
                Log.i("Success", "User " + curUsr.getName() + " Successfully restored");
            }
            c = db2.rawQuery("SELECT * FROM " + MedicineContract.MedicinesEntry.TABLE_NAME + " WHERE " + MedicineContract.MedicinesEntry.COLUMN_NAME_USER + " =?", new String[]{name});
            while(c.moveToNext())
            {
                Medication m =  new Medication(c.getString(0), c.getInt(1), c.getInt(2));
                curUsr.addToList(m);
                Log.d("Debug", "Medication added to User");
            }
            c.close();
    }
}
