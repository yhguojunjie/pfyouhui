package com.yoxi.jgframework.core.tag;

import com.yoxi.jgframework.core.utils.StringUtils;

public class ChooseTagUtils {
	public static StringBuffer end(String hiddenName, String textname,
			String icon, String title, String url, String top, String left,
			String width, String height, String name, String hiddenid,
			Boolean isclear, String fun) {
		StringBuffer sb = new StringBuffer();
		sb.append("<a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" icon=\""
				+ icon + "\" onClick=\"choose()\">选择</a>");
		if (isclear && StringUtils.isNotEmpty(textname)) {
			sb.append("<a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" icon=\"icon-redo\" onClick=\"clearAll();\">清空</a>");
		}
		sb.append("<script type=\"text/javascript\">");
		sb.append("function choose(){");
		sb.append("$.dialog({");
		sb.append("content: \'url:" + url + "\',");
		sb.append("zIndex: 1997,");
		if (title != null) {
			sb.append("title: \'" + title + "\',");
		}
		sb.append("lock : true,");
		if (width != null) {
			sb.append("width :\'" + width + "\',");
		} else {
			sb.append("width :400,");
		}
		if (height != null) {
			sb.append("height :\'" + height + "\',");
		} else {
			sb.append("height :350,");
		}
		if (left != null) {
			sb.append("left :\'" + left + "\',");
		} else {
			sb.append("left :'85%',");
		}
		if (top != null) {
			sb.append("top :\'" + top + "\',");
		} else {
			sb.append("top :'65%',");
		}
		sb.append("opacity : 0.4,");
		sb.append("button : [ {");
		sb.append("name : \'确认\',");
		sb.append("callback : function() {");
		sb.append("iframe = this.iframe.contentWindow;");
		String[] textnames = null;
		if (StringUtils.isNotEmpty(textname)) {
			textnames = textname.split(",");
			for (int i = 0; i < textnames.length; i++) {
				sb.append("var " + textnames[i] + "=iframe.get" + name
						+ "Selections(\'" + textnames[i] + "\');	");
				sb.append("$(\'#" + textnames[i] + "\').val(" + textnames[i]
						+ ");");
				// update-begin--Author:tanghong Date:20130422
				// for：用户编辑，角色选择后，仍提示错误信息
				sb.append("$(\'#" + textnames[i] + "\').blur();");
				// update-end--Author:tanghong Date:20130422
				// for：用户编辑，角色选择后，仍提示错误信息
			}
		}
		sb.append("var id =iframe.get" + name + "Selections(\'" + hiddenid
				+ "\');");
		sb.append("if (id!== undefined &&id!=\"\"){");
		sb.append("$(\'#" + hiddenName + "\').val(id);");
		sb.append("}");
		if (StringUtils.isNotEmpty(fun)) {
			sb.append("" + fun + "();");// 执行自定义函数
		}
		sb.append("},");
		sb.append("focus : true");
		sb.append("}, {");
		sb.append("name : \'取消\',");
		sb.append("callback : function() {");
		sb.append("}");
		sb.append("} ]");
		sb.append("});");
		sb.append("}");
		if (isclear && StringUtils.isNotEmpty(textname)) {
			sb.append("function clearAll(){");
			for (int i = 0; i < textnames.length; i++) {
				sb.append("$(\'#" + textnames[i] + "\').val(\"\");");
				// update-begin--Author:tanghong Date:20130422
				// for：用户编辑，角色选择后，仍提示错误信息
				sb.append("$(\'#" + textnames[i] + "\').blur();");
				// update-end--Author:tanghong Date:20130422
				// for：用户编辑，角色选择后，仍提示错误信息
			}
			sb.append("$(\'#" + hiddenName + "\').val(\"\");");
			sb.append("}");
		}
		sb.append("</script>");
		return sb;
	}
}
