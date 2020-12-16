package com.xz.sims.ui;

import com.xz.sims.data.Controller;
import com.xz.sims.entity.Student;
import com.xz.sims.entity.Teacher;
import com.xz.sims.utils.AccountGenerate;
import com.xz.sims.utils.ScreenUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * @Author: xz
 * @Date: 2020/12/15
 * <p>
 * 登录窗口
 */
public class LoginFrame extends JFrame {
    // 定义窗体的宽高
    private int windowsWedth = 300;
    private int windowsHeight = 220;
    private Container mainContainer = getContentPane();
    private JTextField username;
    private JPasswordField password;
    private JRadioButton rb1;
    private JRadioButton rb2;
    private JButton submit;
    private ActionListener roleSelectListener;
    //当前选定角色 0 学生  1 教师
    private int type = 0;

    /**
     * 初始化这个窗口的一些操作
     */
    public void init() {
        setTitle("角色登录");

        //居中显示窗体
        setBounds((ScreenUtil.getScreenWidth() - windowsWedth) / 2
                , (ScreenUtil.getScreenHeight() - windowsHeight) / 2
                , windowsWedth
                , windowsHeight);
        //禁止最大化
        setResizable(false);

        mainPanel();
        listener();

        //自动退出销毁
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * 主要的面板
     */
    private void mainPanel() {
        //输入部分--Center
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(null);
        username = new JTextField();
        password = new JPasswordField();
        JLabel a1 = new JLabel("学工号：");
        JLabel a2 = new JLabel("密   码：");
        JLabel a3 = new JLabel("角   色：");
        a1.setBounds(50, 20, 50, 20);
        a2.setBounds(50, 60, 50, 20);
        a3.setBounds(50, 100, 50, 20);
        fieldPanel.add(a1);
        fieldPanel.add(a2);
        fieldPanel.add(a3);
        username.setBounds(110, 20, 120, 20);
        password.setBounds(110, 60, 120, 20);
        fieldPanel.add(username);
        fieldPanel.add(password);
        mainContainer.add(fieldPanel, "Center");

        //单选框
        rb1 = new JRadioButton("学生");//创建JRadioButton对象
        rb1.setSelected(true);//默认选中
        rb2 = new JRadioButton("教师");//创建JRadioButton对象
        ButtonGroup group = new ButtonGroup();
        group.add(rb1);
        group.add(rb2);
        rb1.setBounds(110, 100, 50, 20);
        rb2.setBounds(160, 100, 50, 20);
        fieldPanel.add(rb1);
        fieldPanel.add(rb2);

        //按钮部分--South
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        submit = new JButton("登录");
        buttonPanel.add(submit);
        submit.setPreferredSize(new Dimension(150, 30));
        mainContainer.add(buttonPanel, "South");

        //单选按钮监听进行角色切换
        roleSelectListener = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JRadioButton temp = (JRadioButton) e.getSource();
                if (temp == rb1) {
                    type = 0;
                } else if (temp == rb2) {
                    type = 1;
                }
            }
        };
        rb1.addActionListener(roleSelectListener);
        rb2.addActionListener(roleSelectListener);

    }

    /**
     * 监听各类事件
     */
    private void listener() {
        //登录按钮点击事件监听
        submit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userNo = username.getText().trim();
                String userPwd = new String(password.getPassword()).trim();
                if (userNo.equals("") || userPwd.equals("")) {
                    JOptionPane.showMessageDialog(null, "学工号和密码都不可为空"
                            , "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                System.out.println("输入：" + userNo);
                System.out.println("输入：" + userPwd);

                if (type == 0) {
                    //System.out.println("----学生登录-----");
                    //教师注册
                    //Teacher teacher = new Teacher();
                    //teacher.setAge(18);
                    //teacher.setClassName("一年级一班");
                    //teacher.setName("李明");
                    //teacher.setPhone("17666666666");
                    //teacher.setUserNo(AccountGenerate.makeAccount(8));//随机生成账号8位
                    //teacher.setUserPwd("123456");//随机生成账号8位
                    //int i = Controller.tRegister(teacher);
                    //System.out.println(i);

                    //学生注册
                    //Student student = new Student();
                    //student.setUserNo(AccountGenerate.makeAccount(8));
                    //student.setUserPwd("123");
                    //student.setTeacherNo("123");
                    //student.setTeacherName("李明");
                    //student.setPhone("10086");
                    //student.setName("咳咳");
                    //student.setAge("12");
                    //Controller.sRegister(student);


                    //添加至班级名单
                    //Student student = new Student();
                    //student.setAge("12");
                    //student.setName("小苗");
                    //student.setPhone("12345678");
                    //student.setTeacherName("李明");
                    //student.setTeacherNo("123");
                    //student.setUserNo("54123874");
                    //Controller.addStuToClasses("123", student);

                } else if (type == 1) {
                    System.out.println("----教师登录-----");
                    Teacher teacher = Controller.tlogin(userNo, userPwd);
                    if (teacher == null) {
                        JOptionPane.showMessageDialog(null, "人员不存在或密码错误"
                                , "警告", JOptionPane.WARNING_MESSAGE);
                    } else {
                        System.out.println("学工号：" + teacher.getUserNo() + "   登录成功");
                        new TeacherManagerFrame().init(teacher);
                        dispose();
                    }
                }

            }
        });


    }


}
