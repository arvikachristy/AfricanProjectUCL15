package com.example.user.thenewavaafrican2015;

import android.app.Activity;
import android.os.Bundle;
import android.app.NotificationManager;

/**
 * Created by Vika on 22/04/2015.
 */
public class AlertDetails extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alertdetails);

        NotificationManager nm = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        //---cancel the notification---
        nm.cancel(getIntent().getExtras().getInt("NotifID"));
    }

}
