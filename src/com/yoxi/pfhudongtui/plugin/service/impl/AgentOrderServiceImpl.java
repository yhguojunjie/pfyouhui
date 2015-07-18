package com.yoxi.pfhudongtui.plugin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoxi.pfhudongtui.plugin.service.AgentOrderService;
import com.yoxi.jgframework.common.service.impl.CommonServiceImpl;

@Service("agentOrderService")
@Transactional
public class AgentOrderServiceImpl extends CommonServiceImpl implements AgentOrderService {

	@Override
	public Integer showDownOrder(Integer orderId) {
		 String sql ="update t_agent_order set tradeState=1 where id=?";
	     return this.executeSql(sql, orderId);
	}
	
}