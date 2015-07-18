package com.yoxi.pfhudongtui.user.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoxi.pfhudongtui.user.service.PlatformUserService;
import com.yoxi.jgframework.common.service.impl.CommonServiceImpl;

@Service("platformUserService")
@Transactional
public class PlatformUserServiceImpl extends CommonServiceImpl implements PlatformUserService {
	
}