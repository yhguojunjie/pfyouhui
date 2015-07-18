package com.yoxi.jgframework.ui.tag.core.easyui;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 
 * 类描述：列表操作项处理标签
 * 
 * @author: jeecg
 * @date： 日期：2012-12-7 时间：上午10:17:45
 * @version 1.0
 */
public class DataGridConfOptTag extends TagSupport {
	private static final long serialVersionUID = 256254900989328246L;
	protected String url;
	protected String title;
	private String message;//询问链接的提示语
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
		parent.setConfUrl(url,title,message,exp,operationCode);
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
	public void setMessage(String message) {
		this.message = message;
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