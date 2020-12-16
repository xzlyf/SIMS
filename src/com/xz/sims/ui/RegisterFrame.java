package com.xz.sims.ui;

import com.xz.sims.utils.ScreenUtil;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: xz
 * @Date: 2020/12/17
 */
public class RegisterFrame extends JFrame {
    private int windowsWedth = 500;
    private int windowsHeight = 300;
    private Container mainContainer = getContentPane();

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

        //自动退出销毁
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void mainPanel() {
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));

        mainContainer.add(new JButton("a"));
        mainContainer.add(new JButton("a"));
        mainContainer.add(new JButton("a"));
        mainContainer.add(new JButton("a"));
        mainContainer.add(new JButton("a"));
    }
}
