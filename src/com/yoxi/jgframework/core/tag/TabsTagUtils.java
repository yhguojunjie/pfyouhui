package com.yoxi.jgframework.core.tag;

import java.util.List;

import com.yoxi.jgframework.ui.tag.vo.easyui.Tab;
import com.yoxi.jgframework.core.utils.ConvertUtils;

public class TabsTagUtils {
	public static StringBuffer end(List<Tab> tabList, String id, String width, String heigth, boolean plain, boolean fit, boolean border, String scrollIncrement, String scrollDuration, boolean tools, boolean tabs, boolean iframe,
			String tabPosition) {
		return endTag(tabList, id, width, heigth, plain, fit, border, scrollIncrement, scrollDuration, tools, tabs, iframe, tabPosition);
	}

	private static StringBuffer endTag(List<Tab> tabList, String id, String width, String heigth, boolean plain, boolean fit, boolean border, String scrollIncrement, String scrollDuration, boolean tools, boolean tabs, boolean iframe,
			String tabPosition) {
		StringBuffer sb = new StringBuffer();
		if (iframe) {
			sb.append("<script type=\"text/javascript\">");
			sb.append("$(function(){");
			if (tabList.size() > 0) {
				for (Tab tab : tabList) {
					sb.append("add" + id + "(\'" + tab.getTitle() + "\',\'" + tab.getHref() + "\',\'" + tab.getId() + "\',\'" + tab.getIcon() + "\',\'" + tab.isClosable() + "\');");
				}
			}
			sb.append("function add" + id + "(title,url,id,icon,closable) {");
			sb.append("$(\'#" + id + "\').tabs(\'add\',{");
			sb.append("id:id,");
			sb.append("title:title,");
			if (iframe) {
				sb.append("content:createFrame" + id + "(id),");
			} else {
				sb.append("href:url,");
			}
			sb.append("closable:closable=(closable =='false')?false : true,");
			sb.append("icon:icon");
			sb.append("});");
			sb.append("}");
			sb.append("$(\'#" + id + "\').tabs(");
			sb.append("{");
			sb.append("onSelect : function(title) {");
			sb.append("var p = $(this).tabs(\'getTab\', title);");
			if (tabList.size() > 0) {
				for (Tab tab : tabList) {
					sb.append("if (title == \'" + tab.getTitle() + "\') {");
					sb.append("p.find(\'iframe\').attr(\'src\',");
					sb.append("\'" + tab.getIframe() + "\');}");
				}
			}
			sb.append("}");
			sb.append("});");

			sb.append("function createFrame" + id + "(id)");
			sb.append("{");
			sb.append("var s = \'<iframe id=\"\'+id+\'\" scrolling=\"no\" frameborder=\"0\"  src=\"about:com.yoxi.jgframework\" width=\"" + ConvertUtils.getString(width, "100%") + "\" height=\"" + ConvertUtils.getString(heigth, "99.5%")
					+ "\"></iframe>\';");
			sb.append("return s;");
			sb.append("}");
			sb.append("});");
			sb.append("</script>");
		}
		if (tabs) {
			// ----------------------------------------------------------------
			// update-end--Author:wangyang Date:20130413 for：自动缩放
			// ----------------------------------------------------------------
			// 增加width属性，fit属性之前写死，改为由页面设定，不填默认true
			sb.append("<div id=\"" + id + "\" tabPosition=\"" + tabPosition + "\" border=flase style=\"margin:0px;padding:0px;overflow:hidden;width:" + ConvertUtils.getString(width, "auto") + ";\" class=\"easyui-tabs\" fit=\"" + fit
					+ "\">");
			// ----------------------------------------------------------------
			// update-end--Author:wangyang Date:20130413 for：自动缩放
			// ----------------------------------------------------------------
			if (!iframe) {
				for (Tab tab : tabList) {
					if (tab.getHref() != null) {
						sb.append("<div title=\"" + tab.getTitle() + "\" href=\"" + tab.getHref() + "\" style=\"margin:0px;padding:0px;\"></div>");// overflow:hidden;
					} else {
						sb.append("<div title=\"" + tab.getTitle() + "\"  style=\"margin:0px;padding:0px;overflow:hidden;\">");
						sb.append("<iframe id=\"\'" + tab.getId() + "\'\" scrolling=\"auto\" frameborder=\"0\"  src=\"" + tab.getIframe() + "\" width=\"" + ConvertUtils.getString(tab.getWidth(), "100%") + "\" height=\""
								+ ConvertUtils.getString(tab.getHeigth(), "99.5%") + "\"></iframe>\';");
						sb.append("</div>");
					}

				}
			}
			sb.append("</div>");

		}
		return sb;
	}
	public static void setTab(List<Tab> tabList,String id, String title,String iframe, String href, String iconCls, boolean cache, String content, String width, String heigth, boolean closable) {
		setTabTag(tabList, id, title, iframe, href, iconCls, cache, content, width, heigth, closable);
	}
		
		
	private static void setTabTag(List<Tab> tabList,String id, String title,String iframe, String href, String iconCls, boolean cache, String content, String width, String heigth, boolean closable) {
		Tab tab = new Tab();
		tab.setId(id);
		tab.setTitle(title);
		tab.setHref(href);
		tab.setCache(cache);
		tab.setIframe(iframe);
		tab.setContent(content);
		tab.setHeigth(heigth);
		tab.setIcon(iconCls);
		tab.setWidth(width);
		tab.setClosable(closable);
		tabList.add(tab);
	}
}
