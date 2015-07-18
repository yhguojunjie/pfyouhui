package com.yoxi.jgframework.core.tag;

import com.yoxi.jgframework.core.utils.StringUtils;

public class AutoCompleteTagUtils {
	/**
	 * 
	 * @param errormsg
	 * @param nullmsg
	 * @param datatype
	 * @param closefun
	 * @param label
	 * @param value
	 * @param name
	 * @param parse
	 * @param formatItem
	 * @param result
	 * @param dataSource
	 * @param minLength
	 * @param labelField
	 * @param valueField
	 * @param entityName
	 * @param searchField
	 * @param selectfun
	 * @param maxRows
	 * @return
	 */
	public static StringBuffer end(String errormsg, String nullmsg,
			String datatype, String closefun, String label, String value,
			String name, String parse, String formatItem, String result,
			String dataSource, Integer minLength, String labelField,
			String valueField, String entityName, String searchField,
			String selectfun, Integer maxRows) {
		return endTag(errormsg, nullmsg, datatype, closefun, label, value,
				name, parse, formatItem, result, dataSource, minLength,
				labelField, valueField, entityName, searchField, selectfun,
				maxRows);
	}

	private static StringBuffer endTag(String errormsg, String nullmsg,
			String datatype, String closefun, String label, String value,
			String name, String parse, String formatItem, String result,
			String dataSource, Integer minLength, String labelField,
			String valueField, String entityName, String searchField,
			String selectfun, Integer maxRows) {
		if (maxRows == null) {
			maxRows = 10;
		}
		StringBuffer sb = new StringBuffer();
		sb.append("<script type=\"text/javascript\">");
		sb.append("$(function() {" + "var tag=false;$(\"#" + name
				+ "\" ).autocomplete({"
				+ "source: function(request,response) {" + "$.ajax({"
				+ "url: \"commonController.do?getAutoList\","
				+ "jsonp:\"jsonpcallback\"," + "dataType: \"jsonp\","
				+ "data: {" + "featureClass: \"P\"," + "style: \"full\","
				+ "maxRows: 10," + "labelField: \"" + labelField + "\","
				+ "valueField: \"" + valueField + "\"," + "searchField: \""
				+ searchField + "\"," + "entityName: \"" + entityName + "\","
				+ "trem: encodeURIComponent(request.term)" + "},"
				+ "success: function(data) {"
				+ "response($.map(data.geonames,function(item) {");
		sb.append("var labels=\"\";");
		String[] labels = labelField.split(",");
		sb.append("if(item.nodate=='yes'){");
		for (String string : labels) {
			sb.append("labels+=item." + string + "+\",\";");
		}
		sb.append("}");
		sb.append("else{labels=item.nodate;}");
		sb.append("return {" + "label: labels," + "value: item." + searchField
				+ "," + "val: item." + valueField + "," + "obj: item" + "}"
				+ "}));" + "}" + "});" + "}," + "minLength: " + minLength + ","
				+ "select: function( event, ui ) {" + "$('#" + valueField
				+ "').val(ui.item.val);");
		if (selectfun != null) {
			sb.append("" + selectfun + "(ui.item.obj);");
		}
		sb.append("tag=true;if(ui.item.obj.nodate!=\'yes\'){tag=false}");
		sb.append("},"
				+ "open: function() {"
				+ "$( this ).removeClass( \"ui-corner-all\" ).addClass( \"ui-corner-top\" );"
				+ "tag=false;"
				+ "},"
				+ "close: function() {"
				+ "$( this ).removeClass( \"ui-corner-top\" ).addClass( \"ui-corner-all\" );"
				+ "if(tag==false){$('#" + valueField + "').val(\'\');$('#"
				+ name + "').val(\'\');");
		if (closefun != null) {
			sb.append("" + closefun + "(tag)");
		}
		sb.append("}" + "}" + "});" + "});");
		sb.append("</script>");
		sb.append("<input type=\"text\" id=\"" + name + "\" ");
		if (StringUtils.isNotEmpty(label)) {
			sb.append(" value=" + label + " readonly=true");
		}
		sb.append(">");
		sb.append("<input type=\"hidden\" id=\"" + valueField + "\" name=\""
				+ valueField + "\"");
		if (StringUtils.isNotEmpty(value)) {
			sb.append(" value=" + value + "");
		}
		if (StringUtils.isNotEmpty(datatype)) {
			sb.append(" datatype=" + datatype + "");
		}
		if (StringUtils.isNotEmpty(nullmsg)) {
			sb.append(" nullmsg=" + nullmsg + "");
		}
		if (StringUtils.isNotEmpty(errormsg)) {
			sb.append(" errormsg=" + errormsg + "");
		}
		sb.append(">");
		// update-begin--Author:tanghong Date:20130528 for：[ 114 ]自动补全标签优化
		StringBuffer nsb = new StringBuffer();
		nsb.append("<script type=\"text/javascript\">");
		nsb.append("$(document).ready(function() {")
				.append("$(\"#" + name + "\").autocomplete(\"" + dataSource
						+ "\",{")
				.append("max: 5,minChars: "
						+ minLength
						+ ",width: 200,scrollHeight: 100,matchContains: true,autoFill: false,extraParams:{")
				.append("featureClass : \"P\",style : \"full\",	maxRows : "
						+ maxRows + ",labelField : \"" + labelField
						+ "\",valueField : \"" + valueField + "\",")
				.append("searchField : \"" + searchField + "\",entityName : \""
						+ entityName + "\",trem: getTremValue}");
		if (StringUtils.isNotEmpty(parse)) {
			nsb.append(",parse:function(data){return " + parse
					+ ".call(this,data);}");
		}
		if (StringUtils.isNotEmpty(formatItem)) {
			nsb.append(",formatItem:function(row, i, max){return " + formatItem
					+ ".call(this,row, i, max);} ");
		}
		nsb.append("}).result(function (event, row, formatted) {");
		if (StringUtils.isNotEmpty(result)) {
			nsb.append(result + ".call(this,row); ");
		}
		nsb.append("}); });")
				.append("function getTremValue(){return $(\"#" + name
						+ "\").val();}")
				.append("</script>")
				.append("<input type=\"text\" id=\"" + name + "\" datatype=\""
						+ datatype + "\" nullmsg=\"" + nullmsg
						+ "\" errormsg=\"" + errormsg + "\"/>");
		if (StringUtils.isNotEmpty(label)) {
			nsb.append(" value=" + label + " readonly=true");
		}
		nsb.append("<input type=\"hidden\" id=\"" + valueField + "\" name=\""
				+ valueField + "\"/>");
		return nsb;
		// update-end--Author:tanghong Date:20130528 for：[ 114 ]自动补全标签优化-
	}
}
