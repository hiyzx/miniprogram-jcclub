package org.jimei.jcclub.servlet;

import org.jimei.jcclub.dao.ArticleDao;
import org.jimei.jcclub.model.po.Article;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author yezhaoxing
 * @since 2019/02/19
 */
@WebServlet("/article")
public class ArticleServlet extends BaseServletFactory {

    @Override
    protected List<Article> dataModel(HttpServletRequest request, HttpServletResponse response) {
        String title = request.getParameter("title");
        ArticleDao articleDao = new ArticleDao();
        return articleDao.getDataList(title);
    }
}
