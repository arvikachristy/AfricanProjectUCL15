package com.example.user.thenewavaafrican2015;

/**
 * Created by User on 17/03/2015.
 */
import java.util.Date;
import java.text.SimpleDateFormat;
import 	java.util.Calendar;
import java.util.Locale;

import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import com.example.user.thenewavaafrican2015.UserContract.UserEntry;
import android.database.Cursor;
import android.util.Log;

public class User
{
    //attributes
    private String name;
    private int age;
    private int infected;
    //Date/Time
    private String dateTime;

    public User(String nName, int nAge, int nInfected)
    {
        this.name = nName;
        this.age = nAge;
        this.infected = nInfected;
        this.updateAccessed();
    }

    //Getter/Setter for Name
    public void setName(String str)
    {
        this.name = str;
    }

    public String getName()
    {
        return this.name;
    }

    //Getter/Setter for age
    public void setAge(int newAge)
    {
        this.age = newAge;
    }
    public int getAge()
    {
        return this.age;
    }

    //toggle/getter for infected
    public void toggleInfected()
    {
        if(infected == 1)
        {
            infected = 0;
        }
        else
        {
            infected = 1;
        }
    }
    public int getInfected()
    {
        return this.infected;
    }

    //updates last accessed date/time
    public void updateAccessed()
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        this.dateTime = dateFormat.format(date);
    }

    //Saves object to Database
    public void saveProfile(SQLiteDatabase db)
    {

        ContentValues values = new ContentValues();

        Cursor c = db.rawQuery("SELECT * FROM " + UserEntry.TABLE_NAME + " WHERE " + UserEntry.COLUMN_NAME_NAME + " =?", new String[] {this.name});

        if (c.getCount() == 0)
        {
            values.put(UserEntry.COLUMN_NAME_NAME, this.name);
            values.put(UserEntry.COLUMN_NAME_AGE, this.age);
            values.put(UserEntry.COLUMN_NAME_INFECTED, this.infected);
            values.put(UserEntry.COLUMN_NAME_LAST_ACCESS, this.dateTime);
            db.insert(UserEntry.TABLE_NAME, null, values);
            Log.d("AfricaApp-Database", "Notification: User " + this.name + " successfully inserted into the database.");
        }
        else
        {
            Log.e("AfricaApp-Database", "Error: User already exists");
        }
    }

}
