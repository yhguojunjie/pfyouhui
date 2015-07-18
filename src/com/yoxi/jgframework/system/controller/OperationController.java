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
import com.yoxi.jgframework.system.entity.TSOperation;
import com.yoxi.jgframework.ui.tag.core.easyui.TagUtil;
import com.yoxi.jgframework.util.StringUtil;
import com.yoxi.jgframework.util.oConvertUtils;


/**
 * 操作权限处理类
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/operationController")
public class OperationController extends BaseServiceController {
	
	
	/**
	 * 操作列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "operation")
	public ModelAndView operation(HttpServletRequest request,Integer functionId) {
		//----------------------------------------------------------------
		//update-start--Author:anchao  Date:20130415 for：按钮权限控制
		//----------------------------------------------------------------
		request.setAttribute("functionId", functionId);
		//----------------------------------------------------------------
		//update-end--Author:anchao  Date:20130415 for：按钮权限控制
		//----------------------------------------------------------------
		return new ModelAndView("system/operation/operationList");
	}


	/**
	 * easyuiAJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */
	@RequestMapping(params = "operationGrid")
	public void opdategrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSOperation.class, dataGrid);
		//----------------------------------------------------------------
		//update-start--Author:anchao  Date:20130415 for：按钮权限控制
		//----------------------------------------------------------------
		Integer functionId=oConvertUtils.getInt(request.getParameter("functionId"));
		cq.eq("TSFunction.id", functionId);
		cq.add(); 
		//----------------------------------------------------------------
		//update-end--Author:anchao  Date:20130415 for：按钮权限控制
		//----------------------------------------------------------------
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除操作权限
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "delOperation")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson delOperation(TSOperation operation, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		operation = systemService.getEntity(TSOperation.class, operation.getId());
		message = "操作: " + operation.getOperationName() + "被删除成功";
		userService.delete(operation);
		//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		return j;
	}
	

	/**
	 * 操作录入
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveOperation")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson saveOperation(TSOperation operation, HttpServletRequest request) {
		String pid = request.getParameter("TSFunction.id");
		if (pid.equals("")) {
			operation.setTSFunction(null);
		}
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(operation.getId())) {
			message = "操作: " + operation.getOperationName() + "被更新成功";
			this.isNew = false;
			userService.saveOrUpdate(operation);
			//systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "操作: " + operation.getOperationName() + "被添加成功";
			this.isNew = true;
			userService.save(operation);
			//systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

		}
		
		return j;
	}

	
	/**
	 * 新建、修改操作权限页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdateOperation")
	public ModelAndView addorupdateOperation(TSOperation operation, HttpServletRequest req) {
//		List<TSIcon> iconlist = systemService.getList(TSIcon.class);
//		req.setAttribute("iconlist", iconlist);
		if (operation.getId() != null) {
			operation = systemService.getEntity(TSOperation.class, operation.getId());
			req.setAttribute("operation", operation);
		}
		//----------------------------------------------------------------
		//update-start--Author:anchao  Date:20130415 for：按钮权限控制
		//----------------------------------------------------------------
		Integer functionId=oConvertUtils.getInt(req.getParameter("functionId"));
		req.setAttribute("functionId", functionId);
		//----------------------------------------------------------------
		//update-end--Author:anchao  Date:20130415 for：按钮权限控制
		//----------------------------------------------------------------
		return new ModelAndView("system/operation/operation");
	}	
}
