package org.jimei.jcclub.servlet;

import org.jimei.jcclub.dao.ArticleDao;
import org.jimei.jcclub.dao.CarouselMapDao;
import org.jimei.jcclub.model.po.CarouselMap;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yezhaoxing
 * @since 2019/02/19
 */
@WebServlet("/index")
public class IndexServlet extends BaseServletFactory {

    @Override
    protected Object dataModel(HttpServletRequest request, HttpServletResponse response) {
        String actionName = request.getParameter("actionName");
        if ("carouselMap".equals(actionName)) {
            System.out.println("查询首页轮播图地址");
            return this.carouselMap();
        } else if ("article".equals(actionName)) {
            System.out.println("查询首页公众号信息");
            return this.article();
        } else {
            throw new RuntimeException("actionName不能为空");
        }
    }

    private Object article() {
        ArticleDao articleDao = new ArticleDao();
        return articleDao.getDataList();
    }

    private List<String> carouselMap() {
        List<CarouselMap> dataList = new CarouselMapDao().getDataList();
        return dataList.stream().map(CarouselMap::getImageUrl).collect(Collectors.toList());
    }
}
