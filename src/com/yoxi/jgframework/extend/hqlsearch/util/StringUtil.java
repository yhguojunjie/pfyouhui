package com.yoxi.jgframework.extend.hqlsearch.util;

import org.apache.commons.lang.StringUtils;

public class StringUtil {

	/**
	 * 首字母大写
	 * @param realName
	 * @return
	 */
	public static String firstUpperCase(String realName) {
		return StringUtils.replaceChars(realName, realName.substring(0, 1),realName.substring(0, 1).toUpperCase());
	}

	/**
	 * 首字母小写
	 * @param realName
	 * @return
	 */
	public static String firstLowerCase(String realName) {
		return StringUtils.replaceChars(realName, realName.substring(0, 1),realName.substring(0, 1).toLowerCase());
	}

}
