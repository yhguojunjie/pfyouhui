package com.yoxi.jgframework.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yoxi.jgframework.annotation.AddOrUpdateAnnotation;
import com.yoxi.jgframework.annotation.DeleteAnnotation;
import com.yoxi.jgframework.annotation.OtherAnnotation;
import com.yoxi.jgframework.common.hibernate.qbc.CriteriaQuery;
import com.yoxi.jgframework.common.model.json.AjaxJson;
import com.yoxi.jgframework.common.model.json.ComboTree;
import com.yoxi.jgframework.common.model.json.DataGrid;
import com.yoxi.jgframework.common.model.json.ValidForm;
import com.yoxi.jgframework.system.entity.TSFunction;
import com.yoxi.jgframework.system.entity.TSOperation;
import com.yoxi.jgframework.system.entity.TSRole;
import com.yoxi.jgframework.system.entity.TSRoleFunction;
import com.yoxi.jgframework.system.entity.TSRoleUser;
import com.yoxi.jgframework.system.entity.TSUser;
import com.yoxi.jgframework.ui.tag.core.easyui.TagUtil;
import com.yoxi.jgframework.ui.tag.vo.easyui.ComboTreeModel;
import com.yoxi.jgframework.util.ExceptionUtil;
import com.yoxi.jgframework.util.ResourceUtil;
import com.yoxi.jgframework.util.StringUtil;
import com.yoxi.jgframework.util.oConvertUtils;

