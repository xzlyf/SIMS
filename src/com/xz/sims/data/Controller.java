package com.xz.sims.data;

import com.alibaba.fastjson.JSON;
import com.xz.sims.content.Local;
import com.xz.sims.entity.Teacher;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @Author: xz
 * @Date: 2020/12/15
 */
public class Controller {

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
            tt = JSON.parseObject(sb.toString(), Teacher.class);
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

        String userData = JSON.toJSONString(teacher);

        FileWriter writer = null;

        try {
            writer = new FileWriter(file);
            writer.write(userData);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return 2;
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return 0;
    }
}
