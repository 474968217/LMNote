package com.ljj.remnote.Thread;

import com.ljj.remnote.RemLog;
import com.ljj.remnote.TimeTool;

import java.util.Comparator;
import java.util.List;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class RemThread extends Thread {
    private static final String TAG = "RemThread";
    private String threadName;
    private Object taskListLock = new Object();

    static Comparator<RemThreadTask> cmpRemThreadTask = new Comparator<RemThreadTask>() {
        @Override
        public int compare(RemThreadTask t1, RemThreadTask t2) {
            return (int) (t1.getNextRunTime() - t2.getNextRunTime());
        }
    };

    private PriorityQueue<RemThreadTask> taskList;

    public RemThread(String threadName) {
        this.threadName = threadName;
        taskList = new PriorityQueue<>(cmpRemThreadTask);
    }

    public void run() {
        RemLog.LogD(TAG, "thread=" + getThreadName() + " run");
        while (true) {
            dealTask();
        }
    }

    public void dealTask() {
        synchronized (taskListLock) {
            RemLog.LogD(TAG, "threadName=" + getThreadName() + " get lock");
            while (taskList.isEmpty() || taskList.peek().getNextRunTime() > TimeTool.getTimestampNow()) {
                int waitInterval = 0;
                if (taskList.isEmpty()) {
                    waitInterval = 5000;
                } else {
                    waitInterval = (int) (taskList.peek().getNextRunTime() - TimeTool.getTimestampNow());
                    if (waitInterval > 5000)
                        waitInterval = 5000;
                    if (waitInterval < 1)
                        waitInterval = 1;
                }
                try {
                    RemLog.LogD(TAG, "threadName=" + getThreadName() + " wait=" + waitInterval + " now=" + TimeTool.getTimestampNow());
                    taskListLock.wait(waitInterval);
                } catch (InterruptedException e) {
                } finally {
                    RemLog.LogD(TAG, "threadName=" + getThreadName() + " wait end");
                }
            }
            if (!taskList.isEmpty() && taskList.peek().getNextRunTime() <= TimeTool.getTimestampNow()) {
                RemThreadTask task = taskList.poll();
                task.run();
                if (task.getRunTimes() != 0) {
                    taskList.add(task);
                }
            }
        }
    }

    public boolean addTask(RemThreadTask task) {
        if (task == null)
            return false;
        boolean bWakeUp = false;
        synchronized (taskListLock) {
            taskList.add(task);
            RemLog.LogD(TAG, "threadName=" + getThreadName() + " addTask taskName=" + task.getName());
            taskListLock.notifyAll();
            bWakeUp = taskList.size() == 1;
            if (bWakeUp)
                taskListLock.notifyAll();
        }

        return true;
    }

    public boolean removeTask(RemThreadTask task) {
        if (task == null)
            return false;
        synchronized (taskListLock) {
            RemLog.LogD(TAG, "threadName=" + getThreadName() + " removeTask taskName=" + task.getName());
            taskList.remove(task);
        }
        return true;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }
}
