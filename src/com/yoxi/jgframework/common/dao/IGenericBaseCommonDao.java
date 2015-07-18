package com.yoxi.jgframework.common.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.type.Type;
import org.springframework.dao.DataAccessException;

import com.yoxi.jgframework.common.hibernate.qbc.CriteriaQuery;
import com.yoxi.jgframework.common.hibernate.qbc.HqlQuery;
import com.yoxi.jgframework.common.hibernate.qbc.PageList;
import com.yoxi.jgframework.common.model.common.DBTable;
import com.yoxi.jgframework.common.model.json.DataGridReturn;
import com.yoxi.jgframework.ui.tag.vo.datatable.DataTableReturn;

/**
 * 
 * 类描述：DAO层泛型基类接口
 * 
 * @author: com.yoxi.jgframework
 * @date： 日期：2012-12-8 时间：下午05:37:33
 * @version 1.0
 */
public interface IGenericBaseCommonDao {
	/**
	 * 获取所有数据库表
	 * 
	 * @return
	 */
	public List<DBTable> getAllDbTableName();

	public Integer getAllDbTableSize();

	public <T> Serializable save(T entity);

	public <T> void batchSave(List<T> entitys);

	public <T> void batchSaveOrUpdate(List<T> entitys);

	public <T> void batchUpdate(List<T> entitys);

	public <T> void saveOrUpdate(T entity);

	/**
	 * 删除实体
	 * 
	 * @param <T>
	 * 
	 * @param <T>
	 * 
	 * @param <T>
	 * @param entitie
	 */
	public <T> void delete(T entitie);

	/**
	 * 根据实体名称和主键获取实体
	 * 
	 * @param <T>
	 * @param entityName
	 * @param id
	 * @return
	 */
	public <T> T get(Class<T> entityName, Serializable id);

	/**
	 * 根据实体名字获取唯一记录
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public <T> T findUniqueByProperty(Class<T> entityClass, String propertyName, Object value);

	/**
	 * 按属性查找对象列表.
	 */
	public <T> List<T> findByProperty(Class<T> entityClass, String propertyName, Object value);

	/**
	 * 加载全部实体
	 * 
	 * @param <T>
	 * @param entityClass
	 * @return
	 */
	public <T> List<T> loadAll(final Class<T> entityClass);

	/**
	 * 根据实体名称和主键获取实体
	 * 
	 * @param <T>
	 * 
	 * @param <T>
	 * @param entityName
	 * @param id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public <T> T getEntity(Class entityName, Serializable id);

	@SuppressWarnings("rawtypes")
	public <T> void deleteEntityById(Class entityName, Serializable id);

	/**
	 * 删除实体集合
	 * 
	 * @param <T>
	 * @param entities
	 */
	public <T> void deleteAllEntitie(Collection<T> entities);

	/**
	 * 更新指定的实体
	 * 
	 * @param <T>
	 * @param pojo
	 */
	public <T> void updateEntitie(T pojo);

	@SuppressWarnings("rawtypes")
	public <T> void updateEntityById(Class entityName, Serializable id);

	public <T> void mergeEntitie(T pojo);

	/**
	 * 通过hql 查询语句查找对象
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public <T> List<T> findByQueryString(String hql);

	/**
	 * 通过hql查询对象列表
	 * 
	 * @param <T>
	 * 
	 * @param query
	 * @param params
	 * @return
	 */
	public <T> List<T> findByQueryString(final String query, Map<String, Object> params);

	/**
	 * 分页查询数据
	 * 
	 * @param hql
	 * @param paramsMap
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public <T> List<T> queryList(String hql, Map<String, Object> paramsMap, Integer page, Integer pageSize);
	
	public Long queryListCount(String hql, Map<String, Object> paramsMap);
	/**
	 * 通过hql查询唯一对象
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public <T> T singleResult(String hql);

	/**
	 * 根据sql更新
	 * 
	 * @param query
	 * @return
	 */
	public int updateBySqlString(String sql);

	/**
	 * 根据sql查找List
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public <T> List<T> findListbySql(String query);

	// public <T> List<T> findListMapbySql(final String sql);

	/**
	 * 通过属性称获取实体带排序
	 * 
	 * @param <T>
	 * @param clas
	 * @return
	 */
	public <T> List<T> findByPropertyisOrder(Class<T> entityClass, String propertyName, Object value, boolean isAsc);

	/**
	 * 
	 * cq方式分页
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	public PageList getPageList(final CriteriaQuery cq, final boolean isOffset);

	/**
	 * 通过cq获取全部实体
	 * 
	 * @param <T>
	 * @param cq
	 * @return
	 */
	public <T> List<T> getListByCriteriaQuery(final CriteriaQuery cq, Boolean ispage);

	/**
	 * 
	 * hqlQuery方式分页
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	public PageList getPageList(final HqlQuery hqlQuery, final boolean needParameter);

	/**
	 * 
	 * sqlQuery方式分页
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	public PageList getPageListBySql(final HqlQuery hqlQuery, final boolean needParameter);

	public Session getSession();

	public Session openSession();

	@SuppressWarnings("rawtypes")
	public List findByExample(final String entityName, final Object exampleEntity);

	/**
	 * 通过hql 查询语句查找HashMap对象
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public Map<Object, Object> getHashMapbyQuery(String query);

	/**
	 * 返回jquery datatables模型
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	public DataTableReturn getDataTableReturn(final CriteriaQuery cq, final boolean isOffset);

	/**
	 * 返回easyui datagrid模型
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	public DataGridReturn getDataGridReturn(final CriteriaQuery cq, final boolean isOffset);

	/**
	 * 返回easyui datagrid模型
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	public DataGridReturn getDataGridReturn(final CriteriaQuery cq, final boolean isOffset, final boolean isCache);

	/**
	 * 执行SQL
	 */
	public Integer executeSql(String sql, List<Object> param);

