package org.jimei.jcclub.dao;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.jimei.jcclub.model.po.DeliveryRelationship;
import org.jimei.jcclub.utils.DBUtil;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author yezhaoxing
 * @since 2019/02/26
 */
public class DeliveryRelationshipDao {

    public void save(Integer talentId, Integer teamId, Integer teamUserInfoId) {
        try {
            String sql = "INSERT INTO delivery_relationship (talentId,teamId,teamUserInfoId,createTime) VALUES(?,?,?,?)";
            DBUtil.getQr().update(sql, talentId, teamId, teamUserInfoId, new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<DeliveryRelationship> myDeliveryRelationshipList(Integer talentId) {
        try {
            String sql = "select * from delivery_relationship where talentId = ?";
            return DBUtil.getQr().query(sql, new BeanListHandler<>(DeliveryRelationship.class), talentId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<DeliveryRelationship> otherDeliveryRelationshipList(Integer teamUserInfoId) {
        try {
            String sql = "select * from delivery_relationship where teamUserInfoId = ?";
            return DBUtil.getQr().query(sql, new BeanListHandler<>(DeliveryRelationship.class), teamUserInfoId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }
}
