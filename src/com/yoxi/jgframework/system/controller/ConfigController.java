package com.yoxi.jgframework.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yoxi.jgframework.annotation.AddOrUpdateAnnotation;
import com.yoxi.jgframework.annotation.DeleteAnnotation;
import com.yoxi.jgframework.common.hibernate.qbc.CriteriaQuery;
import com.yoxi.jgframework.common.model.json.AjaxJson;
import com.yoxi.jgframework.common.model.json.DataGrid;
import com.yoxi.jgframework.system.entity.TSConfig;
import com.yoxi.jgframework.ui.tag.core.easyui.TagUtil;
import com.yoxi.jgframework.util.ResourceUtil;
import com.yoxi.jgframework.util.StringUtil;


/**
 * 配置信息处理类
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/configController")
public class ConfigController extends BaseServiceController {
	

	/**
	 * 配置列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "config")
	public ModelAndView config() {
		return new ModelAndView("system/config/configList");
	}

	/**
	 * easyuiAjax表单请求,获取配置列表Grid数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "configGrid")
	public void configGrid(TSConfig aTSConfig,HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSConfig.class, dataGrid);
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, aTSConfig);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除配置信息
	 * 
	 * @param config
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "delConfig")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson delConfig(TSConfig config, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		config = systemService.getEntity(TSConfig.class, config.getId());
		message = "配置信息: " + config.getName() + "被删除成功";
		systemService.delete(config);
//		systemService.addLog(message, Globals.Log_Type_DEL,
//				Globals.Log_Leavel_INFO);
		
		return j;
	}

	/**
	 * 添加和更新配置信息时，保存数据库操作
	 * 
	 * @param config
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "saveConfig")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson saveConfig(TSConfig tsConfig,HttpServletRequest request) {
		if (StringUtil.isEmpty(tsConfig.getId())) {
			TSConfig tsConfig2=systemService.findUniqueByProperty(TSConfig.class, "code", tsConfig.getCode());
			if(tsConfig2!=null){
				this.isNew = null; //不保存日志
				message = "编码为: " + tsConfig.getCode() + "的配置信息已存在";
			}else{
				this.isNew = true;//保存新建日志
				tsConfig.setTSUser(ResourceUtil.getSessionUserName());
				systemService.save(tsConfig);
				message = "配置信息: " + tsConfig.getName() + "被添加成功";
				//systemService.addLog(message, Globals.Log_Type_INSERT,Globals.Log_Leavel_INFO);
			}
			
		}else{
			this.isNew = false;//保存修改日志
			message = "配置信息: " + tsConfig.getName() + "被修改成功";
			systemService.updateEntitie(tsConfig);
			//systemService.addLog(message, Globals.Log_Type_INSERT,Globals.Log_Leavel_INFO);
		}
		AjaxJson j = new AjaxJson();
		j.setMsg(message);
		
		return j;
	}

	/**
	 * 添加和更新配置信息页面
	 * 
	 * @param config
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "addorupdateConfig")
	public ModelAndView addorupdateConfig(TSConfig config, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(config.getId())) {
			config = systemService.getEntity(TSConfig.class,
					config.getId());
			req.setAttribute("config", config);
		}
		return new ModelAndView("system/config/config");
	}

}
