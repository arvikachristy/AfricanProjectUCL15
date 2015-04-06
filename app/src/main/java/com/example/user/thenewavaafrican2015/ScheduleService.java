package com.example.user.thenewavaafrican2015;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Calendar;

/**
 * Created by Aidan on 05/04/2015.
 */
public class ScheduleService extends Service {

    public class ServiceBinder extends Binder{
        ScheduleService getService() { return ScheduleService.this; }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        //I have no idea what this does
        return START_STICKY;
    }


    @Override
    public IBinder onBind(Intent intent) { return mBinder; }

    private final IBinder mBinder = new ServiceBinder();

    //Write the alarm function

    public void setAlarm(Calendar c) { new AlarmTask(this, c).run(); }



}
