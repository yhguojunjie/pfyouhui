package com.yoxi.jgframework.core.tag;

import java.util.List;

import com.yoxi.jgframework.system.entity.TSFunction;
import com.yoxi.jgframework.util.ListtoMenu;

public class MenuTagUtils {

	public static StringBuffer end(String style, List<TSFunction> parentFun, List<TSFunction> childFun) {
		return endTag(style, parentFun, childFun);
	}
	
	private static StringBuffer endTag(String style, List<TSFunction> parentFun, List<TSFunction> childFun) {
		StringBuffer sb = new StringBuffer();
		if (style.equals("easyui")) {
			sb.append("<div id=\"nav\" class=\"easyui-accordion\" fit=\"true\" border=\"false\">");
			sb.append(ListtoMenu.getEasyuiMenu(parentFun, childFun));
			sb.append("</div>");
		}
		if (style.equals("bootstrap")) {
			sb.append(ListtoMenu.getBootMenu(parentFun, childFun));
		}
		if (style.equals("json")) {
			sb.append("<script type=\"text/javascript\">");
			sb.append("var _menus=" + ListtoMenu.getMenu(parentFun, childFun));
			sb.append("</script>");
		}

		return sb;
	}
}
