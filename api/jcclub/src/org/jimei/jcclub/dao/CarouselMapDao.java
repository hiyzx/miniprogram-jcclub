package org.jimei.jcclub.dao;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.jimei.jcclub.model.po.CarouselMap;
import org.jimei.jcclub.utils.DBUtil;

import java.util.Collections;
import java.util.List;

/**
 * @author yexiaoling
 * @date 2019/3/4
 * @description 对CarouselMap表的数据库操作
 */
public class CarouselMapDao {

    /**
     * @author yexiaoling
     * @date 2019/3/4
     * @description 查询所有isShow=1的数据
     */
    public List<CarouselMap> getDataList() {
        try {
            String sql = "select * from carousel_map where isShow = 1";
            return DBUtil.getQr().query(sql, new BeanListHandler<>(CarouselMap.class));
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }
}
