package com.example.user.thenewavaafrican2015;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MyMedicine extends ActionBarActivity implements View.OnClickListener{
    Button mydosagebutton, alertsbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_medicine);

    }


    private void buttonClick(int choice){
        switch(choice)
        {
            case 1:
                startActivity(new Intent("com.example.user.thenewavaafrican2015.MyDosage"));
                break;
            case 2:
                startActivity(new Intent("com.example.user.thenewavaafrican2015.MyAlerts"));
                break;
        }
    }


    public void onClick(View v){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_medicine, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

}
