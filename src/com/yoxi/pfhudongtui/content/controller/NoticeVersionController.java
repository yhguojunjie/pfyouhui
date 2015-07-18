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
import com.yoxi.pfhudongtui.content.entity.NoticeVersion;
import com.yoxi.pfhudongtui.content.service.NoticeVersionService;

/**   
 * @Title: Controller
 * @Description: NoticeVersion
 * @author jwhat generate
 * @date 2015-05-07 20:42:15
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/noticeVersionController")
public class NoticeVersionController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(NoticeVersionController.class);

	@Autowired
	private NoticeVersionService noticeVersionService;

	/**
	 * NoticeVersion列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "noticeVersion")
	public ModelAndView noticeVersion(HttpServletRequest request) {
		return new ModelAndView("content/noticeversion/noticeVersionList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "noticeVersionGrid")
	public void noticeVersionGrid(NoticeVersion noticeVersion,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(NoticeVersion.class, dataGrid);
		cq.addOrder("seq", SortDirection.asc);
		//查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, noticeVersion);
		this.noticeVersionService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除NoticeVersion
	 * 
	 * @return
	 */
	@RequestMapping(params = "delNoticeVersion")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson delNoticeVersion(NoticeVersion noticeVersion, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		noticeVersion = systemService.getEntity(NoticeVersion.class, noticeVersion.getId());
		message = "删除成功";
		noticeVersionService.delete(noticeVersion);
		//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加NoticeVersion
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveNoticeVersion")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson saveNoticeVersion(NoticeVersion noticeVersion, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(noticeVersion.getId())) {
			message = "更新成功";
			this.isNew = false;
			NoticeVersion t = noticeVersionService.get(NoticeVersion.class, noticeVersion.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(noticeVersion, t);
				t.setUpdateTime(new Date());
				noticeVersionService.saveOrUpdate(t);
				//systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "添加成功";
			this.isNew = true;
			noticeVersion.setCreateTime(new Date());
			noticeVersion.setUpdateTime(new Date());
			noticeVersionService.save(noticeVersion);
			//systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		return j;
	}

	/**
	 * NoticeVersion列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdateNoticeVersion")
	public ModelAndView addorupdateNoticeVersion(NoticeVersion noticeVersion, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(noticeVersion.getId())) {
			noticeVersion = noticeVersionService.getEntity(NoticeVersion.class, noticeVersion.getId());
			req.setAttribute("noticeVersionPage", noticeVersion);
		}
		return new ModelAndView("content/noticeversion/noticeVersionPage");
	}
}
