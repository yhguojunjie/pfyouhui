package com.yoxi.pfhudongtui.pay.service;

import javax.servlet.http.HttpServletRequest;

import com.yoxi.jgframework.common.service.CommonService;
import com.yoxi.pfhudongtui.pay.entity.DirectPaySyncBack;

/**
 * 
 * 支付宝serivce
 *
 * @author wql
 *
 * @Date 2015年4月1日
 *
 */
public interface AlipayService extends CommonService {
	
	/**
	 * 即时到账回调处理
	 * @param directPaySyncBack
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String directPayBack(DirectPaySyncBack directPaySyncBack,HttpServletRequest request);
}
