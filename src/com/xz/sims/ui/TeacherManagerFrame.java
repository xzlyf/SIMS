package com.xz.sims.ui;

import com.xz.sims.data.Controller;
import com.xz.sims.entity.Classes;
import com.xz.sims.entity.Student;
import com.xz.sims.entity.Teacher;
import com.xz.sims.utils.ScreenUtil;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @Author: xz
 * @Date: 2020/12/15
 */
public class TeacherManagerFrame extends JFrame {
    //教工信息
    private Teacher teacher;
    //接管班级名单
    private Classes classes;

    private int windowsWedth = 600;
    private int windowsHeight = 400;
    private Container mainContainer = getContentPane();
    //多行文本域
    private JTextArea userInfoArea;
    private JTextArea stuInfoArea;
    //定义滚动窗格
    private JScrollPane jspane;
    private JScrollPane jspane2;

    /**
     * 初始化 并传入登录人员信息
     *
     * @param teacher
     */
    public void init(Teacher teacher) {
        setTitle("SIMS校园管理系统");
        this.teacher = teacher;
        if (teacher.getUserNo() != null) {
            this.classes = Controller.getClasses(teacher.getUserNo());
        }
        //居中显示窗体
        setBounds((ScreenUtil.getScreenWidth() - windowsWedth) / 2
                , (ScreenUtil.getScreenHeight() - windowsHeight) / 2
                , windowsWedth
                , windowsHeight);
        //禁止最大化
        setResizable(false);

        mainPanel();
        showInfo();
        showClasses();

        //自动退出销毁
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    private void mainPanel() {
        //展示区
        mainContainer.setLayout(null);
        userInfoArea = new JTextArea();
        stuInfoArea = new JTextArea();
        //文本域不可编辑
        userInfoArea.setEditable(false);
        stuInfoArea.setEditable(false);
        //创建滚动窗格
        jspane = new JScrollPane(userInfoArea);
        jspane2 = new JScrollPane(stuInfoArea);
        jspane.setBounds(10, 10, 150, 90);
        jspane2.setBounds(10, 130, 150, 170);
        mainContainer.add(jspane);
        mainContainer.add(jspane2);
        JLabel label = new JLabel("---班级学生---");
        label.setBounds(10, 100, 150, 30);
        mainContainer.add(label);


        //功能区
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());
        jPanel.add(new JButton("测试1"));
        jPanel.add(new JButton("测试2"));
        jPanel.add(new JButton("测试3"));
        jPanel.add(new JButton("测试4"));
        jPanel.add(new JButton("测试5"));
        jPanel.add(new JButton("测试6"));
        jPanel.add(new JButton("测试7"));
        jPanel.add(new JButton("测试8"));
        jPanel.setBounds(160, 10, 400, 290);
        jPanel.setBackground(Color.pink);
        mainContainer.add(jPanel);


    }

    /**
     * 展示登录人员信息
     */
    private void showInfo() {
        if (teacher == null)
            return;
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

        userInfoArea.setText(sb.toString());
    }

    /**
     * 展示班级名单
     */
    private void showClasses() {
        if (classes == null)
            return;
        StringBuilder sb = new StringBuilder();
        List<Student> stuList = classes.getStudentList();
        //遍历拼接数据展示
        for (Student st : stuList) {
            sb.append(st.getName());
            sb.append("\t");
            sb.append(st.getUserNo());
            sb.append("\n");
        }

        stuInfoArea.setText(sb.toString());
    }

}
