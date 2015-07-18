package com.yoxi.jgframework.system.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yoxi.jgframework.annotation.AddOrUpdateAnnotation;
import com.yoxi.jgframework.annotation.DeleteAnnotation;
import com.yoxi.jgframework.common.hibernate.qbc.CriteriaQuery;
import com.yoxi.jgframework.common.model.json.AjaxJson;
import com.yoxi.jgframework.common.model.json.ComboTree;
import com.yoxi.jgframework.common.model.json.TreeGrid;
import com.yoxi.jgframework.constant.Globals;
import com.yoxi.jgframework.system.entity.TSFunction;
import com.yoxi.jgframework.system.entity.TSRoleFunction;
import com.yoxi.jgframework.ui.tag.vo.datatable.SortDirection;
import com.yoxi.jgframework.ui.tag.vo.easyui.ComboTreeModel;
import com.yoxi.jgframework.ui.tag.vo.easyui.TreeGridModel;
import com.yoxi.jgframework.util.StringUtil;
import com.yoxi.jgframework.util.oConvertUtils;


/**
 * 菜单权限处理类
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/functionController")
public class FunctionController extends BaseServiceController {
	
	/**
	 * 权限列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "function")
	public ModelAndView function() {
		return new ModelAndView("system/function/functionList");
	}	

//	/**
//	 * easyuiAJAX请求数据,获取菜单Grid数据
//	 * 
//	 * @param request
//	 * @param response
//	 * @param dataGrid
//	 * @param user
//	 */
//	@RequestMapping(params = "datagrid")
//	public void datagrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
//		CriteriaQuery cq = new CriteriaQuery(TSFunction.class, dataGrid);
//		this.systemService.getDataGridReturn(cq, true);
//		TagUtil.datagrid(response, dataGrid);
//	}

	
	/**
	 * 删除菜单操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "delFunction")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson delFunction(TSFunction function, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		function = systemService.getEntity(TSFunction.class, function.getId());
		message = "权限: " + function.getFunctionName() + "被删除成功";
		// 删除权限时先删除权限与角色之间关联表信息
		List<TSRoleFunction> roleFunctions = systemService.findByProperty(TSRoleFunction.class, "TSFunction.id", function.getId());
		
		if (roleFunctions.size() > 0) {
			j.setMsg("菜单已分配无法删除");
			
		}
		else {
			userService.delete(function);
			//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}
		
		
		return j;
	}



	/**
	 * 新建、修改菜单时、保存数据操作
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveFunction")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson saveFunction(TSFunction function, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		//----------------------------------------------------------------
		//update-end--Author:wangyang  Date:20130402 for：添加排序
		//----------------------------------------------------------------
		String functionOrder = function.getFunctionOrder();
		if(StringUtils.isEmpty(functionOrder)){
			function.setFunctionOrder("0");
		}
		//----------------------------------------------------------------
		//update-end--Author:wangyang  Date:20130402 for：添加排序
		//----------------------------------------------------------------
		if(function.getTSFunction() != null){
			if (function.getTSFunction().getId() == null || "".equals(function.getTSFunction().getId())) {
				function.setTSFunction(null);
			}
		}

		if (StringUtil.isNotEmpty(function.getId())) {
			message = "权限: " + function.getFunctionName() + "被更新成功";
			this.isNew = false;
			userService.saveOrUpdate(function);
			//systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			
			//----------------------------------------------------------------
			//update-end--Author:liangly  Date:20130514 for：如果图标改变了，刷新session
			//----------------------------------------------------------------			
			systemService.flushRoleFunciton(function.getId(), function);			
			//----------------------------------------------------------------
			//update-end--Author:liangly  Date:20130514 for：添加排序
			//----------------------------------------------------------------
			
		} else {
			if(function.getFunctionLevel().equals(Globals.Function_Leave_ONE))
			{
				//List<TSFunction> functionList=systemService.findByProperty(TSFunction.class,"functionLevel",Globals.Function_Leave_ONE);
				//int ordre=functionList.size()+1;
				//function.setFunctionOrder(Globals.Function_Order_ONE+ordre);
				function.setFunctionOrder(function.getFunctionOrder());
			}
			else {
				//List<TSFunction> functionList=systemService.findByProperty(TSFunction.class,"functionLevel",Globals.Function_Leave_TWO);
				//int ordre=functionList.size()+1;
				//function.setFunctionOrder(Globals.Function_Order_TWO+ordre);
				function.setFunctionOrder(function.getFunctionOrder());
			}
			message = "权限: " + function.getFunctionName() + "被添加成功";
			this.isNew = true;
			userService.save(function);
			//systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);

		}
		j.setMsg(message);
		
		return j;
	}

	

	/**
	 * 新建、修改菜单页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdateFunction")
	public ModelAndView addorupdateFunction(TSFunction function, HttpServletRequest req) {
		Integer functionid = oConvertUtils.getInt(req.getParameter("id"));
		List<TSFunction> fuinctionlist = systemService.getList(TSFunction.class);
		req.setAttribute("flist", fuinctionlist);
		
		if (functionid != null) {
			function = systemService.getEntity(TSFunction.class, functionid);
			req.setAttribute("function", function);
		}
		return new ModelAndView("system/function/function");
	}

	/**
	 * 获取菜单树形Grid数据
	 */
	@RequestMapping(params = "functionTreeGrid")
	@ResponseBody
	public List<TreeGrid> functionTreeGrid(TSFunction aTSFunction,
			HttpServletRequest request, TreeGrid treegrid) {
		CriteriaQuery cq = new CriteriaQuery(TSFunction.class);
		
		if(oConvertUtils.isEmpty(aTSFunction.getFunctionName()) == false){
			com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, aTSFunction);
		}
		
		if (treegrid.getId() != null) {
			cq.eq("TSFunction.id", oConvertUtils.getInt(treegrid.getId()));
		}
		if (treegrid.getId() == null) {
			cq.isNull("TSFunction");
		}
		//----------------------------------------------------------------
		//update-begin--Author:wangyang  Date:20130402 for：添加排序
		//----------------------------------------------------------------
		cq.addOrder("functionOrder", SortDirection.asc);
		//----------------------------------------------------------------
		//update-end--Author:wangyang  Date:20130402 for：添加排序
		//----------------------------------------------------------------
		cq.add();
		List<TSFunction> functionList = systemService.getListByCriteriaQuery(cq, false);
		List<TreeGrid> treeGrids = new ArrayList<TreeGrid>();
		TreeGridModel treeGridModel = new TreeGridModel();
		treeGridModel.setIcon("TSIcon_iconPath");
		treeGridModel.setTextField("functionName");
		treeGridModel.setParentText("TSFunction_functionName");
		treeGridModel.setParentId("TSFunction_id");
		treeGridModel.setSrc("functionUrl");
		treeGridModel.setIdField("id");
		treeGridModel.setChildList("TSFunctions");
		//添加排序字段
		treeGridModel.setOrder("functionOrder");
		treeGrids = systemService.treegrid(functionList, treeGridModel);
		return treeGrids;
	}

	/**
	 * 权限列表
	 */
