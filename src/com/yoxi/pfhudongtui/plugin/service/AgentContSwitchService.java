package com.yoxi.pfhudongtui.plugin.service;

import com.yoxi.jgframework.common.service.CommonService;

public interface AgentContSwitchService extends CommonService {

	public String getStatus(String filedName,Integer userId);
}
