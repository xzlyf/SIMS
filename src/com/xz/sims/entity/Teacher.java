package com.xz.sims.entity;

/**
 * @Author: xz
 * @Date: 2020/12/15
 */
public class Teacher {
    //账号
    private String userNo;
    //密码
    private String userPwd;
    //姓名
    private String name;
    //年龄
    private int age;
    //电话
    private String phone;
    //管理班级
    private String className;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
