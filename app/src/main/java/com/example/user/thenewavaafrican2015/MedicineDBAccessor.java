package com.example.user.thenewavaafrican2015;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Aaron on 09/04/2015 in an attempt to make database accessing a little more elegant.
 */
public class MedicineDBAccessor
{
    private SQLiteDatabase db;
    //simply initiating the database
    public MedicineDBAccessor(Context context)
    {
        MedicineDbHelper md =  new MedicineDbHelper(context);
        db = md.getWritableDatabase();
    }
    //Gets medicine data for current user
    public Cursor getMedicineData(SharedPreferences sp)
    {
        String username = sp.getString("CurUsr", "No User");
        Cursor c = db.rawQuery("SELECT * FROM " + MedicineContract.MedicinesEntry.TABLE_NAME + " WHERE name =?", new String[]{username});
        return c;
    }
    //Method allows for specific user info
    public Cursor getMedicineData(String username)
    {
        Cursor c = db.rawQuery("SELECT * FROM " + MedicineContract.MedicinesEntry.TABLE_NAME + " WHERE name =?", new String[]{username});
        return c;
    }
    //Inserts to current User.
    public void insertNewMedication(Medication newMed, SharedPreferences sp)
    {
        ContentValues values = new ContentValues();
        String username = sp.getString("CurUsr", "No User");

        values.put(MedicineContract.MedicinesEntry.COLUMN_NAME_USER, username);
        values.put(MedicineContract.MedicinesEntry.COLUMN_NAME_MEDNAME, newMed.getName());
        values.put(MedicineContract.MedicinesEntry.COLUMN_NAME_COURSE, newMed.getCourseLength());
        values.put(MedicineContract.MedicinesEntry.COLUMN_NAME_START, newMed.getStartDate());
        values.put(MedicineContract.MedicinesEntry.COLUMN_NAME_DOSAGE, newMed.getDosage());
        //Possibly more
    }
}
