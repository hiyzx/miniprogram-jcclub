package org.jimei.jcclub.utils;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author yezhaoxing
 * @date 2019/3/3
 * @description 数据工具类, 用于或者QueryRunner
 */
public class DBUtil extends BasicDataSource {

    // 存储配置文件中的值
    private static HashMap<String, String> dbMap = new HashMap<>();

    // 获取QueryRunner
    public static QueryRunner getQr() {
        DataSource dataSource = new DBUtil().getDataSource();
        return new QueryRunner(dataSource);
    }

    private DataSource getDataSource() {
        DataSource ds = null;
        super.setDriverClassName(getDBValue("driverclass"));
        super.setUrl(getDBValue("url"));
        super.setUsername(getDBValue("username"));
        super.setPassword(getDBValue("password"));
        try {
            ds = super.createDataSource();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }

    // 从map获取数据库配置
    private static String getDBValue(String name) {
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
