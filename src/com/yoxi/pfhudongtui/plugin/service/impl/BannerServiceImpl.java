package com.yoxi.pfhudongtui.plugin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoxi.pfhudongtui.plugin.service.BannerService;
import com.yoxi.jgframework.common.service.impl.CommonServiceImpl;

@Service("bannerService")
@Transactional
public class BannerServiceImpl extends CommonServiceImpl implements BannerService {
	
}