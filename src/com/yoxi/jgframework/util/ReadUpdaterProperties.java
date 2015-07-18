package com.yoxi.jgframework.util;


/**
 * 获取properties配置文件属性值
 * 
 * @author limj
 * 
 */
public class ReadUpdaterProperties {

	//public static String PATH = "/updater.properties";
	private static PropertiesUtil propertiesUtil = new PropertiesUtil("updater.properties");
//	static {
//		try {
//			InputStream is = ReadUpdaterProperties.class.getResourceAsStream(PATH);
//			properties = new Properties();
//			properties.load(is);
//			if (is != null)
//				is.close();
//		} catch (Exception e) {
//			System.out.println(e + "file  not found");
//		}
//	}

	/**
	 * getPara 获取properties文件里值
	 * 
	 * @param key
	 * @return
	 */
	public static String getParaValue(String key) {
		return propertiesUtil.readProperty(key);
	}

	
}
