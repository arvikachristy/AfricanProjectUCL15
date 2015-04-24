package com.example.user.thenewavaafrican2015;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Aaron on 20/03/2015 dat singleton
 */
public class MedicineDbHelper extends SQLiteOpenHelper
{
    public static final int DATABASE_VER = 3;
    public static final String DATABASE_NAME = "Medicines.db";
    public static MedicineDbHelper instance;

    public MedicineDbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + MedicineContract.MedicinesEntry.TABLE_NAME + " ("
                                   + MedicineContract.MedicinesEntry.COLUMN_NAME_ID + " INTEGER AUTO_INCREMENT PRIMARY KEY, "
                                   + MedicineContract.MedicinesEntry.COLUMN_NAME_USER + " VARCHAR(30), "
                                   + MedicineContract.MedicinesEntry.COLUMN_NAME_MEDNAME + " VARCHAR(30), "
                                   + MedicineContract.MedicinesEntry.COLUMN_NAME_DOSAGE + " INTEGER, "
                                   + MedicineContract.MedicinesEntry.COLUMN_NAME_COURSE + " INTEGER, "
                                   + MedicineContract.MedicinesEntry.COLUMN_NAME_START + " TIMESTAMP," +
                "FOREIGN KEY(" + MedicineContract.MedicinesEntry.COLUMN_NAME_USER + ") REFERENCES " + UserContract.UserEntry.TABLE_NAME + "(" + UserContract.UserEntry.COLUMN_NAME_NAME + "))");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + UserContract.UserEntry.TABLE_NAME);
        onCreate(db);
    }
    public static MedicineDbHelper getInstance(Context context)
    {
        if(instance == null)
        {
            instance = new MedicineDbHelper(context);
        }
        return instance;
    }

}
