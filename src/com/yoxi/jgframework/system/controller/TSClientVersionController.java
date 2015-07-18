package com.yoxi.jgframework.system.controller;
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
import com.yoxi.jgframework.system.entity.TSClientVersion;
import com.yoxi.jgframework.system.service.TSClientVersionService;
import com.yoxi.jgframework.ui.tag.core.easyui.TagUtil;
import com.yoxi.jgframework.util.MyBeanUtils;
import com.yoxi.jgframework.util.StringUtil;

/**   
 * @Title: Controller
 * @Description: ClientVersion
 * @author jwhat generate
 * @date 2013-08-09 16:53:58
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/tSClientVersionController")
public class TSClientVersionController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TSClientVersionController.class);

	@Autowired
	private TSClientVersionService tSClientVersionService;

	/**
	 * ClientVersion列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tSClientVersion")
	public ModelAndView tSClientVersion(HttpServletRequest request) {
		return new ModelAndView("system/tsclientversion/tSClientVersionList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "tSClientVersionGrid")
	public void tSClientVersionGrid(TSClientVersion tSClientVersion,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSClientVersion.class, dataGrid);
		//查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tSClientVersion);
		this.tSClientVersionService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除ClientVersion
	 * 
	 * @return
	 */
	@RequestMapping(params = "delTSClientVersion")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson delTSClientVersion(TSClientVersion tSClientVersion, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tSClientVersion = systemService.getEntity(TSClientVersion.class, tSClientVersion.getId());
		message = "删除成功";
		tSClientVersionService.delete(tSClientVersion);
		//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加ClientVersion
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveTSClientVersion")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson saveTSClientVersion(TSClientVersion tSClientVersion, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tSClientVersion.getId())) {
			message = "更新成功";
			this.isNew = false;
			TSClientVersion t = tSClientVersionService.get(TSClientVersion.class, tSClientVersion.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tSClientVersion, t);
				tSClientVersionService.saveOrUpdate(t);
				//systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "添加成功";
			this.isNew = true;
			tSClientVersionService.save(tSClientVersion);
			//systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		return j;
	}

	/**
	 * ClientVersion列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdateTSClientVersion")
	public ModelAndView addorupdateTSClientVersion(TSClientVersion tSClientVersion, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tSClientVersion.getId())) {
			tSClientVersion = tSClientVersionService.getEntity(TSClientVersion.class, tSClientVersion.getId());
			req.setAttribute("tSClientVersionPage", tSClientVersion);
		}
		return new ModelAndView("system/tsclientversion/tSClientVersionPage");
	}
}
