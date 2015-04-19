package com.example.user.thenewavaafrican2015;

/**
 * Created by Vika on 17/04/2015.
 */

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MyTBWorld extends ActionBarActivity  implements View.OnClickListener{
    Button whatisbutton, helpbutton, managebutton, preventbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tb_world);

        whatisbutton = (Button)findViewById(R.id.whatisbutton);
        whatisbutton.setOnClickListener(this);

        helpbutton= (Button)findViewById(R.id.gethelpbutton);
        helpbutton.setOnClickListener(this);

        managebutton= (Button)findViewById(R.id.managebutton);
        managebutton.setOnClickListener(this);

        preventbutton= (Button)findViewById(R.id.preventbutton);
        preventbutton.setOnClickListener(this);

    }

    private void buttonClick(int choice){
        switch(choice)
        {
            case 1:
                startActivity(new Intent("com.example.user.thenewavaafrican2015.WhatIsTB"));
                break;
            case 2:
                startActivity(new Intent("com.example.user.thenewavaafrican2015.GetHelp"));
                break;
            case 3:
                startActivity(new Intent("com.example.user.thenewavaafrican2015.ManagingTB"));
                break;
            case 4:
                startActivity(new Intent("com.example.user.thenewavaafrican2015.PreventingTB"));
                break;
        }
    }

    public void onClick(View v){
        switch (v.getId())
        {
            case R.id.whatisbutton:
                buttonClick(1);
                break;
            case R.id.gethelpbutton:
                buttonClick(2);
                break;
            case R.id.managebutton:
                buttonClick(3);
                break;
            case R.id.preventbutton:
                buttonClick(4);
                break;

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_tb_world, menu);
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
