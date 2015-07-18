package com.yoxi.jgframework.common.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoxi.jgframework.common.dao.ICommonDao;
import com.yoxi.jgframework.common.hibernate.qbc.CriteriaQuery;
import com.yoxi.jgframework.common.hibernate.qbc.HqlQuery;
import com.yoxi.jgframework.common.hibernate.qbc.PageList;
import com.yoxi.jgframework.common.model.common.DBTable;
import com.yoxi.jgframework.common.model.common.UploadFile;
import com.yoxi.jgframework.common.model.json.ComboTree;
import com.yoxi.jgframework.common.model.json.DataGridReturn;
import com.yoxi.jgframework.common.model.json.ImportFile;
import com.yoxi.jgframework.common.model.json.TreeGrid;
import com.yoxi.jgframework.common.service.CommonService;
import com.yoxi.jgframework.system.entity.TSDepart;
import com.yoxi.jgframework.ui.tag.vo.datatable.DataTableReturn;
import com.yoxi.jgframework.ui.tag.vo.easyui.Autocomplete;
import com.yoxi.jgframework.ui.tag.vo.easyui.ComboTreeModel;
import com.yoxi.jgframework.ui.tag.vo.easyui.TreeGridModel;


@Service("commonService")
@Transactional
public class CommonServiceImpl implements CommonService {
	public ICommonDao commonDao = null;
	/**
	 * 获取所有数据库表
	 * @return
	 */
	public List<DBTable> getAllDbTableName()
	{
		return commonDao.getAllDbTableName();
	}
	public Integer getAllDbTableSize() {
		return commonDao.getAllDbTableSize();
	}
	@Resource
	public void setCommonDao(ICommonDao commonDao) {
		this.commonDao = commonDao;
	}

	@Override
	public <T> void save(T entity) {
		commonDao.save(entity);
	}
	
	@Override
	public <T> Serializable saveForId(T entity) {
		return commonDao.save(entity);
	}

	@Override
	public <T> void saveOrUpdate(T entity) {
		commonDao.saveOrUpdate(entity);

	}

	@Override
	public <T> void delete(T entity) {
		commonDao.delete(entity);

	}

	/**
	 * 删除实体集合
	 * 
	 * @param <T>
	 * @param entities
	 */
	public <T> void deleteAllEntitie(Collection<T> entities) {
		commonDao.deleteAllEntitie(entities);
	}

	/**
	 * 根据实体名获取对象
	 */
	public <T> T get(Class<T> class1, Serializable id) {
		return commonDao.get(class1, id);
	}

	/**
	 * 根据实体名返回全部对象
	 * 
	 * @param <T>
	 * @param hql
	 * @param size
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> List<T> getList(Class clas) {
		return commonDao.loadAll(clas);
	}

	/**
	 * 根据实体名获取对象
	 */
	@SuppressWarnings("rawtypes")
	public <T> T getEntity(Class entityName, Serializable id) {
		return commonDao.getEntity(entityName, id);
	}

	/**
	 * 根据实体名称和字段名称和字段值获取唯一记录
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public <T> T findUniqueByProperty(Class<T> entityClass, String propertyName, Object value) {
		return commonDao.findUniqueByProperty(entityClass, propertyName, value);
	}

	/**
	 * 按属性查找对象列表.
	 */
	public <T> List<T> findByProperty(Class<T> entityClass, String propertyName, Object value) {

		return commonDao.findByProperty(entityClass, propertyName, value);
	}

	/**
	 * 加载全部实体
	 * 
	 * @param <T>
	 * @param entityClass
	 * @return
	 */
	public <T> List<T> loadAll(final Class<T> entityClass) {
		return commonDao.loadAll(entityClass);
	}
	
	public <T> T singleResult(String hql)
	{
		return commonDao.singleResult(hql);
	}

	/**
	 * 删除实体主键ID删除对象
	 * 
	 * @param <T>
	 * @param entities
	 */
	@SuppressWarnings("rawtypes")
	public <T> void deleteEntityById(Class entityName, Serializable id) {
		commonDao.deleteEntityById(entityName, id);
	}

	/**
	 * 更新指定的实体
	 * 
	 * @param <T>
	 * @param pojo
	 */
	public <T> void updateEntitie(T pojo) {
		commonDao.updateEntitie(pojo);

	}
	
	public <T> void mergeEntitie(T pojo) {
		commonDao.mergeEntitie(pojo);

	}
	
