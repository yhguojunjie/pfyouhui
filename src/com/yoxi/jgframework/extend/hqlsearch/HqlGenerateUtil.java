package com.yoxi.jgframework.extend.hqlsearch;

import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;

import com.yoxi.jgframework.common.hibernate.qbc.CriteriaQuery;
import com.yoxi.jgframework.util.DateUtils;
import com.yoxi.jgframework.util.StringUtil;

public class HqlGenerateUtil {
	
	private static final String SUFFIX_COMMA = ",";
	private static final String SUFFIX_KG = " ";
	/**模糊查询符号*/
	private static final String SUFFIX_ASTERISK = "*";
	private static final String SUFFIX_ASTERISK_VAGUE = "%%";
	/**不等于查询符号*/
	private static final String SUFFIX_NOT_EQUAL = "!";
	private static final String SUFFIX_NOT_EQUAL_NULL = "!NULL";
	
	/**时间区间查询符号*/
	private static final String END = "_end";
	private static final String BEGIN = "_begin";
	
	/**数值区间查询符号*/
	private static final String GT="_gt"; //(>)查询条件
	private static final String LT="_lt"; //(<)查询条件
	private static final String GE="_ge"; //(>=)查询条件
	private static final String LE="_le"; //(<=)查询条件
	
	/**数值不等查询符号*/
	private static final String NOTEQ="_noteq"; //(!=)不等条件
	
