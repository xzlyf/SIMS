package com.xz.sims.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.xz.sims.data.Controller;
import com.xz.sims.entity.Student;
import com.xz.sims.utils.ScreenUtil;

/**
 * @Author: xz
 * @Date: 2020/12/17
 */
public class StudentManagerFrame extends JFrame {
    private Student student;
    private int windowsWedth = 600;
    private int windowsHeight = 300;
    private Container mainContainer = getContentPane();

    private JButton b1 = new JButton("退出登录");
    private JButton b2 = new JButton("绑定班级");

    // 多行文本域
    private JTextArea userInfoArea;
    // 定义滚动窗格
    private JScrollPane jspane;
    //课表
    private JTable table;

    public void init(Student student) {
        setTitle("SIMS校园管理系统");
        this.student = student;
        // 居中显示窗体
        setBounds((ScreenUtil.getScreenWidth() - windowsWedth) / 2, (ScreenUtil.getScreenHeight() - windowsHeight) / 2,
                windowsWedth, windowsHeight);
        // 禁止最大化
        setResizable(false);

        mainPanel();
        showInfo();
        addListener();
        getTimetable();

        // 自动退出销毁
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    private void mainPanel() {
        // 展示区
        mainContainer.setLayout(null);
        userInfoArea = new JTextArea();
        // 文本域不可编辑
        userInfoArea.setEditable(false);
        // 创建滚动窗格
        jspane = new JScrollPane(userInfoArea);
        jspane.setBounds(10, 10, 150, 100);
        mainContainer.add(jspane);
        table = new JTable(6, 5);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(160, 130, 400, 120);
        mainContainer.add(pane);

        // 功能区
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());
        jPanel.setBounds(160, 10, 400, 100);
        jPanel.setBackground(Color.LIGHT_GRAY);
        jPanel.add(b1);
        jPanel.add(b2);
        mainContainer.add(jPanel);

    }

    /**
     *
     */
    private void addListener() {
        b1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginFrame().init();
                dispose();
            }
        });
        b2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddTeachFrame(student);
            }
        });
    }

    /**
     * 展示登录人员信息
     */
    private void showInfo() {
        if (student == null)
            return;
        StringBuilder sb = new StringBuilder();
        sb.append("学生：").append(student.getName()).append("\n");
        sb.append("学号：").append(student.getUserNo()).append("\n");
        sb.append("电话：").append(student.getPhone()).append("\n");
        sb.append("班级：").append(student.getClassName()).append("\n");
        sb.append("导员：").append(student.getTeacherName()).append("\n");

        userInfoArea.setText(sb.toString());
    }

    /**
     * 获取课表
     */
    private void getTimetable() {
        if (student.getTeacherNo() == null) {
            table.setVisible(false);
            return;
        }
        DefaultTableModel model = Controller.getTable(student.getTeacherNo());
        if (model==null){
            table.setVisible(false);
            return;
        }
        table.setModel(model);
    }


}
