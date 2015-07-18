package com.yoxi.jgframework.core.tag;

public class ComboBoxTagUtils {
	public static StringBuffer end(String id, String url, String name,
			Integer width, String value, Integer listWidth, Integer listHeight,
			boolean editable, boolean multiple) {
		return endTag(id, url, name, width, value, listWidth, listHeight,
				editable, multiple);
	}

	private static StringBuffer endTag(String id, String url, String name,
			Integer width, String value, Integer listWidth, Integer listHeight,
			boolean editable, boolean multiple) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script type=\"text/javascript\">" + "$(function() {"
				+ "$(\'#"
				+ id
				+ "\').combobox({"
				+ "url:\'"
				+ url
				+ "\',"
				+ "editable:"
				+ editable
				+ ","
				+ "valueField:\'id\',"
				+ "textField:\'text\',"
				+ "width:\'"
				+ width
				+ "\',"
				+ "listWidth:\'"
				+ listWidth
				+ "\',"
				+ "listHeight:\'"
				+ listWidth
				+ "\',"
				+ "multiple:"
				+ multiple + "," + "panelHeight:\'auto\'");
		sb.append("});");
		sb.append("});");
		sb.append("</script>");
		sb.append("<input  name=\"" + name + "\" id=\"" + id + "\" value=\'"
				+ value + "\'>");
		return sb;
	}
}
