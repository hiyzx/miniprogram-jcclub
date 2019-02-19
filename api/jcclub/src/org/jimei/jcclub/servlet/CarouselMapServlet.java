package org.jimei.jcclub.servlet;

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
@WebServlet("/carousel")
public class CarouselMapServlet extends BaseServletFactory {

    @Override
    protected List<String> dataModel(HttpServletRequest request, HttpServletResponse response) {
        List<CarouselMap> dataList = new CarouselMapDao().getDataList();
        return dataList.stream().map(CarouselMap::getImageUrl).collect(Collectors.toList());
    }
}
