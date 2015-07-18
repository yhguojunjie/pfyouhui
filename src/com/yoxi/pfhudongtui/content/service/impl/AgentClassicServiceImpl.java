package com.yoxi.pfhudongtui.content.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoxi.pfhudongtui.content.entity.AgentClassic;
import com.yoxi.pfhudongtui.content.service.AgentClassicService;
import com.yoxi.pfhudongtui.plugin.entity.ActClassic;
import com.yoxi.jgframework.common.service.impl.CommonServiceImpl;

@Service("agentClassicService")
@Transactional
public class AgentClassicServiceImpl extends CommonServiceImpl implements AgentClassicService {

	@Override
	public void saveAgentClassic(ActClassic actClassic) {
		AgentClassic agent = new AgentClassic();
		agent.setActClassic(actClassic);
		agent.setSeq(actClassic.getSeq());
		agent.setAgentId(actClassic.getAgentInfo().getId());
		agent.setHideState("0");
		agent.setCreateTime(new Date());
		agent.setUpdateTime(new Date());
		this.save(agent);
	}
	
}