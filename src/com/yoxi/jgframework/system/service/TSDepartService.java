package com.yoxi.jgframework.system.service;

import java.util.List;
import java.util.Map;

import com.yoxi.jgframework.common.service.CommonService;
import com.yoxi.jgframework.system.entity.TSDepart;

public interface TSDepartService extends CommonService {
	/**
	 * 根据用户Id查询用户所在部门列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> queryListMaps(Integer userId);
	
	/**
	 * 根据账号id和部门类型查询部门
	 * @param userId
	 * @param departType
	 * @return
	 */
	public TSDepart query(Integer userId,int departType);
	
	/**
	 * 查询渠道
	 * @return
	 */
	public List<Map<String, Object>> queryList(String departType);
	
	/**
	 * 根据用户id查找所属部门
	 * @param userId
	 * @return
	 */
	public TSDepart query(Integer userId);
	
	/**
	 * 根据父部门id或者没有部门id直接产生一个部门id
	 * @param id
	 * @return
	 */
	public String makeDepartId(String id);
	/**
	 * 根据id，查找是否有子部门的数据
	 */
	public List<Map<String, Object>>  queryParentId(String id);
	/**
	 * 根据id，查找该部门下面是否有用户
	 */
	public List<Map<String, Object>>  queryUserId(String id);
	
	/**
	 * 根据部门id，查询自己和所有子部门
	 * @param departType
	 * @return
	 */
	public List<Map<String, Object>> queryChildList(String id);
}
