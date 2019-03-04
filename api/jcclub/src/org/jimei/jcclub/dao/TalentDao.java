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
 * @date 2019/3/4
 * @description 对Talent表的操作
 */
public class TalentDao {

    /**
     * @author yezhaoxing
     * @date 2019/3/3
     * @description 保存或者更新用户信息, 如果是有记录, 就更新, 没有的话就新增一条记录
     */
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

    /**
     * @author yezhaoxing
     * @date 2019/3/3
     * @description 查询所有已经发布的人才库信息
     */
    public List<Talent> list() {
        try {
            String sql = "SELECT * FROM talent WHERE isPublish = 1";
            return DBUtil.getQr().query(sql, new BeanListHandler<>(Talent.class));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @author yezhaoxing
     * @date 2019/3/3
     * @description 根据用户id查询人才库信息
     */
    public Talent query(Integer userInfoId) {
        try {
            String sql = "SELECT * FROM talent WHERE userInfoId = ?";
            return DBUtil.getQr().query(sql, new BeanHandler<>(Talent.class), userInfoId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @author yezhaoxing
     * @date 2019/3/3
     * @description 根据人才库id查询人才库信息
     */
    public Talent queryById(Integer talentId) {
        try {
            String sql = "SELECT * FROM talent WHERE id = ?";
            return DBUtil.getQr().query(sql, new BeanHandler<>(Talent.class), talentId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @author yezhaoxing
     * @date 2019/3/3
     * @description 根据用户id发布或者取消发布到人才库列表
     * @param userInfoId 用户id
     * @param isPublish 1为发布,0为取消
     */
    public void publish(String userInfoId, String isPublish) {
        try {
            String sql = "UPDATE talent SET isPublish = ? WHERE userInfoId  = ?";
            DBUtil.getQr().update(sql, isPublish, userInfoId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author yezhaoxing
     * @date 2019/3/3
     * @description 保存人才库信息
     */
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

    /**
     * @author yezhaoxing
     * @date 2019/3/3
     * @description 更新人才库信息
     */
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
