package com.yoxi.pfhudongtui.content.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.yoxi.pfhudongtui.content.entity.AgentChannel;
import com.yoxi.pfhudongtui.content.entity.AgentClassic;
import com.yoxi.pfhudongtui.content.service.AgentChannelService;
import com.yoxi.pfhudongtui.plugin.entity.ActClassic;
import com.yoxi.pfhudongtui.plugin.entity.AgentContSwitch;
import com.yoxi.pfhudongtui.plugin.service.AgentContSwitchService;
import com.yoxi.pfhudongtui.user.entity.Channel;
import com.yoxi.pfhudongtui.utils.PropertiesUtils;

/**   
 * @Title: Controller
 * @Description: AgentChannel
 * @author jwhat generate
 * @date 2015-04-29 10:38:43
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/agentChannelController")
public class AgentChannelController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AgentChannelController.class);

	@Autowired
	private AgentChannelService agentChannelService;
	@Autowired
	private AgentContSwitchService agentContSwitchService;

	/**
	 * AgentChannel列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "agentChannel")
	public ModelAndView agentChannel(HttpServletRequest request) {
		AgentContSwitch agentContSwitch = systemService.findUniqueByProperty(AgentContSwitch.class, "agentId", getUser().getId());
		request.setAttribute("agentContSwitch", agentContSwitch);
		return new ModelAndView("content/agentchannel/agentChannelList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "agentChannelGrid")
	public void agentChannelGrid(AgentChannel agentChannel,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String server = PropertiesUtils.getPara("fileAccessPath");
		String channelConType = agentContSwitchService.getStatus("channelConType", getUser().getId());
		if("1".equals(channelConType)){
			CriteriaQuery cq = new CriteriaQuery(AgentChannel.class, dataGrid);
			cq.addOrder("seq", SortDirection.asc);
			cq.eq("agentId", getUser().getId());
			//查询条件组装器
			com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, agentChannel);
			this.agentChannelService.getDataGridReturn(cq, true);
			List<AgentChannel> list = dataGrid.getReaults();
			List<AgentChannel> channelList = new ArrayList<AgentChannel>();
			for(AgentChannel a :list){
				a.getChannel().setLogo(server+"/"+a.getChannel().getLogo());
				channelList.add(a);
			}
			dataGrid.setReaults(channelList);
			TagUtil.datagrid(response, dataGrid);
		}else{
			List<AgentClassic> agentList = new ArrayList<AgentClassic>();
			dataGrid.setReaults(agentList);
			TagUtil.datagrid(response, dataGrid);
		}
	}

	/**
	 * 删除AgentChannel
	 * 
	 * @return
	 */
	@RequestMapping(params = "delAgentChannel")
	@ResponseBody
	@DeleteAnnotation
	public AjaxJson delAgentChannel(AgentChannel agentChannel, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		agentChannel = systemService.getEntity(AgentChannel.class, agentChannel.getId());
		message = "删除成功";
		agentChannelService.delete(agentChannel);
		//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加AgentChannel
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveAgentChannel")
	@ResponseBody
	@AddOrUpdateAnnotation
	public AjaxJson saveAgentChannel(AgentChannel agentChannel, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(agentChannel.getId())) {
			message = "更新成功";
			this.isNew = false;
			AgentChannel t = agentChannelService.get(AgentChannel.class, agentChannel.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(agentChannel, t);
				t.setUpdateTime(new Date());
				agentChannelService.saveOrUpdate(t);
				//systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			message = "添加成功";
			this.isNew = true;
			agentChannelService.save(agentChannel);
			//systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		
		return j;
	}
	
	/**
	 * 添加渠道
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "saveChannel")
	@ResponseBody
	public AjaxJson saveChannel(Channel ch,HttpServletRequest request) {
		   AjaxJson j = new AjaxJson();
		   String ids[] = request.getParameter("ids").split(",");
		   for(String id : ids){
			   if("".equals(id))continue;
			   AgentChannel agentChannel = new AgentChannel();
			   Channel channel = new Channel();
			   channel.setId(Integer.parseInt(id));
			   agentChannel.setChannel(channel);
			   agentChannel.setAgentId(getUser().getId());
			   agentChannel.setSeq(1);
			   agentChannel.setHideState("0");
			   agentChannel.setCreateTime(new Date());
			   agentChannel.setUpdateTime(new Date());
			   systemService.save(agentChannel);
		   }
		   j.setMsg("添加成功");
			j.setSuccess(true);
		return j;
	}

	/**
	 * AgentChannel列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdateAgentChannel")
	public ModelAndView addorupdateAgentChannel(AgentChannel agentChannel, HttpServletRequest req) {
		String server = PropertiesUtils.getPara("fileAccessPath");
		if (StringUtil.isNotEmpty(agentChannel.getId())) {
			agentChannel = agentChannelService.getEntity(AgentChannel.class, agentChannel.getId());
			req.setAttribute("agentChannelPage", agentChannel);
		}
		return new ModelAndView("content/agentchannel/agentChannelPage").addObject("server", server);
	}
	
	/**
	 * 跳转渠道列表
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "channelAgentList")
	public String channelAgentList(HttpServletRequest req) {
		return "content/agentchannel/channelAgentList";
	}
	/**
	 * 经典案列列表
	 * @param actClassic
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "channelAgentListGrid")
	public void channelAgentListGrid(Channel channel,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		List<Integer> classicId = systemService.findListbySql("select channelId from t_agent_channel where agentId="+getUser().getId());
		CriteriaQuery cq = new CriteriaQuery(Channel.class, dataGrid);
		String myChannelAgent = oConvertUtils.getString(request.getParameter("myChannelAgent"));
		if(StringUtils.isNotBlank(myChannelAgent)){
			if("1".equals(myChannelAgent)){
				cq.eq("agentId", getUser().getId());
			}
		}
		cq.eq("auditState", "1");
		cq.addOrder("createTime", SortDirection.desc);
		if(classicId.size()>0){
			cq.add(Restrictions.not(Restrictions.in("id", classicId)));
		}
		//查询条件组装器
		com.yoxi.jgframework.extend.hqlsearch.HqlGenerateUtil.installHql(cq, channel);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
}
