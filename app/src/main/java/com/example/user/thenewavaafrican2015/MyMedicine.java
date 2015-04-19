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
/**
 * Created by Vika on 18/03/2015.
 */
public class MyMedicine extends ActionBarActivity implements View.OnClickListener{

    Button mydosagebutton, alertsbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_medicine);

        mydosagebutton = (Button)findViewById(R.id.mydosagebutton);
        mydosagebutton.setOnClickListener(this);

        alertsbutton= (Button)findViewById(R.id.alertsbutton);
        alertsbutton.setOnClickListener(this);

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
        switch (v.getId())
        {
            case R.id.mydosagebutton:
                buttonClick(1);
                break;
            case R.id.alertsbutton:
                buttonClick(2);
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
        RelativeLayout  medicineview = (RelativeLayout) findViewById(R.id.medicineview);
        LinearLayout mainL_view;
        switch(item.getItemId()){
            case R.id.charliceicon:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                medicineview.setBackgroundResource(R.drawable.charbacklogo);

                return true;

            case R.id.edwardicon:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                medicineview.setBackgroundResource(R.drawable.edbacklogo);
                return true;

            case R.id.demarcoicon:
                if(item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
                medicineview.setBackgroundResource(R.drawable.marcbacklogo);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
