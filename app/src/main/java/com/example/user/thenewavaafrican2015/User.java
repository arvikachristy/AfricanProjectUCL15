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

public class User
{
    //attributes
    private String name;
    private int age;
    private boolean infected;
    //Date/Time
    private String dateTime;

    public User(String nName, int nAge, boolean nInfected)
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
        if(infected == true)
        {
            infected = false;
        }
        else
        {
            infected = true;
        }
    }
    public boolean getInfected()
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
        values.put(UserEntry.COLUMN_NAME_NAME, this.name);
        values.put(UserEntry.COLUMN_NAME_AGE, this.age);
        values.put(UserEntry.COLUMN_NAME_INFECTED, this.infected);
        values.put(UserEntry.COLUMN_NAME_LAST_ACCESS, this.dateTime);
        db.insert(UserEntry.TABLE_NAME, null, values);
    }
}
