package com.example.user.thenewavaafrican2015;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.view.View;
import android.content.Intent;


public class MyAlerts extends ActionBarActivity implements OnClickListener{
    NotificationManager notif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_alerts);
        Button reminder = (Button)findViewById(R.id.remindbutton);
        reminder.setOnClickListener(this);
        notif = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    }

    @Override
    public boolean onClick(View v) {
        Intent intent = new intent(this, StatusBar.Class);
        PendingIntent mypending = PendingIntent.getActivity(this, 0, intent, flags);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_alerts, menu);
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
