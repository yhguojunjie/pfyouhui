package com.yoxi.pfhudongtui.user.controller;
import java.util.ArrayList;
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
import com.yoxi.jgframework.system.entity.TSBaseUser;
import com.yoxi.jgframework.system.entity.TSDepart;
import com.yoxi.jgframework.system.service.SystemService;
import com.yoxi.jgframework.util.MyBeanUtils;
import com.yoxi.pfhudongtui.plugin.entity.Plugin;
import com.yoxi.pfhudongtui.user.entity.AgentInfo;
import com.yoxi.pfhudongtui.user.entity.AgentWithdraw;
import com.yoxi.pfhudongtui.user.entity.AgentWithdrawEntity;
import com.yoxi.pfhudongtui.user.entity.Contactus;
import com.yoxi.pfhudongtui.user.entity.ContactusEntity;
import com.yoxi.pfhudongtui.user.service.ContactusService;

/**   
 * @Title: Controller
 * @Description: Contactus
 * @author jwhat generate
 * @date 2015-04-08 15:20:55
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/contactusController")
public class ContactusController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ContactusController.class);

	@Autowired
	private ContactusService contactusService;

	/**
	 * Contactus列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "contactus")
	public ModelAndView contactus(HttpServletRequest request) {
		return new ModelAndView("user/contactus/contactusList");
	}
	/**
	 * 客服审核列表
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "contactusAudit")
	public ModelAndView contactusAudit(HttpServletRequest request) {
		return new ModelAndView("user/contactus/contactusAuditList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "contactusGrid")
	public void contactusGrid(Contactus contactus,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(Contactus.class, dataGrid);
		cq.eq("agentId", getUser().getId());
		//查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, contactus);
		this.contactusService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	/**
	 * 审核列表
	 * @param contactus
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "contactusAuditGrid")
	public void contactusAuditGrid(Contactus contactus,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(Contactus.class, dataGrid);
		cq.addOrder("applyTime", SortDirection.desc);
		//查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, contactus);
		this.contactusService.getDataGridReturn(cq, true);
		List<Contactus> list = dataGrid.getReaults();
		List<ContactusEntity> entityList = new ArrayList<ContactusEntity>();
		for(Contactus l :list){
			ContactusEntity entity = new ContactusEntity();
			entity.setId(l.getId());
			entity.setServicePhone("");
			entity.setQqGroup("");
			entity.setServiceqq("");
			entity.setEmail("");
			entity.setApplyTime(l.getApplyTime());
			entity.setAuditState(l.getAuditState());
			entity.setAuditTime(l.getAuditTime());
			entity.setStatus(l.getStatus());
			if(l.gettSBaseUser() == null)
				entity.setAuditUserName("");
			else
				entity.setAuditUserName(l.gettSBaseUser().getRealName());
			entityList.add(entity);
		}
		dataGrid.setReaults(entityList);
		TagUtil.datagrid(response, dataGrid);
	}
	/**
	 * 删除Contactus
	 * 
	 * @return
	 */
	@RequestMapping(params = "delContactus")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson delContactus(Contactus contactus, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		contactus = systemService.getEntity(Contactus.class, contactus.getId());
		message = "删除成功";
		contactusService.delete(contactus);
		//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加Contactus
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveContactus")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson saveContactus(Contactus contactus, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(contactus.getId())) {
			message = "更新成功";
			this.isNew = false;
			Contactus t = contactusService.get(Contactus.class, contactus.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(contactus, t);
				contactusService.saveOrUpdate(t);
				//systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "添加成功";
			this.isNew = true;
			contactus.setAgentId(getUser().getId());
			contactus.setApplyTime(new Date());
			contactus.setAuditState("0");
			contactus.setStatus("0");
			contactusService.save(contactus);
			
			//systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		return j;
	}

	/**
	 * Contactus列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdateContactus")
	public ModelAndView addorupdateContactus(Contactus contactus, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(contactus.getId())) {
			contactus = contactusService.getEntity(Contactus.class, contactus.getId());
			req.setAttribute("contactusPage", contactus);
		}
		return new ModelAndView("user/contactus/contactusPage");
	}
	/**
	 * 跳转审核界面
	 * @param contactus
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "toAudit")
	public ModelAndView toAudit(Contactus contactus, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(contactus.getId())) {
			contactus = contactusService.getEntity(Contactus.class, contactus.getId());
			req.setAttribute("contactusPage", contactus);
		}
		return new ModelAndView("user/contactus/audit");
	}
	/**
	 * 审核通过与不通过
	 * @param agentWithdraw
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "auditStateSave")
	@ResponseBody
	public AjaxJson auditStateSave( HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		Integer id = Integer.parseInt(request.getParameter("id"));
		String auditState = request.getParameter("auditState");
		Integer num = systemService.executeSql("update t_contactus set auditState=?,auditTime=?,auditUserId=? where id=?", auditState,new Date(),getUser().getId(),id);
		if(num > 0){
			j.setSuccess(true);
			j.setMsg("审核通过");
		}else{
			j.setSuccess(false);
			j.setMsg("审核不通过");
		}
		return j;
	}
	/**
	 * 启用或禁用
	 * @param contactus
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "disableOrEnable")
	@ResponseBody
	public AjaxJson disableOrEnable(Contactus contactus, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if("0".equals(contactus.getStatus())){
			 systemService.executeSql("update t_contactus set status=1 where id=?", contactus.getId());
			 systemService.executeSql("update t_contactus set status=0 where id!=? and agentId=?",contactus.getId(),getUser().getId() );
		}
		j.setMsg(message);
		return j;
	}
}
