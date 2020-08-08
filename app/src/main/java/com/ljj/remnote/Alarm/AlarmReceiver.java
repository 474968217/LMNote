package com.ljj.remnote.Alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ljj.remnote.RemLog;
import com.ljj.remnote.Notification.NotificationUtils;
import com.ljj.remnote.R;

import java.util.Date;

public class AlarmReceiver extends BroadcastReceiver {
    public static final String TAG = "AlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        RemLog.LogD(TAG, "executed at " + new Date().toString());
        if (intent.getAction().equals("COUNTDOWN_ACTION")) {
            RemLog.LogD(TAG, "Countdown:" + intent.getStringExtra("name"));
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