package com.yoxi.jgframework.util.excel;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.yoxi.jgframework.annotation.Excel;



/**
 * 邮件导出工具类
 * @author yanping.shi
 *
 */
public class ExcelExportUtil {
	/**
	 * 导出文件时，文件名乱码处理
	 * 
	 * @param request
	 * @param fileName
	 * @return
	 */
	public static String encodeFileName(HttpServletRequest request,String fileName){
		final String userAgent = request.getHeader("USER-AGENT");
		String finalFileName = null;
		try {
			if (StringUtils.contains(userAgent, "MSIE")) {// IE浏览器
				finalFileName = URLEncoder.encode(fileName, "UTF-8");
			} else if (StringUtils.contains(userAgent, "Mozilla")) {// google,火狐浏览器
				finalFileName = new String(fileName.getBytes(), "ISO8859-1");
			} else {
				finalFileName = URLEncoder.encode(fileName, "UTF-8");// 其他浏览器
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return finalFileName;
	}

	/**
	 * 
	 * @param title     Sheet名字
	 * @param pojoClass Excel对象Class
	 * @param dataSet   Excel对象数据List
	 * @param out       输出流
	 */
	public static void exportExcel(String title, Class<?> pojoClass,
			Collection<?> dataSet, OutputStream out) {
		// 使用userModel模式实现的，当excel文档出现10万级别的大数据文件可能导致OOM内存溢出
		exportExcelInUserModel(title, pojoClass, dataSet, out);
		// 使用eventModel实现，可以一边读一边处理，效率较高，但是实现复杂，暂时未实现
	}

	private static void exportExcelInUserModel(String title, Class<?> pojoClass,
			Collection<?> dataSet, OutputStream out) {
		try {
			// 首先检查数据看是否是正确的
			if (dataSet == null || dataSet.size() == 0) {
				throw new Exception("导出数据为空！");
			}
			if (title == null || out == null || pojoClass == null) {
				throw new Exception("传入参数不能为空！");
			}
			// 声明一个工作薄
			Workbook workbook = new HSSFWorkbook();
			// 生成一个表格
			Sheet sheet = workbook.createSheet(title);

			// 标题
			List<String> exportFieldTitle = new ArrayList<String>();
			List<Integer> exportFieldWidth = new ArrayList<Integer>();
			// 拿到所有列名，以及导出的字段的get方法
			List<Method> methodObj = new ArrayList<Method>();
			Map<String, Method> convertMethod = new HashMap<String, Method>();
			// 得到所有字段
			Field fileds[] = pojoClass.getDeclaredFields();
			// 遍历整个filed
			for (int i = 0; i < fileds.length; i++) {
				Field field = fileds[i];
				Excel excel = field.getAnnotation(Excel.class);
				// 如果设置了annottion
				if (excel != null) {
					// 添加到标题
					exportFieldTitle.add(excel.exportName());
					// 添加标题的列宽
					exportFieldWidth.add(excel.exportFieldWidth());
					// 添加到需要导出的字段的方法
					String fieldname = field.getName();
					// System.out.println(i+"列宽"+excel.exportName()+" "+excel.exportFieldWidth());
					StringBuffer getMethodName = new StringBuffer("get");
					getMethodName.append(fieldname.substring(0, 1)
							.toUpperCase());
					getMethodName.append(fieldname.substring(1));

					Method getMethod = pojoClass.getMethod(getMethodName
							.toString(), new Class[] {});

					methodObj.add(getMethod);
					if (excel.exportConvertSign() == 1) {
						StringBuffer getConvertMethodName = new StringBuffer(
								"get");
						getConvertMethodName.append(fieldname.substring(0, 1)
								.toUpperCase());
						getConvertMethodName.append(fieldname.substring(1));
						getConvertMethodName.append("Convert");
						// System.out.println("convert: "+getConvertMethodName.toString());
						Method getConvertMethod = pojoClass
								.getMethod(getConvertMethodName.toString(),
										new Class[] {});
						convertMethod.put(getMethodName.toString(),
								getConvertMethod);
					}
				}
			}
			int index = 0;
			// 产生表格标题行
			Row row = sheet.createRow(index);
			for (int i = 0, exportFieldTitleSize = exportFieldTitle.size(); i < exportFieldTitleSize; i++) {
				Cell cell = row.createCell(i);
				// cell.setCellStyle(style);
				RichTextString text = new HSSFRichTextString(exportFieldTitle
						.get(i));
				cell.setCellValue(text);
			}

			// 设置每行的列宽
			for (int i = 0; i < exportFieldWidth.size(); i++) {
				// 256=65280/255
				sheet.setColumnWidth(i, 256 * exportFieldWidth.get(i));
			}
			Iterator its = dataSet.iterator();
			// 循环插入剩下的集合
			while (its.hasNext()) {
				// 从第二行开始写，第一行是标题
				index++;
				row = sheet.createRow(index);
				Object t = its.next();
				for (int k = 0, methodObjSize = methodObj.size(); k < methodObjSize; k++) {
					Cell cell = row.createCell(k);
					Method getMethod = methodObj.get(k);
					Object value = null;
					if (convertMethod.containsKey(getMethod.getName())) {
						Method cm = convertMethod.get(getMethod.getName());
						value = cm.invoke(t, new Object[] {});
					} else {
						value = getMethod.invoke(t, new Object[] {});
					}
					cell.setCellValue(value == null ? "" : value.toString());
				}
			}

			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	/**
	 * 
	 * @param title     Sheet名字
	 * @param pojoClass Excel对象Class
	 * @param dataSet   Excel对象数据List
	 * @param out       输出流
	 */
	public static HSSFWorkbook  exportExcel(String title, Class<?> pojoClass,
			Collection<?> dataSet) {
		// 使用userModel模式实现的，当excel文档出现10万级别的大数据文件可能导致OOM内存溢出
		return exportExcelInUserModel2File(title, pojoClass, dataSet);
	}
	
	
	
	@SuppressWarnings("unchecked")
	private static HSSFWorkbook exportExcelInUserModel2File(String title, Class<?> pojoClass,
			Collection<?> dataSet) {
		// 声明一个工作薄
		HSSFWorkbook  workbook = null;
		try {
			// 首先检查数据看是否是正确的
//			if (dataSet == null || dataSet.size() == 0) {
//				throw new Exception("导出数据为空！");
//			}
//			if (title == null || pojoClass == null) {
//				throw new Exception("传入参数不能为空！");
//			}
			// 声明一个工作薄
			workbook = new HSSFWorkbook();
			// 生成一个表格
			Sheet sheet = workbook.createSheet(title);

			// 标题
			List<String> exportFieldTitle = new ArrayList<String>();
			List<Integer> exportFieldWidth = new ArrayList<Integer>();
			// 拿到所有列名，以及导出的字段的get方法
			List<Method> methodObj = new ArrayList<Method>();
			Map<String, Method> convertMethod = new HashMap<String, Method>();
			// 得到所有字段
			Field fileds[] = pojoClass.getDeclaredFields();
			CellStyle titleStyle = getTitleStyle(workbook);
			
			CellStyle twoCellStyle = getTwoStyle(workbook);
			
			CellStyle oneCellStyle=getOneStyle(workbook);
			// 遍历整个filed
			for (int i = 0; i < fileds.length; i++) {
				Field field = fileds[i];
				Excel excel = field.getAnnotation(Excel.class);
				// 如果设置了annottion
				if (excel != null) {
					// 添加到标题
					exportFieldTitle.add(excel.exportName());
					// 添加标题的列宽
					exportFieldWidth.add(excel.exportFieldWidth());
					// 添加到需要导出的字段的方法
					String fieldname = field.getName();
					// System.out.println(i+"列宽"+excel.exportName()+" "+excel.exportFieldWidth());
					StringBuffer getMethodName = new StringBuffer("get");
					getMethodName.append(fieldname.substring(0, 1)
							.toUpperCase());
					getMethodName.append(fieldname.substring(1));

					Method getMethod = pojoClass.getMethod(getMethodName
							.toString(), new Class[] {});

					methodObj.add(getMethod);
					if (excel.exportConvertSign() == 1) {
						//----------------------------------------------------------------
						//update-begin--Author:Quainty  Date:20130524 for：[8]excel导出时间问题
						// 用get/setXxxxConvert方法名的话， 由于直接使用了数据库绑定的Entity对象，注入会有冲突
						StringBuffer getConvertMethodName = new StringBuffer("convertGet");
						getConvertMethodName.append(fieldname.substring(0, 1).toUpperCase());
						getConvertMethodName.append(fieldname.substring(1));
						//getConvertMethodName.append("Convert");
						//update-end--Author:Quainty  Date:20130524 for：[8]excel导出时间问题
						//----------------------------------------------------------------
						// System.out.println("convert: "+getConvertMethodName.toString());
						Method getConvertMethod = pojoClass
								.getMethod(getConvertMethodName.toString(),
										new Class[] {});
						convertMethod.put(getMethodName.toString(),
								getConvertMethod);
					}
				}
			}
			int index = 0;
			// 产生表格标题行
			Row row = sheet.createRow(index);
			row.setHeight((short)450);
			
			for (int i = 0, exportFieldTitleSize = exportFieldTitle.size(); i < exportFieldTitleSize; i++) {
				Cell cell = row.createCell(i);
				// cell.setCellStyle(style);
				RichTextString text = new HSSFRichTextString(exportFieldTitle
						.get(i));
				cell.setCellValue(text);
				cell.setCellStyle(titleStyle);
			}

			// 设置每行的列宽
			for (int i = 0; i < exportFieldWidth.size(); i++) {
				// 256=65280/255
				sheet.setColumnWidth(i, 256 * exportFieldWidth.get(i));
			}
			Iterator its = dataSet.iterator();
			// 循环插入剩下的集合
			while (its.hasNext()) {
				// 从第二行开始写，第一行是标题
				index++;
				row = sheet.createRow(index);
				row.setHeight((short)350);
				Object t = its.next();
				for (int k = 0, methodObjSize = methodObj.size(); k < methodObjSize; k++) {
					Cell cell = row.createCell(k);
					Method getMethod = methodObj.get(k);
					Object value = null;
					if (convertMethod.containsKey(getMethod.getName())) {
						Method cm = convertMethod.get(getMethod.getName());
						value = cm.invoke(t, new Object[] {});
					} else {
						value = getMethod.invoke(t, new Object[] {});
					}
					cell.setCellValue(value==null?"":value.toString());
					
					if(index%2==0)
						cell.setCellStyle(twoCellStyle);
					else
						cell.setCellStyle(oneCellStyle);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return workbook;
	}
	// ------begin----- author:邢双阳  ---date：2013-5-13  for：【104】修改导出excel的样式
	public static HSSFCellStyle getTitleStyle(HSSFWorkbook workbook){
		// 产生Excel表头
				 HSSFCellStyle titleStyle = workbook.createCellStyle();
		 titleStyle.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE);    //设置边框样式
        titleStyle.setBorderLeft((short)2);     //左边框
        titleStyle.setBorderRight((short)2);    //右边框
        titleStyle.setBorderTop((short)2);     //左边框
        titleStyle.setBorderBottom((short)2);    //右边框
        titleStyle.setBorderTop(HSSFCellStyle.BORDER_DOUBLE);    //顶边框
        titleStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);    //填充的背景颜色
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        
        titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);    //填充图案
	     
		return titleStyle;
	}
	
	public static HSSFCellStyle getTwoStyle(HSSFWorkbook workbook){
		// 产生Excel表头
		// 产生Excel表头
		 HSSFCellStyle style = workbook.createCellStyle(); 
		 style.setBorderLeft((short)1);     //左边框
		 style.setBorderRight((short)1);    //右边框
		 style.setBorderBottom((short)1);
		 style.setBorderTop((short)1);
		 style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);    //填充的背景颜色
		 style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);    //填充图案
		return style;
	}
	
	public static HSSFCellStyle getOneStyle(HSSFWorkbook workbook){
		// 产生Excel表头
		// 产生Excel表头
		 HSSFCellStyle style = workbook.createCellStyle(); 
		 style.setBorderLeft((short)1);     //左边框
		 style.setBorderRight((short)1);    //右边框
		 style.setBorderBottom((short)1);
		 style.setBorderTop((short)1); 
		return style;
	}
	
	// ------end----- author:邢双阳  ---date：2013-5-13  for：【104】修改导出excel的样式
}
