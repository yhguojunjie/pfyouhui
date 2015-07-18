package com.yoxi.jgframework.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yoxi.jgframework.common.controller.BaseController;
import com.yoxi.jgframework.system.service.UserService;


/**
 * 类型字段处理类
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/baseServiceController")
public class BaseServiceController extends BaseController {
	
	
	protected UserService userService;
//	protected SystemService systemService;
//	
//	
//	public SystemService getSystemService() {
//		return this.systemService;
//	}
//
//	@Autowired
//	public void setSystemService(SystemService systemService) {
//		this.systemService = systemService;
//	}
	

	public UserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}	
}
