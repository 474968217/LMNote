package com.ljj.remnote.Alarm;

import android.app.NotificationChannel;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.ljj.remnote.Notification.NotificationUtils;
import com.ljj.remnote.R;

import java.util.Date;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("AlarmReceiver", "executed at " + new Date().
                toString());
        if (intent.getAction().equals("COUNTDOWN_ACTION")) {
            Toast.makeText(context, "Countdown:" + intent.getStringExtra("name"), Toast.LENGTH_LONG).show();
            NotificationUtils notificationUtils = new NotificationUtils(
                    context,
                    0,
                    "",
                    R.mipmap.ic_launcher,
                    "倒计时",
                    intent.getStringExtra("name"));
            notificationUtils.notified();
        }
    }
}