package com.example.user.thenewavaafrican2015;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ImageView;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;

import java.util.PriorityQueue;

public class MyProfileNew extends ActionBarActivity {
    ImageView callprofilepicture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_profile_new);

        callprofilepicture = (ImageView) findViewById(R.id.profilePicture);
        readPrevVals();
        callprofilepicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
            }
        });
    }

    public void readPrevVals()
    {
        //Preferences Read
        SharedPreferences settings = getSharedPreferences("UsrPrefs", 0);
        String name = settings.getString("CurUsr", "No User");
        Log.d("Debug", "Preferences curUsr = " + name);
        //Database Access
        UserDbHelper mDbHelper = new UserDbHelper(getApplicationContext());
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        if(name.equals("No User"))
        {
            ((Button) findViewById(R.id.saveprofiletab)).setText("Create New User");
        }
        else
        {
            try
            {
                //Db query
                Cursor c = db.query(UserContract.UserEntry.TABLE_NAME,
                        null,
                        "name = ?",
                        new String[] {name},
                        null,
                        null,
                        null);
                c.moveToFirst();
                ((TextView) findViewById(R.id.nametab)).setText(c.getString(c.getColumnIndexOrThrow(UserContract.UserEntry.COLUMN_NAME_NAME)));
                ((TextView) findViewById(R.id.passtab)).setText(c.getString(c.getColumnIndexOrThrow(UserContract.UserEntry.COLUMN_NAME_PASS)));
                ((TextView) findViewById(R.id.agetab)).setText(Integer.toString(c.getInt(c.getColumnIndexOrThrow(UserContract.UserEntry.COLUMN_NAME_AGE))));
            //Temporary value
                ((TextView) findViewById(R.id.infectedid)).setText(Integer.toString(c.getInt(c.getColumnIndexOrThrow(UserContract.UserEntry.COLUMN_NAME_INFECTED))));
                ((Button) findViewById(R.id.saveprofiletab)).setText("Update Information");
                c.close();
            }
            catch(Exception e)
            {
                Log.e("Error", "User has no database values. Resetting Current User Preference.");
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("CurUsr", "No User");
                editor.commit();
                ((Button) findViewById(R.id.saveprofiletab)).setText("Create New User");
            }
        }
    }
    public void onActivityResult(int reqCode, int resCode, Intent data){
        if (resCode == RESULT_OK) {
            if (reqCode == 1)
                callprofilepicture.setImageURI(data.getData());
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_profile_new, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        RelativeLayout main_view = (RelativeLayout) findViewById(R.id.main_view);
        LinearLayout mainL_view;

        while (true){
            switch(item.getItemId()){
                case R.id.vika_red:
                    if(item.isChecked())
                        item.setChecked(false);
                    else
                        item.setChecked(true);
                    main_view.setBackgroundResource(R.drawable.circus);

                    return true;

                case R.id.aaron_green:
                    if(item.isChecked())
                        item.setChecked(false);
                    else
                        item.setChecked(true);
                    main_view.setBackgroundResource(R.drawable.park);
                    return true;

                case R.id.aidan_blue:
                    if(item.isChecked())
                        item.setChecked(false);
                    else
                        item.setChecked(true);
                    main_view.setBackgroundResource(R.drawable.underwater);
                    return true;

                default:
                    return super.onOptionsItemSelected(item);
            }
        }
    }
    public void saveChanges(View v)
    {
        if(!checkIfNull())
        {
            //Display Error
            Log.e("Error", "Null fields. All fields must be filled.");
        }
        else
        {
            //Preferences Read
            SharedPreferences settings = getSharedPreferences("UsrPrefs", 0);
            String curUsr = settings.getString("CurUsr", "No User");
            //Database Access
            UserDbHelper mDbHelper = new UserDbHelper(getApplicationContext());
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            if(!curUsr.equals("No User"))
            {
                //delete previous user
                db.delete(UserContract.UserEntry.TABLE_NAME, UserContract.UserEntry.COLUMN_NAME_ID + "=?",new String[] {curUsr});
                Log.d("Debug", "Deleted User " + UserContract.UserEntry.COLUMN_NAME_NAME);
            }
            //read new vals
            String name = (String)((TextView) findViewById(R.id.nametab)).getText().toString();
            int age = Integer.parseInt(((TextView) findViewById(R.id.agetab)).getText().toString());
            int inf = Integer.parseInt(((TextView) findViewById(R.id.infectedid)).getText().toString());
            String pass = (String)((TextView) findViewById(R.id.passtab)).getText().toString();
            //Creates new User
            User usr = new User(name, age, inf, pass);
            Log.d("Debug", "Created User with attributes: " + usr.getName() +  ", " + usr.getAge() + ", " + usr.getInfected() + ", " + usr.getPassword());
            //Save File
            usr.saveProfile(db);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("CurUsr", usr.getName());
            editor.apply();
            readPrevVals();
        }
    }
    public boolean checkIfNull()
    {
        if((((TextView) findViewById(R.id.nametab)).getText().toString()).equals(""))
        {
            return false;
        }
        if((((TextView) findViewById(R.id.agetab)).getText().toString()).equals(""))
        {
            return false;
        }
        if((((TextView) findViewById(R.id.infectedid)).getText().toString()).equals(""))
        {
            return false;
        }
        if((((TextView) findViewById(R.id.passtab)).getText().toString()).equals(""))
        {
            return false;
        }
        return true;
    }
    public User loadProfile(String name)
    {
        try
        {
            UserDbHelper mDbHelper = new UserDbHelper(getApplicationContext());
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            //Creating a database cursor with an SQL command
            Cursor c = db.rawQuery("SELECT * FROM " + UserContract.UserEntry.TABLE_NAME + " WHERE " + UserContract.UserEntry.COLUMN_NAME_NAME + " =?", new String[] {name});
            SharedPreferences settings = getSharedPreferences("UsrPrefs", -1);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("CurUsr", name);
            editor.commit();
            return new User(c.getString(1), c.getInt(2), c.getInt(3), c.getString(4));
        }
        catch(Exception e)
        {
            Log.e("Error", "Could not load profile");
            return null;
        }
    }
}
