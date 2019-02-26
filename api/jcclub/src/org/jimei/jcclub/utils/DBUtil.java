package org.jimei.jcclub.utils;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.jimei.jcclub.utils.LoadDBconfig.getDBValue;

/**
 * 数据库操类
 *
 */
public class DBUtil extends BasicDataSource {

    public DataSource getDataSource() {
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

    public static QueryRunner getQr() {
        DataSource dataSource = new DBUtil().getDataSource();
        return new QueryRunner(dataSource);
    }
}
