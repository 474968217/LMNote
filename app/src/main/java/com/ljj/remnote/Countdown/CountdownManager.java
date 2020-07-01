package com.ljj.remnote.Countdown;

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

    public void addCountdownTask(String name, int h, int m, int s) {
        getTaskList().add(new CountdownTask(name, h, m, s));
    }

    public List<CountdownTask> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<CountdownTask> taskList) {
        this.taskList = taskList;
    }
}
