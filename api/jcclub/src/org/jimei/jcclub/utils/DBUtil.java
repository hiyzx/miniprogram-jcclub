package org.jimei.jcclub.utils;

import java.sql.*;

import static org.jimei.jcclub.utils.LoadDBconfig.getDBValue;

/**
 * 数据库操类
 *
 */
public class DBUtil {

    /**
     * 连接数据库
     * @return
     */
    public static Connection getConn() {
        Connection conn = null;
        try {
            //加载驱动
            Class.forName(getDBValue("driverclass"));
            String url = (getDBValue("url"));
            String user = (getDBValue("username"));
            String password = (getDBValue("password"));
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭连接
     */
    public static void closeConn(Statement stat, Connection conn) {
        try {

            if (stat != null) {
                stat.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
