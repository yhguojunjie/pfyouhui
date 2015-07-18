package com.yoxi.jgframework.ui.tag.core.easyui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

import com.yoxi.jgframework.core.tag.DataGridTagUtils;
import com.yoxi.jgframework.system.entity.TSCodeDetail;
import com.yoxi.jgframework.system.entity.TSCodeType;
import com.yoxi.jgframework.ui.tag.vo.easyui.ColumnValue;
import com.yoxi.jgframework.ui.tag.vo.easyui.DateGridColumn;
import com.yoxi.jgframework.ui.tag.vo.easyui.DateGridUrl;
import com.yoxi.jgframework.util.oConvertUtils;

/**
 * 
 * 类描述：DATAGRID标签处理类
 * 
 * @author: jeecg
 * @date： 日期：2012-12-7 时间：上午10:17:45
 * @version 1.0
 */
public class DataGridTag extends TagSupport {
	private static final long serialVersionUID = -134654071237825955L;
	protected String fields = "";// 显示字段
	protected String footer = "";// 统计列
	protected String name;// 表格标示
	protected String title;// 表格标示
	protected String idField = "id";// 主键字段
	protected boolean treegrid = false;// 是否是树形列表
	protected List<DateGridUrl> urlList = new ArrayList<DateGridUrl>();// 列表操作显示
	protected List<DateGridUrl> toolBarList = new ArrayList<DateGridUrl>();// 工具条列表
	protected List<DateGridColumn> columnList = new ArrayList<DateGridColumn>();// 列表操作显示
	protected List<ColumnValue> columnValueList = new ArrayList<ColumnValue>();// 值替换集合
	public Map<String, Object> map;// 封装查询条件
	private String actionUrl;// 分页提交路径
	public int allCount;
	public int curPageNo;
	public int pageSize = 20;
	public boolean pagination = true;// 是否显示分页
	private String width;
	private String height;
	private boolean checkbox = false;// 是否显示复选框
	private boolean showPageList = true;// 定义是否显示页面列表
	private boolean fit = true;// 是否允许表格自动缩放，以适应父容器
	private boolean fitColumns = true;// 当为true时，自动展开/合同列的大小，以适应的宽度，防止横向滚动.
	private String sortName;// 定义的列进行排序
	private String sortOrder = "asc";// 定义列的排序顺序，只能是"递增"或"降序".
	private boolean showRefresh = true;// 定义是否显示刷新按钮
	private boolean showText = true;// 定义是否显示刷新按钮
	private String style = "easyui";// 列表样式easyui,datatables
	private String onLoadSuccess;// 数据加载完成调用方法
	private String onClick;// 单击事件调用方法
	private String onDblClick;// 双击事件调用方法
	private String queryMode = "single";// 查询模式
	private boolean autoLoadData = true; // 列表是否自动加载数据
	// ---begin--- author:邢双阳 ---date:2013-05-18 ---for:[106]实现冰冻列
	// private boolean frozenColumn=false; // 是否是冰冻列 默认不是
	// ---end--- author:邢双阳 ---date:2013-05-18 ---for:[106]实现冰冻列
	private boolean sortable = false;// 是否允许拖动行排序
	private String sortUrl;// 排序提交的URL
	private boolean flipDeselect = false; //翻页是否取消选择行

	
	public boolean isFlipDeselect() {
		return flipDeselect;
	}

	public void setFlipDeselect(boolean flipDeselect) {
		this.flipDeselect = flipDeselect;
	}

