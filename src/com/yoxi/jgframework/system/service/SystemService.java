package com.yoxi.jgframework.system.service;

import java.util.Set;

import com.yoxi.jgframework.common.service.CommonService;
import com.yoxi.jgframework.system.entity.TSCodeDetail;
import com.yoxi.jgframework.system.entity.TSCodeType;
import com.yoxi.jgframework.system.entity.TSFunction;
import com.yoxi.jgframework.system.entity.TSUser;

public interface SystemService extends CommonService {
	/**
	 * 登陆用户检查
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public TSUser checkUserExits(TSUser user) throws Exception;

	/**
	 * 日志添加
	 * 
	 * @param LogContent 内容
	 * @param loglevel 级别
	 * @param operatetype 类型
	 * @param objectType 操作对象类型
	 * @param objId 操作对象id
	 * @param takeTime 操作耗时
	 */
	public void addLog(String LogContent, Short loglevel, Short operatetype, String objectType, String objectId, int takeTime);

	/**
	 * 根据类型编码和类型名称获取Type,如果为空则创建一个
	 * 
	 * @param typecode
	 * @param typename
	 * @return
	 */
	public TSCodeDetail getCodeDetail(String typecode, String typename, TSCodeType tsTypegroup);

	/**
	 * 根据类型分组编码和名称获取TypeGroup,如果为空则创建一个
	 * 
	 * @param id 代码类别ID
	 * @param typeName 名称
	 * @param codeName1Label Name1标签
	 * @param codeName2Label Name2标签
	 * @param codeName3Label Name3标签
	 * @param codeName4Label Name4标签
	 * @param allowAdd 允许新建
	 * @param allowModify 允许修改
	 * @param allowDelete 允许删除
	 * @return
	 */
	public TSCodeType getCodeType(String id, String typeName, String codeName1Label, String codeName2Label, String codeName3Label, String codeName4Label, Boolean allowAdd, Boolean allowModify, Boolean allowDelete, String typeMemo);

	// /**
	// * 获取大于客户端版本的版本记录
	// * @param num 客户端版本
	// * @return 返回版本数据集
	// */
	// public List<TSVersion> getVersionListByNum(Integer num);

	// ----------------------------------------------------------------
	// update-start--Author:anchao Date:20130415 for：按钮权限控制
	// ----------------------------------------------------------------
	/**
	 * 根据用户ID 和 菜单Id 获取 具有操作权限的按钮Codes
	 * 
	 * @param roleId
	 * @param functionId
	 * @return
	 */
	public Set<String> getOperationCodesByUserIdAndFunctionId(Integer userId, Integer functionId);

	/**
	 * 根据角色ID 和 菜单Id 获取 具有操作权限的按钮Codes
	 * 
	 * @param roleId
	 * @param functionId
	 * @return
	 */
	public Set<String> getOperationCodesByRoleIdAndFunctionId(Integer roleId, Integer functionId);

	// ----------------------------------------------------------------
	// update-start--Author:anchao Date:20130415 for：按钮权限控制
	// ----------------------------------------------------------------
	/**
	 * 根据编码获取字典组
	 * 
	 * @param typegroupCode
	 * @return
	 */
	public TSCodeType getCodeTypeByCode(String typegroupCode);

	/**
	 * 对数据字典进行缓存
	 */
	public void initAllTypeGroups();

	/**
	 * 刷新字典缓存
	 * 
	 * @param type
	 */
	public void refleshCodeDetailsCach(TSCodeDetail type);

	/**
	 * 刷新字典分组缓存
	 */
	public void refleshTypeGroupCach();

	/**
	 * 将config数据放到webApplicationContext
	 */
	public void initAllConfig();

	/**
	 * 刷新菜单
	 * 
	 * @param id
	 */
	public void flushRoleFunciton(Integer id, TSFunction newFunciton);
}
