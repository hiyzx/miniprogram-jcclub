package org.jimei.jcclub.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 监听上下文加载配置
 */
@WebListener
public class ListenerLoadConfig implements ServletContextListener {


    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        String path = event.getServletContext().getRealPath("/WEB-INF/config/dbconfig.properties");
        LoadDBconfig.load(path);
    }
}
