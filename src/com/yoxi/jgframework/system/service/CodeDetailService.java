package com.yoxi.jgframework.system.service;

import com.yoxi.jgframework.common.service.CommonService;
import com.yoxi.jgframework.system.entity.TSCodeDetail;


public interface CodeDetailService extends CommonService{
	

	/*
	 * 对代码表拖动进行排序
	 * currentCodeDetail 当前拖动的行
	 * beforeCodeDetail 往前拖动时，拖到那条记录的前面
	 * backCodeDetail  往后拖动时，拖到那条记录的后面
	 */
	public int orderCodeDetail(TSCodeDetail currentCodeDetail,TSCodeDetail beforeCodeDetail,TSCodeDetail backCodeDetail);
	
}
