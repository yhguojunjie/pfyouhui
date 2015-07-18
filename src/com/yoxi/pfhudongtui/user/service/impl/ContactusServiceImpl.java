package com.yoxi.pfhudongtui.user.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoxi.pfhudongtui.user.service.ContactusService;
import com.yoxi.jgframework.common.service.impl.CommonServiceImpl;

@Service("contactusService")
@Transactional
public class ContactusServiceImpl extends CommonServiceImpl implements ContactusService {
	
}