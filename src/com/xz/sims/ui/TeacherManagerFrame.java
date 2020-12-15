package com.xz.sims.ui;

import com.xz.sims.entity.Teacher;
import com.xz.sims.utils.ScreenUtil;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: xz
 * @Date: 2020/12/15
 */
public class TeacherManagerFrame extends JFrame {
    //教工信息
    private Teacher teacher;

    private int windowsWedth = 600;
    private int windowsHeight = 400;
    private Container mainContainer = getContentPane();
    //多行文本域
    private JTextArea userInfoArea;
    //定义滚动窗格
    private JScrollPane jspane;

    /**
     * 初始化 并传入登录人员信息
     *
     * @param teacher
     */
    public void init(Teacher teacher) {
        setTitle("SIMS校园管理系统");
        this.teacher = teacher;
        //居中显示窗体
        setBounds((ScreenUtil.getScreenWidth() - windowsWedth) / 2
                , (ScreenUtil.getScreenHeight() - windowsHeight) / 2
                , windowsWedth
                , windowsHeight);
        //禁止最大化
        setResizable(false);

        mainPanel();

        //自动退出销毁
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void mainPanel() {
        mainContainer.setLayout(null);
        userInfoArea = new JTextArea();
        //文本域不可编辑
        userInfoArea.setEditable(false);
        jspane = new JScrollPane(userInfoArea);    //创建滚动窗格
        jspane.setBounds(10, 10, 150, 290);
        mainContainer.add(jspane);

        showInfo();

    }

    /**
     * 展示登录人员信息
     */
    private void showInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("教工：")
                .append(teacher.getName())
                .append("\n");
        sb.append("工号：")
                .append(teacher.getUserNo())
                .append("\n");
        sb.append("电话：")
                .append(teacher.getPhone())
                .append("\n");

        sb.append("管理班级：")
                .append(teacher.getClassName())
                .append("\n");
        sb.append("----------------------");

        userInfoArea.setText(sb.toString());
    }
}
