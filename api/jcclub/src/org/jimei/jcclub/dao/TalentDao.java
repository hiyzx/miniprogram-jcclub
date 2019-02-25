package org.jimei.jcclub.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.jimei.jcclub.model.dto.TalentDto;
import org.jimei.jcclub.model.po.Talent;
import org.jimei.jcclub.utils.DBUtil;

import java.sql.Connection;
import java.util.Date;

import static org.jimei.jcclub.utils.DBUtil.getConn;

/**
 * @author yezhaoxing
 * @date 2019/2/24
 */
public class TalentDao {

    public void saveOrUpdate(TalentDto talentDto) {
        Talent talent = this.query(talentDto.getUserInfoId());
        if (talent == null) {
            // 新增
            this.save(talentDto);
        } else {
            // 更新
            this.update(talent.getId(), talentDto);
        }

    }

    public Talent query(Integer userInfoId) {
        Connection conn = null;
        Talent talent = null;
        try {
            conn = getConn();
            QueryRunner qr = new QueryRunner();
            String sql = String.format("SELECT * FROM talent WHERE userInfoId = '%s'", userInfoId);
            talent = qr.query(conn, sql, new BeanHandler<>(Talent.class));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConn(null, conn);
        }
        return talent;
    }

    private void save(TalentDto talentDto) {
        Connection conn = getConn();
        try {
            QueryRunner qr = new QueryRunner();
            String sql =
                    "INSERT INTO talent (userInfoId, name,tel,className,idealPost,type,workExperience,competitionExperience,"
                            + "createTime) VALUES(?,?,?,?,?,?,?,?,?)";
            qr.update(conn, sql, talentDto.getUserInfoId(), talentDto.getName(), talentDto.getTel(),
                    talentDto.getClassName(), talentDto.getIdealPost(), talentDto.getType(),
                    talentDto.getWorkExperience(), talentDto.getCompetitionExperience(), new Date());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConn(null, conn);
        }
    }

    private void update(Integer talentId, TalentDto talentDto) {
        Connection conn = getConn();
        try {
            QueryRunner qr = new QueryRunner();
            String sql = "UPDATE talent SET name = ?, tel = ?,className = ?,idealPost = ? "
                    + ",type = ?, workExperience = ?,competitionExperience = ? WHERE id = ?";
            qr.update(conn, sql, talentDto.getName(), talentDto.getTel(), talentDto.getClassName(),
                    talentDto.getIdealPost(), talentDto.getType(), talentDto.getWorkExperience(),
                    talentDto.getCompetitionExperience(), talentId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConn(null, conn);
        }
    }

    public void publish(String userInfoId, String isPublish) {
        Connection conn = getConn();
        try {
            QueryRunner qr = new QueryRunner();
            String sql = "UPDATE talent SET isPublish = ? WHERE userInfoId  = ?";
            qr.update(conn, sql, userInfoId, isPublish);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConn(null, conn);
        }
    }
}
