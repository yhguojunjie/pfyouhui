package com.yoxi.jgframework.system.controller;




import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yoxi.jgframework.common.controller.BaseController;


/**
 * 数据库管理
 * 
 * @author songsw
 * 
 */
@Controller
@RequestMapping("/dataSourceController")
public class DataSourceController extends BaseController {
	
	/**
	 * 跳转到连接池监控页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "goDruid")
	public ModelAndView goDruid() {
		return new ModelAndView("/system/druid/index");
	}
		

}
