package com.yoxi.pfhudongtui.plugin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoxi.jgframework.common.service.impl.CommonServiceImpl;
import com.yoxi.pfhudongtui.plugin.service.AgentBrandService;

@Service("agentBrandService")
@Transactional
public class AgentBrandServiceImpl extends CommonServiceImpl implements AgentBrandService {

}