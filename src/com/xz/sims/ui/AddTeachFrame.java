package com.xz.sims.ui;

import com.xz.sims.data.Controller;
import com.xz.sims.entity.Student;
import com.xz.sims.entity.Teacher;
import com.xz.sims.utils.ScreenUtil;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

/**
 * @Author: xz
 * @Date: 2020/12/23
 */
public class AddTeachFrame extends JFrame {
    private Student student;
    private int windowsWedth = 250;
    private int windowsHeight = 150;

    public AddTeachFrame(Student student) {
        this.student = student;
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
        initData();

        //置顶
        setAlwaysOnTop(true);
        setVisible(true);
    }

    private void initData() {
        List<Teacher> list = Controller.findAllTeach();
        if (list != null) {
            for (Teacher t : list) {
                System.out.println(t.getName() + "=====" + t.getUserNo());
            }
        }
    }


    private void mainPanel() {

    }

    private void addListener() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
}
