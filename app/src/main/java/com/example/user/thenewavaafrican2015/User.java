package com.example.user.thenewavaafrican2015;

/**
 * Created by User on 17/03/2015.
 */
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import com.example.user.thenewavaafrican2015.UserContract.UserEntry;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class User implements Parcelable
{
    //attributes
    private String name;
    private int age;
    private int infected;
    private String password;
    //List of User Medications
    private ArrayList<Medication> medications;
    //Date/Time
    private String dateTime;

    public User(){}
    public User(String nName, int nAge, int nInfected, String nPassword)
    {
        this.name = nName;
        this.age = nAge;
        this.infected = nInfected;
        this.password = nPassword;
        this.updateAccessed();
        this.medications = new ArrayList<Medication>();
    }

    //Add to List
    public void addToList(Medication m)
    {
        if(medications != null)
        {
            this.medications.add(m);
        }
        else
        {
            Log.e("Error", "Arraylist is null");
        }
    }

    //get from list
    public Medication getFromList(int x)
    {
        return medications.get(x);
    }

    //Get List
    public ArrayList<Medication> getList()
    {
        if(medications!=null)
            return new ArrayList<Medication>();
        return this.medications;
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
    public int getAge(){return this.age;}

    //Getter/Setter for Password
    public void setPassword(String newPass){this.password = newPass;}
    public String getPassword(){return this.password;}

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
        //Creates raw SQL with protection against SQL Injection
        Cursor c = db.rawQuery("SELECT * FROM " + UserEntry.TABLE_NAME + " WHERE " + UserEntry.COLUMN_NAME_NAME + " =?", new String[] {this.name});
        //Ensures not primary key duplicates
        if (c.getCount() == 0)
        {
            values.put(UserEntry.COLUMN_NAME_NAME, this.name);
            values.put(UserEntry.COLUMN_NAME_AGE, this.age);
            values.put(UserEntry.COLUMN_NAME_INFECTED, this.infected);
            //That feel when plain text. 2/10 would not do again.
            values.put(UserEntry.COLUMN_NAME_PASS, this.password);

            db.insert(UserEntry.TABLE_NAME, null, values);
            Log.d("AfricaApp-Database", "Notification: User " + this.name + " successfully inserted into the database.");
        }
        else
        {
            c.moveToFirst();
            Log.e("AfricaAppDatabaseError", "User already exists");
        }

        c.close();
    }

    //Writes Medication data to Parcel
    public void writeToParcel(Parcel p, int x)
    {
        p.writeString(name);
        p.writeInt(age);
        p.writeInt(infected);
        p.writeString(password);
        p.writeTypedList(medications);
    }

    //Creates Medication from Parcel
    public User createFromParcel(Parcel source)
    {
        User m =  new User();
        m.name = source.readString();
        m.age = source.readInt();
        m.infected = source.readInt();
        m.password = source.readString();
        return m;
    }

    public int describeContents(){
        return 0;
    }

}
