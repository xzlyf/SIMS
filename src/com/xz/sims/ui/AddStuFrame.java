package com.xz.sims.ui;

import com.xz.sims.data.Controller;
import com.xz.sims.entity.Student;
import com.xz.sims.utils.ScreenUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @Author: xz
 * @Date: 2020/12/16
 */
public class AddStuFrame extends JFrame {
    private String userNo;
    private int windowsWedth = 250;
    private int windowsHeight = 150;
    private Container mainContainer = getContentPane();
    private JTextField userNoField = new JTextField();
    private JButton submit = new JButton("添加");
    private Callback callback;

    public AddStuFrame(String userNo) {
        this.userNo = userNo;
        setTitle("添加学生");
        //居中显示窗体
        setBounds((ScreenUtil.getScreenWidth() - windowsWedth) / 2
                , (ScreenUtil.getScreenHeight() - windowsHeight) / 2
                , windowsWedth
                , windowsHeight);
        //禁止最大化
        setResizable(false);

        mainPanel();
        addListener();

        //置顶
        setAlwaysOnTop(true);
        //自动退出销毁
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * 开放回调监听
     *
     * @param callback
     * @return
     */
    public AddStuFrame addListener(Callback callback) {
        this.callback = callback;
        return this;
    }


    private void mainPanel() {
        mainContainer.setLayout(null);
        userNoField.setBounds(50, 30, 150, 30);
        mainContainer.add(userNoField);
        JLabel l1 = new JLabel("学号：");
        l1.setBounds(10, 30, 50, 30);
        mainContainer.add(l1);
        submit.setBounds(50, 70, 150, 30);
        mainContainer.add(submit);


    }

    private void addListener() {
        submit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //获取用户输入的学生账号
                String stuNo = userNoField.getText().trim();
                //查询学生信息
                Student stu = Controller.findStu(stuNo);
                if (stu == null) {
                    JOptionPane.showMessageDialog(mainContainer, "学生不存在");
                } else {
                    int n = JOptionPane.showConfirmDialog(mainContainer, "是否添加[" + stu.getName() + "]进入班级", "提示",
                            JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) {
                        //确定添加进班级
                        Controller.addStuToClasses(userNo, stu);
                        callback.done(AddStuFrame.this);
                        System.out.println("======添加成功========");
                    } else if (n == JOptionPane.NO_OPTION) {
                        //取消
                        callback.cancel(AddStuFrame.this);
                    }
                }

            }
        });
    }

    public interface Callback {
        void done(AddStuFrame t);

        void cancel(AddStuFrame t);
    }

}
