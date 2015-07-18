package com.yoxi.pfhudongtui.content.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
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
import com.yoxi.pfhudongtui.content.entity.AgentClassic;
import com.yoxi.pfhudongtui.content.service.AgentClassicService;
import com.yoxi.pfhudongtui.plugin.entity.ActClassic;
import com.yoxi.pfhudongtui.plugin.entity.ActClassicEntity;
import com.yoxi.pfhudongtui.plugin.entity.AgentContSwitch;
import com.yoxi.pfhudongtui.plugin.entity.Plugin;
import com.yoxi.pfhudongtui.plugin.entity.PluginAct;
import com.yoxi.pfhudongtui.plugin.entity.PluginActEntity;
import com.yoxi.pfhudongtui.plugin.service.ActClassicPicService;
import com.yoxi.pfhudongtui.plugin.service.AgentContSwitchService;
import com.yoxi.pfhudongtui.plugin.service.PluginPicService;
import com.yoxi.pfhudongtui.user.entity.AgentInfo;
import com.yoxi.pfhudongtui.utils.PropertiesUtils;

/**   
 * @Title: Controller
 * @Description: AgentClassic
 * @author jwhat generate
 * @date 2015-04-27 10:30:41
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/agentClassicController")
public class AgentClassicController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AgentClassicController.class);

	@Autowired
	private AgentClassicService agentClassicService;
	
	@Autowired
	private ActClassicPicService actClassicPicService;
	@Autowired
	private AgentContSwitchService agentContSwitchService;


	/**
	 * AgentClassic列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "agentClassic")
	public ModelAndView agentClassic(HttpServletRequest request) {
		AgentContSwitch agentContSwitch = systemService.findUniqueByProperty(AgentContSwitch.class, "agentId", getUser().getId());
		request.setAttribute("agentContSwitch", agentContSwitch);
		return new ModelAndView("content/agentclassic/agentClassicList");
	}
	
	

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "agentClassicGrid")
	public void agentClassicGrid(AgentClassic agentClassic,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String server = PropertiesUtils.getPara("fileAccessPath");
		String caseConType = agentContSwitchService.getStatus("caseConType", getUser().getId());
		if("1".equals(caseConType)){
			CriteriaQuery cq = new CriteriaQuery(AgentClassic.class, dataGrid);
			cq.eq("agentId", getUser().getId());
			//查询条件组装器
			com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, agentClassic);
			this.agentClassicService.getDataGridReturn(cq, true);
			List<AgentClassic> list = dataGrid.getReaults();
			List<AgentClassic> agentList = new ArrayList<AgentClassic>();
			for(AgentClassic a :list){
				if("1".equals(a.getActClassic().getType())){
					a.getActClassic().setTitle(a.getActClassic().getPluginAct().getTitle());
				}
				a.getActClassic().setBannerLogo(server+"/"+a.getActClassic().getBannerLogo());
				if(a.getActClassic().getAgentInfo() == null)
					a.getActClassic().setAuditState("");
				agentList.add(a);
			}
			dataGrid.setReaults(agentList);
			TagUtil.datagrid(response, dataGrid);
		}else{
			List<AgentClassic> agentList = new ArrayList<AgentClassic>();
			dataGrid.setReaults(agentList);
			TagUtil.datagrid(response, dataGrid);
		}
		
	}

	/**
	 * 删除AgentClassic
	 * 
	 * @return
	 */
	@RequestMapping(params = "delAgentClassic")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson delAgentClassic(AgentClassic agentClassic, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		agentClassic = systemService.getEntity(AgentClassic.class, agentClassic.getId());
		message = "删除成功";
		agentClassicService.delete(agentClassic);
		//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}

	
	/**
	 * 添加AgentClassic
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveAgentClassic")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson saveAgentClassic(AgentClassic agentClassic, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(agentClassic.getId())) {
			message = "更新成功";
			this.isNew = false;
			AgentClassic t = agentClassicService.get(AgentClassic.class, agentClassic.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(agentClassic, t);
				agentClassicService.saveOrUpdate(t);
				//systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "添加成功";
			this.isNew = true;
			agentClassicService.save(agentClassic);
			//systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		return j;
	}
	

	/**
	 * 添加ActClassic
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveActClassic")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson saveActClassic(ActClassic actClassic, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(actClassic.getId())) {
			message = "更新成功";
			this.isNew = false;
			ActClassic t = systemService.get(ActClassic.class, actClassic.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(actClassic, t);
				t.setUpdateTime(new Date());
				t.setAuditState("0");
				t.setAuditorId(null);
				t.setAuditTime(null);
				systemService.saveOrUpdate(t);
				actClassicPicService.saveClassicPic(actClassic,request);
				AgentClassic agentClassic = new AgentClassic();
				agentClassic.setUpdateTime(new Date());
				systemService.executeSql("update t_agent_classic set updateTime=? where classicId=? and agentId=?", new Date(),actClassic.getId(),getUser().getId());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "添加成功";
			this.isNew = true;
			actClassic.setCreateTime(new Date());
			actClassic.setUpdateTime(new Date());
			PluginAct act = systemService.getEntity( PluginAct.class, Integer.parseInt(request.getParameter("actId")));
			actClassic.setPluginAct(act);
			actClassic.setPluginId(act.getPlugin().getId());
			actClassic.setPlatformUser(act.getPlatformUser());
			actClassic.setAuditState("0");
			actClassic.setHideState("0");
			actClassic.setTitle(null);
			AgentInfo info = new AgentInfo();
			info.setId(getUser().getId());
			actClassic.setAgentInfo(info);
			systemService.save(actClassic);
			actClassicPicService.saveClassicPic(actClassic,request);
			agentClassicService.saveAgentClassic(actClassic);
		}
		return j;
	}
	/**
	 * 添加平台ActClassic
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveAddActClassic")
	@ResponseBody
	public AjaxJson saveAddActClassic(ActClassic actClassic,HttpServletRequest request) {
		   AjaxJson j = new AjaxJson();
		   String ids[] = request.getParameter("ids").split(",");
		   for(String id : ids){
			   if("".equals(id))continue;
			   AgentClassic agent = new AgentClassic();
			   ActClassic act = new ActClassic();
			   act.setId(Integer.parseInt(id));
			   agent.setActClassic(act);
			   agent.setSeq(1);
			   agent.setAgentId(getUser().getId());
			   agent.setHideState("0");
			   agent.setCreateTime(new Date());
			   agent.setUpdateTime(new Date());
			   systemService.save(agent);
		   }
		   j.setMsg("添加成功");
			j.setSuccess(true);
		return j;
	}
	/**
	 * AgentClassic列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdateAgentClassic")
	public ModelAndView addorupdateAgentClassic(AgentClassic agentClassic, HttpServletRequest req) {
		String server = PropertiesUtils.getPara("fileAccessPath");
		if (StringUtil.isNotEmpty(agentClassic.getId())) {
			agentClassic = agentClassicService.getEntity(AgentClassic.class, agentClassic.getId());
			req.setAttribute("agentClassicPage", agentClassic);
			return new ModelAndView("content/agentclassic/agentUpdateClassic").addObject("server", server);
		}
		return new ModelAndView("content/agentclassic/agentClassicPage").addObject("server", server);
	}
	/**
	 * 
	 * @param agentClassic
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "updateAgentSeqSort")
	public ModelAndView updateAgentSeqSort(AgentClassic agentClassic, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(agentClassic.getId())) {
			agentClassic = agentClassicService.getEntity(AgentClassic.class, agentClassic.getId());
			req.setAttribute("agentClassicPage", agentClassic);
		}
		return new ModelAndView("content/agentclassic/agentSort");
	} 
	
	/**
	 * 跳转插件活动界面
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "agentPluginList")
	public String roles(HttpServletRequest req) {
		return "content/agentclassic/agentPluginList";
	}
	/**
	 * 插件活动列表
	 * @param pluginAct
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "pluginActClassGrid")
	public void pluginActClassGrid(PluginAct pluginAct,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(PluginAct.class, dataGrid);
		cq.eq("agentInfo.id", getUser().getId());
		cq.add(Restrictions.isEmpty("actClassic"));
		//查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, pluginAct);
		this.systemService.getDataGridReturn(cq, true);
		List<PluginAct> list = dataGrid.getReaults();
		List<PluginActEntity> actList = new ArrayList<PluginActEntity>();
		for(PluginAct l :list){
				PluginActEntity act = new PluginActEntity();
				act.setId(l.getId());
				act.setTitle(l.getTitle());
				act.setPluginName(l.getPlugin().getName());
				actList.add(act);
		}
		dataGrid.setReaults(actList);
		TagUtil.datagrid(response, dataGrid);
	}
	
	

	/**
	 * 跳转所有经典案例列表
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "agentActClassicList")
	public String agentActClassicList(HttpServletRequest req) {
		return "content/agentclassic/agentActClassicList";
	}
	/**
	 * 经典案列列表
	 * @param actClassic
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "agentActClassicListGrid")
	public void agentActClassicListGrid(ActClassic actClassic,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		List<Integer> classicId = systemService.findListbySql("select classicId from t_agent_classic where agentId="+getUser().getId());
		CriteriaQuery cq = new CriteriaQuery(ActClassic.class, dataGrid);
		cq.addOrder("updateTime", SortDirection.desc);
		cq.eq("auditState", "1");
//		String title = oConvertUtils.getString(request.getParameter("acttitle"));
//		if(StringUtils.isNotBlank(title)){
//			cq.createAlias("pluginAct", "ts");
//			cq.add(Restrictions.or(Restrictions.like("title", title),Restrictions.like("ts.title", title)));
//		}
		if(classicId.size()>0){
			cq.add(Restrictions.not(Restrictions.in("id", classicId)));
		}
		//查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, actClassic);
		this.systemService.getDataGridReturn(cq, true);
		List<ActClassic> list = dataGrid.getReaults();
		List<ActClassic> actList = new ArrayList<ActClassic>();
		for(ActClassic a :list){
			if("1".equals(a.getType())){
				a.setTitle(a.getPluginAct().getTitle());
			}
			actList.add(a);
		}
		dataGrid.setReaults(actList);
		TagUtil.datagrid(response, dataGrid);
	}

	
	/**
	 * 跳转所有经典案例审核列表
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "auditAgentActList")
	public String auditAgentActList(HttpServletRequest req) {
		return "content/agentclassic/auditAgentActList";
	}
	
	/**
	 * 经典案列审核列表
	 * @param actClassic
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "auditAgentActListGrid")
	public void auditAgentActListGrid(ActClassic actClassic,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ActClassic.class, dataGrid);
		cq.isNotNull("agentInfo");
		//查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, actClassic);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	/**
	 * 代理商经典案例审核界面
	 * @param agentClassic
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "auditAgentActPage")
	public ModelAndView auditAgentActPage(ActClassic actClassic, HttpServletRequest req) {
		String server = PropertiesUtils.getPara("fileAccessPath");
		if (StringUtil.isNotEmpty(actClassic.getId())) {
			actClassic = systemService.getEntity(ActClassic.class, actClassic.getId());
			req.setAttribute("actClassicPage", actClassic);
		}
		return new ModelAndView("content/agentclassic/auditAgentActPage").addObject("server", server);
	} 
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "saveAuditAgentAct")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson saveAuditAgentAct(ActClassic actClassic,HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(actClassic.getId())) {
			message = "审核成功";
			this.isNew = false;
			ActClassic t = systemService.get(ActClassic.class, actClassic.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(actClassic, t);
				t.setAuditorId(getUser().getId());
				t.setAuditTime(new Date());
				systemService.saveOrUpdate(t);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return j;
	}
}
