package com.yoxi.jgframework.ui.tag.core.easyui;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.yoxi.jgframework.core.tag.ComboTreeTagUtils;

/**
 * 
 * 类描述：下拉树形菜单
 * 
 * @author: com.yoxi.jgframework
 * @date： 日期：2012-12-7 时间：上午10:17:45
 * @version 1.0
 */
public class ComboTreeTag extends TagSupport {
	private static final long serialVersionUID = -285864933849312712L;
	protected String id;// ID
	protected String url;// 远程数据
	protected String name;// 控件名称
	protected String width;// 宽度
	protected String value;// 控件值
	private boolean multiple=false;//是否多选
	private String datatype=""; // 验证类型
	
	public int doStartTag() throws JspTagException {
		return EVAL_PAGE;
	}

	public int doEndTag() throws JspTagException {
		try {
			JspWriter out = this.pageContext.getOut();
			out.print(ComboTreeTagUtils.endTag(id, url, name, width, value, multiple, datatype).toString());
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

	public void setWidth(String width) {
		this.width = width;
	}

	public void setValue(String value) {
		this.value = value;
	}
	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	
	
}
