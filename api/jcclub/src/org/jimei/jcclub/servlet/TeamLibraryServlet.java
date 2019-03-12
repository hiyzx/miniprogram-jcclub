package org.jimei.jcclub.servlet;

import org.apache.commons.beanutils.BeanUtils;
import org.jimei.jcclub.dao.DeliveryRelationshipDao;
import org.jimei.jcclub.dao.TalentDao;
import org.jimei.jcclub.dao.TeamDao;
import org.jimei.jcclub.model.dto.TeamDto;
import org.jimei.jcclub.model.po.DeliveryRelationship;
import org.jimei.jcclub.model.po.Talent;
import org.jimei.jcclub.model.po.Team;
import org.jimei.jcclub.model.vo.TeamVo;
import org.jimei.jcclub.utils.SendMsgUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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
        } else if ("publish".equals(actionName)) { // 发布
            System.out.println("发布团队信息");
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
        List<Team> list = new TeamDao().list();
        List<TeamVo> rtn = new ArrayList<>(list.size());
        List<Integer> deliveryIds = new DeliveryRelationshipDao().myDeliveryIdList(userInfoId);
        list.forEach(team -> {
            TeamVo teamVo = new TeamVo();
            try {
                BeanUtils.copyProperties(teamVo, team);
                teamVo.setIsDelivery(deliveryIds.contains(team.getId()));
                rtn.add(teamVo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return rtn;
    }

    private Object publish(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        TeamDto talentDto = new TeamDto();
        talentDto.setUserInfoId(Integer.valueOf(parameterMap.get("userInfoId")[0]));
        talentDto.setTeamName(parameterMap.get("teamName")[0]);
        talentDto.setPost(parameterMap.get("post")[0]);
        talentDto.setType(parameterMap.get("type")[0]);
        talentDto.setSalary(parameterMap.get("salary")[0]);
        talentDto.setBriefIntro(parameterMap.get("briefIntro")[0]);
        talentDto.setRequirement(parameterMap.get("requirement")[0]);
        talentDto.setTel(parameterMap.get("tel")[0]);
        talentDto.setPlace(parameterMap.get("place")[0]);
        new TeamDao().save(talentDto);
        return true;
    }

    private Object delivery(HttpServletRequest request) {

        Integer teamId = Integer.valueOf(request.getParameter("teamId"));// 团队id
        Integer userInfoId = Integer.valueOf(request.getParameter("userInfoId"));// 投递者用户id
        Talent talent = new TalentDao().query(userInfoId);// 根据id查询人才
        if (talent == null) {
            System.out.println("必须完善资料才能投递");
            return 1;
        }
        Team team = new TeamDao().query(teamId);// 根据id查询团队
        if (userInfoId.equals(team.getUserInfoId())) {
            System.out.println("不能投递自己的团队");
            return 2;
        }
        DeliveryRelationship deliveryRelationship = new DeliveryRelationshipDao()
                .queryByTalentIdAndTeamId(userInfoId, teamId);
        if (deliveryRelationship != null) {
            System.out.println("您已经投递,无需重复投递");
            return 4;
        }
        Boolean sendResult = SendMsgUtil.send(team.getTel(), "137774");
        System.out.println("短信发送完成,结果=" + sendResult);
        // 保存投递记录表
        new DeliveryRelationshipDao().save(userInfoId, talent.getId(), team.getId(), team.getUserInfoId());
        return 0;
    }
}
