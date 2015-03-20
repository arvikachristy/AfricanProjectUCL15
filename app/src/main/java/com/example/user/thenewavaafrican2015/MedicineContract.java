package com.example.user.thenewavaafrican2015;

import android.provider.BaseColumns;

/**
 * Created by Aaron on 20/03/2015.
 */
public class MedicineContract
{
    public MedicineContract(){}
    public static abstract class MedicinesEntry implements BaseColumns
    {
        //Table def
        public static final String TABLE_NAME = "medicines";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_MEDNAME = "medName";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DOSAGE = "dosage";
        public static final String COLUMN_NAME_COURSE = "courseLength";
        public static final String COLUMN_NAME_START = "startDate";
    }
}
