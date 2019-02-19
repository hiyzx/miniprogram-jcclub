package org.jimei.jcclub.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.lang.StringUtils;
import org.jimei.jcclub.model.po.Article;
import org.jimei.jcclub.utils.DBUtil;

import java.sql.Connection;
import java.util.List;

import static org.jimei.jcclub.utils.DBUtil.getConn;

public class ArticleDao {

    public List<Article> getDataList(String title) {
        Connection conn = null;
        List<Article> articleList = null;
        try {
            conn = getConn();
            QueryRunner qr = new QueryRunner();
            String sql = "select * from article where isShow = 1";
            if (StringUtils.isEmpty(title)) {
                articleList = qr.query(conn, sql, new BeanListHandler<>(Article.class));
            } else {
                sql = sql + " and title like %?%";
                articleList = qr.query(conn, sql, new BeanListHandler<>(Article.class), title);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConn(null,conn);
        }
        return articleList;
    }
}
