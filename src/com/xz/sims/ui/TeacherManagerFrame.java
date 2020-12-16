package com.xz.sims.ui;

import com.xz.sims.data.Controller;
import com.xz.sims.entity.Classes;
import com.xz.sims.entity.Student;
import com.xz.sims.entity.Teacher;
import com.xz.sims.utils.ScreenUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
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
    //课表
    private JTable table;

    //按钮
    private JButton b1 = new JButton("添加学生");
    private JButton b2 = new JButton("生成随机课表（测试）");
    private JButton b3 = new JButton("保存课表");
    private JButton b4 = new JButton("退出登录");

    /**
     * 初始化 并传入登录人员信息
     *
     * @param teacher
     */
    public void init(Teacher teacher) {
        setTitle("SIMS校园管理系统(教师端)");
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
        addListener();
        showInfo();
        showClasses();
        DefaultTableModel model = Controller.getTable(teacher.getUserNo());
        if (model == null) {
            //如果这个老师没有创建过课表就生成一个随机课表
            showTabel(Controller.getRandomModel());
        } else {
            //如果课表存在就显示本地保存的课表数据
            showTabel(model);

        }
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
        jPanel.add(b1);
        jPanel.add(b2);
        jPanel.add(b3);
        jPanel.add(b4);
        jPanel.setBounds(160, 10, 400, 90);
        jPanel.setBackground(Color.pink);
        mainContainer.add(jPanel);

        //课表
        JLabel label2 = new JLabel("---班级课表---");
        label2.setBounds(170, 100, 150, 30);
        mainContainer.add(label2);
        table = new JTable(6, 5);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(160, 130, 400, 170);
        mainContainer.add(pane);


    }

    /**
     * 注册监听事件
     */
    private void addListener() {
        b1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //打开AddStuFrame窗体
                new AddStuFrame(teacher.getUserNo()).addListener(new AddStuFrame.Callback() {
                    @Override
                    public void done(AddStuFrame addStuFrame) {
                        //刷新学生名单列表
                        classes = Controller.getClasses(teacher.getUserNo());
                        showClasses();
                        addStuFrame.dispose();
                    }

                    @Override
                    public void cancel(AddStuFrame addStuFrame) {
                        addStuFrame.dispose();
                    }

                });
            }
        });

        //生成一个随机课表
        b2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTabel(Controller.getRandomModel());
            }
        });
        //保存课表
        b3.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.saveTimetable(teacher.getUserNo(), (DefaultTableModel) table.getModel());
                JOptionPane.showMessageDialog(mainContainer, "已保存当前课表");
            }
        });
        //退出登录
        b4.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginFrame().init();
                dispose();
            }
        });
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


    /**
     * 显示课表
     */
    private void showTabel(DefaultTableModel model) {
        table.setModel(model);
    }


}
