package com.example.user.thenewavaafrican2015;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.app.Notification;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TimePicker;
import java.util.Calendar;
import android.app.AlarmManager;
/**
 * Created by Vika on 22/04/2015.
 */

public class MyAlerts extends ActionBarActivity implements OnClickListener{
    TimePicker timepick;
    DatePicker datepick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_alerts);

        Button reminder = (Button) findViewById(R.id.remindbutton);
        reminder.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                timepick = (TimePicker) findViewById(R.id.timechoose);
                datepick = (DatePicker) findViewById(R.id.datechoose);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE); //trigger alarm

                Calendar calendar = Calendar.getInstance(); //current date and time

                calendar.set(Calendar.YEAR, datepick.getYear());
                calendar.set(Calendar.MONTH, datepick.getMonth());
                calendar.set(Calendar.DAY_OF_MONTH, datepick.getDayOfMonth());
                calendar.set(Calendar.HOUR_OF_DAY, timepick.getCurrentHour());
                calendar.set(Calendar.MINUTE, timepick.getCurrentMinute());
                calendar.set(Calendar.SECOND, 0); //sets date and alarm to trigger

                Intent intent = new Intent("com.example.user.thenewavaafrican2015.DisplayNotif");
                intent.putExtra("NotifID",1);

                PendingIntent displayIntent = PendingIntent.getActivity(getBaseContext(), 0, new Intent("com.example.user.thenewavaafrican2015.AlertDetails"), 0);
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), displayIntent);
            }
        });

    }

   @Override
   public void onClick(View v) {
       /*Context context = getApplicationContext();
       Intent intent = new Intent(this, MyAlerts.class); //set up class to open, replace class
        PendingIntent mypending = PendingIntent.getActivity(this, 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK);

       Notification mynotify = new Notification.Builder(this)
               .setSmallIcon(R.drawable.logo)
               .setContentTitle("Hello There!")
               .setContentText("info")
               .build();
       notifman = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
       notifman.notify(unique, mynotify);
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

        return super.onOptionsItemSelected(item);*/
    }

}