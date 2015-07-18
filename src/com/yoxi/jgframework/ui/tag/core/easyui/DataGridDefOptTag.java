package com.yoxi.jgframework.ui.tag.core.easyui;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 
 * 类描述：列表默认操作项标签
 * 
 * @author: jeecg
 * @date： 日期：2012-12-7 时间：上午10:17:45
 * @version 1.0
 */
public class DataGridDefOptTag extends TagSupport {
	private static final long serialVersionUID = -3035726581472393259L;
	protected String url;
	protected String title;
	private String exp;//判断链接是否显示的表达式
	//----------------------------------------------------------------
	//update-start--Author:anchao  Date:20130415 for：按钮权限控制
	//----------------------------------------------------------------
	private String operationCode;//按钮的操作Code
	//----------------------------------------------------------------
	//update-end--Author:anchao  Date:20130415 for：按钮权限控制
	//----------------------------------------------------------------
	public int doStartTag() throws JspTagException {
		return EVAL_PAGE;
	}
	public int doEndTag() throws JspTagException {
		Tag t = findAncestorWithClass(this, DataGridTag.class);
		DataGridTag parent = (DataGridTag) t;
		//----------------------------------------------------------------
		//update-start--Author:anchao  Date:20130415 for：按钮权限控制
		//----------------------------------------------------------------
		parent.setDefUrl(url, title, exp,operationCode);
		//----------------------------------------------------------------
		//update-end--Author:anchao  Date:20130415 for：按钮权限控制
		//----------------------------------------------------------------
		return EVAL_PAGE;
	}
	
	public void setExp(String exp) {
		this.exp = exp;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	//----------------------------------------------------------------
	//update-start--Author:anchao  Date:20130415 for：按钮权限控制
	//----------------------------------------------------------------
	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}
	//----------------------------------------------------------------
	//update-end--Author:anchao  Date:20130415 for：按钮权限控制
	//----------------------------------------------------------------
	
}