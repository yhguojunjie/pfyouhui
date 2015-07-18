package com.yoxi.jgframework.core.tag;

import javax.servlet.jsp.PageContext;

import org.apache.commons.lang.StringUtils;

import com.yoxi.jgframework.common.model.common.SessionInfo;
import com.yoxi.jgframework.constant.Globals;

public class UploadTagUtils {

	public static StringBuffer end(PageContext pageContext, String id, String uploader, String name, String formData, String extend, Integer fileSizeLimit, String fileSizeLimitUnit,String buttonText, boolean multi, String queueID, boolean dialog, String callback,
			boolean auto, String onUploadSuccess, boolean view, String showUrlId) {
		return endTag(pageContext, id, uploader, name, formData, extend, fileSizeLimit,fileSizeLimitUnit, buttonText, multi, queueID, dialog, callback, auto, onUploadSuccess, view, showUrlId);
	}

	private static StringBuffer endTag(PageContext pageContext, String id, String uploader, String name, String formData, String extend, Integer fileSizeLimit,String fileSizeLimitUnit, String buttonText, boolean multi, String queueID, boolean dialog,
			String callback, boolean auto, String onUploadSuccess, boolean view, String showUrlId) {
		StringBuffer sb = new StringBuffer();
		if ("pic".equals(extend)) {
			extend = "*.jpg;*.jpeg;*.png;*.gif;*.bmp;*.ico;*.tif";
		}
		if ("mp3".equals(extend)) {
			extend = "*.mp3";
		}
		if ("android".equals(extend)) {
			extend = "*.apk";
		}
		if ("epub".equals(extend)) {
			extend = "*.epub";
		}
		if ("office".equals(extend)) {
			extend = "*.doc;*.docx;*.txt;*.ppt;*.xls;*.xlsx;*.html;*.htm;*.epub;*.rar;*.zip";
		}
		if ("excel".equals(extend)) {
			extend = "*.xls;*.xlsx";
		}
		if("jsp".equals(extend)){
			extend = "*.js;*cs;*.html";
		}
		sb.append("<link rel=\"stylesheet\" href=\"plug-in/uploadify/css/uploadify.css\" type=\"text/css\"></link>");
		sb.append("<script type=\"text/javascript\" src=\"plug-in/uploadify/jquery.uploadify-3.1.js\"></script>");
		sb.append("<script type=\"text/javascript\" src=\"plug-in/tools/Map.js\"></script>");
		// HttpSession session =
		// this.pageContext.getRequest().getServletContext().getAttribute("session");
		// ServletRequest request = pageContext.getRequest();
		// String path = pageContext.getServletContext().getContextPath();
		// String basePath = request.getScheme() + "://" +
		// request.getServerName() + ":" + request.getServerPort() + path
		// + "/";
		sb.append("<script type=\"text/javascript\">" + "var flag = false;" + "var success = false;" + "var successMsg = \'\';" + "var fileitem=\"\";" + "var fileKey=\"\";" + "var m = new Map();" + "$(function(){ setTimeout(function(){" + "$(\'#" + id
				+ "\').uploadify({" + "buttonText:\'" + buttonText + "\'," + "auto:" + auto + "," + "progressData:\'speed\'," + "multi:" + multi + "," + "height:25," + "overrideEvents:[\'onDialogClose\']," + "fileTypeDesc:\'文件格式:\',"
				+ "queueID:\'" + queueID + "\'," + "fileTypeExts:\'" + extend + "\',successTimeout: 30000," + "fileSizeLimit:\'" + fileSizeLimit + fileSizeLimitUnit+"\'," + "swf:\'plug-in/uploadify/uploadify.swf\',	" + "uploader:\'");
		if (StringUtils.contains(uploader, "?")) {
			String prefix = StringUtils.substring(uploader, 0, StringUtils.indexOf(uploader, "?"));
			String suffix = StringUtils.substring(uploader, StringUtils.indexOf(uploader, "?"), StringUtils.length(uploader));
			System.out.println("prefix==" + prefix + ",suffix=" + suffix);
			sb.append(prefix + ";jsessionid=" + pageContext.getSession().getId() + suffix + "\'");
		} else {
			sb.append(uploader + ";jsessionid=" + pageContext.getSession().getId() + "\'");
		}

		sb.append(",onUploadStart : function(file) { ");
		SessionInfo sessioninfo = (SessionInfo) (pageContext.getSession().getAttribute(Globals.USER_SESSION));
		if (formData != null) {
			String[] paramnames = formData.split(",");
			for (int i = 0; i < paramnames.length; i++) {
				String paramname = paramnames[i];
				sb.append("var " + paramname + "=$(\'#" + paramname + "\').val();");
			}
			sb.append("$(\'#" + id + "\').uploadify(\"settings\", \"formData\", {");
			for (int i = 0; i < paramnames.length; i++) {
				String paramname = paramnames[i];
				// sb.append("'"+paramname+"':"+paramname+",");
				if (i == paramnames.length - 1) {
					sb.append("'" + paramname + "':" + paramname + "");
				} else {
					sb.append("'" + paramname + "':" + paramname + ",");
				}
			}
			if (sessioninfo != null && sessioninfo.getUser() != null)
				sb.append(",\"userid\":\"" + sessioninfo.getUser().getId() + "\"");
			sb.append("});");
		} else if (sessioninfo != null && sessioninfo.getUser() != null) {
			sb.append("$(\'#" + id + "\').uploadify(\"settings\", \"formData\", {");
			sb.append("\"userid\":\"" + sessioninfo.getUser().getId() + "\"");
			sb.append("});");
		}

		sb.append("} ," + "onQueueComplete : function(queueData) { ");
		if (dialog) {
			sb.append("if (success) {var win = frameElement.api.opener; " //
					+ "win.reloadTable(); win.tip(successMsg); frameElement.api.close();} ");
		} else {
			if (callback != null)
				sb.append("" + callback + "();");
		}
		if (view) {
			sb.append("$(\"#viewmsg\").html(m.toString());");
			sb.append("$(\"#fileKey\").val(fileKey);");
		}
		sb.append("},");

		// sb.append("onUploadComplete:function(file) {if (flag) console.info(file);},");
		// 上传成功处理函数
		sb.append("onUploadSuccess : function(file, data, response) {");
		sb.append("var d=$.parseJSON(data);success = d.success;successMsg = d.msg;");
		if (showUrlId != null) {
			sb.append("if(d.attributes != null){$(\'#" + showUrlId + "\').val(d.attributes.showUrlId);}");
		}
		if (view) {
			sb.append("var fileitem=\"<span id=\'\"+d.attributes.id+\"\'>" + "<a href=\'#\' onclick=openwindow(\'文件查看\',\'\"+d.attributes.viewhref+\"\',\'70%\',\'80%\') title=\'查看\'>\"+d.attributes.name+\"</a>"
					+ "<img border=\'0\' onclick=confuploadify(\'\"+d.attributes.delurl+\"\',\'\"+d.attributes.id+\"\') title=\'删除\' src=\'plug-in/uploadify/img/uploadify-cancel.png\' widht=\'15\' height=\'15\'>&nbsp;&nbsp;</span>\";");
			sb.append("m.put(d.attributes.id,fileitem);");
			sb.append("fileKey=d.attributes.fileKey;");
		}
		if (onUploadSuccess != null) {
			if (StringUtils.contains(onUploadSuccess, "(") && StringUtils.contains(onUploadSuccess, ")")) {
				sb.append(onUploadSuccess + ";");
			} else {
				sb.append(onUploadSuccess + "(d,file,response);");
			}

		}
		sb.append("if(!d.success){");
		sb.append("$.dialog.setting.zIndex = 19800;$.dialog.tips(d.msg, 1);$.dialog.setting.zIndex = 1980; ");
		sb.append("}");
		sb.append("},");
		sb.append("onFallback : function(){tip(\"您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试\")},");
		sb.append("onSelectError : function(file, errorCode, errorMsg){");
		sb.append("switch(errorCode) {");
		sb.append("case -100:");
		sb.append("tip(\"上传的文件数量已经超出系统限制的\"+$(\'#" + id + "\').uploadify(\'settings\',\'queueSizeLimit\')+\"个文件！\");");
		sb.append("break;");
		sb.append("case -110:" + "tip(\"文件 [\"+file.name+\"] 大小超出系统限制的\"+$(\'#" + id + "\').uploadify(\'settings\',\'fileSizeLimit\')+\"大小！\");" + "break;" + "case -120:" + "tip(\"文件 [\"+file.name+\"] 大小异常！\");" + "break;" + "case -130:"
				+ "tip(\"文件 [\"+file.name+\"] 类型不正确！\");" + "break;" + "}");
		sb.append("}," + "onUploadProgress : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) { "
		// +"tip('<span>文件上传成功:'+totalBytesUploaded/1024 + ' KB 已上传 ,总数' + totalBytesTotal/1024 + ' KB.</span>');"
				+ "}" + "});" + "},10)});" + "function upload() {" + "	$(\'#" + id + "\').uploadify('upload', '*');" + "		return flag;" + "}" + "function cancel() {" + "$(\'#" + id + "\').uploadify('cancel', '*');" + "}" + "</script>");
		sb.append("<span id=\"" + id + "span\"><input type=\"file\" name=\"" + name + "\" id=\"" + id + "\" /></span>");
		if (view) {
			sb.append("<span id=\"viewmsg\"></span>");
			sb.append("<input type=\"hidden\" name=\"fileKey\" id=\"fileKey\" />");
		}

		return sb;
	}
}