/**
 * 角色处理类
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/roleController")
public class RoleController extends BaseServiceController {

	private static final Logger logger = Logger.getLogger(RoleController.class);

	/**
	 * 角色列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "role")
	public ModelAndView role(){
		return new ModelAndView("system/role/roleList");
	}

	/**
	 * easyuiAJAX请求数据，获取角色Grid数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "roleGrid")
	public void roleGrid(TSRole role, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid){
		CriteriaQuery cq = new CriteriaQuery(TSRole.class, dataGrid);
		// update-begin--Author:zhaojunfu Date:20130411 for：加上条件查询
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, role);
		// cq.add();
		// update-end--Author:zhaojunfu Date:20130411 for：加上条件查询
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
		;
	}

	/**
	 * 删除角色
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "delRole")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson delRole(TSRole role, HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		// 删除角色之前先删除角色权限关系
		delRoleFunction(role);
		role = systemService.getEntity(TSRole.class, role.getId());
		userService.delete(role);
		message = "角色: " + role.getRoleName() + "被删除成功";
		// systemService.addLog(message, Globals.Log_Type_DEL,
		// Globals.Log_Leavel_INFO);

		return j;
	}

	/**
	 * 删除角色权限
	 * 
	 * @param role
	 */
	private void delRoleFunction(TSRole role){
		List<TSRoleFunction> roleFunctions = systemService.findByProperty(TSRoleFunction.class, "TSRole.id",
						role.getId());
		if (roleFunctions.size() > 0) {
			for (TSRoleFunction tsRoleFunction : roleFunctions) {
				systemService.delete(tsRoleFunction);
			}
		}
		List<TSRoleUser> roleUsers = systemService.findByProperty(TSRoleUser.class, "TSRole.id", role.getId());
		for (TSRoleUser tsRoleUser : roleUsers) {
			systemService.delete(tsRoleUser);
		}
	}

	/**
	 * 检查角色，判断角色编码是否存在
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "checkRole")
	@ResponseBody
	public ValidForm checkRole(TSRole role, HttpServletRequest request, HttpServletResponse response){
		ValidForm v = new ValidForm();
		String roleCode = oConvertUtils.getString(request.getParameter("param"));
		String code = oConvertUtils.getString(request.getParameter("code"));
		List<TSRole> roles = systemService.findByProperty(TSRole.class, "roleCode", roleCode);
		if (roles.size() > 0 && !code.equals(roleCode)) {
			v.setInfo("角色编码已存在");
			v.setStatus("n");
		}
		return v;
	}

	/**
	 * 角色新建、修改时，保存角色数据
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveRole")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson saveRole(TSRole role, HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(role.getId())) {
			message = "角色: " + role.getRoleName() + "被更新成功";
			this.isNew = false;
			userService.saveOrUpdate(role);
		} else {
			message = "角色: " + role.getRoleName() + "被添加成功";
			userService.save(role);
			this.isNew = true;
		}

		return j;
	}

	/**
	 * 角色权限设置跳转页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "roleFunctionSet")
	public ModelAndView roleFunctionSet(HttpServletRequest request){
		String roleId = request.getParameter("roleId");
		request.setAttribute("roleId", roleId);
		return new ModelAndView("system/role/roleFunctionSet");
	}

	/**
	 * 设置当前角色，对应的功能权限是否已经选择
	 * 
	 * @param role
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 * @return
	 */
	@RequestMapping(params = "setAuthority")
	@ResponseBody
	public List<ComboTree> setAuthority(TSRole role, HttpServletRequest request, ComboTree comboTree){
		CriteriaQuery cq = new CriteriaQuery(TSFunction.class);
		if (comboTree.getId() != null) {
			cq.eq("TSFunction.id", oConvertUtils.getInt(comboTree.getId()));
		}
		if (comboTree.getId() == null) {
			cq.isNull("TSFunction");
		}
		cq.notEq("functionLevel", Short.parseShort("-1"));
		cq.add();
		List<TSFunction> functionList = systemService.getListByCriteriaQuery(cq, false);
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		Integer roleId = oConvertUtils.getInt(request.getParameter("roleId"));
		List<TSFunction> loginActionlist = new ArrayList<TSFunction>();// 已有权限菜单
		role = this.systemService.get(TSRole.class, roleId);
		if (role != null) {
			List<TSRoleFunction> roleFunctionList = systemService.findByProperty(TSRoleFunction.class, "TSRole.id",
							role.getId());
			if (roleFunctionList.size() > 0) {
				for (TSRoleFunction roleFunction : roleFunctionList) {
					TSFunction function = (TSFunction) roleFunction.getTSFunction();
					loginActionlist.add(function);
				}
			}
		}
		ComboTreeModel comboTreeModel = new ComboTreeModel("id", "functionName", "TSFunctions");
		comboTrees = systemService.ComboTree(functionList, comboTreeModel, loginActionlist);
		return comboTrees;
	}

	/**
	 * 更新当前角色设置的权限信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "updateAuthority")
	@ResponseBody
	@OtherAnnotation
	public AjaxJson updateAuthority(HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		// ----------------------------------------------------------------
		// update--Author:宋双旺 Date:20130414 for：删除角色，点击保存权限报错
		String roleName = "";
		try {
			Integer roleId = oConvertUtils.getInt(request.getParameter("roleId"));
			String rolefunction = request.getParameter("rolefunctions");
			TSRole role = this.systemService.get(TSRole.class, roleId);
			roleName = role.getRoleName();
			List<TSRoleFunction> roleFunctionList = systemService.findByProperty(TSRoleFunction.class, "TSRole.id",
							role.getId());
			systemService.deleteAllEntitie(roleFunctionList);
			String[] roleFunctions = null;
			List<TSRoleFunction> entitys = new ArrayList<TSRoleFunction>();
			if (rolefunction != "") {
				roleFunctions = rolefunction.split(",");
				for (String s : roleFunctions) {
					TSRoleFunction rf = new TSRoleFunction();
					TSFunction f = this.systemService.get(TSFunction.class, oConvertUtils.getInt(s));
					rf.setTSFunction(f);
					rf.setTSRole(role);

					// update-begin--Author:anchao Date:20130421 for：按钮权限控制
					for (TSRoleFunction tsRoleFunction : roleFunctionList) {
						Integer fId = tsRoleFunction.getTSFunction().getId();
						if (fId.equals(oConvertUtils.getInt(s))) {
							rf.setOperation(tsRoleFunction.getOperation());
						}
					}
					// update-begin--Author:anchao Date:20130421 for：按钮权限控制

					// ----------------------------------------------------------------
					// update-begin--Author:lianglaiyang Date:20130413
					// for：性能优化，改成批量插入
					entitys.add(rf);
					// this.systemService.save(rf);
					// update-end--Author:lianglaiyang Date:20130413
					// for：性能优化，改成批量插入
					// ----------------------------------------------------------------
				}
			}
			this.systemService.batchSave(entitys);
			this.message = "角色:" + roleName + "权限更新成功";
			j.setMsg("角色:" + roleName + "权限更新成功");
			TSUser user = ResourceUtil.getSessionUserName();
			String roles = "";
			if (user != null) {
				List<TSRoleUser> rUsers = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
				for (TSRoleUser ru : rUsers) {
					role = ru.getTSRole();
					roles += role.getRoleName() + ",";
				}
				request.setAttribute("roleName", roles);
				request.setAttribute("userName", user.getRealName());
			}
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			this.message = "角色:" + roleName + "权限更新失败";
			j.setMsg("角色:" + roleName + "权限更新失败");
		}
		return j;
	}

	/**
	 * 新建、修改角色页面跳转
	 * 
	 * @param role
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "addorupdateRole")
	public ModelAndView addorupdateRole(TSRole role, HttpServletRequest req){
		if (role.getId() != null) {
			role = systemService.getEntity(TSRole.class, role.getId());
			req.setAttribute("role", role);
		}
		return new ModelAndView("system/role/role");
	}

	// ----------------------------------------------------------------
	// update-start--Author:anchao Date:20130415 for：按钮权限控制
	// ----------------------------------------------------------------
	/**
	 * 按钮权限展示
	 * 
	 * @param request
	 * @param functionId
	 * @param roleId
	 * @return
	 */
	@RequestMapping(params = "operationListForFunction")
	public ModelAndView operationListForFunction(HttpServletRequest request, Integer functionId, Integer roleId){
		CriteriaQuery cq = new CriteriaQuery(TSOperation.class);
		cq.eq("TSFunction.id", functionId);
		cq.add();
		List<TSOperation> operationList = this.systemService.getListByCriteriaQuery(cq, false);
		Set<String> operationCodes = systemService.getOperationCodesByRoleIdAndFunctionId(roleId, functionId);
		request.setAttribute("operationList", operationList);
		request.setAttribute("operationcodes", operationCodes);
		request.setAttribute("functionId", functionId);
		return new ModelAndView("system/role/operationListForFunction");
	}

	/**
	 * 更新按钮权限
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "updateOperation")
	@ResponseBody
	public AjaxJson updateOperation(HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		Integer roleId = oConvertUtils.getInt(request.getParameter("roleId"));
		Integer functionId = oConvertUtils.getInt(request.getParameter("functionId"));
		String operationcodes = request.getParameter("operationcodes");
		CriteriaQuery cq1 = new CriteriaQuery(TSRoleFunction.class);
		cq1.eq("TSRole.id", roleId);
		cq1.eq("TSFunction.id", functionId);
		cq1.add();
		List<TSRoleFunction> rFunctions = systemService.getListByCriteriaQuery(cq1, false);
		if (null != rFunctions && rFunctions.size() > 0) {
			TSRoleFunction tsRoleFunction = rFunctions.get(0);
			tsRoleFunction.setOperation(operationcodes);
			systemService.saveOrUpdate(tsRoleFunction);
		}
		j.setMsg("按钮权限更新成功");
		return j;
	}
	// ----------------------------------------------------------------
	// update-start--Author:anchao Date:20130415 for：按钮权限控制
	// ------

	// /**
	// * 权限操作列表
	// *
	// * @param role
	// * @param request
	// * @param response
	// * @param dataGrid
	// * @param user
	// * @return
	// */
	// @RequestMapping(params = "setOperate")
	// @ResponseBody
	// public List<TreeGrid> setOperate(HttpServletRequest request,
	// TreeGrid treegrid) {
	// String roleId = request.getParameter("roleId");
	// CriteriaQuery cq = new CriteriaQuery(TSFunction.class);
	// if (treegrid.getId() != null) {
	// cq.eq("TSFunction.id",
	// treegrid.getId());
	// }
	// if (treegrid.getId() == null) {
	// cq.isNull("TSFunction");
	// }
	// cq.add();
	// List<TSFunction> functionList =
	// systemService.getListByCriteriaQuery(cq,false);
	// List<TreeGrid> treeGrids = new ArrayList<TreeGrid>();
	// Collections.sort(functionList, new SetListSort());
	// TreeGridModel treeGridModel=new TreeGridModel();
	// treeGridModel.setRoleid(roleId);
	// treeGrids = systemService.treegrid(functionList, treeGridModel);
	// return treeGrids;
	//
	// }

	// /**
	// * 操作录入
	// *
	// * @param ids
	// * @return
	// */
	// @RequestMapping(params = "saveOperate")
	// @ResponseBody
	// public AjaxJson saveOperate(HttpServletRequest request) {
	// AjaxJson j = new AjaxJson();
	// String fop = request.getParameter("fp");
	// String roleId = request.getParameter("roleId");
	// //录入操作前清空上一次的操作数据
	// clearp(roleId);
	// String[] fun_op = fop.split(",");
	// String aa = "";
	// String bb = "";
	// //只有一个被选中
	// if (fun_op.length == 1) {
	// bb = fun_op[0].split("_")[1];
	// aa = fun_op[0].split("_")[0];
	// savep( roleId,bb,aa);
	// }else{
	// //至少2个被选中
	// for (int i = 0; i < fun_op.length; i++) {
	// String cc = fun_op[i].split("_")[0]; //操作id
	// if (i > 0 && bb.equals(fun_op[i].split("_")[1])) {
	// aa += "," + cc;
	// if (i== (fun_op.length - 1)) {
	// savep( roleId,bb,aa);
	// }
	// } else if (i > 0) {
	// savep(roleId,bb,aa);
	// aa = fun_op[i].split("_")[0]; //操作ID
	// if (i==(fun_op.length-1)){
	// bb = fun_op[i].split("_")[1]; //权限id
	// savep(roleId,bb,aa);
	// }
	//
	// } else {
	// aa = fun_op[i].split("_")[0]; //操作ID
	// }
	// bb = fun_op[i].split("_")[1]; //权限id
	//
	// }
	// }
	//
	//
	// return j;
	// }
	/**
	 * 更新操作
	 * 
	 * @param roleId
	 * @param functionid
	 * @param ids
	 */
	// public void savep(String roleId,String functionid, String ids) {
	// //String hql = "from TSRoleFunction t where" + " t.TSRole.id=" +
	// oConvertUtils.getInt(roleId,0)
	// // + " " + "and t.TSFunction.id=" + oConvertUtils.getInt(functionid,0);
	// CriteriaQuery cq=new CriteriaQuery(TSRoleFunction.class);
	// cq.eq("TSRole.id",roleId);
	// cq.eq("TSFunction.id",functionid);
	// cq.add();
	// List<TSRoleFunction> rFunctions
	// =systemService.getListByCriteriaQuery(cq,false);
	// if (rFunctions.size() > 0) {
	// TSRoleFunction roleFunction = rFunctions.get(0);
	// roleFunction.setOperation(ids);
	// systemService.saveOrUpdate(roleFunction);
	// }
	// }
	/**
	 * 清空操作
	 * 
	 * @param roleId
	 */
	// public void clearp(String roleId) {
	// List<TSRoleFunction> rFunctions =
	// systemService.findByProperty(TSRoleFunction.class,"TSRole.id",roleId);
	// if (rFunctions.size() > 0){
	// for (TSRoleFunction tRoleFunction : rFunctions) {
	// tRoleFunction.setOperation(null);
	// systemService.saveOrUpdate(tRoleFunction);
	// }
	// }
	// }
}
