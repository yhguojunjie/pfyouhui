package com.yoxi.jgframework.ui.tag.core.easyui;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.yoxi.jgframework.core.tag.MenuTagUtils;
import com.yoxi.jgframework.system.entity.TSFunction;

/**
 * 
 * 类描述：上传标签
 * 
 * @author: com.yoxi.jgframework
 * @date： 日期：2012-12-7 时间：上午10:17:45
 * @version 1.0
 */
public class MenuTag extends TagSupport {
	private static final long serialVersionUID = 203143171437082253L;
	protected String style = "easyui";// 菜单样式
	protected List<TSFunction> parentFun;// 一级菜单
	protected List<TSFunction> childFun;// 二级菜单

	public void setParentFun(List<TSFunction> parentFun) {
		this.parentFun = parentFun;
	}

	public void setChildFun(List<TSFunction> childFun) {
		this.childFun = childFun;
	}

	public int doStartTag() throws JspTagException {
		return EVAL_PAGE;
	}

	public int doEndTag() throws JspTagException {
		try {
			JspWriter out = this.pageContext.getOut();
			out.print(MenuTagUtils.end(style, parentFun, childFun).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	public void setStyle(String style) {
		this.style = style;
	}

}
