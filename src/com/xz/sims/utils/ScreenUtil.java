package com.xz.sims.utils;

import java.awt.*;

/**
 * @Author: xz
 * @Date: 2020/12/15
 * 屏幕工具类
 * 获取屏幕参数，封边率大小等
 */
public class ScreenUtil {

    /**
     * 获取屏幕宽度
     *
     * @return 单位px
     */
    public static int getScreenWidth() {
        return Toolkit.getDefaultToolkit().getScreenSize().width;
    }

    /**
     * 获取屏幕高度
     *
     * @return 单位px
     */
    public static int getScreenHeight() {
        return Toolkit.getDefaultToolkit().getScreenSize().height;
    }
}
