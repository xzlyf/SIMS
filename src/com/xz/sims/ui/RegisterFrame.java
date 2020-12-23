package com.xz.sims.ui;

import com.xz.sims.data.Controller;
import com.xz.sims.entity.Student;
import com.xz.sims.entity.Teacher;
import com.xz.sims.utils.AccountGenerate;
import com.xz.sims.utils.ScreenUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Author: xz
 * @Date: 2020/12/17
 */
public class RegisterFrame extends JFrame {
    private int windowsWedth = 500;
    private int windowsHeight = 300;
    private Container mainContainer = getContentPane();
    private JRadioButton rb1;
    private JRadioButton rb2;
    private JTextField userPwd = new JTextField();
    private JTextField userName = new JTextField();
    private JTextField userAge = new JTextField();
    private JTextField userPhone = new JTextField();
    private JTextField className = new JTextField();
    private JLabel l6 = new JLabel("管理班级：");
    private JButton submit;

    //当前角色 0学生  1教师
    private int type = 0;

    public void init() {
        setTitle("注册用户");
        //居中显示窗体
        setBounds((ScreenUtil.getScreenWidth() - windowsWedth) / 2
                , (ScreenUtil.getScreenHeight() - windowsHeight) / 2
                , windowsWedth
                , windowsHeight);
        //禁止最大化
        setResizable(false);

        mainPanel();
        addListener();

        //居中
        //setLocationRelativeTo(null);
        //自动退出销毁
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }


    private void mainPanel() {
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));

        JPanel selectPanel = new JPanel();
        selectPanel.setLayout(new BoxLayout(selectPanel, BoxLayout.X_AXIS));

        //单选框
        rb1 = new JRadioButton("学生");//创建JRadioButton对象
        rb1.setSelected(true);//默认选中
        rb2 = new JRadioButton("教师");//创建JRadioButton对象
        ButtonGroup group = new ButtonGroup();
        group.add(rb1);
        group.add(rb2);
        JLabel l1 = new JLabel("注册角色：");
        selectPanel.add(l1);
        selectPanel.add(rb1);
        selectPanel.add(rb2);
        mainContainer.add(selectPanel);

        //标签区
        JPanel txtPanel = new JPanel();
        txtPanel.setLayout(new BoxLayout(txtPanel, BoxLayout.Y_AXIS));

        JLabel l2 = new JLabel("姓名：");
        JLabel l3 = new JLabel("年龄：");
        JLabel l4 = new JLabel("手机号：");
        JLabel l5 = new JLabel("登录密码：");
        txtPanel.add(l2);
        txtPanel.add(l3);
        txtPanel.add(l4);
        txtPanel.add(l5);
        txtPanel.add(l6);
        l6.setVisible(false);

        //输入区
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        userPwd.setPreferredSize(new Dimension(70, 20));
        inputPanel.add(userName);
        inputPanel.add(userAge);
        inputPanel.add(userPhone);
        inputPanel.add(userPwd);
        inputPanel.add(className);
        className.setVisible(false);

        JPanel rpa = new JPanel();
        rpa.setLayout(new BoxLayout(rpa, BoxLayout.X_AXIS));
        rpa.add(txtPanel);
        rpa.add(inputPanel);

        rpa.setPreferredSize(new Dimension(200, 100));
        mainContainer.add(rpa);

        //按钮
        submit = new JButton("注册");
        mainContainer.add(submit);
    }

    private void addListener() {
        submit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pwd = userPwd.getText().trim();
                if (pwd.equals("")) {
                    JOptionPane.showMessageDialog(null, "密码不可为空"
                            , "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                System.out.println("输入：" + userPwd);
                String userNo = AccountGenerate.makeAccount(8);

                if (type == 0) {
                    //学生注册
                    String name = userName.getText().trim();
                    String age = userAge.getText().trim();
                    String phone = userPhone.getText().trim();
                    String p = userPwd.getText().trim();
                    Student student = new Student();
                    student.setUserNo(userNo);
                    student.setName(name);
                    student.setUserPwd(p);
                    student.setPhone(phone);
                    student.setAge(age);
                    Controller.sRegister(student);
                } else {
                    //教工注册
                    String name = userName.getText().trim();
                    String age = userAge.getText().trim();
                    String phone = userPhone.getText().trim();
                    String p = userPwd.getText().trim();
                    String classes = className.getText().trim();
                    Teacher teacher = new Teacher();
                    teacher.setUserPwd(p);
                    teacher.setUserNo(userNo);
                    teacher.setPhone(phone);
                    teacher.setName(name);
                    teacher.setAge(age);
                    teacher.setClassName(classes);
                    Controller.tRegister(teacher);
                }

                JOptionPane.showMessageDialog(mainContainer, "账号：" + userNo + "密码：" + userPwd.getText().trim() + "\n请记住账号和密码");
            }
        });

        //单选按钮监听进行角色切换
        AbstractAction roleSelectListener = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JRadioButton temp = (JRadioButton) e.getSource();
                if (temp == rb1) {
                    type = 0;
                    l6.setVisible(false);
                    className.setVisible(false);
                } else if (temp == rb2) {
                    type = 1;
                    l6.setVisible(true);
                    className.setVisible(true);
                    pack();
                }
            }
        };
        rb1.addActionListener(roleSelectListener);
        rb2.addActionListener(roleSelectListener);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

}
