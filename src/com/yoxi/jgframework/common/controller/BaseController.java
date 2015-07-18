package com.yoxi.jgframework.common.controller;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yoxi.jgframework.interceptors.DateConvertEditor;
import com.yoxi.jgframework.system.controller.BaseServiceController;
import com.yoxi.jgframework.system.entity.TSUser;
import com.yoxi.jgframework.system.service.SystemService;
import com.yoxi.jgframework.util.ResourceUtil;


/**
 * 基础控制器，其他控制器需集成此控制器获得initBinder自动转换的功能
 * 
 * 
 */
@Controller
@RequestMapping("/baseController")
public class BaseController {
	
	
	//操作信息，或错误信息
	protected String message;
	//当页面保存数据时判断是新增还是修改，如果true则新增
	protected Boolean isNew;
	//系统服务
	protected SystemService systemService;
	//获取用户信息
	public TSUser getUser(){
		return ResourceUtil.getSessionUserName();
	}
	
	public SystemService getSystemService() {
		return this.systemService;
	}

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public Boolean isNew() {
		return isNew;
	}

	/**
	 * 将前台传递过来的日期格式的字符串，自动转化为Date类型
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat(
//				"yyyy-MM-dd hh:mm:ss");
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(
//				dateFormat, true));
		
		//-----update-begin---- author:zhangdaihao  date:20130227 for:时间转换问题
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
		//-----update-end---- author:zhangdaihao  date:20130227 for:时间转换问题
	}

}