	/**
	 * 通过hql 查询语句查找对象
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public <T> List<T> findByQueryString(String hql) {
		return commonDao.findByQueryString(hql);
	}

	/**
	 * 根据sql更新
	 * 
	 * @param query
	 * @return
	 */
	public int updateBySqlString(String sql) {
		return commonDao.updateBySqlString(sql);
	}

	/**
	 * 根据sql查找List
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public <T> List<T> findListbySql(String query) {
		return commonDao.findListbySql(query);
	}

	/**
	 * 通过属性称获取实体带排序
	 * 
	 * @param <T>
	 * @param clas
	 * @return
	 */
	public <T> List<T> findByPropertyisOrder(Class<T> entityClass, String propertyName, Object value, boolean isAsc) {
		return commonDao.findByPropertyisOrder(entityClass, propertyName, value, isAsc);
	}

	/**
	 * 
	 * cq方式分页
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	public PageList getPageList(final CriteriaQuery cq, final boolean isOffset) {
		return commonDao.getPageList(cq, isOffset);
	}

	/**
	 * 返回DataTableReturn模型
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	public DataTableReturn getDataTableReturn(final CriteriaQuery cq, final boolean isOffset) {
		return commonDao.getDataTableReturn(cq, isOffset);
	}

	/**
	 * 返回easyui datagrid模型
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	public DataGridReturn getDataGridReturn(final CriteriaQuery cq, final boolean isOffset) {
		return commonDao.getDataGridReturn(cq, isOffset);
	}
	
	/**
	 * 返回easyui datagrid模型
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	public DataGridReturn getDataGridReturn(final CriteriaQuery cq, final boolean isOffset, final boolean isCache) {
		return commonDao.getDataGridReturn(cq, isOffset,isCache);
	}	

	/**
	 * 
	 * hqlQuery方式分页
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	public PageList getPageList(final HqlQuery hqlQuery, final boolean needParameter) {
		return commonDao.getPageList(hqlQuery, needParameter);
	}

	/**
	 * 
	 * sqlQuery方式分页
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	public PageList getPageListBySql(final HqlQuery hqlQuery, final boolean isToEntity) {
		return commonDao.getPageListBySql(hqlQuery, isToEntity);
	}

	public Session getSession()

	{
		return commonDao.getSession();
	}
	
	public Session openSession()
	{
		return commonDao.openSession();
	}

	@SuppressWarnings("rawtypes")
	public List findByExample(final String entityName, final Object exampleEntity) {
		return commonDao.findByExample(entityName, exampleEntity);
	}

	/**
	 * 通过cq获取全部实体
	 * 
	 * @param <T>
	 * @param cq
	 * @return
	 */
	public <T> List<T> getListByCriteriaQuery(final CriteriaQuery cq,Boolean ispage) {
		return commonDao.getListByCriteriaQuery(cq,ispage);
	}

	/**
	 * 文件上传
	 * 
	 * @param request
	 */
	public <T> T  uploadFile(UploadFile uploadFile) {
		return commonDao.uploadFile(uploadFile);
	}

	public HttpServletResponse viewOrDownloadFile(UploadFile uploadFile)

	{
		return commonDao.viewOrDownloadFile(uploadFile);
	}


	/**
	 * 生成XML文件
	 * 
	 * @param fileName
	 *            XML全路径
	 * @return
	 */
	public HttpServletResponse createXml(ImportFile importFile) {
		return commonDao.createXml(importFile);
	}

	/**
	 * 解析XML文件
	 * 
	 * @param fileName
	 *            XML全路径
	 */
	public void parserXml(String fileName) {
		commonDao.parserXml(fileName);
	}

	public List<ComboTree> comTree(List<TSDepart> all, ComboTree comboTree) {
		return commonDao.comTree(all, comboTree);
	}

	/**
	 * 根据模型生成JSON
	 * 
	 * @param all
	 *            全部对象
	 * @param in
	 *            已拥有的对象
	 * @param comboBox
	 *            模型
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<ComboTree> ComboTree(List all,ComboTreeModel comboTreeModel,List in) {
		return commonDao.ComboTree(all,comboTreeModel,in);
	}
	/**
	 * 构建树形数据表
	 */
	@SuppressWarnings("rawtypes")
	public List<TreeGrid> treegrid(List all, TreeGridModel treeGridModel) {
		return commonDao.treegrid(all, treeGridModel);
	}

