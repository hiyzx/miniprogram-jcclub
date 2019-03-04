package org.jimei.jcclub.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author yezhaoxing
 * @date 2019/3/3
 * @description 监听上下文加载配置, 用于服务器启动的时候, 加载配置文件
 */
@WebListener
public class ListenerLoadConfig implements ServletContextListener {


    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        String path = event.getServletContext().getRealPath("/WEB-INF/config/dbconfig.properties");
        DBUtil.load(path);
    }
}
