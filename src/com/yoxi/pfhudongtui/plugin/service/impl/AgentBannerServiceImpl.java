package com.yoxi.pfhudongtui.plugin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoxi.jgframework.common.service.impl.CommonServiceImpl;
import com.yoxi.pfhudongtui.plugin.service.AgentBannerService;

@Service("agentBannerService")
@Transactional
public class AgentBannerServiceImpl extends CommonServiceImpl implements AgentBannerService {

}