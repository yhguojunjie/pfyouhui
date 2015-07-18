package com.yoxi.pfhudongtui.user.controller;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import com.yoxi.jgframework.util.oConvertUtils;
import com.yoxi.jgframework.ui.tag.core.easyui.TagUtil;
import com.yoxi.jgframework.ui.tag.vo.datatable.SortDirection;
import com.yoxi.jgframework.system.entity.TSDepart;
import com.yoxi.jgframework.system.service.SystemService;
import com.yoxi.jgframework.util.MyBeanUtils;
import com.yoxi.pfhudongtui.user.entity.AgentWithdraw;
import com.yoxi.pfhudongtui.user.entity.AgentWithdrawEntity;
import com.yoxi.pfhudongtui.user.entity.PlatformUser;
import com.yoxi.pfhudongtui.user.service.PlatformUserService;
import com.yoxi.pfhudongtui.utils.PropertiesUtils;

/**   
 * @Title: Controller
 * @Description: PlatformUser
 * @author jwhat generate
 * @date 2015-03-25 19:28:44
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/platformUserController")
public class PlatformUserController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PlatformUserController.class);

	@Autowired
	private PlatformUserService platformUserService;

	/**
	 * 跳转所有用户列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "platformUser")
	public ModelAndView platformUser(HttpServletRequest request) {
		return new ModelAndView("user/platformuser/platformUserList");
	}
	/**
	 * 
	 * @param platformUser
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "platformUserGrid")
	public void platformUserGrid(PlatformUser platformUser,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String server = PropertiesUtils.getPara("fileAccessPath");
		CriteriaQuery cq = new CriteriaQuery(PlatformUser.class, dataGrid);
		cq.addOrder("createTime", SortDirection.desc);
		String companyName = oConvertUtils.getString(request.getParameter("companyName"));
		if(StringUtils.isNotBlank(companyName)){
			cq.createAlias("agentInfo", "ts");
			cq.like("ts.companyName", companyName);
			//cq.add(Restrictions.like("ts.title",actTitle));
		}
		//查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, platformUser);
		this.platformUserService.getDataGridReturn(cq, true);
		List<PlatformUser> list = dataGrid.getReaults();
		List<PlatformUser> userList = new ArrayList<PlatformUser>();
		for(PlatformUser l :list){
			if(l.getHeadimgUrl()!= null && l.getHeadimgUrl().startsWith("group1")){
				l.setHeadimgUrl(server+"/"+l.getHeadimgUrl());
			}
			userList.add(l);
		}
		dataGrid.setReaults(userList);
		TagUtil.datagrid(response, dataGrid);
	}
	/**
	 * 跳转代理商用户列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "agentUser")
	public ModelAndView agentPlatformUser(HttpServletRequest request) {
		return new ModelAndView("user/platformuser/agentUserList");
	}

	/**
	 * 代理商用户列表
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "agentUserGrid")
	public void agentUserGrid(PlatformUser platformUser,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String server = PropertiesUtils.getPara("fileAccessPath");
		CriteriaQuery cq = new CriteriaQuery(PlatformUser.class, dataGrid);
		cq.addOrder("createTime", SortDirection.desc);
		cq.eq("agentInfo.id", getUser().getId());
		//查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, platformUser);
		this.platformUserService.getDataGridReturn(cq, true);
		List<PlatformUser> list = dataGrid.getReaults();
		List<PlatformUser> userList = new ArrayList<PlatformUser>();
		for(PlatformUser l :list){
			if(l.getHeadimgUrl() != null && l.getHeadimgUrl().startsWith("group1")){
				l.setHeadimgUrl(server+"/"+l.getHeadimgUrl());
			}
			userList.add(l);
		}
		dataGrid.setReaults(userList);
		TagUtil.datagrid(response, dataGrid);
	}
	

	/**
	 * 删除PlatformUser
	 * 
	 * @return
	 */
	@RequestMapping(params = "delPlatformUser")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson delPlatformUser(PlatformUser platformUser, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		platformUser = systemService.getEntity(PlatformUser.class, platformUser.getUserId());
		message = "删除成功";
		platformUserService.delete(platformUser);
		//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加PlatformUser
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "savePlatformUser")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson savePlatformUser(PlatformUser platformUser, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(platformUser.getUserId())) {
			message = "更新成功";
			this.isNew = false;
			PlatformUser t = platformUserService.get(PlatformUser.class, platformUser.getUserId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(platformUser, t);
				platformUserService.saveOrUpdate(t);
				//systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "添加成功";
			this.isNew = true;
			platformUserService.save(platformUser);
			//systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		return j;
	}

	/**
	 * PlatformUser列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdatePlatformUser")
	public ModelAndView addorupdatePlatformUser(PlatformUser platformUser, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(platformUser.getUserId())) {
			platformUser = platformUserService.getEntity(PlatformUser.class, platformUser.getUserId());
			req.setAttribute("platformUserPage", platformUser);
		}
		return new ModelAndView("user/platformuser/platformUserPage");
	}
}
