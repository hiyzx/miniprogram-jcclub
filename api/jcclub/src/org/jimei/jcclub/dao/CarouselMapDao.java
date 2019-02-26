package org.jimei.jcclub.dao;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.jimei.jcclub.model.po.CarouselMap;
import org.jimei.jcclub.utils.DBUtil;

import java.util.Collections;
import java.util.List;

public class CarouselMapDao {

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
