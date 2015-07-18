package com.yoxi.jgframework.ui.tag.core.easyui;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 
 * 类描述：列表字段处理项目
 * 
 * @author: jeecg
 * @date： 日期：2012-12-7 时间：上午10:17:45
 * @version 1.0
 */
public class DataGridColumnTag extends TagSupport {
	private static final long serialVersionUID = -700089431483677295L;
	protected String title;
	protected String field;
	protected Integer width;
	protected String rowspan;
	protected String colspan;
	protected String align;
	protected boolean sortable=false;
	protected boolean checkbox;
	protected String formatter;
	protected boolean hidden=true;
	protected String replace;
	protected String treefield;
	protected boolean image;
	protected boolean thumbnail=false;
	protected boolean banner = false;
	protected boolean query=false;
	protected String queryClass;  //查询字段的控件class
	private String queryMode = "single";//字段查询模式：single单字段查询；scope范围查询
	private String statsMode; //统计模式：sum合计模式；avg平均值
	protected boolean bSearchable=true;
	
	//private boolean autoLoadData=true; // 列表是否自动加载数据  
	// ---begin---  author:邢双阳 ---date:2013-05-18 ---for:[106]实现冰冻列
	private boolean frozenColumn=false; // 是否是冰冻列    默认不是
	// ---end---  author:邢双阳 ---date:2013-05-18 ---for:[106]实现冰冻列
	protected String url;//自定义链接
	protected String link;//自定义标签函数
	protected String funname="openwindow";//自定义函数名称
	protected String arg;//自定义链接传入参数字段
	protected String dictionary;	//数据字典组编码
	protected String dictionaryCode;	//数据字典key字段
	/**
	 * 子属性
	 */
	protected String childField; 
	
	
	public void setChildField(String childField) {
		this.childField = childField;
	}

	public boolean isFrozenColumn() {
		return frozenColumn;
	}

	public void setFrozenColumn(boolean frozenColumn) {
		this.frozenColumn = frozenColumn;
	}

	public void setArg(String arg) {
		this.arg = arg;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}

	public void setFunname(String funname) {
		this.funname = funname;
	}

	public void setbSearchable(boolean bSearchable) {
		this.bSearchable = bSearchable;
	}

	public void setQuery(boolean query) {
		this.query = query;
	}

	public void setImage(boolean image) {
		this.image = image;
	}

	public void setTreefield(String treefield) {
		this.treefield = treefield;
	}

	public void setReplace(String replace) {
		this.replace = replace;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setField(String field) {
		this.field = field;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public void setRowspan(String rowspan) {
		this.rowspan = rowspan;
	}

	public void setColspan(String colspan) {
		this.colspan = colspan;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}

	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}

	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public int doStartTag() throws JspTagException {
		return EVAL_PAGE;
	}

	
	public void setDictionary(String dictionary) {
		this.dictionary = dictionary;
	}
	
	public void setQueryClass(String queryClass) {
		this.queryClass = queryClass;
	}

	//--------begin ------------author:邢双阳  ---date：2013-5-13  for：【103】页面载入时，数据是否载入采取可配置模式
	public String getQueryMode() {
		return queryMode;
	}

	public void setQueryMode(String queryMode) {
		this.queryMode = queryMode;
	}
	
	
	public String getStatsMode() {
		return statsMode;
	}

	public void setStatsMode(String statsMode) {
		this.statsMode = statsMode;
	}

	public String getDictionaryCode() {
		return dictionaryCode;
	}

	public void setDictionaryCode(String dictionaryCode) {
		this.dictionaryCode = dictionaryCode;
	}

	public void setThumbnail(boolean thumbnail) {
		this.thumbnail = thumbnail;
	}

	public void setBanner(boolean banner) {
		this.banner = banner;
	}

	public int doEndTag() throws JspTagException {
		Tag t = findAncestorWithClass(this, DataGridTag.class);
		DataGridTag parent = (DataGridTag) t;
		parent.setColumn(title,field,width,rowspan,colspan,align,sortable,checkbox,formatter,hidden,replace,treefield,image,query,queryClass,url,link,funname,arg,queryMode,statsMode, dictionary,frozenColumn,dictionaryCode,childField,thumbnail,banner);
		return EVAL_PAGE;
	}
	//--------end ------------author:邢双阳  ---date：2013-5-13  for：【103】页面载入时，数据是否载入采取可配置模式
	
}