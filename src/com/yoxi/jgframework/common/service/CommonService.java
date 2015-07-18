package com.yoxi.jgframework.common.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;


import org.hibernate.Session;

import com.yoxi.jgframework.common.hibernate.qbc.CriteriaQuery;
import com.yoxi.jgframework.common.hibernate.qbc.HqlQuery;
import com.yoxi.jgframework.common.hibernate.qbc.PageList;
import com.yoxi.jgframework.common.model.common.DBTable;
import com.yoxi.jgframework.common.model.common.UploadFile;
import com.yoxi.jgframework.common.model.json.ComboTree;
import com.yoxi.jgframework.common.model.json.DataGridReturn;
import com.yoxi.jgframework.common.model.json.ImportFile;
import com.yoxi.jgframework.common.model.json.TreeGrid;
import com.yoxi.jgframework.extend.template.Template;
import com.yoxi.jgframework.system.entity.TSDepart;
import com.yoxi.jgframework.ui.tag.vo.datatable.DataTableReturn;
import com.yoxi.jgframework.ui.tag.vo.easyui.Autocomplete;
import com.yoxi.jgframework.ui.tag.vo.easyui.ComboTreeModel;
import com.yoxi.jgframework.ui.tag.vo.easyui.TreeGridModel;


public interface CommonService {
	/**
	 * 获取所有数据库表
	 * @return
	 */
	public List<DBTable> getAllDbTableName();
	public Integer getAllDbTableSize();
	public <T> void save(T entity);
	public <T> Serializable saveForId(T entity);
	public <T> void saveOrUpdate(T entity);
	public <T> void delete(T entity);
	public <T> void batchSave(List<T> entitys);
	public <T> void batchSaveOrUpdate(List<T> entitys);
	public <T> void batchUpdate(List<T> entitys);
	/**
	 * 根据实体名称和主键获取实体
	 * 
	 * @param <T>
	 * @param entityName
	 * @param id
	 * @return
	 */
	public <T> T get(Class<T> class1, Serializable id);
	/**
	 * 根据实体名称和主键获取实体
	 * 
	 * @param <T>
	 * @param entityName
	 * @param id
	 * @return
	 */
	public <T> T getEntity(Class entityName, Serializable id);
	/**
	 * 根据实体名称和字段名称和字段值获取唯一记录
	 * @param <T>
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public <T> T findUniqueByProperty(Class<T> entityClass, String propertyName, Object value);
	/**
	 * 按属性查找对象列表.
	 */
	public <T> List<T> findByProperty(Class<T> entityClass,String propertyName, Object value);
	/**
	 * 加载全部实体
	 * @param <T>
	 * @param entityClass
	 * @return
	 */
	public <T> List<T> loadAll(final Class<T> entityClass);
		

	/**
	 * 删除实体主键删除
	 * 
	 * @param <T>
	 * @param entities
	 */
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
	 * @param query
	 * @param params
	 * @return
	 */
	public <T> List<T> findByQueryString(final String query, Map<String, Object> params);

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
	/**
	 * 通过属性称获取实体带排序
	 * 
	 * @param <T>
	 * @param clas
	 * @return
	 */
	public <T> List<T> findByPropertyisOrder(Class<T> entityClass, String propertyName, Object value, boolean isAsc);
	
	public <T> List<T> getList(Class clas);
	
	public <T> T singleResult(String hql);
	
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
	 * 返回DataTableReturn模型
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	public DataTableReturn getDataTableReturn(final CriteriaQuery cq, final boolean isOffset);
	
	/**
	 * 返回easyui datagrid模型
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	public DataGridReturn getDataGridReturn(final CriteriaQuery cq, final boolean isOffset);
	
	/**
	 * 返回easyui datagrid模型
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	public DataGridReturn getDataGridReturn(final CriteriaQuery cq, final boolean isOffset, final boolean isCache);	
	

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
	public PageList getPageListBySql(final HqlQuery hqlQuery, final boolean isToEntity);
	public Session getSession();
	public Session openSession();
	public List findByExample(final String entityName, final Object exampleEntity);
	/**
	 * 通过cq获取全部实体
	 * 
	 * @param <T>
	 * @param cq
	 * @return
	 */
	public <T> List<T> getListByCriteriaQuery(final CriteriaQuery cq,Boolean ispage);
	/**
	 * 文件上传
	 * @param request
	 */
	public <T> T uploadFile(UploadFile uploadFile);
	public HttpServletResponse viewOrDownloadFile(UploadFile uploadFile);

