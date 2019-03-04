package org.jimei.jcclub.utils;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.jimei.jcclub.utils.LoadDBconfig.getDBValue;

/**
 * @author yezhaoxing
 * @date 2019/3/3
 * @description 数据工具类, 用于或者QueryRunner
 */
public class DBUtil extends BasicDataSource {

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
}
