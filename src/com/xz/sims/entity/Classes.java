package com.xz.sims.entity;

import java.util.List;

/**
 * @Author: xz
 * @Date: 2020/12/16
 */
public class Classes {
    //班主任工号
    private String userNo;
    //学生列表
    private List<Student> studentList;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
