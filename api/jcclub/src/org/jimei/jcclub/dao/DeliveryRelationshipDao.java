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
 * @author yexiaoling
 * @date 2019/3/4
 * @description 对DeliveryRelationship表的数据库操作
 */
public class DeliveryRelationshipDao {

    /**
     * @author yexiaoling
     * @date 2019/3/4
     * @description 保存
     */
    public void save(Integer talentUserInfoId, Integer talentId, Integer teamId, Integer teamUserInfoId,
            Integer postId) {
        try {
            String sql = "INSERT INTO delivery_relationship (talentUserInfoId,talentId,teamId,teamUserInfoId,postId,createTime) VALUES(?,?,?,?,?,?)";
            DBUtil.getQr().update(sql, talentUserInfoId, talentId, teamId, teamUserInfoId, postId, new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author yexiaoling
     * @date 2019/3/4
     * @description 根据用户id查询我投递的团队id
     */
    public List<Integer> myDeliveryIdList(Integer talentUserInfoId) {
        List<DeliveryRelationship> deliveryRelationships = myDeliveryRelationshipList(talentUserInfoId);
        return deliveryRelationships.stream().map(DeliveryRelationship::getPostId).collect(Collectors.toList());
    }

    /**
     * @author yexiaoling
     * @date 2019/3/4
     * @description 根据用户id查询我投递的团队
     */
    public List<DeliveryRelationship> myDeliveryRelationshipList(Integer talentUserInfoId) {
        try {
            String sql = "select * from delivery_relationship where talentUserInfoId = ?";
            return DBUtil.getQr().query(sql, new BeanListHandler<>(DeliveryRelationship.class), talentUserInfoId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * @author yexiaoling
     * @date 2019/3/4
     * @description 根据人才id和团队id查询数据
     */
    public DeliveryRelationship queryByTalentIdAndPostId(Integer talentId, Integer postId) {
        try {
            String sql = "select * from delivery_relationship where talentId = ? AND postId = ?";
            return DBUtil.getQr().query(sql, new BeanHandler<>(DeliveryRelationship.class), talentId, postId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    /**
     * @author yexiaoling
     * @date 2019/3/4
     * @description 根据团队用户id查询被投递的记录
     */
    public List<DeliveryRelationship> otherDeliveryRelationshipList(Integer teamUserInfoId) {
        try {
            String sql = "select * from delivery_relationship where teamUserInfoId = ?";
            return DBUtil.getQr().query(sql, new BeanListHandler<>(DeliveryRelationship.class), teamUserInfoId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * @author yezhaoxing
     * @date 2019/5/13
     * @description 修改合伙人状态
     */
    public void addPartner(Integer userInfoId, Integer talentId, Integer isPartner) {
        try {
            String sql = "UPDATE delivery_relationship SET isPartner = ? WHERE teamUserInfoId= ? AND talentId = ?";
            DBUtil.getQr().update(sql, isPartner, userInfoId, talentId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @author yexiaoling
     * @date 2019/3/4
     * @description 查询合伙人状态
     */
    public List<DeliveryRelationship> queryDeliveryRelationshipList(Integer teamUserInfoId) {
        try {
            String sql = "select DISTINCT(talentId) from delivery_relationship where teamUserInfoId = ? AND isPartner = 1";
            return DBUtil.getQr().query(sql, new BeanListHandler<>(DeliveryRelationship.class), teamUserInfoId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }
}
