package com.yoxi.pfhudongtui.pay.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoxi.pfhudongtui.pay.service.BuserOrderService;
import com.yoxi.jgframework.common.service.impl.CommonServiceImpl;

@Service("buserOrderService")
@Transactional
public class BuserOrderServiceImpl extends CommonServiceImpl implements BuserOrderService {
	
}