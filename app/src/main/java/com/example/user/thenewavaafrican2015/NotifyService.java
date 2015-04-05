package com.example.user.thenewavaafrican2015;

import android.app.Service;
import android.os.Binder;

import java.security.Provider;

/**
 * Created by Aidan on 05/04/2015.
 */
public class NotifyService extends Service {

    public class ServiceBinder extends Binder
    {
        NotifyService getService(){return NotifyService.this; }
    }
}
