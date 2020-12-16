package com.xz.sims.data;

import com.google.gson.Gson;
import com.xz.sims.content.Local;
import com.xz.sims.entity.Classes;
import com.xz.sims.entity.Student;
import com.xz.sims.entity.Teacher;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xz
 * @Date: 2020/12/15
 */
public class Controller {
    private static Gson gson;

    /**
     * 数据存储位置初始化
     * 调用顺序为最高
     * 防止查询文件或创建文件时路径无效
     */
    public static void init() {
        gson = new Gson();
        File file = new File(Local.CLASS_STU);
        if (!file.exists()) {
            file.mkdirs();
        }
        file = new File(Local.USER_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 登录 实现
     *
     * @param userNo 账号
     * @param pwd    密码
     * @return 人员信息实体
     */
    public static Teacher tlogin(String userNo, String pwd) {
        //根据账号查询本地是否存在这个文件，也就是是否存在这个人员
        File file = new File(Local.USER_DIR + File.separator + userNo);
        if (!file.exists()) {
            //人员不存在
            return null;
        }
        StringBuffer sb;
        Reader reader = null;
        try {

            //尝试读取人员数据
            //如果提供的账号不存在表示人员也不存在的
            //存在的话就开始判断密码是否正确
            reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
            int ch = 0;
            sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //--开始判断密码是否正确--

        //1.把json数据转成实体类
        Teacher tt = null;
        try {
            tt = gson.fromJson(sb.toString(), Teacher.class);
        } catch (Exception e) {
            e.printStackTrace();
            //json文件已损坏
            System.out.println("======用户数据损坏=====");
            return null;
        }

        //2.判断用户输入的密码是否正确
        if (tt.getUserPwd().equals(pwd)) {
            //密码正确
            return tt;
        } else {
            //密码错误
            return null;
        }
    }


    /**
     * 注册 实现
     * 原理同上
     *
     * @param teacher 教师信息实体
     * @return 0 注册成功
     * 1 账号存在
     * 2 文件写入失败
     */
    public static int tRegister(Teacher teacher) {

        File file = new File(Local.USER_DIR + File.separator + teacher.getUserNo());
        if (file.exists()) {
            //人员存在
            return 1;
        }

        String userData = gson.toJson(teacher);


        boolean isOk = writerData(file, userData);
        if (!isOk) {
            return 2;
        }
        return 0;
    }

    /**
     * 获取接管班级学生名单
     *
     * @param userNo 教工号
     * @return 名单数据
     */
    public static Classes getClasses(String userNo) {
        File file = new File(Local.CLASS_STU + File.separator + userNo);
        if (!file.exists()) {
            //不存在班级名单
            return null;
        }

        StringBuffer sb;
        Reader reader = null;
        try {

            reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
            int ch = 0;
            sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        Classes cc = null;
        try {
            cc = gson.fromJson(sb.toString(), Classes.class);
        } catch (Exception e) {
            e.printStackTrace();
            //json文件已损坏
            System.out.println("======班级名单数据损坏=====");
            return null;
        }

        return cc;

    }


    /**
     * 添加学生进班级列表
     *
     * @param userNo  教工号
     * @param student 学生信息
     * @return -1 文件创建失败
     */
    public static int addStuToClasses(String userNo, Student student) {
        Classes c = new Classes();
        List<Student> oldList = c.getStudentList();

        File file = new File(Local.CLASS_STU + File.separator + userNo);
        if (!file.exists()) {
            c.setUserNo(userNo);
            String tempData = gson.toJson(c);
            //没有找到班级文件就创建一个空的班级
            writerData(file, tempData);
        }
        if (oldList == null) {
            oldList = new ArrayList<>();
        }

        oldList.add(student);
        c.setStudentList(oldList);
        //写入数据
        String data = gson.toJson(c);
        writerData(file, data);
        return 0;
    }


    /**
     * 写数据工具
     * 替换式修改，不追加
     *
     * @param file
     * @param data
     * @return
     */
    private static boolean writerData(File file, String data) {
        FileWriter writer = null;

        try {
            writer = new FileWriter(file);
            writer.write(data);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

}
