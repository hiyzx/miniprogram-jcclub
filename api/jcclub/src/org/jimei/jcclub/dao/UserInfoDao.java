package org.jimei.jcclub.dao;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.jimei.jcclub.model.dto.UserInfoDto;
import org.jimei.jcclub.model.po.UserInfo;
import org.jimei.jcclub.model.vo.UserInfoVo;
import org.jimei.jcclub.utils.DBUtil;

import java.util.Date;

/**
 * @author yexiaoling
 * @date 2019/3/4
 * @description 对微信用户表的操作
 */
public class UserInfoDao {

    /**
     * @author yexiaoling
     * @date 2019/3/4
     * @description 新增或者更新用户信息
     */
    public UserInfoVo auth(UserInfoDto userInfoDto) {
        UserInfo userInfo = this.query(userInfoDto.getOpenid());
        if (userInfo == null) {
            // 新增
            this.save(userInfoDto);
            userInfo = this.query(userInfoDto.getOpenid());
        } else {
            // 更新
            this.update(userInfo.getId(), userInfoDto);
        }
        return UserInfoVo.of(userInfo.getId(), userInfo.getIsAdmin());

    }

    /**
     * @author yexiaoling
     * @date 2019/3/4
     * @description 根据用户昵称查询用户信息(因为openid还要做服务器关联,而且这个字段没有用到)
     */
    public UserInfo query(String openid) {
        try {
            String sql = String.format("SELECT * FROM user_info WHERE openid = '%s'", openid);
            return DBUtil.getQr().query(sql, new BeanHandler<>(UserInfo.class));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @author yexiaoling
     * @date 2019/3/4
     * @description 保存用户信息
     */
    private void save(UserInfoDto userInfoDto) {
        // 根据openid查询是否存在
        try {
            String sql = "INSERT INTO user_info (openid, nickName,avatarUrl,gender,createTime) VALUES(?,?,?,?,?)";
            DBUtil.getQr().update(sql, userInfoDto.getOpenid(), userInfoDto.getNickName(), userInfoDto.getAvatarUrl(),
                    userInfoDto.getGender(), new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author yexiaoling
     * @date 2019/3/4
     * @description 更新用户信息
     */
    private void update(Integer userInfoId, UserInfoDto userInfoDto) {
        try {
            String sql = "UPDATE user_info SET openid = ?, nickName = ?,avatarUrl = ?,gender = ? WHERE id = ?";
            DBUtil.getQr().update(sql, userInfoDto.getOpenid(), userInfoDto.getNickName(), userInfoDto.getAvatarUrl(),
                    userInfoDto.getGender(), userInfoId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
