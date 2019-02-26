package org.jimei.jcclub.dao;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.jimei.jcclub.model.dto.TalentDto;
import org.jimei.jcclub.model.po.Talent;
import org.jimei.jcclub.utils.DBUtil;

import java.util.Date;
import java.util.List;

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

    public List<Talent> list() {
        try {
            String sql = "SELECT * FROM talent WHERE isPublish = 1";
            return DBUtil.getQr().query(sql, new BeanListHandler<>(Talent.class));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Talent query(Integer userInfoId) {
        try {
            String sql = "SELECT * FROM talent WHERE userInfoId = ?";
            return DBUtil.getQr().query(sql, new BeanHandler<>(Talent.class), userInfoId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Talent queryById(Integer talentId) {
        try {
            String sql = "SELECT * FROM talent WHERE id = ?";
            return DBUtil.getQr().query(sql, new BeanHandler<>(Talent.class), talentId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void publish(String userInfoId, String isPublish) {
        try {
            String sql = "UPDATE talent SET isPublish = ? WHERE userInfoId  = ?";
            DBUtil.getQr().update(sql, userInfoId, isPublish);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void save(TalentDto talentDto) {
        try {
            String sql =
                    "INSERT INTO talent (userInfoId, name,tel,className,idealPost,type,workExperience,competitionExperience,"
                            + "createTime) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            DBUtil.getQr().update(sql, talentDto.getUserInfoId(), talentDto.getName(), talentDto.getTel(),
                    talentDto.getClassName(), talentDto.getIdealPost(), talentDto.getType(),
                    talentDto.getWorkExperience(), talentDto.getCompetitionExperience(), new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void update(Integer talentId, TalentDto talentDto) {
        try {
            String sql = "UPDATE talent SET name = ?, tel = ?,className = ?,idealPost = ? "
                    + ",type = ?, workExperience = ?,competitionExperience = ? WHERE id = ?";
            DBUtil.getQr().update(sql, talentDto.getName(), talentDto.getTel(), talentDto.getClassName(),
                    talentDto.getIdealPost(), talentDto.getType(), talentDto.getWorkExperience(),
                    talentDto.getCompetitionExperience(), talentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
