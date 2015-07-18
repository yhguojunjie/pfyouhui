package com.yoxi.jgframework.system.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoxi.jgframework.system.service.TSClientVersionService;
import com.yoxi.jgframework.common.service.impl.CommonServiceImpl;

@Service("tSClientVersionService")
@Transactional
public class TSClientVersionServiceImpl extends CommonServiceImpl implements TSClientVersionService {
	
}