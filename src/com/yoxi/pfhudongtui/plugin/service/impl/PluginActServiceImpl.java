package com.yoxi.pfhudongtui.plugin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoxi.pfhudongtui.plugin.service.PluginActService;
import com.yoxi.jgframework.common.service.impl.CommonServiceImpl;

@Service("pluginActService")
@Transactional
public class PluginActServiceImpl extends CommonServiceImpl implements PluginActService {
	
}