	/**
	 * 获取自动完成列表
	 * 
	 * @param <T>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> getAutoList(Autocomplete autocomplete) {
//		String hql = "from " + autocomplete.getEntityName() + " where " + autocomplete.getSearchField() + " like '%" + autocomplete.getTrem() + "%'";
//		List autoList = commonDao.findByQueryString(hql);
//		return autoList;
		// update-begin--Author:tanghong  Date:20130528 for：[ 114 ]自动补全标签优化
		StringBuffer sb = new StringBuffer("");
		for(String searchField:autocomplete.getSearchField().split(",")){
			sb.append("  or "+searchField+" like '%"+autocomplete.getTrem() + "%' ");
		}
		String hql = "from " + autocomplete.getEntityName() + " where 1!=1 " + sb.toString();
		return commonDao.getSession().createQuery(hql).setFirstResult(autocomplete.getCurPage()-1).setMaxResults(autocomplete.getMaxRows()).list();
		// update-end--Author:tanghong  Date:20130528 for：[ 114 ]自动补全标签优化
	}
	
	
	@Override
	public Integer executeSql(String sql, List<Object> param) {
		return commonDao.executeSql(sql, param);
	}
	@Override
	public Integer executeSql(String sql, Object... param) {
		return commonDao.executeSql(sql, param);
	}
	@Override
	public List<Map<String, Object>> findForJdbc(String sql, int page, int rows) {
		return commonDao.findForJdbc(sql, page,rows);
	}
	@Override
	public List<Map<String, Object>> findForJdbc(String sql, Object... objs) {
		return commonDao.findForJdbc(sql, objs);
	}
	@Override
	public List<Map<String, Object>> findForJdbcParam(String sql, int page,
			int rows, Object... objs) {
		return commonDao.findForJdbcParam(sql, page, rows, objs);
	}
	@Override
	public <T> List<T> findObjForJdbc(String sql, int page, int rows,
			Class<T> clazz) {
		return commonDao.findObjForJdbc(sql, page, rows, clazz);
	}
	@Override
	public Map<String, Object> findOneForJdbc(String sql, Object... objs) {
		return commonDao.findOneForJdbc(sql, objs);
	}
	@Override
	public Long getCountForJdbc(String sql) {
		return commonDao.getCountForJdbc(sql);
	}
	@Override
	public Long getCountForJdbcParam(String sql, Object[] objs) {
		return commonDao.getCountForJdbcParam(sql,objs);
	}
	@Override
	public <T> void batchSave(List<T> entitys) {
		this.commonDao.batchSave(entitys);
	}
	@Override
	public <T> void batchSaveOrUpdate(List<T> entitys) {
		this.commonDao.batchSaveOrUpdate(entitys);
	}
	
	@Override
	public <T> void batchUpdate(List<T> entitys) {
		this.commonDao.batchUpdate(entitys);
	}
	

	@Override
	public <T> List<T> findByQueryString(String query, Map<String, Object> params){
		return commonDao.findByQueryString(query, params);
	}

	@Override
	public Integer updateBySql(String sql, Map<String, Object> paramsMap){
		return commonDao.updateBySql(sql, paramsMap);
	}

	@Override
	public Integer updateBySql(String sql, Object...objects){
		return commonDao.updateBySql(sql, objects);
	}
	@Override
	public <T> T findEntityBySQL(String sql, Class<T> clazz, Map<String, ?> paramsMap){
		return commonDao.findEntityBySQL(sql, clazz, paramsMap);
	}
	@Override
	public <T> T findEntityBySQL(String sql, Class<T> clazz, Object...params){
		return commonDao.findEntityBySQL(sql, clazz, params);
	}
	@Override
	public <T> List<T> findListPageByHQL(String hql, Integer pageNo, Integer pageSize,
			Object... params) {
		return commonDao.findListPageByHQL(hql, pageNo, pageSize, params);
	}
	@Override
	public <T> List<T> findListPageByHQL(String hql, Map<String, ?> paramsMap, Integer pageNo,
			Integer pageSize) {
		return commonDao.findListPageByHQL(hql, paramsMap, pageNo, pageSize);
	}
	@Override
	public <T> List<T> findListPageBySQL(String sql, Class<T> clazz, Map<String, ?> paramsMap,
			Integer pageNo, Integer pageSize) {
		return commonDao.findListPageBySQL(sql, clazz, paramsMap, pageNo, pageSize);
	}
	@Override
	public Long findListPageCountBySQL(String sql, Map<String, ?> paramsMap) {
		return commonDao.findListPageCountBySQL(sql,paramsMap);
	}
	@Override
	public <T> List<T> findListPageBySQL(String sql, Class<T> clazz, Integer pageNo,
			Integer pageSize, Object... params) {
		return commonDao.findListPageBySQL(sql, clazz, pageNo, pageSize, params);
	}
	
}
