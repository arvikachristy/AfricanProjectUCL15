package com.example.user.thenewavaafrican2015;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * Created by Aidan on 05/04/2015.
 */
public class AlarmTask implements Runnable {

    //This is the alarm object
    private final Calendar date;
    private final AlarmManager am;
    private final Context context;

    public AlarmTask(Context context, Calendar date)
    {
        this.context = context;
        this.am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        this.date = date;
    }

    @Override
    public void run()
    {
        Intent intent = new Intent(context, NotifyService.class);
        intent.putExtra(NotifyService.INTENT_NOTIFY, true);
        PendingIntent pIntent = PendingIntent.getService(context, 0, intent, 0);

        //Set the alarm with an intent

        am.set(AlarmManager.RTC, date.getTimeInMillis(), pIntent);
    }


}
