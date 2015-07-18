package com.yoxi.pfhudongtui.user.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoxi.jgframework.common.service.impl.CommonServiceImpl;
import com.yoxi.pfhudongtui.user.service.FUserService;

@Service("fUserService")
@Transactional
public class FUserServiceImpl extends CommonServiceImpl implements FUserService {

}