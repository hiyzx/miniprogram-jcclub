package org.jimei.jcclub.servlet;

import org.jimei.jcclub.dao.DeliveryRelationshipDao;
import org.jimei.jcclub.dao.TalentDao;
import org.jimei.jcclub.dao.TeamDao;
import org.jimei.jcclub.dao.UserInfoDao;
import org.jimei.jcclub.model.dto.UserInfoDto;
import org.jimei.jcclub.model.po.DeliveryRelationship;
import org.jimei.jcclub.model.po.Talent;
import org.jimei.jcclub.model.po.Team;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author yezhaoxing
 * @since 2019/02/19
 */
@WebServlet("/personal")
public class PersonalServlet extends BaseServletFactory {

    @Override
    protected Object handle(HttpServletRequest request, HttpServletResponse response) {
        String actionName = request.getParameter("actionName");
        if ("auth".equals(actionName)) {
            System.out.println("更新用户微信资料");
            return this.auth(request);
        } else if ("myDelivery".equals(actionName)) {
            System.out.println("查询我的投递记录");
            return this.myDeliveryList(request);
        } else if ("otherDelivery".equals(actionName)) {
            System.out.println("查询我的团队被投递记录");
            return this.otherDeliveryList(request);
        } else {
            throw new RuntimeException("actionName不能为空");
        }
    }

    // 更新用户微信资料
    private Integer auth(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        UserInfoDto userInfo = new UserInfoDto();
        userInfo.setAvatarUrl(parameterMap.get("avatarUrl")[0]);
        userInfo.setNickName(parameterMap.get("nickName")[0]);
        userInfo.setGender(Integer.valueOf(parameterMap.get("gender")[0]));
        return new UserInfoDao().auth(userInfo);
    }

    // 查询我的投递记录
    private List<Team> myDeliveryList(HttpServletRequest request) {
        Integer userInfoId = Integer.valueOf(request.getParameter("userInfoId"));
        List<DeliveryRelationship> deliveryRelationships = new DeliveryRelationshipDao()
                .myDeliveryRelationshipList(userInfoId);
        if (deliveryRelationships.isEmpty()) {
            System.out.println("投递记录表为空");
            return Collections.emptyList();
        } else {
            List<Team> teams = new ArrayList<>(deliveryRelationships.size());
            deliveryRelationships.forEach(deliveryRelationship -> {
                Team team = new TeamDao().query(deliveryRelationship.getTeamId());
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
}
