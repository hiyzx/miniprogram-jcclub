package org.jimei.jcclub.model.dto;

/**
 * @author yezhaoxing
 * @since 2019/02/20
 */
public class UserInfoDto {

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
}
