package com.yoxi.pfhudongtui.utils;

/**
 * 
 * 业务相关需要转换的
 *
 * @author wql
 *
 * @Date 2015年3月19日
 *
 */
public class ConvertUtil {
	/**
	 * 模板价格折扣换算
	 * @param mon 购买月数
	 * @param price 模板价格
	 * @return
	 */
	public static double calPluginPrice(Integer mon,Double price){
		if(mon != null){
			if(mon == 1 || mon == 2){
				price = price * mon;
			}
			if(mon == 3 || mon == 4 || mon == 5){
				price = price * mon * 0.8;
			}
			if(mon == 6 || mon == 7 || mon == 8){
				price = price  * mon * 0.7;
			}
			if(mon == 9 || mon == 10 || mon == 11){
				price = price  * mon * 0.6;
			}
			if(mon == 12){
				price = price * mon * 0.5;
			}
		}
		return price;
	}
}
