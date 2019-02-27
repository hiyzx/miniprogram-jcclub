package org.jimei.jcclub.servlet;


import com.alibaba.fastjson.JSON;
import org.jimei.jcclub.model.vo.BaseReturnVo;
import org.jimei.jcclub.model.vo.ReturnVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public abstract class BaseServletFactory extends HttpServlet {

    /**
     *
     */
    protected static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        BaseReturnVo rtn;
        try {
            Map<String, String[]> parameterMap = request.getParameterMap();
            System.out.println("请求参数为: " + JSON.toJSONString(parameterMap));
            Object object = dataModel(request, response);
            rtn = ReturnVo.success(object);
        } catch (Exception ex) {
            ex.printStackTrace();
            rtn = BaseReturnVo.fail();
        }
        PrintWriter pw = response.getWriter();
        String json = JSON.toJSONString(rtn);
        pw.print(json);
        System.out.println("返回的数据 :" + rtn.getResCode());
        pw.close();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected abstract Object dataModel(HttpServletRequest request, HttpServletResponse response);

}
