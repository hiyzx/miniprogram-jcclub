package org.jimei.jcclub.model.po;

import java.util.Date;

/**
 * @author yexiaoling
 * @since 2019/02/20
 */
public class UserInfo {

    private Integer id;

    /**
     * 微信唯一识别号
     */
    private String openid;

    /**
     * 微信昵称
     */
    private String nickName;

    /**
     * 微信头像
     */
    private String avatarUrl;

    /**
     * 性别:1男 2女
     */
    private Integer gender;

    /**
     * 是否管理员
     */
    private Integer isAdmin;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }
}
