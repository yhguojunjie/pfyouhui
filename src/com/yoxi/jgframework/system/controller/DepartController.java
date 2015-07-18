package com.yoxi.jgframework.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
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
import com.yoxi.jgframework.system.entity.TSDepart;
import com.yoxi.jgframework.system.entity.TSUser;
import com.yoxi.jgframework.system.service.TSDepartService;
import com.yoxi.jgframework.ui.tag.vo.easyui.TreeGridModel;
import com.yoxi.jgframework.util.ResourceUtil;
import com.yoxi.jgframework.util.StringUtil;
import com.yoxi.jgframework.util.oConvertUtils;

/**
 * 部门信息处理类
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/departController")
public class DepartController extends BaseServiceController {
	private static final Logger LOGGER = Logger
			.getLogger(DepartController.class);

	@Resource
	private TSDepartService departService;

	/**
	 * 部门列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "depart")
	public ModelAndView depart() {

		return new ModelAndView("system/depart/departList");
	}

	/**
	 * 删除部门
	 * 
	 * @return
	 */
	@RequestMapping(params = "delDepart")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson delDepart(TSDepart depart, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		depart = systemService.getEntity(TSDepart.class, depart.getId());
		message = "部门: " + depart.getDepartName() + "被删除成功";
		List<Map<String, Object>> depSize = departService.queryParentId(depart
				.getId());// 根据id,查找是否有子部门
		if (depSize.size() > 0) {
			message = "请先删除该部门下的子部门！";
		} else {
			List<Map<String, Object>> depUserSize = departService
					.queryUserId(depart.getId());// 根据id,查找部门下面的用户
			if (depUserSize.size() > 0) {
				message = "请先删除该部门下用户！";
			} else {
				// 删除部门之前更新与之相关的实体
				upEntity(depart);
				systemService.delete(depart);
				// systemService.addLog(message, Globals.Log_Type_DEL,
				// Globals.Log_Leavel_INFO);
			}

		}

		j.setMsg(message);
		return j;
	}

	/**
	 * 更新与depart相关的实体
	 * 
	 * @param depart
	 */
	private void upEntity(TSDepart depart) {
		List<TSUser> users = systemService.findByProperty(TSUser.class,
				"TSDepart.id", depart.getId());
		if (users.size() > 0) {
			for (TSUser tsUser : users) {
				tsUser.setTSDepart(null);
				systemService.saveOrUpdate(tsUser);
			}
		}
	}

	/**
	 * 添加、修改部门时，保存部门数据
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveDepart")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson saveDepart(TSDepart depart, HttpServletRequest request) {
		// 设置上级部门
		String pid = request.getParameter("TSPDepart.id");
		// 如果上级部门为空设置上级为空
		if (pid == null || pid.equals("")) {
			depart.setTSPDepart(null);
		}
		// 取部门id
		String idOld = oConvertUtils.getString(request.getParameter("idOld"));
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(idOld)) {
			message = "部门: " + depart.getDepartName() + "被更新成功";
			this.isNew = false;
			if (depart.getTSPDepart() != null) {
				TSDepart dept = systemService.getEntity(TSDepart.class, depart
						.getTSPDepart().getId());
				userService.getSession().evict(depart);
				depart.setDepartType(dept.getDepartType());// 类型跟父亲
			}
			userService.saveOrUpdate(depart);

		} else {
			// 做新增的工作
			message = "部门: " + depart.getDepartName() + "被添加成功";
			this.isNew = true;
			String departId = null;
			if (depart.getTSPDepart() != null) {
				TSDepart dept = systemService.getEntity(TSDepart.class, depart
						.getTSPDepart().getId());
				depart.setDepartType(dept.getDepartType());// 类型跟父亲
				departId = departService.makeDepartId(dept.getId());
			} else {
				// 第一级父级
				departId = departService.makeDepartId(null);
			}
			if (StringUtils.isNotBlank(departId)) {
				depart.setId(departId);
				if (departId.equals("00000000")) {
					message = "该部门下不允许在有子部门，添加失败！";
				} else if (departId.equals("xxxxxxxx")) {
					message = "部门已满，无法再设置，请联系管理人员！";
				} else if (departId.length() > 8) {
					message = departId;
				} else {
					userService.save(depart);
				}
			}
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 部门管理的添加页面跳转，逻辑处理
	 * 
	 * @param depart
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "addDepart")
	public ModelAndView addDepart(TSDepart depart, HttpServletRequest req) {
		List<TSDepart> departList = systemService.getList(TSDepart.class);
		req.setAttribute("departList", departList);
		if (StringUtil.isNotEmpty(depart.getId())) {
			TSDepart tspDepart = new TSDepart();
			TSDepart tsDepart = new TSDepart();
			depart = systemService.getEntity(TSDepart.class, depart.getId());
			tspDepart.setId(depart.getId());
			tspDepart.setDepartName(depart.getDepartName());
			tsDepart.setTSPDepart(tspDepart);
			req.setAttribute("depart", tsDepart);
		}
		return new ModelAndView("system/depart/depart");
	}

	/**
	 * 部门管理的修改页面跳转，逻辑处理
	 * 
	 * @return
	 */
	@RequestMapping(params = "updateDepart")
	public ModelAndView updateDepart(TSDepart depart, HttpServletRequest req) {
		List<TSDepart> departList = systemService.getList(TSDepart.class);
		req.setAttribute("departList", departList);
		if (StringUtil.isNotEmpty(depart.getId())) {
			depart = systemService.getEntity(TSDepart.class, depart.getId());
			req.setAttribute("depart", depart);
		}
		return new ModelAndView("system/depart/depart");
	}

	/**
	 * 父级权限列表
	 * 
	 * @param role
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 * @return
	 */
	@RequestMapping(params = "getPDepart")
	@ResponseBody
	public List<ComboTree> getPDepart(HttpServletRequest request,
			ComboTree comboTree) {
		CriteriaQuery cq = new CriteriaQuery(TSDepart.class);
		if (StringUtil.isNotEmpty(comboTree.getId())) {
			cq.eq("TSPDepart.id", comboTree.getId());
		}
		// ----------------------------------------------------------------
		// update-begin--Author:liutao Date:20130205 for：将isNotEmpty方法改为isEmpty
		// ----------------------------------------------------------------
		if (StringUtil.isEmpty(comboTree.getId())) {
			cq.isNull("TSPDepart.id");
		}
		// ----------------------------------------------------------------
		// update-begin--Author:liutao Date:20130205 for：将isNotEmpty方法改为isEmpty
		// ----------------------------------------------------------------
		cq.add();
		List<TSDepart> departsList = systemService.getListByCriteriaQuery(cq,
				false);
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		comboTrees = systemService.comTree(departsList, comboTree);
		return comboTrees;

	}

	/**
	 * 部门列表，获取树形展示数据
	 * 
	 * @param request
	 * @param response
	 * @param treegrid
	 * @return
	 */
	@RequestMapping(params = "departTreeGrid")
	@ResponseBody
	public List<TreeGrid> departTreeGrid(TSDepart tSDepart,
			HttpServletRequest request, HttpServletResponse response,
			TreeGrid treegrid) {
		CriteriaQuery cq = new CriteriaQuery(TSDepart.class);

		// ----------------------------------------------------------------
		// update-begin--Author:yeshuai Date:20130412
		// for：增加部门名称搜寻框,注意如果点父节点，展开时，如果有值，则节点出不来
		if (oConvertUtils.isEmpty(tSDepart.getDepartName()) == false) {
			com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
					tSDepart);
		}
		// update-end--Author:yeshuai Date:20130412 for：增加部门名称搜寻框
		// ----------------------------------------------------------------
		if (treegrid.getId() != null) {
			cq.eq("TSPDepart.id", oConvertUtils.getString(treegrid.getId()));
		}
		if (treegrid.getId() == null) {
			cq.isNull("TSPDepart");
		}
		cq.add();

		List<TreeGrid> departList = systemService.getListByCriteriaQuery(cq,
				false);
		List<TreeGrid> treeGrids = new ArrayList<TreeGrid>();
		TreeGridModel treeGridModel = new TreeGridModel();
		treeGridModel.setIdField("id");
		treeGridModel.setTextField("departName");
		treeGridModel.setSrc("description");
		treeGridModel.setCode("departCode");
		treeGridModel.setOrder("departOrder");
		treeGridModel.setIcon("departType");
		treeGridModel.setParentId("TSPDepart_id");
		treeGridModel.setParentText("TSPDepart_departName");

		treeGridModel.setChildList("TSDeparts");
		treeGrids = systemService.treegrid(departList, treeGridModel);
		return treeGrids;
	}

	/**
	 * 获取部门数据
	 */
	@RequestMapping(params = "getDepartComboTree")
	@ResponseBody
	public List<ComboTree> getDepartComboTree(HttpServletRequest request) {
		CriteriaQuery cq = new CriteriaQuery(TSDepart.class);
		String departType = request.getParameter("departType");
		if (oConvertUtils.isEmpty(departType) == false)
			cq.eq("departType", oConvertUtils.getInt(departType));
		cq.isNull("TSPDepart");
		cq.add();
		List<TSDepart> departList = systemService.getListByCriteriaQuery(cq,
				false);
		List<ComboTree> comboTrees = systemService.comTree(departList, null);
		return comboTrees;
	}

	/**
	 * 获取部门数据，根据用户权限获取
	 */
	@RequestMapping(params = "getDepartComboTreeRight")
	@ResponseBody
	public List<ComboTree> getDepartComboTreeRight(HttpServletRequest request) {
		CriteriaQuery cq = new CriteriaQuery(TSDepart.class);
		String departType = request.getParameter("departType");
		if (oConvertUtils.isEmpty(departType) == false)
			cq.eq("departType", oConvertUtils.getInt(departType));

		TSUser user = ResourceUtil.getSessionUserName();
		if (user == null || user.getTSDepart() == null
				|| oConvertUtils.isEmpty(user.getTSDepart().getId()))
			cq.eq("id", "$"); // 无权限查询
		else {
			Integer userDepartType = user.getTSDepart().getDepartType();
			if (userDepartType == 2) { // 渠道，只能取得自己或下一级
				String departid = user.getTSDepart().getId();
				cq.eq("id", departid); // 获取自己或子渠道
			} else if (userDepartType != 0)
				cq.eq("id", "$%"); // 无权限查询
			else
				cq.isNull("TSPDepart");
		}

		cq.add();
		List<TSDepart> departList = systemService.getListByCriteriaQuery(cq,
				false);
		List<ComboTree> comboTrees = systemService.comTree(departList, null);
		return comboTrees;
	}

}