//	@RequestMapping(params = "functionList")
//	@ResponseBody
//	public void functionList(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
//		CriteriaQuery cq = new CriteriaQuery(TSFunction.class, dataGrid);
//		String id = oConvertUtils.getString(request.getParameter("id"));
//		cq.isNull("TSFunction");
//		if (id != null) {
//			cq.eq("TSFunction.id", id);
//		}
//		cq.add();
//		List<TSFunction> functionList = systemService.getListByCriteriaQuery(cq, false);
//		List<TreeGrid> treeGrids = new ArrayList<TreeGrid>();
//		this.systemService.getDataGridReturn(cq, true);
//		TagUtil.datagrid(response, dataGrid);
//	}

	/**
	 * 获取菜单树形下拉数据
	 */
	@RequestMapping(params = "getFunctionTree")
	@ResponseBody
	public List<ComboTree> getFunctionTree(HttpServletRequest request, ComboTree comboTree) {
		CriteriaQuery cq = new CriteriaQuery(TSFunction.class);
		if (comboTree.getId() != null) {
			cq.eq("TSFunction.id", oConvertUtils.getInt(comboTree.getId()));
		}
		if (comboTree.getId() == null) {
			cq.isNull("TSFunction");
		}
		cq.add();
		List<TSFunction> functionList = systemService.getListByCriteriaQuery(cq, false);
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		ComboTreeModel comboTreeModel = new ComboTreeModel("id", "functionName", "TSFunctions");
		comboTrees = systemService.ComboTree(functionList, comboTreeModel, null);
		return comboTrees;
	}
}
