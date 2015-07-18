package com.yoxi.pfhudongtui.content.controller;
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
import com.yoxi.pfhudongtui.content.entity.Aboutus;
import com.yoxi.pfhudongtui.content.service.AboutusService;

/**   
 * @Title: Controller
 * @Description: Aboutus
 * @author jwhat generate
 * @date 2015-04-23 11:03:02
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/aboutusController")
public class AboutusController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AboutusController.class);

	@Autowired
	private AboutusService aboutusService;

	/**
	 * Aboutus列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "aboutus")
	public ModelAndView aboutus(HttpServletRequest request) {
		return new ModelAndView("content/aboutus/aboutusList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "aboutusGrid")
	public void aboutusGrid(Aboutus aboutus,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(Aboutus.class, dataGrid);
		cq.addOrder("createTime", SortDirection.desc);
		//查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, aboutus);
		this.aboutusService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除Aboutus
	 * 
	 * @return
	 */
	@RequestMapping(params = "delAboutus")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson delAboutus(Aboutus aboutus, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		aboutus = systemService.getEntity(Aboutus.class, aboutus.getId());
		message = "删除成功";
		aboutusService.delete(aboutus);
		//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加Aboutus
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveAboutus")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson saveAboutus(Aboutus aboutus, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(aboutus.getId())) {
			message = "更新成功";
			this.isNew = false;
			Aboutus t = aboutusService.get(Aboutus.class, aboutus.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(aboutus, t);
				t.setUpdateTime(new Date());
				aboutusService.saveOrUpdate(t);
				//systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "添加成功";
			this.isNew = true;
			aboutus.setCreateTime(new Date());
			aboutusService.save(aboutus);
			//systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		return j;
	}

	/**
	 * Aboutus列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdateAboutus")
	public ModelAndView addorupdateAboutus(Aboutus aboutus, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(aboutus.getId())) {
			aboutus = aboutusService.getEntity(Aboutus.class, aboutus.getId());
			req.setAttribute("aboutusPage", aboutus);
		}
		return new ModelAndView("content/aboutus/aboutusPage");
	}
}
