package org.jimei.jcclub.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.jimei.jcclub.model.dto.UserInfoDto;
import org.jimei.jcclub.model.po.UserInfo;
import org.jimei.jcclub.utils.DBUtil;

import java.sql.Connection;
import java.util.Date;

import static org.jimei.jcclub.utils.DBUtil.getConn;

public class UserInfoDao {

    public Integer auth(UserInfoDto userInfoDto) {
        UserInfo userInfo = this.query(userInfoDto.getNickName());
        if (userInfo == null) {
            // 新增
            this.save(userInfoDto);
            return this.query(userInfoDto.getNickName()).getId();
        } else {
            // 更新
            this.update(userInfo.getId(), userInfoDto);
            return userInfo.getId();
        }

    }

    public UserInfo query(String nickName) {
        Connection conn = null;
        UserInfo userInfo = null;
        try {
            conn = getConn();
            QueryRunner qr = new QueryRunner();
            String sql = String.format("SELECT * FROM user_info WHERE nickName = '%s'", nickName);
            userInfo = qr.query(conn, sql, new BeanHandler<>(UserInfo.class));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConn(null, conn);
        }
        return userInfo;
    }

    private Boolean save(UserInfoDto userInfoDto) {
        Connection conn = getConn();
        // 根据openid查询是否存在
        try {
            QueryRunner qr = new QueryRunner();
            String sql = "INSERT INTO user_info (openid, nickName,avatarUrl,gender,createTime) VALUES(?,?,?,?,?)";
            qr.update(conn, sql, userInfoDto.getOpenid(), userInfoDto.getNickName(), userInfoDto.getAvatarUrl(),
                    userInfoDto.getGender(), new Date());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.closeConn(null, conn);
        }
        return true;
    }

    private Boolean update(Integer userInfoId, UserInfoDto userInfoDto) {
        Connection conn = getConn();
        try {
            QueryRunner qr = new QueryRunner();
            String sql = "UPDATE user_info SET openid = ?, nickName = ?,avatarUrl = ?,gender = ? WHERE id = ?";
            qr.update(conn, sql, userInfoDto.getOpenid(), userInfoDto.getNickName(), userInfoDto.getAvatarUrl(),
                    userInfoDto.getGender(), userInfoId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.closeConn(null, conn);
        }
        return true;
    }
}
