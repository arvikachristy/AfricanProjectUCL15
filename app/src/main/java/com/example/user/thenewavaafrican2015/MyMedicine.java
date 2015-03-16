package com.example.user.thenewavaafrican2015;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MyMedicine extends ActionBarActivity implements View.OnClickListener{
    Button mydosagebutton, alertsbutton, historybutton, progressbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_medicine);

        mydosagebutton = (Button)findViewById(R.id.mydosagebutton);
        mydosagebutton.setOnClickListener(this);

        alertsbutton= (Button)findViewById(R.id.alertsbutton);
        alertsbutton.setOnClickListener(this);

        progressbutton = (Button)findViewById(R.id.progressbutton);
        progressbutton.setOnClickListener(this);

        historybutton = (Button)findViewById(R.id.historybutton);
        historybutton.setOnClickListener(this);
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
            case 3:
                startActivity(new Intent("com.example.user.thenewavaafrican2015.MyProgress"));
                break;
            case 4:
                startActivity(new Intent("com.example.user.thenewavaafrican2015.MyHistory"));
                break;
        }
    }


    public void onClick(View v){
        switch (v.getId())
        {
            case R.id.mydosagebutton:
                buttonClick(1);
                break;
            case R.id.alertsbutton:
                buttonClick(2);
                break;
            case R.id.progressbutton:
                buttonClick(3);
                break;
            case R.id.historybutton:
                buttonClick(3);
                break;

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_medicine, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
