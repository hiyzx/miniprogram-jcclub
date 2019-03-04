package org.jimei.jcclub.dao;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.jimei.jcclub.model.dto.TeamDto;
import org.jimei.jcclub.model.po.Team;
import org.jimei.jcclub.utils.DBUtil;

import java.util.Date;
import java.util.List;

/**
 * @author yezhaoxing
 * @date 2019/3/4
 * @description 对Team表的操作
 */
public class TeamDao {

    public Team query(Integer teamId) {
        try {
            String sql = "select * from team where id = ?";
            return DBUtil.getQr().query(sql, new BeanHandler<>(Team.class), teamId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Team> list() {
        try {
            String sql = "select * from team ";
            return DBUtil.getQr().query(sql, new BeanListHandler<>(Team.class));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void save(TeamDto teamDto) {
        try {
            String sql = "INSERT INTO team (userInfoId, teamName,post,type,salary,briefIntro,requirement,tel,"
                    + "place ,createTime) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
            DBUtil.getQr()
                    .update(sql, teamDto.getUserInfoId(), teamDto.getTeamName(), teamDto.getPost(), teamDto.getType(),
                            teamDto.getSalary(), teamDto.getBriefIntro(), teamDto.getRequirement(), teamDto.getTel(),
                            teamDto.getPlace(), new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
