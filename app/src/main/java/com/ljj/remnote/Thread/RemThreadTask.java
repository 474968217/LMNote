package com.ljj.remnote.Thread;

import androidx.core.util.Supplier;

import com.ljj.remnote.RemLog;

public class RemThreadTask {
    private static final String TAG = "RemThreadTask";
    private String name;
    private Long nextRunTime;
    private Long intervalRunTime;
    private int runTimes;
    private Supplier<Integer> runFunc;

    public RemThreadTask(String name, Long nextRunTime, Long intervalRunTime, int runTimes, Supplier<Integer> runFunc) {
        this.name = name;
        this.nextRunTime = nextRunTime;
        this.intervalRunTime = intervalRunTime;
        this.runTimes = runTimes;
        this.runFunc = runFunc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNextRunTime() {
        return nextRunTime;
    }

    public void setNextRunTime(Long nextRunTime) {
        this.nextRunTime = nextRunTime;
    }

    public Long getIntervalRunTime() {
        return intervalRunTime;
    }

    public void setIntervalRunTime(Long intervalRunTime) {
        this.intervalRunTime = intervalRunTime;
    }

    public int getRunTimes() {
        return runTimes;
    }

    public void setRunTimes(int runTimes) {
        this.runTimes = runTimes;
    }

    public Supplier<Integer> getRunFunc() {
        return runFunc;
    }

    public void setRunFunc(Supplier<Integer> runFunc) {
        this.runFunc = runFunc;
    }

    public int run() {
        Long nextRunTime = getNextRunTime();
        Long newNextRunTime = nextRunTime + getIntervalRunTime();
        RemLog.LogD(TAG, "taskName=" + getName() + " run now=" + nextRunTime + " next=" + newNextRunTime);
        setNextRunTime(newNextRunTime);
        if (getRunTimes() > 0)
            setRunTimes(getRunTimes() - 1);
        return runFunc.get();
    }
}
