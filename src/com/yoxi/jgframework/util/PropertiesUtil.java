package com.yoxi.jgframework.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Properties文件操作类
 * @author william
 *
 */
public class PropertiesUtil {
	/**
	 * Properties文件名
	 */
	private String properiesName = "";

	/**
	 * 构造函数
	 */
	public PropertiesUtil() {

	}
	/**
	 * 构造函数
	 * @param fileName Properties文件名
	 */
	public PropertiesUtil(String fileName) {
		this.properiesName = fileName;
	}
	/**
	 * 根据key获取Properties中的值
	 * @param key Properties中的key
	 * @return  返回key对应的值
	 */
	public String readProperty(String key) {
		String value = "";
		InputStream is = null;
		try {
			is = PropertiesUtil.class.getClassLoader().getResourceAsStream(
					properiesName);
			Properties p = new Properties();
			p.load(is);
			value = p.getProperty(key);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return value;
	}
	/**
	 * 获取Properties中所有的Properties键值对集合
	 * @return Properties键值对集合
	 */
	public Properties getProperties() {
		Properties p = new Properties();
		InputStream is = null;
		try {
			is = PropertiesUtil.class.getClassLoader().getResourceAsStream(
					properiesName);
			p.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return p;
	}
	/**
	 * 给Properties文件写入键值对
	 * @param key  指定的键
	 * @param value  要写入的值
	 */
	public void writeProperty(String key, String value) {
		InputStream is = null;
		OutputStream os = null;
		Properties p = new Properties();
		try {
			is = new FileInputStream(properiesName);
			p.load(is);
			os = new FileOutputStream(PropertiesUtil.class.getClassLoader().getResource(properiesName).getFile());

			p.setProperty(key, value);
			p.store(os, key);
			os.flush();
			os.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (null != is)
					is.close();
				if (null != os)
					os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 测试类，测试sysConfig.properties，写入namess=wang
	 * 然后读出namess的值
	 * @param args
	 */
	public static void main(String[] args) {
		PropertiesUtil p = new PropertiesUtil("sysConfig.properties");
		p.writeProperty("namess", "wang");
		System.out.println(p.readProperty("namess"));
	}

}
