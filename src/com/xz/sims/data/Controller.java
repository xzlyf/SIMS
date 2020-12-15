package com.xz.sims.data;

import com.xz.sims.content.Local;
import com.xz.sims.entity.Teacher;

import java.io.*;

/**
 * @Author: xz
 * @Date: 2020/12/15
 */
public class Controller {

    /**
     * 登录实现
     *
     * @param userNo 账号
     * @param pwd    密码
     * @return 人员信息实体
     */
    public static Teacher tlogin(String userNo, String pwd) {
        //根据账号查询本地是否存在这个文件，也就是是否存在这个人员
        File file = new File(Local.USER_DIR + File.separator + userNo);
        if (!file.exists()) {
            //人员不存在
            return null;
        }

        //读取文件
        try {
            Reader reader = new InputStreamReader(new FileInputStream(file), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            reader.close();
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }
}