	/**
	 * 生成XML文件
	 * @param fileName XML全路径
	 */
	public HttpServletResponse createXml(ImportFile importFile);
	/**
	 * 解析XML文件
	 * @param fileName XML全路径
	 */
	public void parserXml(String fileName);
	public List<ComboTree> comTree(List<TSDepart> all,ComboTree comboTree);
	/**
	 * 根据模型生成JSON
	 * @param all 全部对象
	 * @param in  已拥有的对象
	 * @param comboBox 模型
	 * @return
	 */
	public  List<ComboTree> ComboTree(List all,ComboTreeModel comboTreeModel, List in);
	/**
	 * 构建树形数据表
	 * @param all
	 * @param treeGridModel
	 * @return
	 */
	public  List<TreeGrid> treegrid(List all,TreeGridModel treeGridModel);
	/**
	 * 获取自动完成列表
	 * @param <T>
	 * @return
	 */
	public  <T> List<T> getAutoList(Autocomplete autocomplete);
	
	
	
	
	
	/**
	 * 执行SQL
	 */
	public Integer executeSql(String sql, List<Object> param);
	
	/**
	 * 执行SQL
	 */
	public Integer executeSql(String sql, Object... param);
	
	
	/**
	 * 使用hibernate的原生sql执行更新
	 * 
	 * @param sql
	 * @param paramsMap
	 * @return
	 */
	public Integer updateBySql(String sql, Map<String, Object> paramsMap);

	/**
	 * 使用hibernate的原生sql执行更新
	 * 
	 * @param sql
	 * @param objects
	 * @return
	 */
	public Integer updateBySql(String sql, Object...objects);

	/**
	 * 通过JDBC查找对象集合 使用指定的检索标准检索数据返回数据
	 */
	public List<Map<String, Object>> findForJdbc(String sql, Object...objs);

	/**
	 * 通过JDBC查找对象集合 使用指定的检索标准检索数据返回数据
	 */
	public Map<String, Object> findOneForJdbc(String sql, Object...objs);

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
	public List<Map<String, Object>> findForJdbcParam(String  sql,  int page, int rows,Object... objs);
	
	/**
	 * 使用指定的检索标准检索数据并分页返回数据For JDBC
	 */
	public Long getCountForJdbc(String  sql) ;
	/**
	 * 使用指定的检索标准检索数据并分页返回数据For JDBC-采用预处理方式
	 * 
	 */
	public Long getCountForJdbcParam(String  sql,Object[] objs);

	/**
	 * 根据SQL查Entity
	 * @param sql
	 * @param clazz
	 * @param paramsMap
	 * @return
	 */
	public <T> T findEntityBySQL(String sql, Class<T> clazz, Map<String, ?> paramsMap);
	/**
	 * 根据SQL查Entity
	 * @param sql
	 * @param clazz
	 * @param params
	 * @return
	 */
	public <T> T findEntityBySQL(String sql, Class<T> clazz, Object...params);

	public <T> List<T> findListPageByHQL(String hql, Integer pageNo, Integer pageSize, Object... params);
	
	public <T> List<T> findListPageByHQL(String hql, Map<String, ?> paramsMap, Integer pageNo,
			Integer pageSize);
	
	public <T> List<T> findListPageBySQL(String sql, Class<T> clazz, Map<String, ?> paramsMap, Integer pageNo,
			Integer pageSize);
	public Long findListPageCountBySQL(String sql, Map<String, ?> paramsMap);
	public <T> List<T> findListPageBySQL(String sql, Class<T> clazz, Integer pageNo, Integer pageSize, Object... params);
}
