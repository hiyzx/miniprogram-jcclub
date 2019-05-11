package org.jimei.jcclub.dao;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.jimei.jcclub.model.dto.PostRequirementDto;
import org.jimei.jcclub.model.dto.TeamDto;
import org.jimei.jcclub.model.po.PostRequirement;
import org.jimei.jcclub.model.po.Team;
import org.jimei.jcclub.model.vo.TeamVo;
import org.jimei.jcclub.utils.DBUtil;

import java.util.Date;
import java.util.List;

/**
 * @author yexiaoling
 * @date 2019/3/4
 * @description 对Team表的操作
 */
public class TeamDao {

    // 以下是对team表的操作

    public Team query(Integer userInfoId) {
        try {
            String sql = "select * from team where userInfoId = ?";
            return DBUtil.getQr().query(sql, new BeanHandler<>(Team.class), userInfoId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Team queryByTeamId(Integer teamId) {
        try {
            String sql = "select * from team where id = ?";
            return DBUtil.getQr().query(sql, new BeanHandler<>(Team.class), teamId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveOrUpdate(TeamDto teamDto) {
        try {
            Team team = this.query(teamDto.getUserInfoId());
            if (team == null) {
                String sql = "INSERT INTO team (userInfoId, teamName,briefIntro,tel,"
                        + "place ,createTime) VALUES(?, ?, ?, ?, ?, ?)";
                DBUtil.getQr().update(sql, teamDto.getUserInfoId(), teamDto.getTeamName(), teamDto.getBriefIntro(),
                        teamDto.getTel(), teamDto.getPlace(), new Date());
            } else {
                String sql = "UPDATE TEAM SET teamName = ?,briefIntro = ? ,tel = ? ,place = ? WHERE id = ?";
                DBUtil.getQr().update(sql, teamDto.getTeamName(), teamDto.getBriefIntro(), teamDto.getTel(),
                        teamDto.getPlace(), team.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 以下是对post_requirement表的操作
    public void save(PostRequirementDto postRequirementDto) {
        try {
            String sql = "INSERT INTO post_requirement (userInfoId,teamId, post ,type,salary,requirement,createTime) "
                    + "VALUES (?,?,?,?,?,?,?)";
            DBUtil.getQr().update(sql, postRequirementDto.getUserInfoId(), postRequirementDto.getTeamId(),
                    postRequirementDto.getPost(), postRequirementDto.getType(), postRequirementDto.getSalary(),
                    postRequirementDto.getSalary(), 0, new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void approval(Integer postRequirementIdd, Integer isPublish, String remark) {
        try {
            String sql = "UPDATE post_requirement SET approvalStatus = ? ,remark = ? WHERE id = ?";
            DBUtil.getQr().update(sql, isPublish, remark, postRequirementIdd);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void publish(Integer postRequirementIdd, Integer isPublish) {
        try {
            String sql = "UPDATE post_requirement SET isPublish = ? WHERE id = ?";
            DBUtil.getQr().update(sql, isPublish, postRequirementIdd);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // 以下是对team和post_requirement表的联合操作
    public List<TeamVo> list(Integer type) {
        try {
            String sql;
            if (type == 1) {// 如果是管理员,查询所有发布的
                sql = "select * from team t join post_requirement pr where t.id = pr.teamId and pr.isPublish = 1";
            } else {// 如果是团队库的话,查询审批通过的
                sql = "select * from team t join post_requirement pr where t.id = pr.teamId and pr.isPublish = 1 and approvalStatus = 1";
            }
            return DBUtil.getQr().query(sql, new BeanListHandler<>(TeamVo.class));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public TeamVo queryByPostId(Integer postId) {
        try {
            String sql =
                    "select * from team t join post_requirement pr where t.id = pr.teamId and pr.isPublish = 1 and pr.id = "
                            + postId;
            return DBUtil.getQr().query(sql, new BeanHandler<>(TeamVo.class));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<PostRequirement> listByUserId(Integer userInfoId) {
        try {
            String sql = "select * from post_requirement where userInfoId = ?";
            return DBUtil.getQr().query(sql, new BeanListHandler<>(PostRequirement.class), userInfoId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
