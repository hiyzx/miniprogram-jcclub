package org.jimei.jcclub.servlet;

import org.jimei.jcclub.dao.DeliveryRelationshipDao;
import org.jimei.jcclub.dao.TalentDao;
import org.jimei.jcclub.dao.TeamDao;
import org.jimei.jcclub.dao.UserInfoDao;
import org.jimei.jcclub.model.dto.UserInfoDto;
import org.jimei.jcclub.model.po.DeliveryRelationship;
import org.jimei.jcclub.model.po.Talent;
import org.jimei.jcclub.model.vo.TeamVo;
import org.jimei.jcclub.model.vo.UserInfoVo;
import org.jimei.jcclub.utils.WxUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author yexiaoling
 * @since 2019/02/19
 */
@WebServlet("/personal")
public class PersonalServlet extends BaseServletFactory {

    @Override
    protected Object handle(HttpServletRequest request, HttpServletResponse response) {
        String actionName = request.getParameter("actionName");
        if ("openid".equals(actionName)) {
            System.out.println("获取微信openid");
            return this.getOpenid(request);
        } else if ("auth".equals(actionName)) {
            System.out.println("更新用户微信资料");
            return this.auth(request);
        } else if ("myDelivery".equals(actionName)) {
            System.out.println("查询我的投递记录");
            return this.myDeliveryList(request);
        } else if ("otherDelivery".equals(actionName)) {
            System.out.println("查询我的团队被投递记录");
            return this.otherDeliveryList(request);
        } else if ("myTeam".equals(actionName)) {
            System.out.println("查询我发布的团队招聘信息");
            return this.myTeam(request);
        } else if ("publishMyPost".equals(actionName)) {
            System.out.println("发布/取消发布团队信息");
            return this.publishMyPost(request);
        } else if ("queryPost".equals(actionName)) {
            System.out.println("查询已发布的团队信息");
            return this.queryPost(request);
        } else if ("approval".equals(actionName)) {
            System.out.println("审批团队信息");
            return this.approval(request);
        } else {
            throw new RuntimeException("actionName不能为空");
        }
    }

    // 获取微信openid
    private String getOpenid(HttpServletRequest request) {
        String code = request.getParameter("code");
        return WxUtil.getOpenid(code);
    }

    // 更新用户微信资料
    private UserInfoVo auth(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        UserInfoDto userInfo = new UserInfoDto();
        userInfo.setAvatarUrl(parameterMap.get("avatarUrl")[0]);
        userInfo.setNickName(parameterMap.get("nickName")[0]);
        userInfo.setGender(Integer.valueOf(parameterMap.get("gender")[0]));
        userInfo.setOpenid(parameterMap.get("openid")[0]);
        return  new UserInfoDao().auth(userInfo);
    }

    // 查询我的投递记录
    private List<TeamVo> myDeliveryList(HttpServletRequest request) {
        Integer userInfoId = Integer.valueOf(request.getParameter("userInfoId"));
        List<DeliveryRelationship> deliveryRelationships = new DeliveryRelationshipDao()
                .myDeliveryRelationshipList(userInfoId);
        if (deliveryRelationships.isEmpty()) {
            System.out.println("投递记录表为空");
            return Collections.emptyList();
        } else {
            List<TeamVo> teams = new ArrayList<>(deliveryRelationships.size());
            deliveryRelationships.forEach(deliveryRelationship -> {
                TeamVo team = new TeamDao().queryByPostId(deliveryRelationship.getPostId());
                teams.add(team);
            });
            return teams;
        }
    }

    // 查询我的团队被投递记录
    private Object otherDeliveryList(HttpServletRequest request) {
        Integer userInfoId = Integer.valueOf(request.getParameter("userInfoId"));
        List<DeliveryRelationship> deliveryRelationships = new DeliveryRelationshipDao()
                .otherDeliveryRelationshipList(userInfoId);
        if (deliveryRelationships.isEmpty()) {
            System.out.println("投递记录表为空");
            return Collections.emptyList();
        } else {
            List<Talent> talents = new ArrayList<>(deliveryRelationships.size());
            deliveryRelationships.forEach(deliveryRelationship -> {
                Talent talent = new TalentDao().queryById(deliveryRelationship.getTalentId());
                talents.add(talent);
            });
            return talents;
        }
    }

    // 查询我的团队信息
    private Object myTeam(HttpServletRequest request) {
        Integer userInfoId = Integer.valueOf(request.getParameter("userInfoId"));
        return new TeamDao().listByUserId(userInfoId);
    }

    // 审批团队信息
    private Object approval(HttpServletRequest request) {
        Integer postId = Integer.valueOf(request.getParameter("postId"));
        Integer isPublish = Integer.valueOf(request.getParameter("approvalStatus"));
        String remark = request.getParameter("remark");
        new TeamDao().approval(postId, isPublish, remark);
        return true;
    }

    // 发布/取消发布团队信息
    private Object publishMyPost(HttpServletRequest request) {
        Integer teamId = Integer.valueOf(request.getParameter("teamId"));
        Integer isPublish = Integer.valueOf(request.getParameter("isPublish"));
        new TeamDao().publish(teamId, isPublish);
        return true;
    }

    // 查询所有已发布的岗位
    private List<TeamVo> queryPost(HttpServletRequest request){
        return new TeamDao().list(1);
    }


}