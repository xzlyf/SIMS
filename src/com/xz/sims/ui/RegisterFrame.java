package com.xz.sims.ui;

import com.xz.sims.data.Controller;
import com.xz.sims.entity.Student;
import com.xz.sims.utils.ScreenUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

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
    private JTextField ClassName = new JTextField();
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

        //自动退出销毁
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    private void mainPanel() {
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));
        mainContainer.setLayout(null);


        //单选框
        rb1 = new JRadioButton("学生");//创建JRadioButton对象
        rb1.setSelected(true);//默认选中
        rb2 = new JRadioButton("教师");//创建JRadioButton对象
        ButtonGroup group = new ButtonGroup();
        group.add(rb1);
        group.add(rb2);
        rb1.setBounds(80, 15, 50, 20);
        rb2.setBounds(130, 15, 50, 20);
        JLabel l1 = new JLabel("注册角色：");
        l1.setBounds(15, 15, 70, 20);
        mainContainer.add(l1);
        mainContainer.add(rb1);
        mainContainer.add(rb2);
        JLabel l2 = new JLabel("登录密码：");
        l2.setBounds(15, 55, 70, 20);
        mainContainer.add(l2);

        userPwd = new JTextField();
        userPwd.setBounds(80, 55, 100, 20);
        mainContainer.add(userPwd);

        submit = new JButton("注册");
        submit.setBounds(15, 100, 170, 30);
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

                if (type == 0) {
                    //学生注册
                } else {
                    //教工注册
                }
            }
        });

        //单选按钮监听进行角色切换
        AbstractAction roleSelectListener = new AbstractAction() {
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

}
