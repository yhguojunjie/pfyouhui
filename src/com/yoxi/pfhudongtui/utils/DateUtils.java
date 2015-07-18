
package com.yoxi.pfhudongtui.utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** 
 * 用于格式化日期或者取得特定的日期如每天开始、每天结束、每月第一天
 */
public class DateUtils {
	//SimpleDateFormat是线程不安全的.不能使用static来保存
	public static SimpleDateFormat getYyyymmddFormatter(){
		return new SimpleDateFormat("yyyy-MM-dd");
	}
	public static SimpleDateFormat getYyyymmddhhmmssFormatter(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}	
	public static SimpleDateFormat getYyyymmddhhmmssAgainFormatter(){
		return new SimpleDateFormat("yyyyMMddHHmmss");
	}	
	/**
	 * 得到今天的yyyyMMdd格式的字符串.
	 */
	public static String getYmdOfToday(){		
		return getYyyymmddFormatter().format(new Date());
		
	}
	
	/**
	 * 把格式为yyyy-MM-dd HH:mm:ss的字符串转为日期Date格式
	 */
	public static Date getDateByFull(String s){
		try {
			return getYyyymmddhhmmssFormatter().parse(s);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 把格式为yyyy-MM-dd的字符串转为日期Date格式
	 */
	public static Date getDateByYMD(String s){
		try {
			return getYyyymmddFormatter().parse(s);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}	
	
	/**
	 * 得到今天的开始日期,如2010-08-01 00:00:00
	 */
	public static Date getTodayStart(){
		try {
			return getYyyymmddhhmmssFormatter().parse(getYmdOfToday()+" 00:00:00");
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 得到今天的结束日期,如2010-08-01 23:59:59
	 */
	public static Date getTodayEnd(){
		try {
			return getYyyymmddhhmmssFormatter().parse(getYmdOfToday()+" 23:59:59");
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	/** 
	   * 得到几天前的时间 
	   * @param d 
	   * @param day 
	   * @return 
	   */  
	 public static Date getDateBefore(Date d,int day){  
		   Calendar now =Calendar.getInstance();  
		   now.setTime(d);  
		   now.set(Calendar.DATE,now.get(Calendar.DATE)-day);  
		   return now.getTime();  
	  }
	  /** 
	   * 得到几天后的时间 
	   * @param d 
	   * @param day 
	   * @return 
	   */  
	 public static Date getDateAfter(Date d,int day){  
		   Calendar now =Calendar.getInstance();  
		   now.setTime(d);  
		   now.set(Calendar.DATE,now.get(Calendar.DATE)+day);  
		   return now.getTime();  
	  } 
	/**
	 * 把日期转为yyyy-MM-dd格式的字符串
	 */
	public static String getYmd(Date value){
		return getYyyymmddFormatter().format(value);
	}
	/**
	 * 把日期转为yyyy-MM-dd HH:mm:ss格式的字符串
	 */
	public static String getYmdhms(Date value){
		return getYyyymmddhhmmssFormatter().format(value);
	}	
	
	/**
	 * 把日期转为yyyyMMddHHmmss格式的字符串
	 * @param value
	 * @return
	 */
	public static String getYmdhmsAgain(Date value){
		return getYyyymmddhhmmssAgainFormatter().format(value);
	}
	/**
	 * 取得指定日期的最早时间
	 */
	public static Date getStartDate(Date value){
		try {
			return getYyyymmddhhmmssFormatter().parse(getYmd(value)+" 00:00:00");
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 取得指定日期的最晚时间
	 */
	public static Date getEndDate(Date value){
		try {
			return getYyyymmddhhmmssFormatter().parse(getYmd(value)+" 23:59:59");
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 日期前移或后移几天
	 */
	public static Date addDate(Date value,int day){
		Calendar cal=Calendar.getInstance();
		cal.setTime(value);
		cal.add(Calendar.DAY_OF_YEAR,day);
		return cal.getTime();
	}
	/**
	 * 两个日期相差的天数
	 */
	public static long moreDay(Date start,Date end){
		Long startM=getStartDate(start).getTime();
		Long endM=getStartDate(end).getTime();
		long result = (startM-endM) / (24 * 60 * 60*1000); 
		return result;
	}
	/**
	 * 指定日期的开始时间
	 */
	public static Date getStartDate(String dateStr){
		try{
			return getYyyymmddhhmmssFormatter().parse(dateStr+" 00:00:00");
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}		
	}
	/**
	 * 指定日期的结束时间
	 */
	public static Date getEndDate(String dateStr){
		try{
			return getYyyymmddhhmmssFormatter().parse(dateStr+" 23:59:59");
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}		
	}
	
	/**
	 * 指定日期的当前月的第一天的字符串
	 */
	public static String getMonthBeginString(Date now){
		return getYyyymmddFormatter().format(getMonthBeginDate(now));			
	}
	
	
	/**
	 * 得到所传日期的当前月的最后一天的日期
	 */
	public static Date getMonthBeginDate(Date now){
		Calendar cal=Calendar.getInstance();
		cal.setTime(now);
		cal.set(Calendar.DAY_OF_MONTH,1);
		return DateUtils.getStartDate(cal.getTime());			
	}
	/**
	 * 得到所传日期的当前周的第一天的字符串
	 */
	public static String getWeekBeginString(Date now){
		return getYyyymmddFormatter().format(getWeekBeginDate(now));			
	}
	
	
	/**
	 * 得到所传日期的当前周的第一天的日期
	 */
	public static Date getWeekBeginDate(Date now){
		Calendar cal =Calendar.getInstance();   
		int dayofweek=cal.get(Calendar.DAY_OF_WEEK)-1;   
		if(dayofweek==0){   
			dayofweek=7;
		}
		cal.add(Calendar.DATE,-dayofweek+1); 
		return DateUtils.getStartDate(cal.getTime());			
	}
	 /** 
     * 获得本月的开始时间，即2012-01-01 00:00:00 
     * 
     * @return 
     */ 
    public  static Date getCurrentMonthStartTime() { 
        Calendar c = Calendar.getInstance(); 
        Date now = null; 
        try { 
            c.set(Calendar.DATE, 1); 
            now = getYyyymmddFormatter().parse(getYyyymmddFormatter().format(c.getTime())); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return now; 
    } 

    /** 
     * 当前月的结束时间，即2012-01-31 23:59:59 
     * 
     * @return 
     */ 
    public static  Date getCurrentMonthEndTime() { 
        Calendar c = Calendar.getInstance(); 
        Date now = null; 
        try { 
            c.set(Calendar.DATE, 1); 
            c.add(Calendar.MONTH, 1); 
            c.add(Calendar.DATE, -1); 
            now = getYyyymmddhhmmssFormatter().parse(getYyyymmddFormatter().format(c.getTime()) + " 23:59:59"); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return now; 
    } 
	
	/**
	 * 格林威治时间对应的秒数
	 */
	public static int getGSMTime(Date now)
	{
		long gsmTime=-28800000;//1970-01-01 对应的毫秒数
		long nowTime=now.getTime();//现在对应的毫秒数
		return Integer.valueOf((nowTime-gsmTime)/1000+"");
		
	}
	
}
