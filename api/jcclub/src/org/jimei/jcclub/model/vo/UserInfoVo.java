package org.jimei.jcclub.model.vo;

/**
 * @author yezhaoxing
 * @date 2019/5/11
 */
public class UserInfoVo {

    private Integer userInfoId;

    private Integer isAdmin;

    public static UserInfoVo of(Integer userInfoId,Integer isAdmin){
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setUserInfoId(userInfoId);
        userInfoVo.setIsAdmin(isAdmin);
        return userInfoVo;
    }

    public Integer getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Integer userInfoId) {
        this.userInfoId = userInfoId;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

}
