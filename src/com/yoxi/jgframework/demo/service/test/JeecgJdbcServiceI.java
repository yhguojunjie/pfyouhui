package com.yoxi.jgframework.demo.service.test;

import net.sf.json.JSONObject;

import com.yoxi.jgframework.common.model.json.DataGrid;
import com.yoxi.jgframework.common.service.CommonService;
import com.yoxi.jgframework.demo.entity.test.JeecgJdbcEntity;

public interface JeecgJdbcServiceI extends CommonService{
	public void getDatagrid1(JeecgJdbcEntity pageObj, DataGrid dataGrid);
	public void getDatagrid2(JeecgJdbcEntity pageObj, DataGrid dataGrid);
	public JSONObject getDatagrid3(JeecgJdbcEntity pageObj, DataGrid dataGrid);
}
