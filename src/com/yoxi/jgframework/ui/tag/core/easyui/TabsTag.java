package com.yoxi.jgframework.ui.tag.core.easyui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.yoxi.jgframework.core.tag.TabsTagUtils;
import com.yoxi.jgframework.ui.tag.vo.easyui.Tab;


/**
 * 
 * 类描述：选项卡标签
 * 
 * @author: com.yoxi.jgframework
 * @date： 日期：2012-12-7 时间：上午10:17:45
 * @version 1.0
 */
public class TabsTag extends TagSupport {
	private static final long serialVersionUID = -7200111293947725922L;
	private String id;// 容器ID
	private String width;// 宽度
	private String heigth;// 高度
	private boolean plain;// 简洁模式
	private boolean fit = true;// 铺满浏览器
	private boolean border;// 边框
	private String scrollIncrement;// 滚动增量
	private String scrollDuration;// 滚动时间
	private boolean tools;// 工具栏
	private boolean tabs = true;// 是否创建父容器
	private boolean iframe = true;// 是否是iframe方式
	private String tabPosition = "top";// 选项卡位置

	public void setIframe(boolean iframe) {
		this.iframe = iframe;
	}

	public void setTabs(boolean tabs) {
		this.tabs = tabs;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public void setHeigth(String heigth) {
		this.heigth = heigth;
	}

	public void setPlain(boolean plain) {
		this.plain = plain;
	}

	public void setFit(boolean fit) {
		this.fit = fit;
	}

	public void setBorder(boolean border) {
		this.border = border;
	}

	public void setScrollIncrement(String scrollIncrement) {
		this.scrollIncrement = scrollIncrement;
	}

	public void setScrollDuration(String scrollDuration) {
		this.scrollDuration = scrollDuration;
	}

	public void setTools(boolean tools) {
		this.tools = tools;
	}

	public void setTabPosition(String tabPosition) {
		this.tabPosition = tabPosition;
	}

	public void setTabList(List<Tab> tabList) {
		this.tabList = tabList;
	}

	private List<Tab> tabList = new ArrayList<Tab>();

	public int doStartTag() throws JspTagException {
		tabList.clear();
		return EVAL_PAGE;
	}

	public int doEndTag() throws JspTagException {
		try {
			JspWriter out = this.pageContext.getOut();
			out.print(TabsTagUtils.end(tabList, id, width, heigth, plain, fit, border, scrollIncrement, scrollDuration, tools, tabs, iframe, tabPosition).toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	public void setTab(String id, String title,String iframe, String href, String iconCls, boolean cache, String content, String width, String heigth, boolean closable) {
		TabsTagUtils.setTab(tabList, id, title, iframe, href, iconCls, cache, content, width, heigth, closable);
	}

}
