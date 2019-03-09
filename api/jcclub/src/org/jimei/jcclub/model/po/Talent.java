package org.jimei.jcclub.model.po;

import java.util.Date;

/**
 * @author yexiaoling
 * @date 2019/2/24
 */
public class Talent {

    private Integer id;

    private Integer userInfoId;

    private String name;

    private String tel;

    private String className;

    private String idealPost;

    private String type;

    private String workExperience;

    private String competitionExperience;

    private Date createTime;

    private Integer isPublish;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Integer userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getIdealPost() {
        return idealPost;
    }

    public void setIdealPost(String idealPost) {
        this.idealPost = idealPost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public String getCompetitionExperience() {
        return competitionExperience;
    }

    public void setCompetitionExperience(String competitionExperience) {
        this.competitionExperience = competitionExperience;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(Integer isPublish) {
        this.isPublish = isPublish;
    }
}