	public void setOnLoadSuccess(String onLoadSuccess) {
		this.onLoadSuccess = onLoadSuccess;
	}

	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}

	public void setOnDblClick(String onDblClick) {
		this.onDblClick = onDblClick;
	}

	public void setShowText(boolean showText) {
		this.showText = showText;
	}

	public void setPagination(boolean pagination) {
		this.pagination = pagination;
	}

	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setTreegrid(boolean treegrid) {
		this.treegrid = treegrid;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public void setIdField(String idField) {
		this.idField = idField;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFit(boolean fit) {
		this.fit = fit;
	}

	public void setShowPageList(boolean showPageList) {
		this.showPageList = showPageList;
	}

	public void setShowRefresh(boolean showRefresh) {
		this.showRefresh = showRefresh;
	}

	/**
	 * 设置询问操作URL
	 */
	public void setConfUrl(String url, String title, String message, String exp, String operationCode) {
		DataGridTagUtils.setConfUrl(pageContext, url, title, message, exp, operationCode, urlList);
	}

	/**
	 * 设置删除操作URL
	 */
	public void setDelUrl(String url, String title, String message, String exp, String funname, String operationCode) {
		DataGridTagUtils.setDelUrl(pageContext, urlList, url, title, message, exp, funname, operationCode);
	}

	/**
	 * 设置默认操作URL
	 */
	public void setDefUrl(String url, String title, String exp, String operationCode) {
		DataGridTagUtils.setDefUrl(pageContext, urlList, url, title, exp, operationCode);
	}

	/**
	 * 设置工具条
	 */
	public void setToolbar(String url, String title, String icon, String exp, String onclick, String funname,
			String operationCode,String popWidth,String popHeight) {
		DataGridTagUtils.setToolbar(pageContext, toolBarList, url, title, icon, exp, onclick, funname, operationCode,popWidth,popHeight);
	}

	/**
	 * 设置自定义函数操作URL
	 */
	public void setFunUrl(String title, String exp, String funname, String operationCode) {
		DataGridTagUtils.setFunUrl(pageContext, urlList, title, exp, funname, operationCode);
	}

	/**
	 * 设置自定义函数操作URL
	 */
	public void setOpenUrl(String url, String title, String width, String height, String exp, String operationCode,
			String funname) {
		DataGridTagUtils.setOpenUrl(pageContext, urlList, url, title, width, height, exp, operationCode, funname);
	}

	/**
	 * 
	 * <b>Summary: </b> setColumn(设置字段)
	 * 
	 * @param name
	 * @param text
	 * @param value
	 */
	public void setColumn(String title, String field, Integer width, String rowspan, String colspan, String align,
			boolean sortable, boolean checkbox, String formatter, boolean hidden, String replace, String treefield,
			boolean image, boolean query, String queryClass, String url, String link, String funname, String arg,
			String queryMode, String statsMode, String dictionary, boolean frozenColumn,String dictionaryCode,String childField,boolean thumbnail,boolean banner) {
		DateGridColumn dateGridColumn = new DateGridColumn();
		dateGridColumn.setAlign(align);
		dateGridColumn.setCheckbox(checkbox);
		dateGridColumn.setColspan(colspan);
		dateGridColumn.setField(field);
		dateGridColumn.setFormatter(formatter);
		dateGridColumn.setHidden(hidden);
		dateGridColumn.setRowspan(rowspan);
		dateGridColumn.setSortable(sortable);
		dateGridColumn.setTitle(title);
		dateGridColumn.setWidth(width);
		dateGridColumn.setTreefield(treefield);
		dateGridColumn.setImage(image);
		dateGridColumn.setReplace(replace);
		dateGridColumn.setQuery(query);
		dateGridColumn.setQueryClass(queryClass);
		dateGridColumn.setUrl(url);
		dateGridColumn.setLink(link);
		dateGridColumn.setFunname(funname);
		dateGridColumn.setArg(arg);
		dateGridColumn.setQueryMode(queryMode);
		dateGridColumn.setStatsMode(statsMode);
		dateGridColumn.setFrozenColumn(frozenColumn);
		dateGridColumn.setDictionary(dictionary);
		dateGridColumn.setDictionaryCode(dictionaryCode);
		dateGridColumn.setChildField(childField);
		dateGridColumn.setThumbnail(thumbnail);
		dateGridColumn.setBanner(banner);
		columnList.add(dateGridColumn);

		if (!oConvertUtils.isEmpty(statsMode)) {
			footer += field + ":" + statsMode + ",";
		}

		if (field != "opt") {
			fields += field + ",";
		}
		if (replace != null) {
			String[] test = replace.split(",");
			String text = "";
			String value = "";
			for (String string : test) {
				text += string.substring(0, string.indexOf("_")) + ",";
				value += string.substring(string.indexOf("_") + 1) + ",";
			}
			setColumn(field, text, value);

		}
		if (!StringUtils.isBlank(dictionary)) {
			String text = "";
			String value = "";
			List<TSCodeDetail> typeList = TSCodeType.allTSCodeDetail.get(dictionary.toLowerCase());
			if (typeList != null && !typeList.isEmpty()) {
				for (TSCodeDetail type : typeList) {
					text += type.getCodeName() + ",";
					//value += type.getId() + ",";//limj 20140320
					if (!StringUtils.isBlank(dictionaryCode)) {//limj 20140401
						value += type.getCode() + ",";
					}else {
						value += type.getId() + ",";
					}
				}
				setColumn(field, text, value);
			}
		}
	}

	/**
	 * 
	 * <b>Summary: </b> setColumn(设置字段替换值)
	 * 
	 * @param name
	 * @param text
	 * @param value
	 */
	public void setColumn(String name, String text, String value) {
		DataGridTagUtils.setColumn(columnValueList, name, text, value);
	}

	public boolean isSortable() {
		return sortable;
	}

	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}

	public String getSortUrl() {
		return sortUrl;
	}

	public void setSortUrl(String sortUrl) {
		this.sortUrl = sortUrl;
	}

	public int doStartTag() throws JspTagException {
		// 清空资源
		urlList.clear();
		toolBarList.clear();
		columnValueList.clear();
		columnList.clear();
		fields = "";
		footer = "";
		return EVAL_PAGE;
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			JspWriter out = this.pageContext.getOut();
			if (style.equals("easyui")) {
				out.print(end().toString());
			} else {
				out.print(datatables().toString());
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	/**
	 * datatables构造方法
	 * 
	 * @return
	 */
	public StringBuffer datatables() {
		return DataGridTagUtils.datatables(name, columnList, urlList, idField, style);
	}

	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * easyui构造方法
	 * 
	 * @return
	 */
	public StringBuffer end() {
		return DataGridTagUtils.end(name, queryMode, showRefresh, showText, showPageList, urlList, columnValueList, actionUrl, fields, checkbox, toolBarList, onClick, onDblClick, columnList, fitColumns, sortUrl, onLoadSuccess, sortable, pagination, sortOrder, sortName, footer, pageSize, fit, idField, title, autoLoadData, width, height, treegrid,flipDeselect);
	}

	/**
	 * 判断是否存在查询字段
	 * 
	 * @return hasQuery true表示有查询字段,false表示没有
	 */
	protected boolean hasQueryColum(List<DateGridColumn> columnList) {
		return DataGridTagUtils.hasQueryColum(columnList);
	}

	/**
	 * 拼装操作地址
	 * 
	 * @param sb
	 */
	protected void getOptUrl(StringBuffer sb) {
		DataGridTagUtils.getOptUrl(sb, urlList, name);
	}

	/**
	 * 列自定义函数
	 * 
	 * @param sb
	 * @param column
	 */
	protected void getFun(StringBuffer sb, DateGridColumn column) {
		DataGridTagUtils.getFun(sb, column);
	}

	/**
	 * 列自定义函数
	 * 
	 * @param sb
	 * @param column
	 */
	protected void getLinkFun(StringBuffer sb, DateGridColumn column) {
		DataGridTagUtils.getLinkFun(sb, column);
	}

	/**
	 * 格式化URL
	 * 
	 * @return
	 */
	protected String formatUrl(String url) {
		return DataGridTagUtils.formatUrl(url);
	}

	// ---begin--- author:邢双阳 ---date:2013-05-18 ---for:[106]实现冰冻列
	/**
	 * 拼接字段 普通列
	 * 
	 * @param sb
	 * 
	 */
	protected void getField(StringBuffer sb) {
		getField(sb, 1);
	}

	/**
	 * 拼接字段
	 * 
	 * @param sb
	 * @frozen 0 冰冻列 1 普通列
	 */
	protected void getField(StringBuffer sb, int frozen) {
		DataGridTagUtils.getField(sb, urlList, name, frozen, checkbox, columnList, treegrid, columnValueList);
	}

	// ---end--- author:邢双阳 ---date:2013-05-18 ---for:[106]实现冰冻列
	/**
	 * 设置分页条信息
	 * 
	 * @param sb
	 */
	protected void setPager(StringBuffer sb, String grid) {
		DataGridTagUtils.setPager(sb, grid, grid, showRefresh, showText, pageSize, showPageList);
	}

	// 列表查询框函数
	protected void searchboxFun(StringBuffer sb, String grid) {
		DataGridTagUtils.searchboxFun(sb, grid, name);
	}

	public boolean isFitColumns() {
		return fitColumns;
	}

	public void setFitColumns(boolean fitColumns) {
		this.fitColumns = fitColumns;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getQueryMode() {
		return queryMode;
	}

	public void setQueryMode(String queryMode) {
		this.queryMode = queryMode;
	}

	public boolean isAutoLoadData() {
		return autoLoadData;
	}

	public void setAutoLoadData(boolean autoLoadData) {
		this.autoLoadData = autoLoadData;
	}
}