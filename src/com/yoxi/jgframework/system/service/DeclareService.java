package com.yoxi.jgframework.system.service;

import java.util.List;

import com.yoxi.jgframework.common.service.CommonService;
import com.yoxi.jgframework.system.entity.TSAttachment;


public interface DeclareService extends CommonService{
	
	public List<TSAttachment> getAttachmentsByCode(String businessKey,String description);
	
}
