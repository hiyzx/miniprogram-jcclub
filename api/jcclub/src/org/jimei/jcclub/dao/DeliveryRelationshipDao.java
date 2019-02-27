package org.jimei.jcclub.dao;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.jimei.jcclub.model.po.DeliveryRelationship;
import org.jimei.jcclub.utils.DBUtil;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yezhaoxing
 * @since 2019/02/26
 */
public class DeliveryRelationshipDao {

    public void save(Integer talentUserInfoId, Integer talentId, Integer teamId, Integer teamUserInfoId) {
        try {
            String sql = "INSERT INTO delivery_relationship (talentUserInfoId,talentId,teamId,teamUserInfoId,createTime) VALUES(?,?,?,?,?)";
            DBUtil.getQr().update(sql, talentUserInfoId, talentId, teamId, teamUserInfoId, new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Integer> myDeliveryIdList(Integer talentUserInfoId) {
        List<DeliveryRelationship> deliveryRelationships = myDeliveryRelationshipList(talentUserInfoId);
        return deliveryRelationships.stream().map(DeliveryRelationship::getTeamId).collect(Collectors.toList());
    }

    public List<DeliveryRelationship> myDeliveryRelationshipList(Integer talentUserInfoId) {
        try {
            String sql = "select * from delivery_relationship where talentUserInfoId = ?";
            return DBUtil.getQr().query(sql, new BeanListHandler<>(DeliveryRelationship.class), talentUserInfoId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    public DeliveryRelationship queryByTalentIdAndTeamId(Integer talentId, Integer teamId) {
        try {
            String sql = "select * from delivery_relationship where talentId = ? AND teamId = ?";
            return DBUtil.getQr().query(sql, new BeanHandler<>(DeliveryRelationship.class), talentId, teamId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
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
