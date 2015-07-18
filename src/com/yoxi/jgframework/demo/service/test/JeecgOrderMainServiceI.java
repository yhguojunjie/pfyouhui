package com.yoxi.jgframework.demo.service.test;

import java.util.List;


import com.yoxi.jgframework.common.service.CommonService;
import com.yoxi.jgframework.demo.entity.test.JeecgOrderCustomEntity;
import com.yoxi.jgframework.demo.entity.test.JeecgOrderMainEntity;
import com.yoxi.jgframework.demo.entity.test.JeecgOrderProductEntity;


public interface JeecgOrderMainServiceI extends CommonService{
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(JeecgOrderMainEntity jeecgOrderMain,List<JeecgOrderProductEntity> jeecgOrderProducList,List<JeecgOrderCustomEntity> jeecgOrderCustomList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(JeecgOrderMainEntity jeecgOrderMain,List<JeecgOrderProductEntity> jeecgOrderProducList,List<JeecgOrderCustomEntity> jeecgOrderCustomList) ;
	public void delMain (JeecgOrderMainEntity jeecgOrderMain);
}
