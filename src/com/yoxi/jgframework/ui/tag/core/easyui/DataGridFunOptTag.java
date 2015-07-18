package com.yoxi.jgframework.ui.tag.core.easyui;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 
 * 类描述：列表自定义函数操作项处理标签
 * 
 * @author: jeecg
 * @date： 日期：2012-12-7 时间：上午10:17:45
 * @version 1.0
 */
public class DataGridFunOptTag extends TagSupport {
	private static final long serialVersionUID = 8908189420790176790L;
	protected String title;
	private String exp;//判断链接是否显示的表达式
	private String funname;//自定义函数名称
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
		parent.setFunUrl(title,exp,funname,operationCode);
		//----------------------------------------------------------------
		//update-end--Author:anchao  Date:20130415 for：按钮权限控制
		//----------------------------------------------------------------
		return EVAL_PAGE;
	}
	public void setFunname(String funname) {
		this.funname = funname;
	}
	public void setExp(String exp) {
		this.exp = exp;
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