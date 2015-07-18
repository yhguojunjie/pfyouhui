package com.yoxi.pfhudongtui.user.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoxi.pfhudongtui.user.service.AgentInfoService;
import com.yoxi.jgframework.common.service.impl.CommonServiceImpl;

@Service("agentInfoService")
@Transactional
public class AgentInfoServiceImpl extends CommonServiceImpl implements AgentInfoService {
	
}