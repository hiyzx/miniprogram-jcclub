package org.jimei.jcclub.servlet;

import org.jimei.jcclub.dao.DeliveryRelationshipDao;
import org.jimei.jcclub.dao.TalentDao;
import org.jimei.jcclub.dao.TeamDao;
import org.jimei.jcclub.model.dto.PostRequirementDto;
import org.jimei.jcclub.model.dto.TeamDto;
import org.jimei.jcclub.model.po.DeliveryRelationship;
import org.jimei.jcclub.model.po.Talent;
import org.jimei.jcclub.model.po.Team;
import org.jimei.jcclub.model.vo.TeamVo;
import org.jimei.jcclub.utils.SendMsgUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author yexiaoling
 * @since 2019/02/19
 * @description 团队库接口
 */
@WebServlet("/teamLibrary")
public class TeamLibraryServlet extends BaseServletFactory {

    @Override
    protected Object handle(HttpServletRequest request, HttpServletResponse response) {
        String actionName = request.getParameter("actionName");
        if ("list".equals(actionName)) {// 查询列表页
            System.out.println("查询团队库列表页");
            return this.list(request);
        } else if ("saveTeamInfo".equals(actionName)) { // 发布团队信息
            System.out.println("发布团队信息");
            return this.saveTeamInfo(request);
        } else if ("queryTeamInfo".equals(actionName)) { // 获取团队信息
            System.out.println("获取团队信息");
            return this.queryTeamInfo(request);
        } else if ("publish".equals(actionName)) { // 发布岗位信息
            System.out.println("发布岗位信息");
            return this.publish(request);
        } else if ("delivery".equals(actionName)) {// 投递
            System.out.println("投递到团队");
            return this.delivery(request);
        } else {
            throw new RuntimeException("actionName不能为空");
        }
    }

    private Object list(HttpServletRequest request) {
        Integer userInfoId = Integer.valueOf(request.getParameter("userInfoId"));
        // Integer userInfoId = 1;
        List<TeamVo> list = new TeamDao().list(0);
        List<Integer> deliveryIds = new DeliveryRelationshipDao().myDeliveryIdList(userInfoId);
        list.forEach(team -> {
            try {
                team.setIsDelivery(deliveryIds.contains(team.getId()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return list;
    }

    private Object saveTeamInfo(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        TeamDto talentDto = new TeamDto();
        talentDto.setUserInfoId(Integer.valueOf(parameterMap.get("userInfoId")[0]));
        talentDto.setTeamName(parameterMap.get("teamName")[0]);
        talentDto.setBriefIntro(parameterMap.get("briefIntro")[0]);
        talentDto.setTel(parameterMap.get("tel")[0]);
        talentDto.setPlace(parameterMap.get("place")[0]);
        new TeamDao().saveOrUpdate(talentDto);
        return true;
    }

    private Object queryTeamInfo(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Integer userInfoId = Integer.valueOf(parameterMap.get("userInfoId")[0]);
        return new TeamDao().query(userInfoId);
    }

    private Object publish(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        PostRequirementDto postRequirement = new PostRequirementDto();
        postRequirement.setUserInfoId(Integer.valueOf(parameterMap.get("userInfoId")[0]));
        postRequirement.setTeamId(Integer.valueOf(parameterMap.get("teamId")[0]));
        postRequirement.setPost(parameterMap.get("post")[0]);
        postRequirement.setType(parameterMap.get("type")[0]);
        postRequirement.setSalary(parameterMap.get("salary")[0]);
        postRequirement.setRequirement(parameterMap.get("requirement")[0]);
        new TeamDao().save(postRequirement);
        return true;
    }

    private Object delivery(HttpServletRequest request) {

        Integer teamId = Integer.valueOf(request.getParameter("teamId"));// 团队id
        Integer userInfoId = Integer.valueOf(request.getParameter("userInfoId"));// 投递者用户id
        Integer postId = Integer.valueOf(request.getParameter("postId"));// 岗位id
        Talent talent = new TalentDao().query(userInfoId);// 根据id查询人才
        if (talent == null) {
            System.out.println("必须完善资料才能投递");
            return 1;
        }
        Team team = new TeamDao().queryByTeamId(teamId);// 根据id查询团队
        if (userInfoId.equals(team.getUserInfoId())) {
            System.out.println("不能投递自己的团队");
            return 2;
        }
        DeliveryRelationship deliveryRelationship = new DeliveryRelationshipDao()
                .queryByTalentIdAndPostId(talent.getId(), postId);
        if (deliveryRelationship != null) {
            System.out.println("您已经投递,无需重复投递");
            return 3;
        }
        Boolean sendResult = SendMsgUtil.send(team.getTel(), "137774");
        System.out.println("短信发送完成,结果=" + sendResult);
        // 保存投递记录表
        new DeliveryRelationshipDao().save(userInfoId, talent.getId(), team.getId(), team.getUserInfoId(),postId);
        return 4;
    }
}
