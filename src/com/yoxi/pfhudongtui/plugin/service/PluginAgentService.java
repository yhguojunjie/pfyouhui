package com.yoxi.pfhudongtui.plugin.service;

import com.yoxi.jgframework.common.service.CommonService;
import com.yoxi.pfhudongtui.user.entity.AgentInfo;

public interface PluginAgentService extends CommonService{

	/**
	 * 为新建的代理商 添加所有插件
	 */
	public void savePluginAgent(AgentInfo agentInfo);
}
