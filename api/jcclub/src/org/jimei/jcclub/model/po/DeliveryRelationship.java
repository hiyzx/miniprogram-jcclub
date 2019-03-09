package org.jimei.jcclub.model.po;

import java.util.Date;

/**
 * @author yexiaoling
 * @since 2019/02/26
 */
public class DeliveryRelationship {

    private Integer id;

    private Integer talentUserInfoId;

    private Integer talentId;

    private Integer teamId;

    private Integer teamUserInfoId;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTalentUserInfoId() {
        return talentUserInfoId;
    }

    public void setTalentUserInfoId(Integer talentUserInfoId) {
        this.talentUserInfoId = talentUserInfoId;
    }

    public Integer getTalentId() {
        return talentId;
    }

    public void setTalentId(Integer talentId) {
        this.talentId = talentId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getTeamUserInfoId() {
        return teamUserInfoId;
    }

    public void setTeamUserInfoId(Integer teamUserInfoId) {
        this.teamUserInfoId = teamUserInfoId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
