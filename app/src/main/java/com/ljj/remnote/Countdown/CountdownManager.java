package com.ljj.remnote.Countdown;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class CountdownManager {
    private static class CountdownManagerClassInstance {
        private static final CountdownManager instance = new CountdownManager();
    }

    public static CountdownManager getInstance() {
        return CountdownManagerClassInstance.instance;
    }

    private List<CountdownTask> taskList = new ArrayList<>();

    public void addCountdownTask(Context context, String name, int h, int m, int s) {
        CountdownTask task = new CountdownTask(name, h, m, s);
        getTaskList().add(task);
        task.createAlarm(context);
    }

    public List<CountdownTask> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<CountdownTask> taskList) {
        this.taskList = taskList;
    }
}
