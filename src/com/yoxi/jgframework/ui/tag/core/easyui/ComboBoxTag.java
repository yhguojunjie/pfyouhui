package com.yoxi.jgframework.ui.tag.core.easyui;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.yoxi.jgframework.core.tag.ComboBoxTagUtils;


/**
 * 
 * 类描述：下拉选择框标签
 * 
 * @author: com.yoxi.jgframework
 * @date： 日期：2012-12-7 时间：上午10:17:45
 * @version 1.0
 */
public class ComboBoxTag extends TagSupport {
	private static final long serialVersionUID = 2793724470407051041L;
	protected String id;// ID
	protected String url;//远程数据
	protected String name;//控件名称
	protected Integer width;//宽度
	protected String value;//控件值
	protected Integer listWidth;//下拉框宽度
	protected Integer listHeight;//下拉框高度
	protected boolean editable = false;//定义是否可以直接到文本域中键入文本
	protected boolean multiple = false;//是否允许多选
	public int doStartTag() throws JspTagException {
		return EVAL_PAGE;
	}
	public int doEndTag() throws JspTagException {
		try {
			JspWriter out = this.pageContext.getOut();
			out.print(ComboBoxTagUtils.end(id, url, name, width, value, listWidth, listHeight, editable, multiple).toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isMultiple() {
		return multiple;
	}
	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public void setListWidth(Integer listWidth) {
		this.listWidth = listWidth;
	}
	public void setListHeight(Integer listHeight) {
		this.listHeight = listHeight;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	
}
