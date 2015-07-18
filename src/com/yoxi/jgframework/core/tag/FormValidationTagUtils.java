package com.yoxi.jgframework.core.tag;

import com.yoxi.jgframework.core.utils.StringUtils;

public class FormValidationTagUtils {
	public static StringBuffer end(String formid, Boolean refresh, String callback, String beforeSubmit, String btnsub, String btnreset, String layout, String usePlugin, boolean dialog, String action, String tabtitle, String tiptype) {
		return endTag(formid, refresh, callback, beforeSubmit, btnsub, btnreset, layout, usePlugin, dialog, action, tabtitle, tiptype);
	}

	private static StringBuffer endTag(String formid, Boolean refresh, String callback, String beforeSubmit, String btnsub, String btnreset, String layout, String usePlugin, boolean dialog, String action, String tabtitle, String tiptype) {
		StringBuffer sb = new StringBuffer();
		if (layout.equals("div")) {
			sb.append("<link rel=\"stylesheet\" href=\"plug-in/Validform/css/divfrom.css\" type=\"text/css\"/>");
			if (tabtitle != null)
				sb.append("<script type=\"text/javascript\" src=\"plug-in/Validform/js/form.js\"></script>");
		}
		sb.append("<link rel=\"stylesheet\" href=\"plug-in/Validform/css/style.css\" type=\"text/css\"/>");
		sb.append("<link rel=\"stylesheet\" href=\"plug-in/Validform/css/tablefrom.css\" type=\"text/css\"/>");
		sb.append("<script type=\"text/javascript\" src=\"plug-in/Validform/js/Validform_v5.3.1_min.js\"></script>");
		sb.append("<script type=\"text/javascript\" src=\"plug-in/Validform/js/Validform_Datatype.js\"></script>");
		// ----------------------------------------------------------------
		// update-begin--Author:zhangdaihao Date:20130225 for：金额校验规则
		sb.append("<script type=\"text/javascript\" src=\"plug-in/Validform/js/datatype.js\"></script>");
		// update-end--Author:zhangdaihao Date:20130225 for：金额校验规则
		// ----------------------------------------------------------------
		if (usePlugin != null) {
			if (usePlugin.indexOf("jqtransform") >= 0) {
				sb.append("<SCRIPT type=\"text/javascript\" src=\"plug-in/Validform/plugin/jqtransform/jquery.jqtransform.js\"></SCRIPT>");
				sb.append("<LINK rel=\"stylesheet\" href=\"plug-in/Validform/plugin/jqtransform/jqtransform.css\" type=\"text/css\"></LINK>");
			}
			if (usePlugin.indexOf("password") >= 0) {
				sb.append("<SCRIPT type=\"text/javascript\" src=\"plug-in/Validform/plugin/passwordStrength/passwordStrength-min.js\"></SCRIPT>");
			}
		}
		sb.append("<script type=\"text/javascript\">");
		sb.append("$(function(){");
		sb.append("$(\"#" + formid + "\").Validform({");
		// update-begin--Author:zhaojunfu Date:20130415 for：验证信息的显示方式改动
		if (tiptype != null && !"".equals(tiptype)) {
			sb.append("tiptype:" + tiptype + ",");
		} else {
			sb.append("tiptype:1,");
		}
		// update-end--Author:zhaojunfu Date:20130415 for：验证信息的显示方式改动
		sb.append("btnSubmit:\"#" + btnsub + "\",");
		sb.append("btnReset:\"#" + btnreset + "\",");
		sb.append("ajaxPost:true,");
		if (beforeSubmit != null) {
			sb.append("beforeSubmit:function(curform){var tag=false;");
			sb.append("return " + beforeSubmit + "(curform);");

			sb.append("},");
		}
		if (usePlugin != null) {
			StringBuffer passsb = new StringBuffer();
			if (usePlugin.indexOf("password") >= 0) {
				passsb.append("passwordstrength:{");
				passsb.append("minLen:6,");
				passsb.append("maxLen:18,");
				passsb.append("trigger:function(obj,error)");
				passsb.append("{");
				passsb.append("if(error)");
				passsb.append("{");
				passsb.append("obj.parent().next().find(\".Validform_checktip\").show();");
				passsb.append("obj.find(\".passwordStrength\").hide();");
				passsb.append("}");
				passsb.append("else");
				passsb.append("{");
				passsb.append("$(\".passwordStrength\").show();");
				passsb.append("obj.parent().next().find(\".Validform_checktip\").hide();");
				passsb.append("}");
				passsb.append("}");// trigger结尾
				passsb.append("}");// passwordstrength结尾
			}
			StringBuffer jqsb = new StringBuffer();
			if (usePlugin.indexOf("jqtransform") >= 0) {
				if (usePlugin.indexOf("password") >= 0) {
					sb.append(",");
				}
				jqsb.append("jqtransform :{selector:\"select\"}");
			}
			sb.append("usePlugin:{");
			if (usePlugin.indexOf("password") >= 0) {
				sb.append(passsb);
			}
			if (usePlugin.indexOf("jqtransform") >= 0) {
				sb.append(jqsb);
			}
			sb.append("},");
		}
		sb.append("callback:function(data){");
		if (dialog) {
			sb.append("var win = frameElement.api.opener;");
			// ----------------------------------------------------------------
			// update-begin--Author:wangyang Date:20130409 for：错误提示
			// ----------------------------------------------------------------
			// 先判断是否成功，成功再刷新父页面，否则return false

			// ----------------------------------------------------------------
			// update-begin--Author:sun Date:20130426 for：错误提示返回提示的展示bug
			// ----------------------------------------------------------------
			// 如果不成功，返回值接受使用data.msg. 原有的data.responseText会报null
			sb.append("if(data.success==true){frameElement.api.close();win.tip(data.msg);}else{if(data.responseText==''||data.responseText==undefined)$(\"#" + formid + "\").html(data.msg);else $(\"#" + formid
					+ "\").html(data.responseText); return false;}");
			// ----------------------------------------------------------------
			// update-begin--Author:sun Date:20130426 for：错误提示返回提示的展示bug
			//
			if (refresh) {
				sb.append("win.reloadTable();");
			}
			if (StringUtils.isNotEmpty(callback)) {
				sb.append("win." + callback + "(data);");
			}
			// 失败tip不提示
			// sb.append("win.tip(data.msg);");

			// ----------------------------------------------------------------
			// update-end--Author:wangyang Date:20130409 for：错误提示
			// ----------------------------------------------------------------
		} else {
			sb.append("" + callback + "(data);");
		}
		sb.append("}" + "});" + "});" + "</script>");
		sb.append("");
		sb.append("</form>");
		if ("div".equals(layout)) {
			sb.append("</div>");
			if (tabtitle != null) {
				String[] tabtitles = tabtitle.split(",");
				sb.append("<div id=\"navigation\" style=\"display: none;\">");
				sb.append("<ul>");
				for (String string : tabtitles) {
					sb.append("<li>");
					sb.append("<a href=\"#\">" + string + "</a>");
					sb.append("</li>");
				}
				sb.append("</ul>");
				sb.append("</div>");
			}
			sb.append("</div></div>");
		}
		return sb;
	}

	public static StringBuffer start(String formid, String btnsub, String layout, boolean dialog, String action) {
		return startTag(formid, btnsub, layout, dialog, action);
	}
	private static StringBuffer startTag(String formid, String btnsub, String layout, boolean dialog, String action) {
		StringBuffer sb = new StringBuffer();
		if ("div".equals(layout)) {
			sb.append("<div id=\"content\">");
			sb.append("<div id=\"wrapper\">");
			sb.append("<div id=\"steps\">");
		}
		sb.append("<form id=\"" + formid + "\" action=\"" + action + "\" name=\"" + formid + "\" method=\"post\">");
		if ("btn_sub".equals(btnsub) && dialog)
			sb.append("<input type=\"hidden\" id=\"" + btnsub + "\" class=\"" + btnsub + "\"/>");
		return sb;
	}
}
