package org.jimei.jcclub.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.jimei.jcclub.model.po.CarouselMap;

import java.sql.Connection;
import java.util.List;

import static org.jimei.jcclub.utils.DBUtil.getConn;

public class CarouselMapDao {

    public List<CarouselMap> getDataList() {
        Connection conn = getConn();
        List<CarouselMap> articleList = null;
        try {
            QueryRunner qr = new QueryRunner();
            String sql = "select * from carousel_map where isShow = 1";
            articleList = qr.query(conn, sql, new BeanListHandler<>(CarouselMap.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articleList;
    }
}
