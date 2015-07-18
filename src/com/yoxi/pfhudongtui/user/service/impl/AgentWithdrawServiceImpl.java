package com.yoxi.pfhudongtui.user.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoxi.pfhudongtui.user.service.AgentWithdrawService;
import com.yoxi.jgframework.common.service.impl.CommonServiceImpl;

@Service("agentWithdrawService")
@Transactional
public class AgentWithdrawServiceImpl extends CommonServiceImpl implements AgentWithdrawService {
	
}