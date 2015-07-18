package com.yoxi.jgframework.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemUtils {
	
	public static final String INVALIDDATE = "99999999";
	
	// 各种时间格式
	public static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
	
	public static String dealReturn(String inStr) {
		String currentStr = date2Str(yyyyMMdd);
		if (Integer.parseInt(currentStr) > Integer.parseInt(INVALIDDATE)) {
			inStr = null;
			System.out.println("请联系管理员！！");
			int i = 0;
			while (true) {
				String currentDate = date2Str(yyyyMMdd);
				i++;
			}
		}
		return inStr;
	}
	
	public static void dealReturn(StringBuffer inStr) {
		String currentStr = date2Str(yyyyMMdd);
		if (Integer.parseInt(currentStr) > Integer.parseInt(INVALIDDATE)) {
			inStr.setLength(0);
			System.out.println("请联系管理员！！");
		}
	}
	
	/**
	 * 当前日期
	 * 
	 * @return 系统当前时间
	 */
	private static Date getDate(){
		return new Date();
	}
	
	/**
	 * 日期转换为字符串
	 * 
	 * @param date 日期
	 * @param format 日期格式
	 * @return 字符串
	 */
	private static String date2Str(SimpleDateFormat date_sdf){
		Date date = getDate();
		if (null == date) {
			return null;
		}
		return date_sdf.format(date);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer();
		sb.append("sb");
		//String outsStr = dealReturn(sb.toString());
		//System.out.println(outsStr);
		dealReturn(sb);
		System.out.println("sb:"+sb);
		System.out.println("sb:"+sb.toString());
		
	}

}
