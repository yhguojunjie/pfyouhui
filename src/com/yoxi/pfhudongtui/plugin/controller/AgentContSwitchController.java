package com.yoxi.pfhudongtui.plugin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yoxi.jgframework.common.controller.BaseController;
import com.yoxi.jgframework.common.model.json.AjaxJson;
import com.yoxi.pfhudongtui.plugin.entity.AgentContSwitch;
import com.yoxi.pfhudongtui.plugin.service.AgentContSwitchService;
/**
 * 开关控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/agentContSwitchController")
public class AgentContSwitchController extends BaseController {

	@Autowired
	private AgentContSwitchService agentContSwitchService;

	/**
	 * 经典案例开关0使用平台数据1自己管理
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "saveOrUpdateAgentContSwitch")
	@ResponseBody
	public AjaxJson saveOrUpdateAgentContSwitch( HttpServletRequest request) {
		String caseConType = request.getParameter("caseConType");
		AgentContSwitch agentContSwitch = systemService.findUniqueByProperty(AgentContSwitch.class, "agentId", getUser().getId());
		AjaxJson j = new AjaxJson();
		if(agentContSwitch == null){
			agentContSwitch = new AgentContSwitch();
			agentContSwitch.setAgentId(getUser().getId());
			agentContSwitch.setActRecState("0");
			agentContSwitch.setBannerState("0");
			agentContSwitch.setCaseState("0");
			agentContSwitch.setChannelState("0");
			agentContSwitch.setFaqState("0");
			agentContSwitch.setPluginRecState("0");
			agentContSwitch.setCaseConType(caseConType);
			agentContSwitch.setChannelConType("0");
			this.systemService.save(agentContSwitch);
		}else{
			this.systemService.executeSql("update t_agent_cont_switch set caseConType=? where agentId=?", caseConType,getUser().getId());
		}
		message = "切换成功";	
		j.setMsg(message);
		return j;
	}
	/**
	 * 代理商渠道添加 0使用平台数据，1自己管理数据
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "saveAgentChannel")
	@ResponseBody
	public AjaxJson saveAgentChannel( HttpServletRequest request) {
		String channelConType = request.getParameter("channelConType");
		AgentContSwitch agentContSwitch = systemService.findUniqueByProperty(AgentContSwitch.class, "agentId", getUser().getId());
		AjaxJson j = new AjaxJson();
		if(agentContSwitch == null){
			agentContSwitch = new AgentContSwitch();
			agentContSwitch.setAgentId(getUser().getId());
			agentContSwitch.setActRecState("0");
			agentContSwitch.setBannerState("0");
			agentContSwitch.setCaseState("0");
			agentContSwitch.setChannelState("0");
			agentContSwitch.setFaqState("0");
			agentContSwitch.setPluginRecState("0");
			agentContSwitch.setCaseConType("0");
			agentContSwitch.setChannelConType(channelConType);
			this.systemService.save(agentContSwitch);
		}else{
			this.systemService.executeSql("update t_agent_cont_switch set channelConType=? where agentId=?", channelConType,getUser().getId());
		}
		message = "切换成功";	
		j.setMsg(message);
		return j;
	}

}
