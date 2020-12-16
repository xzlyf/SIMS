package com.xz.sims.entity;

/**
 * @Author: xz
 * @Date: 2020/12/16
 */
public class Timetable {
    private String[] title;
    private String[][] value;

    public String[] getTitle() {
        return title;
    }

    public void setTitle(String[] title) {
        this.title = title;
    }

    public String[][] getValue() {
        return value;
    }

    public void setValue(String[][] value) {
        this.value = value;
    }
}
