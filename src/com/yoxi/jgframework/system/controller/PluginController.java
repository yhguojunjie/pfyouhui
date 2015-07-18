package com.yoxi.jgframework.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yoxi.jgframework.annotation.AddOrUpdateAnnotation;
import com.yoxi.jgframework.annotation.DeleteAnnotation;
import com.yoxi.jgframework.common.hibernate.qbc.CriteriaQuery;
import com.yoxi.jgframework.common.model.json.AjaxJson;
import com.yoxi.jgframework.common.model.json.DataGrid;
import com.yoxi.jgframework.system.entity.TSPlugin;
import com.yoxi.jgframework.ui.tag.core.easyui.TagUtil;
import com.yoxi.jgframework.util.ReadUpdaterProperties;
import com.yoxi.jgframework.util.StringUtil;
import com.yoxi.jgframework.util.oConvertUtils;


/**
 * 插件下载管理类
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/pluginController")
public class PluginController extends BaseServiceController {
	
	/************************************** 版本维护 ************************************/

	/**
	 * 客户端插件列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "plugin")
	public ModelAndView plugin() {
		return new ModelAndView("system/plugin/pluginList");
	}
	
	/**
	 * 获取客户端插件维护Grid数据
	 */
	@RequestMapping(params = "pluginList")
	public void pluginList(TSPlugin aTSPlugin,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSPlugin.class, dataGrid);
		
		
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, aTSPlugin);
		this.systemService.getDataGridReturn(cq, true);
		for (Object obj : dataGrid.getReaults()) {
			if (obj != null && obj instanceof TSPlugin) {
				TSPlugin theTSPlugin = ((TSPlugin) obj);
				theTSPlugin.setPluginIconUrl(ReadUpdaterProperties
						.getParaValue("plugin.image.path")
						+ theTSPlugin.getPluginIconUrl());
			}
		}
		TagUtil.datagrid(response, dataGrid);
	}
	


	/**
	 * 删除客户端插件
	 */
	@RequestMapping(params = "delPlugin")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson delPlugin(TSPlugin plugin, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		plugin = systemService.getEntity(TSPlugin.class, plugin.getId());
		message = "客户端插件：" + plugin.getPluginName() + "被删除成功";
		systemService.delete(plugin);
		//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);

		return j;
	}

	/**
	 * 客户端插件添加、修改页面跳转
	 * 
	 * @param icon
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "aouPlugin")
	public ModelAndView aouPlugin(TSPlugin plugin,HttpServletRequest req) {
		if (StringUtil.isNotEmpty(plugin.getId())) {
			plugin = systemService.getEntity(TSPlugin.class, plugin.getId());
			req.setAttribute("plugin", plugin);
	    }
		return new ModelAndView("system/plugin/plugin");
	}

	/**
	 * 添加、修改客户端插件时，保存版本数据
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "savePlugin", method = RequestMethod.POST)
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson savePlugin(TSPlugin plugin,HttpServletRequest request) throws Exception {
		AjaxJson j = new AjaxJson();
		//AjaxJson j = new AjaxJson();
			
		String id =oConvertUtils.getString(request.getParameter("id"));
		if (StringUtil.isNotEmpty(id)) {
			message = "客户端插件: " + plugin.getPluginName() + "被更新成功";
			this.isNew = false;
			userService.saveOrUpdate(plugin);
			//systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "客户端插件: " + plugin.getPluginName() + "被添加成功";
			this.isNew = true;
			userService.save(plugin);
			//systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}
	
	
}
