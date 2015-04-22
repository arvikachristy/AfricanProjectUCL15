package com.example.user.thenewavaafrican2015;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.content.Context;
import android.app.Notification;
import android.app.Notification.Builder;

/**
 * Created by Vika on 22/04/2015.
 */
public class DisplayNotif extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int notifID = getIntent().getExtras().getInt("NotifID");

        //---PendingIntent to launch activity if the user selects
        // the notification---
        Intent intent = new Intent("com.example.user.thenewavaafrican2015.AlertDetails");
        intent.putExtra("NotifID", notifID);

        PendingIntent detailsIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notif = new Notification();

        Notification mynotify = new Notification.Builder(this)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("Hello There!")
                .setContentText("info")
                .build();
        nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(notifID, mynotify);

        notif.vibrate = new long[] { 100, 250, 100, 500};
        nm.notify(notifID, notif);
        finish();
    }
}
