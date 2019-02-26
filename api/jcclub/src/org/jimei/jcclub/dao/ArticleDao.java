package org.jimei.jcclub.dao;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.jimei.jcclub.model.po.Article;
import org.jimei.jcclub.utils.DBUtil;

import java.util.Collections;
import java.util.List;

public class ArticleDao {

    public List<Article> getDataList() {
        try {
            String sql = "select * from article where isShow = 1";
            return DBUtil.getQr().query(sql, new BeanListHandler<>(Article.class));
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
