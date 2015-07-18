package com.yoxi.pfhudongtui.plugin.service;

import com.yoxi.jgframework.common.service.CommonService;

public interface AgentOrderService extends CommonService{
	/**
	 * 关闭交易订单
	 * @param orderId
	 */
	public Integer showDownOrder(Integer orderId);

}
