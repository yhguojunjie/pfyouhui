package com.yoxi.jgframework.common.dao;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.jdbc.core.JdbcTemplate;

import com.yoxi.jgframework.common.model.common.UploadFile;
import com.yoxi.jgframework.common.model.json.ComboTree;
import com.yoxi.jgframework.common.model.json.ImportFile;
import com.yoxi.jgframework.common.model.json.TreeGrid;
import com.yoxi.jgframework.extend.template.Template;
import com.yoxi.jgframework.system.entity.TSDepart;
import com.yoxi.jgframework.system.entity.TSUser;
import com.yoxi.jgframework.ui.tag.vo.easyui.ComboTreeModel;
import com.yoxi.jgframework.ui.tag.vo.easyui.TreeGridModel;

public interface ICommonDao extends IGenericBaseCommonDao{
	
	/**
	 * 获得jdbcTemplate
	 * @return
	 */
	public JdbcTemplate getJdbcTemplate() ;
	/**
	 * admin账户密码初始化
	 * @param user
	 */
	public void pwdInit(TSUser user,String newPwd);
	/**
	 * 检查用户是否存在
	 * */
	public TSUser getUserByUserIdAndUserNameExits(TSUser user);
	public String getUserRole(TSUser user);
	/**
	 * 文件上传
	 * @param request
	 */
	public <T> T  uploadFile(UploadFile uploadFile);
	/**
	 * 文件上传或预览
	 * @param uploadFile
	 * @return
	 */
	public HttpServletResponse viewOrDownloadFile(UploadFile uploadFile);

	public Map<Object,Object> getDataSourceMap(Template template);
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
	@SuppressWarnings("rawtypes")
	public  List<ComboTree> ComboTree(List all,ComboTreeModel comboTreeModel,List in);
	@SuppressWarnings("rawtypes")
	public  List<TreeGrid> treegrid(List all,TreeGridModel treeGridModel);
	/**
	 * 懒加载，来加载对象
	 * @param clazz
	 * @param id
	 * @return
	 */
	public <T> T load(Class<T> clazz, Serializable id);
	public Long findListPageCountBySQL(String sql, Map<String, ?> paramsMap);
}

