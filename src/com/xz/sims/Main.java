package com.xz.sims;

import com.xz.sims.data.Controller;
import com.xz.sims.ui.LoginFrame;

/**
 * @Author: xz
 * @Date: 2020/12/15
 * 项目名:school information management system
 * 项目简称：SIMS
 * 项目中文名：校园信息管理系统
 */
public class Main {
    public static void main(String[] args) {
        Controller.init();
        new LoginFrame().init();
    }
}
