package com.xz.sims.entity;

import java.util.List;

/**
 * @Author: xz
 * @Date: 2020/12/16
 */
public class Classes {
    private String className;

    private List<Student> studentList;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
