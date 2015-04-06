package com.example.user.thenewavaafrican2015;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Aaron on 20/03/2015.
 */
public class MedicineDbHelper extends SQLiteOpenHelper
{
    public static final int DATABASE_VER = 3;
    public static final String DATABASE_NAME = "Medicines.db";

    public MedicineDbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + MedicineContract.MedicinesEntry.TABLE_NAME + " ("
                                   + MedicineContract.MedicinesEntry.COLUMN_NAME_ID + " INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY, "
                                   + MedicineContract.MedicinesEntry.COLUMN_NAME_USER + " VARCHAR(30) NOT NULL FOREIGN KEY, "
                                   + MedicineContract.MedicinesEntry.COLUMN_NAME_MEDNAME + " VARCHAR(30), "
                                   + MedicineContract.MedicinesEntry.COLUMN_NAME_DOSAGE + " INTEGER, "
                                   + MedicineContract.MedicinesEntry.COLUMN_NAME_COURSE + " INTEGER, "
                                   + MedicineContract.MedicinesEntry.COLUMN_NAME_START + " TIMESTAMP)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + UserContract.UserEntry.TABLE_NAME);
        onCreate(db);
    }
}
