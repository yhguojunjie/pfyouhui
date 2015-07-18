package com.yoxi.pfhudongtui.plugin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoxi.jgframework.common.service.impl.CommonServiceImpl;
import com.yoxi.pfhudongtui.plugin.service.BrandService;

@Service("brandService")
@Transactional
public class BrandServiceImpl extends CommonServiceImpl implements BrandService {

}