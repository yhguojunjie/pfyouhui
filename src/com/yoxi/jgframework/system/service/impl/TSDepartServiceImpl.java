package com.yoxi.jgframework.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoxi.jgframework.common.service.impl.CommonServiceImpl;
import com.yoxi.jgframework.system.entity.TSDepart;
import com.yoxi.jgframework.system.service.TSDepartService;
import com.yoxi.jgframework.util.DepartIdUtils;

@Transactional
@Service
public class TSDepartServiceImpl extends CommonServiceImpl implements
		TSDepartService {
	@Override
	public List<Map<String, Object>> queryListMaps(Integer userId) {
		String query = "select d.id,d.departType from t_s_depart d,t_s_base_user bu WHERE d.id=bu.departid and bu.id=?";
		return commonDao.findForJdbc(query, userId);
	}

	public TSDepart query(Integer userId, int departType) {
		return commonDao
				.findEntityBySQL(
						"select d.* from t_s_depart d,t_s_base_user bu WHERE d.id=bu.departid and bu.id=? and d.departType=?",
						TSDepart.class, userId, departType);
	}

	@Override
	public List<Map<String, Object>> queryList(String departType) {
		String query = "select id,departName from t_s_depart WHERE departType="
				+ departType;
		return commonDao.findForJdbc(query);
	}

	@Override
	public TSDepart query(Integer userId) {
		return commonDao
				.findEntityBySQL(
						"select d.* from t_s_depart d,t_s_base_user bu WHERE d.id=bu.departid and bu.id=?",
						TSDepart.class, userId);
	}

	@Override
	public String makeDepartId(String parentId) {
		String departId = "";
		Map<String, Object> result = null;
		if (parentId == null) {
			// 父级-- 部门、运营商、cp提供商、渠道
			StringBuffer sqlStringBuffer = new StringBuffer(
					"select max(id) maxId from t_s_depart where parentDepartid is null ");
			result = commonDao.queryMapBySQL(sqlStringBuffer.toString(), null,
					null);

			if (MapUtils.isNotEmpty(result) && result.get("maxId") != null) {
				String maxId = (String) result.get("maxId");
				maxId = StringUtils.leftPad(maxId, 8, '0');
				departId = DepartIdUtils.nextDepartId(maxId, 1);
			}
		} else {
			// parentId----父表单id;
			// 子部门，有parentId
			StringBuffer sqlStringBuffer = new StringBuffer(
					"select max(id) maxId from t_s_depart  ");
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			sqlStringBuffer.append("where id like :id");
			String three = parentId.substring(4, 6);
			if (!StringUtils.equals("00", three)) {
				if(StringUtils.equals("00", parentId.substring(6,8))){
					paramsMap.put("id", parentId.substring(0, 6) + "%");
					result = commonDao.queryMapBySQL(sqlStringBuffer.toString(),
							paramsMap, null);
					if (MapUtils.isNotEmpty(result) && result.get("maxId") != null) {
						departId = DepartIdUtils.nextDepartId(
								(String) result.get("maxId"), 4);
					}
				}else{
					departId="部门id不能超过四级";
				}
			} else if (!StringUtils.equals("00", parentId.substring(2, 4))) {
				paramsMap.put("id", parentId.substring(0, 4) + "%");
				result = commonDao.queryMapBySQL(sqlStringBuffer.toString(),
						paramsMap, null);
				if (MapUtils.isNotEmpty(result) && result.get("maxId") != null) {
					departId = DepartIdUtils.nextDepartId(
							(String) result.get("maxId"), 3);
				}
			} else if (!StringUtils.equals("00", parentId.substring(0, 2))) {
				paramsMap.put("id", parentId.substring(0, 2) + "%");
				result = commonDao.queryMapBySQL(sqlStringBuffer.toString(),
						paramsMap, null);
				if (MapUtils.isNotEmpty(result) && result.get("maxId") != null) {
					departId = DepartIdUtils.nextDepartId(
							(String) result.get("maxId"), 2);
				}
			}
		}
		return departId;
	}

	@Override
	public List<Map<String, Object>> queryParentId(String id) {
		// TODO Auto-generated method stub
		// 因为id是'axxxxxxx' 所以必须加单引号
		String query = "select * from t_s_depart WHERE parentDepartid='" + id
				+ "'";
		return commonDao.findForJdbc(query);
	}

	@Override
	public List<Map<String, Object>> queryUserId(String id) {
		// TODO Auto-generated method stub
		String query = "select * from t_s_base_user WHERE departid='" + id
				+ "'";
		return commonDao.findForJdbc(query);
	}

	/* (non-Javadoc)
	 * @see com.yoxi.jgframework.system.service.TSDepartService#queryChildList(java.lang.String)
	 */
	@Override
	public List<Map<String, Object>> queryChildList(String id) {
		String query = "select id,departName from t_s_depart WHERE (id = '"+id+"' or parentDepartid='" + id
				+ "') and departType = 2 ";
		return commonDao.findForJdbc(query);
	}
}
