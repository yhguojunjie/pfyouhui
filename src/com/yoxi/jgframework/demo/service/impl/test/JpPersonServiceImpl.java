package com.yoxi.jgframework.demo.service.impl.test;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.yoxi.jgframework.common.service.impl.CommonServiceImpl;
import com.yoxi.jgframework.demo.service.test.JpPersonServiceI;

@Service("jpPersonService")
@Transactional
public class JpPersonServiceImpl extends CommonServiceImpl implements JpPersonServiceI {
	
}