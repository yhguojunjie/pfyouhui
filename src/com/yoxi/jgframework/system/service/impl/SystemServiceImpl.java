package com.yoxi.jgframework.system.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoxi.jgframework.common.hibernate.qbc.CriteriaQuery;
import com.yoxi.jgframework.common.service.impl.CommonServiceImpl;
import com.yoxi.jgframework.system.entity.TSCodeDetail;
import com.yoxi.jgframework.system.entity.TSCodeType;
import com.yoxi.jgframework.system.entity.TSConfig;
import com.yoxi.jgframework.system.entity.TSFunction;
import com.yoxi.jgframework.system.entity.TSIcon;
import com.yoxi.jgframework.system.entity.TSLog;
import com.yoxi.jgframework.system.entity.TSRole;
import com.yoxi.jgframework.system.entity.TSRoleFunction;
import com.yoxi.jgframework.system.entity.TSRoleUser;
import com.yoxi.jgframework.system.entity.TSUser;
import com.yoxi.jgframework.system.service.SystemService;
import com.yoxi.jgframework.util.BrowserUtils;
import com.yoxi.jgframework.util.ContextHolderUtils;
import com.yoxi.jgframework.util.DateUtils;
import com.yoxi.jgframework.util.ResourceUtil;
import com.yoxi.jgframework.util.StringUtil;
import com.yoxi.jgframework.util.oConvertUtils;

@Service("systemService")
@Transactional
public class SystemServiceImpl extends CommonServiceImpl implements SystemService {
	public TSUser checkUserExits(TSUser user) throws Exception{
		return this.commonDao.getUserByUserIdAndUserNameExits(user);
	}

	/**
	 * 添加日志
	 */
	public void addLog(String logcontent, Short loglevel, Short operatetype, String objectType, String objectId, int takeTime){
		HttpServletRequest request = ContextHolderUtils.getRequest();
		String broswer = BrowserUtils.checkBrowse(request);
		TSLog log = new TSLog();
		log.setLogContent(logcontent);
		log.setLogLevel(loglevel);
		log.setOperateType(operatetype);
		log.setNote(oConvertUtils.getIp());
		log.setBroswer(broswer);
		log.setOperateTime(DateUtils.gettimestamp());
		log.setTSUser(ResourceUtil.getSessionUserName());
		log.setObjectType(objectType);
		log.setObjectId(objectId);
		log.setTakeTime(takeTime);
		commonDao.save(log);
	}

	/**
	 * 根据类型编码和类型名称获取Type,如果为空则创建一个
	 * 
	 * @param typecode
	 * @param typename
	 * @return
	 */
	public TSCodeDetail getCodeDetail(String typecode, String typename, TSCodeType tsTypegroup){
		TSCodeDetail actType = commonDao.findUniqueByProperty(TSCodeDetail.class, "typecode", typecode);
		if (actType == null) {
			actType = new TSCodeDetail();
			actType.setCode(typecode);
			actType.setCodeName(typename);
			actType.setTSCodeType(tsTypegroup);
			commonDao.save(actType);
		}
		return actType;

	}

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
	public TSCodeType getCodeType(String id, String typeName, String codeName1Label, String codeName2Label, String codeName3Label, String codeName4Label, Boolean allowAdd, Boolean allowModify, Boolean allowDelete, String typeMemo){
		TSCodeType tsTypegroup = commonDao.findUniqueByProperty(TSCodeType.class, "id", id);
		if (tsTypegroup == null) {
			tsTypegroup = new TSCodeType();
			tsTypegroup.setId(id);
			tsTypegroup.setCodeName1Label(codeName1Label);
			tsTypegroup.setTypeName(typeName);
			tsTypegroup.setCodeName2Label(codeName2Label);
			tsTypegroup.setCodeName3Label(codeName3Label);
			tsTypegroup.setCodeName4Label(codeName4Label);
			tsTypegroup.setAllowAdd(allowAdd);
			tsTypegroup.setAllowDelete(allowDelete);
			tsTypegroup.setAllowModify(allowModify);
			tsTypegroup.setTypeMemo(typeMemo);
			commonDao.save(tsTypegroup);
		}
		return tsTypegroup;
	}

	@Override
	public TSCodeType getCodeTypeByCode(String typegroupCode){
		TSCodeType tsTypegroup = commonDao.findUniqueByProperty(TSCodeType.class, "typegroupcode", typegroupCode);
		return tsTypegroup;
	}

	@Override
	public void initAllTypeGroups(){
		List<TSCodeType> tsCodeTypes = this.commonDao.loadAll(TSCodeType.class);
		for (TSCodeType tsCodeType : tsCodeTypes) {
			TSCodeType.allTSCodeType.put(tsCodeType.getId().toLowerCase(), tsCodeType);
			List<TSCodeDetail> types = this.commonDao.findByProperty(TSCodeDetail.class, "TSCodeType.id",
							tsCodeType.getId());
			TSCodeType.allTSCodeDetail.put(tsCodeType.getId().toLowerCase(), types);
		}
	}