	private static final Logger logger = Logger.getLogger(HqlGenerateUtil.class);
	

	
	/**
  	 * 自动生成查询条件HQL
  	 * 模糊查询
  	 * 【只对Integer类型和String类型的字段自动生成查询条件】
  	 * @param hql
  	 * @param values
  	 * @param searchObj
  	 * @throws Exception
  	 */
  	public static void installHql(CriteriaQuery cq,Object searchObj){
  		PropertyDescriptor origDescriptors[] = PropertyUtils.getPropertyDescriptors(searchObj);
  		// 获得对象属性中的name 
  		String descriptorsNames = getDescriptorsNames(origDescriptors);
  		
        for (int i = 0; i < origDescriptors.length; i++) {
            String name = origDescriptors[i].getName();
            String type = origDescriptors[i].getPropertyType().toString();
            
            if ("class".equals(name)||"ids".equals(name)||"page".equals(name)
            		||"rows".equals(name)||"sort".equals(name)||"order".equals(name)) {
                continue; // No point in trying to set an object's class
            }
            try {
            if (PropertyUtils.isReadable(searchObj, name)) {
               if("class java.lang.String".equals(type)){
            	   Object value = PropertyUtils.getSimpleProperty(searchObj, name);
            	   
            	   String searchValue = null;
            	   if(value!=null){
            		    searchValue = value.toString().trim();
            	   }else{
            		   continue;
            	   }
            	   if(searchValue!=null&&!"".equals(searchValue)){
            		   //[1].In 多个条件查询{逗号隔开参数}
            		   if(searchValue.indexOf(SUFFIX_COMMA)>=0){
            			   //页面输入查询条件，情况（取消字段的默认条件）
            			   if(searchValue.indexOf(SUFFIX_KG)>=0){
            				   String val = searchValue.substring(searchValue.indexOf(SUFFIX_KG));
            				   cq.eq(name, val);
            			   }else{
            				   String[] vs = searchValue.split(SUFFIX_COMMA);
                			   cq.in(name, vs);
            			   }
            		   }            		   
            		   //[3].不匹配查询{等于！叹号}
            		   //(1).不为空字符串
            		   else if(searchValue.equals(SUFFIX_NOT_EQUAL)){
            			   cq.isNotNull(name);
            		   }
            		   //(2).不为NULL
            		   else if(searchValue.toUpperCase().equals(SUFFIX_NOT_EQUAL_NULL)){
            			   cq.isNotNull(name);
            		   }
            		   //(3).正常不匹配
            		   else if(searchValue.indexOf(SUFFIX_NOT_EQUAL)>=0){
            			   cq.notEq(name, searchValue.replace(SUFFIX_NOT_EQUAL, ""));
            		   }
            		 //[2].模糊查询{带有* 星号的参数}
            		   else { //if(searchValue.indexOf(SUFFIX_ASTERISK)>=0){
            			   cq.like(name, searchValue.replace(SUFFIX_ASTERISK, SUFFIX_ASTERISK_VAGUE));
            		   }
//            		   //[4].全匹配查询{没有特殊符号的参数}
//            		   else{
//            			   cq.eq(name, searchValue);
//            		   }
            	   }
               }else if("class java.lang.Integer".equals(type)){
            	   Object value = PropertyUtils.getSimpleProperty(searchObj, name);
            	   if(value!=null&&!"".equals(value)){
            		   cq.eq(name, value);
            	   }
               }else if("class java.math.BigDecimal".equals(type)){
            	   //update-begin--Author:zhaojunfu  Date:20130503 for：增加对bigDecimal数据的支持
            	   Object value = PropertyUtils.getSimpleProperty(searchObj, name);
            	   if(value!=null&&!"".equals(value)){
            		   cq.eq(name, value);
            	   }
            	   //update-end--Author:zhaojunfu  Date:20130503 for：增加对bigDecimal数据的支持
               }else if("class java.lang.Short".equals(type)){
            	   //update-begin--Author:zhaojunfu  Date:20130518 for：TASK #93 增加对SHORT数据的支持
            	   Object value = PropertyUtils.getSimpleProperty(searchObj, name);
            	   if(value!=null&&!"".equals(value)){
            		   cq.eq(name, value);
            	   }
            	   //update-end--Author:zhaojunfu  Date:20130518 for：TASK #93增加对SHORT数据的支持
               }else if("class java.lang.Long".equals(type)){
            	   //update-begin--Author:zhaojunfu  Date:20130518 for：TASK #93 增加对LONG 数据的支持
            	   Object value = PropertyUtils.getSimpleProperty(searchObj, name);
            	   if(value!=null&&!"".equals(value)){
            		   cq.eq(name, value);
            	   }
            	   //update-end--Author:zhaojunfu  Date:20130518 for：TASK #93 增加对LONG 数据的支持
               }else if ("class java.util.Date".equals(type))  {
					Date value = (Date) PropertyUtils.getSimpleProperty(searchObj, name);
					if (null != value) {
						cq.eq(name, value);
					 }
               }else if("interface java.util.Map".equals(type)){
            	   HashMap map = (HashMap) PropertyUtils.getSimpleProperty(searchObj, name);
            	   if ( null!= map && !map.isEmpty()) {
						Set<String> set = map.keySet();
						Iterator<String> iterator = set.iterator();
						while(iterator.hasNext()){
							String key = iterator.next();
							if(StringUtil.isNotEmpty(map.get(key))){
								addMap(key, map.get(key).toString(), cq, searchObj);
							}
						}
            	   }
               	}
               }
            } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        //添加选择条件
        cq.add();
  	}
  	
  	/**
  	 * 判断map中的key代表的类型
  	 * @param key
  	 * @param value
  	 * @param cq
  	 * @param searchObj
  	 * @throws Exception
  	 */
  	private static void addMap(String key,String value,CriteriaQuery cq,Object searchObj) throws Exception {
  		String name= key.substring(0,key.indexOf("_"));
  		String type=PropertyUtils.getPropertyType(searchObj,name).toString();
  		if(PropertyUtils.isReadable(searchObj, name)){
	  		if("class java.util.Date".equals(type)){
	  			Date dateValue = DateUtils.str2Date(value.toString(), DateUtils.date_sdf);
	  			addCq(name, key, dateValue, cq);
	  		}else if ("class java.math.BigDecimal".equals(type)) {
				BigDecimal decimal = new BigDecimal(value);
				addCq(name, key, decimal, cq);
			}else if("class java.lang.Integer".equals(type)){
				Integer integer = new Integer(value);
				addCq(name, key, integer, cq);
			}else if("class java.lang.Short".equals(type)){
				Short shorts = new Short(value);
				addCq(name, key, shorts, cq);
			}else if("class java.lang.Long".equals(type)){
				Long longs = new Long(value);
				addCq(name, key, longs, cq);
			}else if("class java.lang.Float".equals(type)){
				Float floats = new Float(value);
				addCq(name, key, floats, cq);
			}else if("class java.lang.Double".equals(type)){
				Double doubles = new Double(value);
				addCq(name, key, doubles, cq);
			}else if("class java.sql.Timestamp".equals(type)){
				Date dateValue = DateUtils.str2Date(value.toString(), DateUtils.date_sdf);
				Timestamp timestampValue = new Timestamp(dateValue.getTime());
				addCq(name, key, timestampValue, cq);
			}
  		}
  		
	}
  	
  	/**
  	 * 添加查询条件
  	 * @param name
  	 * @param key
  	 * @param value
  	 * @param cq
  	 */
  	private static void addCq(String name, String key, Object value, CriteriaQuery cq){
  		if(key.endsWith(BEGIN)){
			cq.ge(name, value);
		}else if(key.endsWith(END)){
			if(value instanceof Date){
				Calendar calendar = Calendar.getInstance();
				calendar.setTime((Date)value);
				//增加一天,获取截止日期当天的数据
				calendar.add(Calendar.DAY_OF_MONTH, 1);
				cq.le(name,calendar.getTime());
			}else if(value instanceof Timestamp){
				cq.le(name, value);
			}
		}else if(key.endsWith(GT)){
			cq.gt(name, value);
		}else if(key.endsWith(LT)){
			cq.lt(name, value);
		}else if(key.endsWith(GE)){
			cq.ge(name, value);
		}else if(key.endsWith(LE)){
			cq.le(name, value);
		}else if(key.endsWith(NOTEQ)){
			cq.or(Restrictions.ne(name, value), Restrictions.isNull(name));
		}
  	}
  	
	/**
	 * 得到对象属性中所有name
	 * @param origDescriptors
	 * @return
	 */
  	private static String getDescriptorsNames(PropertyDescriptor origDescriptors[]) {
  		StringBuilder sb = new StringBuilder();
  		for (int i = 0; i < origDescriptors.length; i++) {
  			sb.append(origDescriptors[i].getName() + ",");
  		}
  		return sb.toString();
  	}
  
}


