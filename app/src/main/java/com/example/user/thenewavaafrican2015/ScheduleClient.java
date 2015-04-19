package com.example.user.thenewavaafrican2015;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import java.util.Calendar;

/**
 * Created by Aidan on 05/04/2015.
 */
public class ScheduleClient {

    private ScheduleService mBoundService;
    private Context mContext;
    private boolean mIsBound;

    public ScheduleClient(Context context) {
        mContext = context;
    }

    public void doBindService() {
        mContext.bindService(new Intent(mContext, ScheduleService.class), mConnection, Context.BIND_AUTO_CREATE);
    }

    //Make the connection
    private ServiceConnection mConnection = new ServiceConnection()
    {
        public void onServiceConnected(ComponentName className, IBinder service)
        {
            mBoundService = ((ScheduleService.ServiceBinder) service).getService();
        }

        public void onServiceDisconnected (ComponentName className)
        {
            mBoundService = null;
        }
    };


    //And now the service sets an alarm for a specific date

    public void setAlarmForNotification(Calendar c) {
        mBoundService.setAlarm(c);
    }

    public void doUnbindService() {
        if (mIsBound) {
            //Detach the connection
            mContext.unbindService(mConnection);
            mIsBound = false;
        }
    }
}

