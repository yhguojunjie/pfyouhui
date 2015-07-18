package com.yoxi.pfhudongtui.plugin.controller;

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
import com.yoxi.jgframework.ui.tag.core.easyui.TagUtil;
import com.yoxi.jgframework.util.MyBeanUtils;
import com.yoxi.jgframework.util.StringUtil;
import com.yoxi.pfhudongtui.plugin.entity.AgentBanner;
import com.yoxi.pfhudongtui.plugin.entity.AgentContSwitch;
import com.yoxi.pfhudongtui.plugin.service.AgentBannerService;
import com.yoxi.pfhudongtui.plugin.service.AgentContSwitchService;
import com.yoxi.pfhudongtui.user.service.AgentInfoService;
import com.yoxi.pfhudongtui.utils.PropertiesUtils;
import com.yoxi.pfhudongtui.vo.plugin.AgentBannerVO;

/**
 * @Title: Controller
 * @Description: AgentBanner
 * @author jwhat generate
 * @date 2015-04-21 11:53:22
 * @version V1.0
 * 
 */
@Controller
@RequestMapping("/agentBannerController")
public class AgentBannerController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AgentBannerController.class);

	@Autowired
	private AgentBannerService agentBannerService;
	@Autowired
	private AgentContSwitchService agentContSwitchService;
	@Autowired
	private AgentInfoService agentInfoService;

	/**
	 * AgentBanner列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "agentBanner")
	public ModelAndView agentBanner(HttpServletRequest request) {
		AgentContSwitch agentContSwitch = agentContSwitchService.get(AgentContSwitch.class, getUser().getId());
		if (agentContSwitch != null) {
			request.setAttribute("bannerState", agentContSwitch.getBannerState());
		} else {
			request.setAttribute("bannerState", "2");
		}
		return new ModelAndView("plugin/agentbanner/agentBannerList");
	}

	/**
	 * 
	 * @param agentBanner
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "updateAgentSeqSort")
	public ModelAndView updateAgentSeqSort(AgentBanner agentBanner, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(agentBanner.getId())) {
			agentBanner = agentBannerService.getEntity(AgentBanner.class, agentBanner.getId());
			req.setAttribute("agentBannerPage", agentBanner);
		}
		return new ModelAndView("plugin/agentbanner/agentSort");
	}

	/**
	 * AgentBanner审核列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "auditAgentBanner")
	public ModelAndView auditAgentBanner(HttpServletRequest request) {
		AgentContSwitch agentContSwitch = agentContSwitchService.get(AgentContSwitch.class, getUser().getId());
		if (agentContSwitch != null) {
			request.setAttribute("bannerState", agentContSwitch.getBannerState());
		} else {
			request.setAttribute("bannerState", "2");
		}
		return new ModelAndView("plugin/agentbanner/auditAgentBannerList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "agentBannerGrid")
	public void agentBannerGrid(AgentBanner agentBanner, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		//String ptpath = PropertiesUtils.getPara("ptpath") + PropertiesUtils.getPara("ptplugindetail");
		List<String> mydomain = systemService.findListbySql("select mydomain from t_agent_info where id="+getUser().getId());
		String ptpath="";
		if(!mydomain.isEmpty()){
			 ptpath = mydomain.get(0)+ PropertiesUtils.getPara("ptplugindetail");
		}
		String server = PropertiesUtils.getPara("fileAccessPath");
		CriteriaQuery cq = new CriteriaQuery(AgentBanner.class, dataGrid);
		cq.eq("agentId", getUser().getId());
		// 查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, agentBanner);
		this.agentBannerService.getDataGridReturn(cq, true);
		List<AgentBanner> list = dataGrid.getReaults();
		List<AgentBannerVO> agentBannerList = new ArrayList<AgentBannerVO>();
		/*
		 * for (AgentBanner l : list) { l.setPcLogo(server + "/" +
		 * l.getPcLogo()); l.setWxLogo(server + "/" + l.getWxLogo());
		 * agentBannerList.add(l); }
		 */
		for (AgentBanner l : list) {
			AgentBannerVO ll = new AgentBannerVO();
			ll.setPcLogo(server + "/" + l.getPcLogo());
			ll.setWxLogo(server + "/" + l.getWxLogo());
			ll.setSeq(l.getSeq());
			if (l.getPcLink().contains(ptpath)) {
				ll.setLinkType("1");
			} else {
				ll.setLinkType("2");
			}
			ll.setPcLink(l.getPcLink());
			ll.setWxLink(l.getWxLink());
			ll.setAuditstate(l.getAuditstate());
			ll.setCreateTime(l.getCreateTime());
			ll.setHideState(l.getHideState());
			ll.setId(l.getId());
			agentBannerList.add(ll);
		}

		dataGrid.setReaults(agentBannerList);
		TagUtil.datagrid(response, dataGrid);

	}

	/**
	 * easyui AJAX请求数据2
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "agentBannerGrid2")
	public void agentBannerGrid2(AgentBanner agentBanner, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String server = PropertiesUtils.getPara("fileAccessPath");
		CriteriaQuery cq = new CriteriaQuery(AgentBanner.class, dataGrid);
		cq.notEq("auditstate", " ");
		cq.eq("agentId", getUser().getId());
		// cq.add(Restrictions.in("auditState", userId));
		// 查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, agentBanner);
		this.agentBannerService.getDataGridReturn(cq, true);
		List<AgentBanner> list = dataGrid.getReaults();
		List<AgentBanner> agentBannerList2 = new ArrayList<AgentBanner>();
		for (AgentBanner l : list) {
			l.setPcLogo(server + "/" + l.getPcLogo());
			l.setWxLogo(server + "/" + l.getWxLogo());
			agentBannerList2.add(l);
		}
		dataGrid.setReaults(agentBannerList2);
		TagUtil.datagrid(response, dataGrid);

	}

	/**
	 * 删除AgentBanner
	 * 
	 * @return
	 */
	@RequestMapping(params = "delAgentBanner")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson delAgentBanner(AgentBanner agentBanner, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		agentBanner = systemService.getEntity(AgentBanner.class, agentBanner.getId());
		message = "删除成功";
		agentBannerService.delete(agentBanner);
		// systemService.addLog(message, Globals.Log_Type_DEL,
		// Globals.Log_Leavel_INFO);

		j.setMsg(message);
		return j;
	}

	/**
	 * 审核AgentBanner
	 * 
	 * @return
	 */
	@RequestMapping(params = "changeAgentBanner")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson changeAgentBanner(AgentBanner agentBanner, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		// String bannerState = request.getParameter("auditState");
		AgentBanner t = agentBannerService.get(AgentBanner.class, agentBanner.getId());
		message = "审核成功";
		t.setAuditTime(new Date());
		t.setAuditstate(agentBanner.getAuditstate());
		agentBannerService.saveOrUpdate(t);
		// systemService.addLog(message, Globals.Log_Type_DEL,
		// Globals.Log_Leavel_INFO);

		j.setMsg(message);
		return j;
	}

	/**
	 * 修改AgentContSwitch
	 * 
	 * @return
	 */
	@RequestMapping(params = "changeAgentContSwitch")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson delAgentBanner(AgentContSwitch agentContSwitch, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String bannerState = request.getParameter("bannerState");
		message = "更新成功";
		AgentContSwitch t = agentContSwitchService.get(AgentContSwitch.class, getUser().getId());

		if (t == null) {
			AgentContSwitch tt = new AgentContSwitch();
			tt.setBannerState(bannerState);
			tt.setPluginRecState("0");
			tt.setActRecState("0");
			tt.setCaseState("0");
			tt.setFaqState("0");
			tt.setChannelState("0");
			tt.setCaseConType("0");
			tt.setChannelConType("0");

			tt.setAgentId(getUser().getId());
			agentContSwitchService.save(tt);
		} else {
			try {
				t.setBannerState(bannerState);
				MyBeanUtils.copyBeanNotNull2Bean(agentContSwitch, t);
				agentContSwitchService.saveOrUpdate(t);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// systemService.addLog(message, Globals.Log_Type_DEL,
		// Globals.Log_Leavel_INFO);

		j.setMsg(message);
		return j;
	}

	/**
	 * 添加AgentBanner
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveAgentBanner")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson saveAgentBanner(AgentBanner agentBanner, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(agentBanner.getId())) {
			message = "更新成功";
			this.isNew = false;
			AgentBanner t = agentBannerService.get(AgentBanner.class, agentBanner.getId());
			try {
				agentBanner.setAuditstate("0");
				MyBeanUtils.copyBeanNotNull2Bean(agentBanner, t);
				agentBannerService.saveOrUpdate(t);
				// systemService.addLog(message, Globals.Log_Type_UPDATE,
				// Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "添加成功";
			this.isNew = true;
			agentBanner.setCreateTime(new Date());
			agentBanner.setUpdateTime(new Date());
			agentBanner.setAuditstate("0");
			agentBanner.setType("1");
			agentBanner.setAgentId(getUser().getId());
			agentBannerService.save(agentBanner);
			// systemService.addLog(message, Globals.Log_Type_INSERT,
			// Globals.Log_Leavel_INFO);
		}

		return j;
	}

	/**
	 * 更改AgentBanner
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "updateAgentBanner")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson updateAgentBanner(AgentBanner agentBanner, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(agentBanner.getId())) {
			message = "更新成功";
			this.isNew = false;
			AgentBanner t = agentBannerService.get(AgentBanner.class, agentBanner.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(agentBanner, t);
				agentBannerService.saveOrUpdate(t);
				// systemService.addLog(message, Globals.Log_Type_UPDATE,
				// Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return j;
	}

	/**
	 * AgentBanner列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdateAgentBanner")
	public ModelAndView addorupdateAgentBanner(AgentBanner agentBanner, HttpServletRequest req) {
		String server = PropertiesUtils.getPara("fileAccessPath");
		Integer idaa = agentBanner.getId();
		if (StringUtil.isNotEmpty(agentBanner.getId())) {
			agentBanner = agentBannerService.getEntity(AgentBanner.class, agentBanner.getId());
			req.setAttribute("agentBannerPage", agentBanner);
		}
		return new ModelAndView("plugin/agentbanner/agentBannerPage").addObject("server", server);
	}

	/**
	 * AgentBanner列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "auditAddorupdateAgentBanner")
	public ModelAndView auditAddorupdateAgentBanner(AgentBanner agentBanner, HttpServletRequest req) {
		String server = PropertiesUtils.getPara("fileAccessPath");
		if (StringUtil.isNotEmpty(agentBanner.getId())) {
			agentBanner = agentBannerService.getEntity(AgentBanner.class, agentBanner.getId());
			req.setAttribute("agentBannerPage", agentBanner);
		}
		return new ModelAndView("plugin/agentbanner/auditAgentBannerPage").addObject("server", server);
	}

}
