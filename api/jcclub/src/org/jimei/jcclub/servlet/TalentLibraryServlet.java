package org.jimei.jcclub.servlet;

import org.jimei.jcclub.dao.TalentDao;
import org.jimei.jcclub.model.dto.TalentDto;
import org.jimei.jcclub.model.po.Talent;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author yezhaoxing
 * @since 2019/02/19
 */
@WebServlet("/talentLibrary")
public class TalentLibraryServlet extends BaseServletFactory {

    @Override
    protected Object dataModel(HttpServletRequest request, HttpServletResponse response) {
        String actionName = request.getParameter("actionName");
        if ("list".equals(actionName)) {
            System.out.println("查询人才库列表页");
            return this.list(request);
        } else if ("query".equals(actionName)) {
            System.out.println("查询单个人才信息");
            return this.query(request);
        } else if ("save".equals(actionName)) {
            System.out.println("保存个人人才信息");
            return this.save(request);
        } else if ("publish".equals(actionName)) {
            System.out.println("发布单个人才信息");
            return this.publish(request);
        } else {
            throw new RuntimeException("actionName不能为空");
        }
    }

    private Object list(HttpServletRequest request) {
        return new TalentDao().list();
    }

    private Object query(HttpServletRequest request) {
        Integer userInfoId = Integer.valueOf(request.getParameter("userInfoId"));
        Talent talent = new TalentDao().query(userInfoId);
        if (talent == null) {
            talent = new Talent();

        }
        return talent;
    }

    private Boolean save(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        TalentDto talentDto = new TalentDto();
        talentDto.setUserInfoId(Integer.valueOf(parameterMap.get("userInfoId")[0]));
        talentDto.setName(parameterMap.get("name")[0]);
        talentDto.setTel(parameterMap.get("tel")[0]);
        talentDto.setClassName(parameterMap.get("className")[0]);
        talentDto.setIdealPost(parameterMap.get("idealPost")[0]);
        talentDto.setType(parameterMap.get("type")[0]);
        talentDto.setWorkExperience(parameterMap.get("workExperience")[0]);
        talentDto.setCompetitionExperience(parameterMap.get("competitionExperience")[0]);
        new TalentDao().saveOrUpdate(talentDto);
        return true;
    }

    private Boolean publish(HttpServletRequest request) {
        String userInfoId = request.getParameter("userInfoId");
        String isPublish = request.getParameter("isPublish");
        new TalentDao().publish(userInfoId, isPublish);
        return true;
    }
}
