package com.ljj.remnote.Countdown;

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
}
