package com.yoxi.pfhudongtui.plugin.service;

import javax.servlet.http.HttpServletRequest;

import com.yoxi.jgframework.common.service.CommonService;
import com.yoxi.pfhudongtui.plugin.entity.ActClassic;

public interface ActClassicPicService extends CommonService{

	public void saveClassicPic(ActClassic actClassic,HttpServletRequest request);
}
