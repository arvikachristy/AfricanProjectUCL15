package com.example.user.thenewavaafrican2015;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by Aidan on 05/04/2015.
 */
public class NotifyService extends Service {

    public class ServiceBinder extends Binder
    {
        NotifyService getService(){return NotifyService.this; }
    }

    //Set the notification id
    private static final int NOTIFICATION = 123;
    public static final String INTENT_NOTIFY = "com.example.user.thenewavaafrican2015.INTENT_NOTIFY";
    private NotificationManager mNM;

    @Override
    public void onCreate()
    {
        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }


    public int onStartCommand(Intent intent, int flags, int startId)
    {
        if (intent.getBooleanExtra(INTENT_NOTIFY, false))
            showNotification();

        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return mBinder;
    }

    private final IBinder mBinder = new ServiceBinder();


    private void showNotification()
    {
       /** //This is the notification
        CharSequence title = "Reminder";
        int icon = R.drawable.ic_dialog_alert;
        CharSequence text = "It's time to take your medicine";

        long time = System.currentTimeMillis();

        Notification notification = new Notification(icon, text, time);

        //Clear it when it is is pressed

        notification.flags |= notification.FLAG_AUTO_CANCEL;

        mNM.notify(NOTIFICATION, notification);

        stopSelf();*/
    }


}
