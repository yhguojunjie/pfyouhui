package com.yoxi.pfhudongtui.plugin.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoxi.jgframework.common.service.impl.CommonServiceImpl;
import com.yoxi.pfhudongtui.plugin.service.AgentContSwitchService;

@Service("agentContSwitchService")
@Transactional
public class AgentContSwitchServiceImpl extends CommonServiceImpl implements AgentContSwitchService {

	@Override
	public String getStatus(String filedName,Integer userId) {
		List<String> a = findListbySql("select "+filedName+" from t_agent_cont_switch where agentId ="+userId);
		if(a.size() > 0)
			return  String.valueOf(a.get(0));
		return null;
	}

}