package com.example.user.thenewavaafrican2015;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ImageView;


/**
 * Created by Vika on 16/03/2015.
 */

public class MainActivity extends ActionBarActivity implements View.OnClickListener{
    Button button, button2, button3;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        SharedPreferences ps = getSharedPreferences("UsrPrefs", 0);
        String nip = ps.getString("CurUsr", "No User");
        UserDbHelper mDbHelper = UserDbHelper.getInstance(getApplicationContext());
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + UserContract.UserEntry.TABLE_NAME + " WHERE " + UserContract.UserEntry.COLUMN_NAME_NAME + " =?", new String[]{nip});
        Globals.restoreState(getApplicationContext(), nip);
        if(c.moveToFirst())
        setTitle("Little Dr. TB Africa - " + c.getString(1));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);

        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(this);

        button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(this);


    }

    private void buttonClick(int choice){
        switch(choice)
        {
            case 1:
                startActivity(new Intent("com.example.user.thenewavaafrican2015.MyProfileNew"));
                break;
            case 2:
                startActivity(new Intent("com.example.user.thenewavaafrican2015.MyMedicine"));
                break;
            case 3:
                startActivity(new Intent("com.example.user.thenewavaafrican2015.MyHelp"));
                break;
        }
    }

    public void onClick(View v){
        switch (v.getId())
        {
            case R.id.button:
                buttonClick(1);
                break;
            case R.id.button2:
                buttonClick(2);
                break;
            case R.id.button3:
                buttonClick(3);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        RelativeLayout main_view = (RelativeLayout) findViewById(R.id.main_view);
        LinearLayout mainL_view;
        switch(item.getItemId()){
            case R.id.charlice:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                main_view.setBackgroundResource(R.drawable.circusbck);

                return true;

            case R.id.edward:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                main_view.setBackgroundResource(R.drawable.park);
                return true;

            case R.id.demarco:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                main_view.setBackgroundResource(R.drawable.underwaterbck);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }


    }
