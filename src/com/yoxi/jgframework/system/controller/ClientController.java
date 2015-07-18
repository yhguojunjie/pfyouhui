package com.yoxi.jgframework.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yoxi.jgframework.annotation.AddOrUpdateAnnotation;
import com.yoxi.jgframework.annotation.DeleteAnnotation;
import com.yoxi.jgframework.common.hibernate.qbc.CriteriaQuery;
import com.yoxi.jgframework.common.model.json.AjaxJson;
import com.yoxi.jgframework.common.model.json.DataGrid;
import com.yoxi.jgframework.system.entity.TSClient;
import com.yoxi.jgframework.ui.tag.core.easyui.TagUtil;
import com.yoxi.jgframework.util.StringUtil;
import com.yoxi.jgframework.util.oConvertUtils;


/**
 * 客户端版本管理类
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/clientController")
public class ClientController extends BaseServiceController {
	
	/************************************** 版本维护 ************************************/

	/**
	 * 客户端版本列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "client")
	public ModelAndView client() {
		return new ModelAndView("system/client/clientList");
	}
	
	/**
	 * 获取客户端版本维护Grid数据
	 */
	@RequestMapping(params = "clientList")
	public void clientList(TSClient aTSClient,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSClient.class, dataGrid);
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, aTSClient);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	


	/**
	 * 删除客户端版本操作
	 */
	@RequestMapping(params = "delClient")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson delClient(TSClient client, HttpServletRequest request,String message) {
		AjaxJson j = new AjaxJson();
		client = systemService.getEntity(TSClient.class, client.getId());
		message = "客户端版本：" + client.getVersionCode() + "被删除成功";
		systemService.delete(client);
		//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);

		return j;
	}

	/**
	 * 客户端版本添加、修改页面跳转
	 * 
	 * @param icon
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "aouClient")
	public ModelAndView aouClient(TSClient client,HttpServletRequest req) {
		if (StringUtil.isNotEmpty(client.getId())) {
			client = systemService.getEntity(TSClient.class, client.getId());
			req.setAttribute("client", client);
	    }
		return new ModelAndView("system/client/client");
	}

	/**
	 * 添加、修改客户端版本时，保存客户端版本数据
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "saveClient", method = RequestMethod.POST)
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson saveClient(TSClient client,HttpServletRequest request) throws Exception {
		AjaxJson j = new AjaxJson();
		//AjaxJson j = new AjaxJson();
		//由于checkbox的未选中时 checked的值是null，因此如果null则为false
		if (client.getIsForce() == null){
			if(StringUtils.isNotBlank(client.getForceVersionCode())||StringUtils.isNotBlank(client.getForceVersionArrang())){
				client.setIsForce(true);
			}else{
				client.setIsForce(false);
				//清空强制版本信息
				client.setForceVersionArrang(null);
				client.setForceVersionCode(null);
			}
		}else {
			client.setIsForce(true);
		}
		if (client.getPublishStatus() == null)
			client.setPublishStatus(false);
		
		String id =oConvertUtils.getString(request.getParameter("id"));
		if (StringUtil.isNotEmpty(id)) {
			message = "客户端版本: " + client.getVersionCode() + "被更新成功";
			this.isNew = false;
			userService.saveOrUpdate(client);
		} else {
			message = "客户端版本: " + client.getVersionCode() + "被添加成功";
			this.isNew = true;
			userService.save(client);
			//systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 发布版本
	 * 
	 * @return
	 */
	@RequestMapping(params = "publicClient")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson publicClient(TSClient client, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		client = systemService.getEntity(TSClient.class, client.getId());
		client.setPublishStatus(true);
		message = "版本: " + client.getVersionCode() + "被发布成功";
		this.isNew = false;
		systemService.saveOrUpdate(client);
		//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}
	
	
}
