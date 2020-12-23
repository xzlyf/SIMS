package com.xz.sims.ui;

import com.xz.sims.data.Controller;
import com.xz.sims.entity.Student;
import com.xz.sims.entity.Teacher;
import com.xz.sims.utils.AccountGenerate;
import com.xz.sims.utils.ScreenUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RegisterFrameV2 extends JFrame {

    private JPanel contentPane;
    private JTextField nameTf;
    private JTextField ageTf;
    private JTextField phoneTf;
    private JTextField passwordTf;
    private JTextField classesTf;
    private JRadioButton typeTeach;
    private JRadioButton typeStu;
    private JLabel lbClasses;
    private JButton submit;
    //当前角色 0学生  1教师
    private int type = 0;

    public RegisterFrameV2() {
        setTitle("注册用户");
        //禁止最大化
        setResizable(false);
        setBounds(100, 100, 273, 361);
        setLocationRelativeTo(null);
        setVisible(true);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("注册角色");
        lblNewLabel.setBounds(14, 26, 66, 15);
        contentPane.add(lblNewLabel);

        typeStu = new JRadioButton("学生");
        typeStu.setBounds(159, 22, 68, 23);
        typeStu.setSelected(true);
        contentPane.add(typeStu);

        typeTeach = new JRadioButton("教师");
        typeTeach.setBounds(86, 23, 60, 23);
        contentPane.add(typeTeach);

        ButtonGroup group = new ButtonGroup();
        group.add(typeStu);
        group.add(typeTeach);

        JLabel lblNewLabel_1 = new JLabel("姓名");
        lblNewLabel_1.setBounds(14, 74, 54, 15);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("年龄");
        lblNewLabel_1_1.setBounds(14, 110, 54, 15);
        contentPane.add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_1_1 = new JLabel("手机号");
        lblNewLabel_1_1_1.setBounds(14, 146, 54, 15);
        contentPane.add(lblNewLabel_1_1_1);

        JLabel lblNewLabel_1_1_1_1 = new JLabel("登录密码");
        lblNewLabel_1_1_1_1.setBounds(14, 182, 54, 15);
        contentPane.add(lblNewLabel_1_1_1_1);

        lbClasses = new JLabel("管理班级");
        lbClasses.setBounds(14, 218, 54, 15);
        contentPane.add(lbClasses);

        nameTf = new JTextField();
        nameTf.setBounds(86, 71, 123, 21);
        contentPane.add(nameTf);
        nameTf.setColumns(10);

        ageTf = new JTextField();
        ageTf.setColumns(10);
        ageTf.setBounds(86, 107, 123, 21);
        contentPane.add(ageTf);

        phoneTf = new JTextField();
        phoneTf.setColumns(10);
        phoneTf.setBounds(86, 143, 123, 21);
        contentPane.add(phoneTf);

        passwordTf = new JTextField();
        passwordTf.setColumns(10);
        passwordTf.setBounds(86, 179, 123, 21);
        contentPane.add(passwordTf);

        classesTf = new JTextField();
        classesTf.setColumns(10);
        classesTf.setBounds(86, 215, 123, 21);
        contentPane.add(classesTf);

        submit = new JButton("注册");
        submit.setBounds(14, 261, 213, 23);
        contentPane.add(submit);

        classesTf.setVisible(false);
        lbClasses.setVisible(false);

        addListener();
    }


    private void addListener() {
        submit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pwd = passwordTf.getText().trim();
                if (pwd.equals("")) {
                    JOptionPane.showMessageDialog(null, "密码不可为空"
                            , "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                System.out.println("输入：" + passwordTf);
                String userNo = AccountGenerate.makeAccount(6);

                if (type == 0) {
                    //学生注册
                    String name = nameTf.getText().trim();
                    String age = ageTf.getText().trim();
                    String phone = phoneTf.getText().trim();
                    String p = passwordTf.getText().trim();
                    Student student = new Student();
                    student.setUserNo(userNo);
                    student.setName(name);
                    student.setUserPwd(p);
                    student.setPhone(phone);
                    student.setAge(age);
                    Controller.sRegister(student);
                } else {
                    //教工注册
                    String name = nameTf.getText().trim();
                    String age = ageTf.getText().trim();
                    String phone = phoneTf.getText().trim();
                    String p = passwordTf.getText().trim();
                    String classes = classesTf.getText().trim();
                    Teacher teacher = new Teacher();
                    teacher.setUserPwd(p);
                    teacher.setUserNo(userNo);
                    teacher.setPhone(phone);
                    teacher.setName(name);
                    teacher.setAge(age);
                    teacher.setClassName(classes);
                    Controller.tRegister(teacher);
                }

                dispose();
                JOptionPane.showMessageDialog(null, "账号：" + userNo + "密码："
                        + passwordTf.getText().trim() + "\n请记住账号和密码");
            }
        });

        //单选按钮监听进行角色切换
        AbstractAction roleSelectListener = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JRadioButton temp = (JRadioButton) e.getSource();
                if (temp == typeStu) {
                    type = 0;
                    classesTf.setVisible(false);
                    lbClasses.setVisible(false);
                } else if (temp == typeTeach) {
                    type = 1;
                    lbClasses.setVisible(true);
                    classesTf.setVisible(true);
                }
            }
        };
        typeStu.addActionListener(roleSelectListener);
        typeTeach.addActionListener(roleSelectListener);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
}
