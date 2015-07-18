package com.yoxi.jgframework.demo.controller.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import com.yoxi.jgframework.common.controller.BaseController;
import com.yoxi.jgframework.common.hibernate.qbc.CriteriaQuery;
import com.yoxi.jgframework.common.model.json.AjaxJson;
import com.yoxi.jgframework.common.model.json.ComboTree;
import com.yoxi.jgframework.common.model.json.TreeGrid;
import com.yoxi.jgframework.system.entity.TSDemo;
import com.yoxi.jgframework.ui.tag.vo.datatable.SortDirection;
import com.yoxi.jgframework.ui.tag.vo.easyui.ComboTreeModel;
import com.yoxi.jgframework.ui.tag.vo.easyui.TreeGridModel;
import com.yoxi.jgframework.util.MyBeanUtils;
import com.yoxi.jgframework.util.oConvertUtils;


/**
 * @ClassName: demoController
 * @Description: TODO(演示例子处理类)
 * @author com.yoxi.jgframework
 */
@Controller
@RequestMapping("/demoController")
public class DemoController extends BaseController {
	
	/**
	 * demo页面跳转
	 */
	@RequestMapping(params = "demoList")
	public ModelAndView demoList(HttpServletRequest request) {
		return new ModelAndView("demo/demoList");
	}
	/**
	 * demo列表
	 */
	@RequestMapping(params = "demoGrid")
	@ResponseBody
	public List<TreeGrid> demoGrid(HttpServletRequest request, TreeGrid treegrid) {
		CriteriaQuery cq = new CriteriaQuery(TSDemo.class);
		if (treegrid.getId() != null) {
			cq.eq("TSDemo.id", treegrid.getId());
		}
		if (treegrid.getId() == null) {
			cq.isNull("TSDemo");
		}
		cq.add();
		List<TSDemo> demoList = systemService.getListByCriteriaQuery(cq, false);
		TreeGridModel treeGridModel = new TreeGridModel();
		treeGridModel.setTextField("demotitle");
		treeGridModel.setParentText("TSDemo_demotitle");
		treeGridModel.setParentId("TSDemo_id");
		treeGridModel.setSrc("demourl");
		treeGridModel.setOrder("demoorder");
		treeGridModel.setIdField("id");
		treeGridModel.setChildList("tsDemos");
		List<TreeGrid> treeGrids = systemService.treegrid(demoList, treeGridModel);
		return treeGrids;
	}
	
	/**
	 * demo添加页面跳转
	 */
	@RequestMapping(params = "addorupdateDemo")
	public ModelAndView addorupdateDemo(TSDemo demo, HttpServletRequest request) {
		String type = oConvertUtils.getString(request.getParameter("type"));
		if (demo.getId() != null) {
			demo = systemService.getEntity(TSDemo.class, demo.getId());
			request.setAttribute("demo", demo);
		}
		if (type.equals("table")) {
			return new ModelAndView("demo/tabledemo");
		} else {
			return new ModelAndView("demo/demo");
		}
	}
	
	
	/**
	 * 保存DEMO维护
	 * 
	 * @param jeecgDemo
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(params = "saveDemo")
	@ResponseBody
	public AjaxJson saveDemo(TSDemo demo, HttpServletRequest request) throws Exception{
		AjaxJson j = new AjaxJson();
		if (!oConvertUtils.isEmpty(demo.getId())) {
			message = "Demo维护例子: " + demo.getDemotitle() + "被更新成功";
			TSDemo entity = this.systemService.get(TSDemo.class, demo.getId());
			MyBeanUtils.copyBeanNotNull2Bean(demo, entity);
			
			if (demo.getTSDemo() == null || oConvertUtils.isEmpty(demo.getTSDemo().getId())) {
				entity.setTSDemo(null);
			}
			this.systemService.saveOrUpdate(entity);
		}else {
			message = "Demo例子: " + demo.getDemotitle() + "被添加成功";
			if (demo.getTSDemo() == null || oConvertUtils.isEmpty(demo.getTSDemo().getId())) {
				demo.setTSDemo(null);
			}
			this.systemService.save(demo);
		}
		j.setMsg(message);
		return j;
	}
	
	
	/**
	 * 删除Demo
	 * 
	 * @return
	 */
	@RequestMapping(params = "delDemo")
	@ResponseBody
	public AjaxJson del(TSDemo demo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		demo = systemService.getEntity(TSDemo.class, demo.getId());
		message = "Demo: " + demo.getDemotitle() + "被删除 成功";
		// 删除部门之前更新与之相关的实体
		//upEntity(demo);
		systemService.delete(demo);
		//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 父级DEMO下拉菜单
	 */
	@RequestMapping(params = "pDemoList")
	@ResponseBody
	public List<ComboTree> pDemoList(HttpServletRequest request, ComboTree comboTree) {
		CriteriaQuery cq = new CriteriaQuery(TSDemo.class);
		if (comboTree.getId() != null) {
			cq.eq("TSDemo.id", comboTree.getId());
		}
		if (comboTree.getId() == null) {
			cq.isNull("TSDemo");
		}
		cq.add();
		cq.addOrder("demoorder", SortDirection.asc);
		List<TSDemo> demoList = systemService.getListByCriteriaQuery(cq, false);
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		ComboTreeModel comboTreeModel = new ComboTreeModel("id", "demotitle", "tsDemos", "demourl");
		comboTrees = systemService.ComboTree(demoList, comboTreeModel, null);
		return comboTrees;
	}

	
	
	/**
	 * demo页面跳转
	 */
	@RequestMapping(params = "demoIframe")
	public ModelAndView demoIframe(HttpServletRequest request) {
		CriteriaQuery cq = new CriteriaQuery(TSDemo.class);
		cq.isNull("TSDemo.id");
		cq.add();
		List<TSDemo> demoList = systemService.getListByCriteriaQuery(cq, false);
		request.setAttribute("demoList", demoList);
		return new ModelAndView("demo/demoIframe");
	}
	

	/**
	 * demoCode页面跳转
	 */
	@RequestMapping(params = "demoCode")
	public ModelAndView demoCode(TSDemo demo, HttpServletRequest request) {
		demo = systemService.getEntity(TSDemo.class, demo.getId());
		request.setAttribute("demo", demo);
		return new ModelAndView("demo/democode");
	}
/*	
	@RequestMapping(params = "demoTurn")
	@ResponseBody
	public String demoTurn(Integer id){
		return id.toString();
//		String code = systemService.get(TSDemo.class, id).getDemocode();
//		return code;//HtmlUtils.htmlUnescape(code);
	}*/

	/**
	 * 图片预览TABS跳转
	 *//*
	@RequestMapping(params = "imgViewTabs")
	public ModelAndView imgViewTabs(HttpServletRequest request) {
		return new ModelAndView("demo/picview/imgViewTabs");
	}

	*//**
	 * 动态模板TABS跳转
	 *//*
	@RequestMapping(params = "templeteTabs")
	public ModelAndView templeteTabs(HttpServletRequest request) {
		return new ModelAndView("demo/template/templateiframe");
	}	*/
	
}
