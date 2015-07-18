package com.yoxi.pfhudongtui.plugin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoxi.pfhudongtui.plugin.service.ActClassicService;
import com.yoxi.jgframework.common.service.impl.CommonServiceImpl;

@Service("actClassicService")
@Transactional
public class ActClassicServiceImpl extends CommonServiceImpl implements ActClassicService {
	
}