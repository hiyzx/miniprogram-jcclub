package org.jimei.jcclub.dao;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.jimei.jcclub.model.po.Article;
import org.jimei.jcclub.utils.DBUtil;

import java.util.Collections;
import java.util.List;

/**
 * @author yexiaoling
 * @date 2019/3/4
 * @description 对Article表的数据库操作
 */
public class ArticleDao {

    /**
     * @author yexiaoling
     * @date 2019/3/4
     * @description 查询所有isShow=1的数据
     */
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
