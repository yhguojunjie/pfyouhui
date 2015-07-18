package com.yoxi.jgframework.system.listener;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.yoxi.jgframework.system.service.SystemService;

/**
 * 系统初始化监听器,在系统启动时运行,进行一些初始化工作
 * 
 * @author laien
 * 
 */
public class InitListener implements javax.servlet.ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0){

	}

	@Override
	public void contextInitialized(ServletContextEvent event){
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(event
						.getServletContext());
		SystemService systemService = (SystemService) webApplicationContext.getBean("systemService");
		// 对数据字典进行缓存
		systemService.initAllTypeGroups();

		// 将配置表加入到web应用缓存
		systemService.initAllConfig();
	}

}
