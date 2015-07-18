package com.yoxi.jgframework.ui.tag.vo.easyui;
/**
 * 
 * 类描述：列表字段模型
 * 
 * @author: com.yoxi.jgframework
 * @date： 日期：2012-12-7 时间：上午10:17:45
 * @version 1.0
 */
public class DateGridColumn {
	protected String title;//表格列名
	protected String field;//数据库对应字段
	protected Integer width;//宽度
	protected String rowspan;//跨列
	protected String colspan;//跨行
	protected String align;//对齐方式
	protected boolean sortable;//是否排序
	protected boolean checkbox;//是否显示复选框
	protected String formatter;//格式化函数
	protected boolean hidden;//是否隐藏
	protected String treefield;//
	protected boolean image;//是否是图片
	protected boolean thumbnail;//是否显示缩略图
	protected boolean banner;//显示200*100的图片
	protected boolean query;//是否查询 
	protected String queryClass;////查询字段的控件class
	protected String replace;//替换表达式
	protected String queryMode = "single";//字段查询模式：single单字段查询；group范围查询
	protected String statsMode;//统计模式：sum合计值；avg平均值;其它为展示名称（statsMode="合计"）
	//--------begin ------------author:邢双阳  ---date：2013-5-13  for：【103】页面载入时，数据是否载入采取可配置模式
	
	protected boolean autoLoadData = true; // 列表是否自动加载数据
	//--------end ------------author:邢双阳  ---date：2013-5-13  for：【103】页面载入时，数据是否载入采取可配置模式
	// ---begin---  author:邢双阳 ---date:2013-05-18 ---for:[106]实现冰冻列
	private boolean frozenColumn=false; // 是否是冰冻列    默认不是
	// ---end---  author:邢双阳 ---date:2013-05-18 ---for:[106]实现冰冻列
	
	protected String url;//自定义链接
	protected String funname="openwindow";//自定义函数名称
	protected String arg;
	protected String link;//自定义标签函数
	
	protected String dictionary;
	protected String dictionaryCode;
	
	/**
	 * 子属性
	 */
	protected String childField; 
	public boolean isAutoLoadData() {
		return autoLoadData;
	}

	public void setAutoLoadData(boolean autoLoadData) {
		this.autoLoadData = autoLoadData;
	}
	
	public boolean isQuery() {
		return query;
	}

	public String getArg() {
		return arg;
	}

	public void setArg(String arg) {
		this.arg = arg;
	}

	public String getChildField() {
		return childField;
	}

	public void setChildField(String childField) {
		this.childField = childField;
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

	public void setQuery(boolean query) {
		this.query = query;
	}

	public boolean isImage() {
		return image;
	}

	public void setImage(boolean image) {
		this.image = image;
	}

	public String getTreefield() {
		return treefield;
	}

	public void setTreefield(String treefield) {
		this.treefield = treefield;
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
	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public String getTitle() {
		return title;
	}

	public String getField() {
		return field;
	}

	public Integer getWidth() {
		return width;
	}

	public String getRowspan() {
		return rowspan;
	}

	public String getColspan() {
		return colspan;
	}

	public String getAlign() {
		return align;
	}

	public boolean isSortable() {
		return sortable;
	}

	public boolean isCheckbox() {
		return checkbox;
	}

	public String getFormatter() {
		return formatter;
	}
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFunname() {
		return funname;
	}

	public void setFunname(String funname) {
		this.funname = funname;
	}
	public String getDictionary() {
		return dictionary;
	}

	public void setDictionary(String dictionary) {
		this.dictionary = dictionary;
	}
	public String getQueryMode() {
		return queryMode;
	}

	public void setQueryMode(String queryMode) {
		this.queryMode = queryMode;
	}

	public String getReplace() {
		return replace;
	}

	public void setReplace(String replace) {
		this.replace = replace;
	}

	public boolean isFrozenColumn() {
		return frozenColumn;
	}

	public void setFrozenColumn(boolean frozenColumn) {
		this.frozenColumn = frozenColumn;
	}

	public String getQueryClass() {
		return queryClass;
	}

	public void setQueryClass(String queryClass) {
		this.queryClass = queryClass;
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

	public boolean isThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(boolean thumbnail) {
		this.thumbnail = thumbnail;
	}

	public boolean isBanner() {
		return banner;
	}

	public void setBanner(boolean banner) {
		this.banner = banner;
	}
	
	
}
