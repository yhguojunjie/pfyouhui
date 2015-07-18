package com.yoxi.jgframework.core.utils;

public class JsonUtils {
	/**
	 * JSON字符串特殊字符处理，比如：“\A1;1300”
	 * 
	 * @param s
	 * @return String
	 */
	public static String string2Json(Object s) {
		StringBuffer sb = new StringBuffer();
		if (s instanceof String) {
			String json = (String) s;
			for (int i = 0, length = json.length(); i < length; i++) {
				char c = json.charAt(i);
				switch (c) {
				case '\"':
					sb.append("\\\"");
					break;
				case '\\':
					sb.append("\\\\");
					break;
				case '/':
					sb.append("\\/");
					break;
				case '\b':
					sb.append("\\b");
					break;
				case '\f':
					sb.append("\\f");
					break;
				case '\n':
					sb.append("\\n");
					break;
				case '\r':
					sb.append("\\r");
					break;
				case '\t':
					sb.append("\\t");
					break;
				default:
					sb.append(c);
				}
			}
		} else {
			sb.append(s.toString());
		}
		return sb.toString();
	}
}
