package org.jimei.jcclub.utils;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author yezhaoxing
 * @date 2019/3/3
 * @description 存放数据库配置, 提供访问接口
 */
public class LoadDBconfig {

    // 存储配置文件中的值
    private static HashMap<String, String> dbMap = new HashMap<>();

    // 从map获取数据库配置
    public static String getDBValue(String name) {
        return dbMap.get(name);
    }

    // 初始化数据库配置到map
    public static void load(String path) {
        try {
            //加载配置dbconfig.properties
            Properties pro = new Properties();
            //加载文件
            pro.load(new FileInputStream(path));
            //取值
            String driverclass = pro.getProperty("driverclass");
            dbMap.put("driverclass", driverclass);
            String url = pro.getProperty("url");
            dbMap.put("url", url);
            String username = pro.getProperty("username");
            dbMap.put("username", username);
            String password = pro.getProperty("password");
            dbMap.put("password", password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
