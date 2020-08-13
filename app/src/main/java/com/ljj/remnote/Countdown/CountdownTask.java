package com.ljj.remnote.Countdown;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import com.ljj.remnote.Alarm.AlarmReceiver;
import com.ljj.remnote.RemLog;
import com.ljj.remnote.TimeTool;

import static android.content.Context.ALARM_SERVICE;

public class CountdownTask {
    private static final String TAG = "CountdownTask";

    private String name;
    private int h;
    private int m;
    private int s;
    private Boolean running;
    private Long startTime;
    private Long endTime;

    public CountdownTask() {
        setName("");
        setH(0);
        setM(0);
        setS(0);
        setRunning(false);
        setStartTime(0L);
        setEndTime(0L);
        setRunning(false);
        setStartTime(0L);
        setEndTime(0L);
    }

    public CountdownTask(String name, int h, int m, int s) {
        setName(name);
        setH(h);
        setM(m);
        setS(s);
        setRunning(false);
        setStartTime(0L);
        setEndTime(0L);
        setRunning(false);
        setStartTime(0L);
        setEndTime(0L);
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

    public Boolean getRunning() {
        return running;
    }

    public void setRunning(Boolean running) {
        this.running = running;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Boolean start() {
        if (getRunning()) {
            RemLog.LogE(TAG, "start when task is running");
            return false;
        }
        RemLog.LogD(TAG,"start name="+getName());
        setRunning(true);
        setStartTime(TimeTool.getTimestampNow());
        setEndTime(TimeTool.getTimestampAfterHMS(getH(), getM(), getS()));

        //TODO:接入系统提醒
        return false;
    }

    public Long getRemainTime() {
        Long remainTime = getEndTime();
        remainTime = remainTime - TimeTool.getTimestampNow();
        if (remainTime < 0L)
            remainTime = 0L;
        return remainTime;
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
