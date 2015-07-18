package com.yoxi.jgframework.ui.tag.core.easyui;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.yoxi.jgframework.core.tag.AutoCompleteTagUtils;

/**
 * 
 * 自动补全 jquery ui Autocomplete
 * 
 * @author: com.yoxi.jgframework
 * @date： 日期：2012-12-7 时间：上午10:17:45
 * @version 1.0
 */
@SuppressWarnings("serial")
public class AutocompleteTag extends TagSupport {
	/**
	 * HTML控件名称
	 */
	private String name;// 控件名称
	/**
	 * 数据来源
	 */
	private String dataSource;// 数据源
	/**
	 * 触发提示文字长度
	 */
	private Integer minLength = 2;// 触发提示文字长度
	/**
	 * 提示显示的字段
	 */
	private String labelField;// 提示显示的字段
	/**
	 * 查询关键字字段
	 */
	private String searchField;// 查询关键字字段
	/**
	 * 传递后台的字段
	 */
	private String valueField;// 传递后台的字段
	/**
	 * 实体名称
	 */
	private String entityName;// 实体名称
	/**
	 * 选中后调用的函数
	 */
	private String selectfun;// 选中后调用的函数
	/**
	 * 传入显示值
	 */
	private String label;// 传入显示值
	/**
	 * 传入隐藏域值
	 */
	private String value;// 传入隐藏域值
	/**
	 * 数据验证类型
	 */
	private String datatype;// 数据验证类型
	/**
	 * 数据为空时验证
	 */
	private String nullmsg;// 数据为空时验证
	/**
	 * 数据格式不对时验证
	 */
	private String errormsg;// 数据格式不对时验证
	/**
	 * 没有选择下拉项目的处理函数
	 */
	private String closefun;// 没有选择下拉项目的处理函数

	private String parse;
	private String formatItem;
	private String result;
	private Integer maxRows;// 显示的最多的条数

	public void setClosefun(String closefun) {
		this.closefun = closefun;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	public void setNullmsg(String nullmsg) {
		this.nullmsg = nullmsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int doStartTag() throws JspTagException {
		return EVAL_PAGE;
	}

	public int doEndTag() throws JspTagException {
		try {
			JspWriter out = this.pageContext.getOut();
			StringBuffer stringBuffer = AutoCompleteTagUtils.end(errormsg, nullmsg,
					datatype, closefun, label, value, name, parse, formatItem,
					result, dataSource, minLength, labelField, valueField,
					entityName, searchField, selectfun, maxRows);
			out.print(stringBuffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParse(String parse) {
		this.parse = parse;
	}

	public void setFormatItem(String formatItem) {
		this.formatItem = formatItem;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public void setMinLength(Integer minLength) {
		this.minLength = minLength;
	}

	public void setLabelField(String labelField) {
		this.labelField = labelField;
	}

	public void setValueField(String valueField) {
		this.valueField = valueField;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	public void setSelectfun(String selectfun) {
		this.selectfun = selectfun;
	}

	public void setMaxRows(Integer maxRows) {
		if (maxRows == null) {
			maxRows = 10;
		}
		this.maxRows = maxRows;
	}

}
