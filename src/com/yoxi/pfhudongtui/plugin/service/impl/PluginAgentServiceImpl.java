package com.yoxi.pfhudongtui.plugin.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoxi.pfhudongtui.plugin.entity.Plugin;
import com.yoxi.pfhudongtui.plugin.entity.PluginAgent;
import com.yoxi.pfhudongtui.plugin.service.PluginAgentService;
import com.yoxi.pfhudongtui.plugin.service.PluginService;
import com.yoxi.pfhudongtui.user.entity.AgentInfo;
import com.yoxi.jgframework.common.service.impl.CommonServiceImpl;

@Service("pluginAgentService")
@Transactional
public class PluginAgentServiceImpl extends CommonServiceImpl implements PluginAgentService {

	@Autowired
	private PluginService pluginService;
	
	@Override
	public void savePluginAgent(AgentInfo agentInfo) {
		List<Plugin> list = pluginService.findByProperty(Plugin.class, "status", "1");
		for(Plugin p : list){
			PluginAgent agent = new PluginAgent();
			agent.setPlugin(p);
			agent.setAgentInfo(agentInfo);
			agent.setSalePrice(0.00);
			agent.setOnlineState("0");
			agent.setCreateTime(new Date());
			this.save(agent);
		}
	}

	
	
}