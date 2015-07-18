package com.yoxi.jgframework.ui.tag.core.easyui;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 
 * 类描述：列表弹出窗操作项处理标签
 * 
 * @author: jeecg
 * @date： 日期：2012-12-7 时间：上午10:17:45
 * @version 1.0
 */
public class DataGridOpenOptTag extends TagSupport {
	private static final long serialVersionUID = -4873543371246478901L;
	protected String url;//弹出页面地址
	protected String width="100%";//弹出窗口宽度
	protected String height="100%";//弹出窗口高度
	protected String title;//链接标题
	private String exp;//判断链接是否显示的表达式
	private String funname;
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
		parent.setOpenUrl(url,title,width,height,exp,operationCode,funname);
		//----------------------------------------------------------------
		//update-end--Author:anchao  Date:20130415 for：按钮权限控制
		//----------------------------------------------------------------
		return EVAL_PAGE;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public void setHeight(String height) {
		this.height = height;
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
	/**
	 * @return the funname
	 */
	public String getFunname() {
		return funname;
	}
	/**
	 * @param funname the funname to set
	 */
	public void setFunname(String funname) {
		this.funname = funname;
	}
	
}