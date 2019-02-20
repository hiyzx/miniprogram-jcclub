package org.jimei.jcclub.servlet;

import com.alibaba.fastjson.JSONObject;
import org.jimei.jcclub.dao.UserInfoDao;
import org.jimei.jcclub.model.dto.UserInfoDto;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yezhaoxing
 * @since 2019/02/19
 */
@WebServlet("/personal/auth")
public class PersonalSaveServlet extends BaseServletFactory {

    @Override
    protected Boolean dataModel(HttpServletRequest request, HttpServletResponse response) {
        String userInfo = request.getParameter("userInfo");
        UserInfoDto userInfoDto = JSONObject.parseObject(userInfo, UserInfoDto.class);
        if(new UserInfoDao().auth(userInfoDto)){
            return true;
        }else{
            throw new RuntimeException("保存个人信息异常");
        }
    }
}
