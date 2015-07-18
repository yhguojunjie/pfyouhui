package com.yoxi.jgframework.core.tag;

public class ComboTreeTagUtils {
	public static StringBuffer endTag(String id, String url, String name,
			String width, String value, boolean multiple, String datatype) {
		return end(id, url, name, width, value, multiple, datatype);
	}

	private static StringBuffer end(String id, String url, String name,
			String width, String value, boolean multiple, String datatype) {
		StringBuffer sb = new StringBuffer();
		width = (width == null) ? "140" : width;
		sb.append("<script type=\"text/javascript\">" + "$(function() { "
				+ "$(\'#" + id + "\').combotree({" + "url :\'" + url + "\',"
				+ "width :\'" + width + "\'," + "multiple:" + multiple + ","
				+ "onChange:function(newValue,oldValue){" + " $(\'#" + id
				+ "\').val(newValue);" + "}");
		sb.append("});");
		sb.append("});");
		sb.append("</script>");
		sb.append("<input  name=\"" + name + "\" id=\"" + id + "\" value=\'"
				+ value + "\' datatype=\"" + datatype + "\" >");
		return sb;
	}
}
