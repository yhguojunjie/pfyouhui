package com.yoxi.pfhudongtui.user.controller;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
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
import com.yoxi.jgframework.common.model.json.DataGrid;
import com.yoxi.jgframework.constant.Globals;
import com.yoxi.jgframework.util.StringUtil;
import com.yoxi.jgframework.ui.tag.core.easyui.TagUtil;
import com.yoxi.jgframework.ui.tag.vo.datatable.SortDirection;
import com.yoxi.jgframework.system.entity.TSDepart;
import com.yoxi.jgframework.system.service.SystemService;
import com.yoxi.jgframework.util.MyBeanUtils;
import com.yoxi.pfhudongtui.user.entity.Joinboard;
import com.yoxi.pfhudongtui.user.service.JoinboardService;

/**   
 * @Title: Controller
 * @Description: Joinboard
 * @author jwhat generate
 * @date 2015-04-15 17:34:50
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/joinboardController")
public class JoinboardController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(JoinboardController.class);

	@Autowired
	private JoinboardService joinboardService;

	/**
	 * Joinboard列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "joinboard")
	public ModelAndView joinboard(HttpServletRequest request) {
		return new ModelAndView("user/joinboard/joinboardList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "joinboardGrid")
	public void joinboardGrid(Joinboard joinboard,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(Joinboard.class, dataGrid);
		cq.addOrder("createTime", SortDirection.desc);
		//查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, joinboard);
		this.joinboardService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除Joinboard
	 * 
	 * @return
	 */
	@RequestMapping(params = "delJoinboard")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson delJoinboard(Joinboard joinboard, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		joinboard = systemService.getEntity(Joinboard.class, joinboard.getId());
		message = "删除成功";
		joinboardService.delete(joinboard);
		//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加Joinboard
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveJoinboard")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson saveJoinboard(Joinboard joinboard, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(joinboard.getId())) {
			message = "更新成功";
			this.isNew = false;
			Joinboard t = joinboardService.get(Joinboard.class, joinboard.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(joinboard, t);
				joinboardService.saveOrUpdate(t);
				//systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "添加成功";
			this.isNew = true;
			joinboard.setCreateTime(new Date());
			joinboardService.save(joinboard);
			//systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		return j;
	}

	/**
	 * Joinboard列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdateJoinboard")
	public ModelAndView addorupdateJoinboard(Joinboard joinboard, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(joinboard.getId())) {
			joinboard = joinboardService.getEntity(Joinboard.class, joinboard.getId());
			req.setAttribute("joinboardPage", joinboard);
		}
		return new ModelAndView("user/joinboard/joinboardPage");
	}
	
	/**
	 * Joinboard列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "viewJoinboard")
	public ModelAndView viewJoinboard(Joinboard joinboard, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(joinboard.getId())) {
			joinboard = joinboardService.getEntity(Joinboard.class, joinboard.getId());
			req.setAttribute("joinboardPage", joinboard);
		}
		return new ModelAndView("user/joinboard/viewJoinboard");
	}
}
