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
import com.yoxi.jgframework.system.entity.TSVersion;
import com.yoxi.jgframework.ui.tag.core.easyui.TagUtil;
import com.yoxi.jgframework.util.StringUtil;
import com.yoxi.jgframework.util.oConvertUtils;


/**
 * 版本管理类
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/versionController")
public class VersionController extends BaseServiceController {
	
	/************************************** 版本维护 ************************************/

	/**
	 * 版本列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "version")
	public ModelAndView version() {
		return new ModelAndView("system/version/versionList");
	}
	
	/**
	 * 获取版本维护Grid数据
	 */
	@RequestMapping(params = "versionList")
	public void versionList(TSVersion aTSVersion,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSVersion.class, dataGrid);
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, aTSVersion);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	


	/**
	 * 删除版本操作
	 */
	@RequestMapping(params = "delVersion")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson delVersion(TSVersion version, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		version = systemService.getEntity(TSVersion.class, version.getId());
		message = "版本：" + version.getVersionUrl() + "被删除成功";
		systemService.delete(version);
		//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);

		return j;
	}

	/**
	 * 版本添加、修改页面跳转
	 * 
	 * @param icon
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "aouVersion")
	public ModelAndView aouVersion(TSVersion version,HttpServletRequest req) {
		if (StringUtil.isNotEmpty(version.getId())) {
			version = systemService.getEntity(TSVersion.class, version.getId());
			req.setAttribute("version", version);
	    }
		return new ModelAndView("system/version/version");
	}

	/**
	 * 添加、修改版本时，保存版本数据
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "saveVersion", method = RequestMethod.POST)
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson saveVersion(TSVersion version,HttpServletRequest request) throws Exception {
		AjaxJson j = new AjaxJson();
		//AjaxJson j = new AjaxJson();
		//由于checkbox的未选中时 checked的值是null，因此如果null则为false
		if (version.getIsForce() == null)
			version.setIsForce(false);
		if (version.getPublishStatus() == null)
			version.setPublishStatus(false);
		
		String id =oConvertUtils.getString(request.getParameter("id"));
		if (StringUtil.isNotEmpty(id)) {
			message = "版本信息: " + version.getVersionCode() + "被更新成功";
			this.isNew = false;
			userService.saveOrUpdate(version);
			//systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "版本信息: " + version.getVersionCode() + "被添加成功";
			this.isNew = true;
			userService.save(version);
			//systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}
	
	
}