	@Override
	public void refleshCodeDetailsCach(TSCodeDetail type){
		TSCodeType tsTypegroup = type.getTSCodeType();
		TSCodeType typeGroupEntity = this.commonDao.get(TSCodeType.class, tsTypegroup.getId());
		List<TSCodeDetail> types = this.commonDao.findByProperty(TSCodeDetail.class, "TSCodeType.id",
						tsTypegroup.getId());
		TSCodeType.allTSCodeDetail.put(typeGroupEntity.getId().toLowerCase(), types);
	}

	@Override
	public void refleshTypeGroupCach(){
		TSCodeType.allTSCodeType.clear();
		List<TSCodeType> typeGroups = this.commonDao.loadAll(TSCodeType.class);
		for (TSCodeType tsTypegroup : typeGroups) {
			TSCodeType.allTSCodeType.put(tsTypegroup.getId().toLowerCase(), tsTypegroup);
		}
	}

	// ----------------------------------------------------------------
	// update-start--Author:anchao Date:20130415 for：按钮权限控制
	// ----------------------------------------------------------------

	@Override
	public Set<String> getOperationCodesByRoleIdAndFunctionId(Integer roleId, Integer functionId){
		Set<String> operationCodes = new HashSet<String>();
		TSRole role = commonDao.get(TSRole.class, roleId);
		CriteriaQuery cq1 = new CriteriaQuery(TSRoleFunction.class);
		cq1.eq("TSRole.id", role.getId());
		cq1.eq("TSFunction.id", functionId);
		cq1.add();
		List<TSRoleFunction> rFunctions = getListByCriteriaQuery(cq1, false);
		if (null != rFunctions && rFunctions.size() > 0) {
			TSRoleFunction tsRoleFunction = rFunctions.get(0);
			if (null != tsRoleFunction.getOperation()) {
				String[] operationArry = tsRoleFunction.getOperation().split(",");
				for (int i = 0; i < operationArry.length; i++) {
					operationCodes.add(operationArry[i]);
				}
			}
		}
		return operationCodes;
	}

	@Override
	public Set<String> getOperationCodesByUserIdAndFunctionId(Integer userId, Integer functionId){
		Set<String> operationCodes = new HashSet<String>();
		List<TSRoleUser> rUsers = findByProperty(TSRoleUser.class, "TSUser.id", userId);
		for (TSRoleUser ru : rUsers) {
			TSRole role = ru.getTSRole();
			CriteriaQuery cq1 = new CriteriaQuery(TSRoleFunction.class);
			cq1.eq("TSRole.id", role.getId());
			cq1.eq("TSFunction.id", functionId);
			cq1.add();
			List<TSRoleFunction> rFunctions = getListByCriteriaQuery(cq1, false);
			if (null != rFunctions && rFunctions.size() > 0) {
				TSRoleFunction tsRoleFunction = rFunctions.get(0);
				if (null != tsRoleFunction.getOperation()) {
					String[] operationArry = tsRoleFunction.getOperation().split(",");
					for (int i = 0; i < operationArry.length; i++) {
						operationCodes.add(operationArry[i]);
					}
				}
			}
		}
		return operationCodes;
	}

	// ----------------------------------------------------------------
	// update-start--Author:anchao Date:20130415 for：按钮权限控制
	// ----------------------------------------------------------------
	@Override
	public void flushRoleFunciton(Integer id, TSFunction newFunction){
		TSFunction functionEntity = this.getEntity(TSFunction.class, id);
		if (functionEntity.getTSIcon() == null || !StringUtil.isNotEmpty(functionEntity.getTSIcon().getId())) {
			return;
		}
		TSIcon oldIcon = this.getEntity(TSIcon.class, functionEntity.getTSIcon().getId());
		if (!oldIcon.getIconClas().equals(newFunction.getTSIcon().getIconClas())) {
			// 刷新缓存
			HttpSession session = ContextHolderUtils.getSession();
			TSUser user = ResourceUtil.getSessionUserName();
			List<TSRoleUser> rUsers = this.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
			for (TSRoleUser ru : rUsers) {
				TSRole role = ru.getTSRole();
				session.removeAttribute(oConvertUtils.getString(role.getId()));
			}
		}
	}

	@Override
	public void initAllConfig(){
		List<TSConfig> configs = commonDao.findByQueryString("from TSConfig");
		if (CollectionUtils.isNotEmpty(configs)) {
			for (TSConfig config : configs) {
				if (StringUtil.isNotEmpty(config.getCode()) && StringUtil.isNotEmpty(config.getContents())) {
					TSConfig.someTSConfig.put(config.getCode(), config.getContents());
				}
			}
		}
	}

}
