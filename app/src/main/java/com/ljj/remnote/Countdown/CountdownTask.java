package com.ljj.remnote.Countdown;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import com.ljj.remnote.Alarm.AlarmReceiver;

import static android.content.Context.ALARM_SERVICE;

public class CountdownTask {
    private String name;
    private int h;
    private int m;
    private int s;

    public CountdownTask() {
        setName("");
        setH(0);
        setM(0);
        setS(0);
    }

    public CountdownTask(String name, int h, int m, int s) {
        setName(name);
        setH(h);
        setM(m);
        setS(s);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    public void createAlarm(Context context) {
        AlarmManager manager = (AlarmManager) context.getSystemService(ALARM_SERVICE);

        long triggerAtTime = SystemClock.elapsedRealtime() + ((h * 60 + m) * 60 + s) * 1000;
        Intent i = new Intent(context, AlarmReceiver.class);
        i.setAction("COUNTDOWN_ACTION");
        i.putExtra("name", name);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
    }
}