	/**
	 * 执行SQL
	 */
	public Integer executeSql(String sql, Object... param);

	/**
	 * 使用hibernate执行原生sql
	 * 
	 * @param sql
	 * @param paramsMap
	 * @return
	 */
	public Integer updateBySql(String sql, Map<String, Object> paramsMap);

	/**
	 * 使用hibernate执行原生sql
	 * 
	 * @param sql
	 * @param objects
	 * @return
	 */
	public Integer updateBySql(String sql, Object... objects);

	/**
	 * 通过JDBC查找对象集合 使用指定的检索标准检索数据返回数据
	 */
	public List<Map<String, Object>> findForJdbc(String sql, Object... objs);

	/**
	 * 通过JDBC查找对象集合 使用指定的检索标准检索数据返回数据
	 */
	public Map<String, Object> findOneForJdbc(String sql, Object... objs);

	/**
	 * 通过JDBC查找对象集合,带分页 使用指定的检索标准检索数据并分页返回数据
	 */
	public List<Map<String, Object>> findForJdbc(String sql, int page, int rows);

	/**
	 * 通过JDBC查找对象集合,带分页 使用指定的检索标准检索数据并分页返回数据
	 */
	public <T> List<T> findObjForJdbc(String sql, int page, int rows, Class<T> clazz);

	/**
	 * 使用指定的检索标准检索数据并分页返回数据-采用预处理方式
	 * 
	 * @param criteria
	 * @param firstResult
	 * @param maxResults
	 * @return
	 * @throws DataAccessException
	 */
	public List<Map<String, Object>> findForJdbcParam(String sql, int page, int rows, Object... objs);

	/**
	 * 使用指定的检索标准检索数据并分页返回数据For JDBC
	 */
	public Long getCountForJdbc(String sql);

	/**
	 * 使用指定的检索标准检索数据并分页返回数据For JDBC-采用预处理方式
	 * 
	 */
	public Long getCountForJdbcParam(String sql, Object[] objs);

	/**
	 * 通过hql 查询语句查找对象
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public <T> List<T> findHql(String hql, Object... param);

	/**
	 * hql分页查询数据
	 * 
	 * @param hql
	 * @param paramsMap
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public <T> List<T> findListPageByHQL(String hql, Map<String, ?> paramsMap, Integer pageNo, Integer pageSize);

	/**
	 * hql 分页查询数据
	 * 
	 * @param hql
	 * @param pageNo
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public <T> List<T> findListPageByHQL(String hql, Integer pageNo, Integer pageSize, Object... params);

	/**
	 * 根据SQL查Entity列表
	 * 
	 * @param sql
	 * @param clazz
	 * @param paramsMap
	 * @return
	 */
	public <T> List<T> findListBySQL(String sql, Class<T> clazz, Map<String, ?> paramsMap);

	/**
	 * 根据SQL查Entity列表
	 * 
	 * @param sql
	 * @param clazz
	 * @param params
	 * @return
	 */
	public <T> List<T> findListBySQL(String sql, Class<T> clazz, Object... params);

	/**
	 * 根据SQL查Entity列表
	 * 
	 * @param sql
	 * @param clazz
	 * @param paramsMap
	 * @return
	 */
	public <T> List<T> findListPageBySQL(String sql, Class<T> clazz, Map<String, ?> paramsMap, Integer pageNo,
			Integer pageSize);

	/**
	 * 根据SQL查Entity列表
	 * 
	 * @param sql
	 * @param clazz
	 * @param params
	 * @return
	 */
	public <T> List<T> findListPageBySQL(String sql, Class<T> clazz, Integer pageNo, Integer pageSize, Object... params);

	/**
	 * 根据SQL查Entity
	 * 
	 * @param sql
	 * @param clazz
	 * @param paramsMap
	 * @return
	 */
	public <T> T findEntityBySQL(String sql, Class<T> clazz, Map<String, ?> paramsMap);

	public <T> T queryObjectBySQL(String sql, Map<String, Object> paramsMap, Class<T> clazz, Map<String, Type> scalarMap);

	public Map<String, Object> queryMapBySQL(String sql, Map<String, Object> paramsMap, Map<String, Type> scalarMap);

	public <T> List<T> queryListObjectBySQL(String sql, Map<String, Object> paramsMap, Class<T> clazz,
			Map<String, Type> scalarMap);
	
	public <T> List<T> queryListObjectBySQL(String sql, Map<String, Object> paramsMap, Class<T> clazz,
			Map<String, Type> scalarMap, Integer pageNo, Integer pageSize);

	public List<Map<String, Object>> queryListMapBySQL(String sql, Map<String, Object> paramsMap,
			Map<String, Type> scalarMap);

	/**
	 * 根据SQL查Entity
	 * 
	 * @param sql
	 * @param clazz
	 * @param params
	 * @return
	 */
	public <T> T findEntityBySQL(String sql, Class<T> clazz, Object... params);

}
