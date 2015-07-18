package com.yoxi.pfhudongtui.user.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoxi.pfhudongtui.user.service.JoinboardService;
import com.yoxi.jgframework.common.service.impl.CommonServiceImpl;

@Service("joinboardService")
@Transactional
public class JoinboardServiceImpl extends CommonServiceImpl implements JoinboardService {
	
}