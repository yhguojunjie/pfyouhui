package com.yoxi.jgframework.demo.controller.test;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.yoxi.jgframework.annotation.AddOrUpdateAnnotation;
import com.yoxi.jgframework.annotation.DeleteAnnotation;
import com.yoxi.jgframework.common.controller.BaseController;
import com.yoxi.jgframework.common.hibernate.qbc.CriteriaQuery;
import com.yoxi.jgframework.common.model.json.AjaxJson;
import com.yoxi.jgframework.common.model.json.ComboBox;
import com.yoxi.jgframework.common.model.json.ComboTree;
import com.yoxi.jgframework.common.model.json.DataGrid;
import com.yoxi.jgframework.demo.entity.test.JeecgDemo;
import com.yoxi.jgframework.demo.service.test.JeecgDemoServiceI;
import com.yoxi.jgframework.system.entity.TSDepart;
import com.yoxi.jgframework.ui.tag.core.easyui.TagUtil;
import com.yoxi.jgframework.util.MyBeanUtils;
import com.yoxi.jgframework.util.StringUtil;


/**   
 * @Title: Controller
 * @Description: 单表模型（DEMO）
 * @author zhangdaihao
 * @date 2013-01-23 17:12:40
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/jeecgDemoController")
public class JeecgDemoController extends BaseController {
	

	@Autowired
	private JeecgDemoServiceI jeecgDemoService;
	

	/**
	 * JeecgDemo例子列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "jeecgDemo")
	public ModelAndView jeecgDemo(HttpServletRequest request) {
		//return new ModelAndView("com.yoxi.jgframework/demo/jeecgDemo/jeecgDemoList");
		return new ModelAndView("demo/jeecgDemo/jeecgDemoList");
	}

	/**
	 * easyuiAJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(JeecgDemo jeecgDemo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(JeecgDemo.class, dataGrid);
		//查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, jeecgDemo);
		this.jeecgDemoService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	
	/**
	 * 权限列表
	 */
	@RequestMapping(params = "combox")
	@ResponseBody
	public List<JeecgDemo> combox(HttpServletRequest request, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(JeecgDemo.class);
		List<JeecgDemo> ls = this.jeecgDemoService.getListByCriteriaQuery(cq, false);
		return ls;
	}
	/**
	 * 删除JeecgDemo例子
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson del(JeecgDemo jeecgDemo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		jeecgDemo = systemService.getEntity(JeecgDemo.class, jeecgDemo.getId());
		message = "JeecgDemo例子: " + jeecgDemo.getUserName() + "被删除 成功";
		jeecgDemoService.delete(jeecgDemo);
		//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加JeecgDemo例子
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson save(JeecgDemo jeecgDemo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(jeecgDemo.getId())) {
			message = "JeecgDemo例子: " + jeecgDemo.getUserName() + "被更新成功";
			JeecgDemo t =jeecgDemoService.get(JeecgDemo.class, jeecgDemo.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(jeecgDemo, t);
				jeecgDemoService.saveOrUpdate(t);
				//systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "JeecgDemo例子: " + jeecgDemo.getUserName() + "被添加成功";
			jeecgDemoService.save(jeecgDemo);
			//systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		return j;
	}

	/**
	 * JeecgDemo例子列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(JeecgDemo jeecgDemo, HttpServletRequest req) {
		//获取部门信息
		List<TSDepart> departList = systemService.getList(TSDepart.class);
		req.setAttribute("departList", departList);
		
		if (StringUtil.isNotEmpty(jeecgDemo.getId())) {
			jeecgDemo = jeecgDemoService.getEntity(JeecgDemo.class, jeecgDemo.getId());
			req.setAttribute("jgDemo", jeecgDemo);
		}
		return new ModelAndView("demo/jeecgDemo/jeecgDemo");
		//return new ModelAndView("com.yoxi.jgframework/demo/jeecgDemo/jeecgDemo");
	}
	
	
	/**
	 *获取下拉数据
	 */
	@RequestMapping(params = "getSexCombobox")
	@ResponseBody
	public List<ComboBox> getSexCombobox(HttpServletRequest request) {
		
		List<ComboBox> comboBoxs = new ArrayList<ComboBox>();
		ComboBox comboBox = new ComboBox();
		comboBox.setId("0");
		comboBox.setText("男");
		comboBoxs.add(comboBox);
		
		comboBox = new ComboBox();
		comboBox.setId("1");
		comboBox.setText("女");
		comboBoxs.add(comboBox);
		return comboBoxs;
	}
	
	/**
	 *获取部门数据
	 */
	@RequestMapping(params = "getDepartComboTree")
	@ResponseBody
	public List<ComboTree> getDepartComboTree(HttpServletRequest request) {
		CriteriaQuery cq = new CriteriaQuery(TSDepart.class);
		cq.isNull("TSPDepart");
		cq.add();
		List<TSDepart> departList = systemService.getListByCriteriaQuery(cq, false);
		List<ComboTree> comboTrees = systemService.comTree(departList, null);
		return comboTrees;
	}
	
	/**
	 * 设置签名跳转页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "doCheck")
	public ModelAndView doCheck(HttpServletRequest request) {
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		//return new ModelAndView("com.yoxi.jgframework/demo/jeecgDemo/jeecgDemo-check");
		return new ModelAndView("demo/jeecgDemo/jeecgDemo-check");
	}
}
