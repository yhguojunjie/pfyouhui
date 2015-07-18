package com.yoxi.jgframework.core.tag;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.yoxi.jgframework.common.hibernate.qbc.PageList;
import com.yoxi.jgframework.common.model.json.ComboBox;
import com.yoxi.jgframework.common.model.json.DataGrid;
import com.yoxi.jgframework.system.entity.TSRole;
import com.yoxi.jgframework.ui.tag.vo.datatable.DataTableReturn;
import com.yoxi.jgframework.ui.tag.vo.easyui.Autocomplete;
import com.yoxi.jgframework.core.utils.JsonUtils;
import com.yoxi.jgframework.util.ReflectHelper;
import com.yoxi.jgframework.core.utils.StringUtils;
import com.yoxi.jgframework.core.utils.ConvertUtils;

/**
 * 
 * 类描述：标签工具类
 * 
 * @author: jeecg
 * @date： 日期：2012-12-28 时间：上午09:58:00
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public class TagUtil {
	/**
	 * <b>Summary: </b> getFiled(获得实体Bean中所有属性)
	 * 
	 * @param objClass
	 * @return
	 * @throws ClassNotFoundException
	 */
	
	public static Field[] getFiled(Class objClass)
			throws ClassNotFoundException {
		Field[] field = null;
		if (objClass != null) {
			Class class1 = Class.forName(objClass.getName());
			field = class1.getDeclaredFields();// 这里便是获得实体Bean中所有属性的方法
			return field;
		} else {
			return field;
		}
	}

	/**
	 * 
	 * 获取对象内对应字段的值
	 * 
	 * @param fields
	 */
	public static Object fieldNametoValues(String FiledName, Object o) {
		Object value = "";
		String fieldName = "";
		String childFieldName = null;
		ReflectHelper reflectHelper = new ReflectHelper(o);
		if (FiledName.indexOf("_") == -1) {
			fieldName = FiledName;
		} else {
			fieldName = FiledName.substring(0, FiledName.indexOf("_"));// 外键字段引用名
			childFieldName = FiledName.substring(FiledName.indexOf("_") + 1);// 外键字段名
		}
		value = reflectHelper.getMethodValue(fieldName) == null ? ""
				: reflectHelper.getMethodValue(fieldName);
		if (value != "" && value != null && FiledName.indexOf("_") != -1) {
			value = fieldNametoValues(childFieldName, value);
		}
		return value;
	}

	/**
	 * 对象转数组
	 * 
	 * @param fields
	 * @param o
	 * @param stack
	 * @return
	 * @throws Exception
	 */
	protected static Object[] field2Values(String[] fields, Object o)
			throws Exception {
		Object[] values = new Object[fields.length];
		for (int i = 0; i < fields.length; i++) {
			String fieldName = fields[i].toString();
			values[i] = fieldNametoValues(fieldName, o);
		}
		return values;
	}

	/**
	 * 循环LIST对象拼接EASYUI格式的JSON数据
	 * 
	 * @param fields
	 * @param total
	 * @param list
	 */
	private static String listtojson(String[] fields, int total, List list,
			String footer, boolean isTree) throws Exception {
		Object[] values = new Object[fields.length];
		String jsonTemp = "{\'total\':" + total + ",\'rows\':[";
		for (int j = 0, size = list.size(); j < size; j++) {
			if (isTree) {
				jsonTemp = jsonTemp + "{\'state\':\'closed\',";
			} else {
				jsonTemp = jsonTemp + "{";
			}
			for (int i = 0, length = fields.length; i < length; i++) {
				String fieldName = fields[i].toString();
				values[i] = fieldNametoValues(fieldName, list.get(j));
				if (values[i]!=null && values[i] instanceof String) {
					values[i]=values[i].toString().replace("\'", "&apos;").replace("\"", "&quot;");
				}
				jsonTemp = jsonTemp + "\'" + fieldName + "\'" + ":\'"
						+JsonUtils.string2Json(values[i]) + "\'";
				if (i != fields.length - 1) {
					jsonTemp = jsonTemp + ",";
				}
			}
			if (j != size - 1) {
				jsonTemp = jsonTemp + "},";
			} else {
				jsonTemp = jsonTemp + "}";
			}
		}
		jsonTemp = jsonTemp + "]";
		// update-begin--Author:zhaojunfu Date:20130520 for：TASK #109
		// datagrid标签没有封装合计功能
		if (!ConvertUtils.isEmpty(footer)) {
			jsonTemp = jsonTemp + ",";
			jsonTemp = jsonTemp + "\'footer\':[";
			jsonTemp = jsonTemp + getfooter(footer, list);
			jsonTemp = jsonTemp + "]";
		}
		// update-end--Author:zhaojunfu Date:20130520 for：TASK #109
		// datagrid标签没有封装合计功能
		jsonTemp = jsonTemp + "}";
		jsonTemp = jsonTemp.replaceAll("\r\n",
		"<br/>").replaceAll("\r", "<br/>").replaceAll("\n",
		"<br/>");
		return jsonTemp;
	}

	private static String getfooter(String footer, List list) {

		StringBuffer sumBuffer = new StringBuffer();
		StringBuffer avgBuffer = new StringBuffer();
		if (footer.contains("sum") || footer.contains("_s")) {
			sumBuffer.append("{");
			sumBuffer.append("\'statsMode_foot\':true,");
		}
		if (footer.contains("avg") || footer.contains("_a")) {
			avgBuffer.append("{");
			avgBuffer.append("\'statsMode_foot\':true,");
		}
		// footer field:合计_s-平均值_a,field:合计_s,field:avg-sum,field:avg,field:sum

		String[] footers = footer.split(",");
		for (String foot : footers) {
			String footerFiled = foot.split(":")[0];
			Object value = null;
			if (foot.split(":").length == 2) {
				value = foot.split(":")[1];
			}
			if (!ConvertUtils.isEmpty(value)) {
				if ("sum".equals(value)) {// field:sum
					value = getTotalValue(footerFiled, list, false);
					sumBuffer.append("\'" + footerFiled + "\':\'" + value
							+ "\',");
				} else if ("avg".equals(value)) {// field:avg
					value = getTotalValue(footerFiled, list, true);
					avgBuffer.append("\'" + footerFiled + "\':\'" + value
							+ "\',");
				} else if (value.toString().contains("-")) {// field:avg-sum
															// field:合计_s-平均值_a
					String[] foots = value.toString().split("-");
					for (Object footValue : foots) {
						if ("sum".equals(footValue)) {
							footValue = getTotalValue(footerFiled, list, false);
							sumBuffer.append("\'" + footerFiled + "\':\'"
									+ footValue + "\',");
						} else if ("avg".equals(footValue)) {
							footValue = getTotalValue(footerFiled, list, true);
							avgBuffer.append("\'" + footerFiled + "\':\'"
									+ footValue + "\',");
						} else if (footValue.toString().contains("_")) {// field:合计_s-平均值_a
							String[] footTitle = footValue.toString()
									.split("_");
							if ("s".equals(footTitle[1])) {
								sumBuffer.append("\'" + footerFiled + "\':\'"
										+ footTitle[0] + "\',");
							} else if ("a".equals(footTitle[1])) {
								avgBuffer.append("\'" + footerFiled + "\':\'"
										+ footTitle[0] + "\',");
							}
						}
					}
				} else if (value.toString().contains("_")) {// field:合计_s
					String[] footTitle = value.toString().split("_");
					if ("s".equals(footTitle[1])) {
						sumBuffer.append("\'" + footerFiled + "\':\'"
								+ footTitle[0] + "\',");
					} else if ("a".equals(footTitle[1])) {
						avgBuffer.append("\'" + footerFiled + "\':\'"
								+ footTitle[0] + "\',");
					}
				}
			}
		}
		if (footer.contains("sum") || footer.contains("_s")) {
			sumBuffer = sumBuffer.deleteCharAt(sumBuffer.length() - 1);
			sumBuffer.append("}");
		}
		if (footer.contains("avg") || footer.contains("_a")) {
			avgBuffer = avgBuffer.deleteCharAt(avgBuffer.length() - 1);
			avgBuffer.append("}");
		}
		if (!ConvertUtils.isEmpty(sumBuffer.toString())
				&& !ConvertUtils.isEmpty(avgBuffer.toString())) {
			return avgBuffer.append(",").append(sumBuffer).toString();
		} else if (!ConvertUtils.isEmpty(sumBuffer.toString())) {
			return sumBuffer.toString();
		} else if (!ConvertUtils.isEmpty(avgBuffer.toString())) {
			return avgBuffer.toString();
		}
		return "";
	}

	/**
	 * 计算指定列的合计或平均值
	 * 
	 * @param filed
	 *            字段名
	 * @param list
	 *            列表数据
	 * @return
	 */
	private static Object getTotalValue(String fieldName, List list,
			boolean avgFlg) {
		Double sum = 0D;
		try {
			for (int j = 0; j < list.size(); j++) {
				Double v = 0d;
				String vstr = String.valueOf(fieldNametoValues(fieldName,
						list.get(j)));
				if (!StringUtils.isEmpty(vstr)) {
					v = Double.valueOf(vstr);
				}
				sum += v;
			}
			if (avgFlg) {
				sum = sum / list.size();
			}
		} catch (Exception e) {
			return "";
		}
		return sum;
	}

	/**
	 * 循环LIST对象拼接自动完成控件数据
	 * 
	 * @param fields
	 * @param total
	 * @param list
	 * @throws Exception
	 */
	public static String getAutoList(Autocomplete autocomplete, List list)
			throws Exception {
		String field = autocomplete.getLabelField() + ","
				+ autocomplete.getValueField();
		String[] fields = field.split(",");
		Object[] values = new Object[fields.length];
		String jsonTemp = "{\'totalResultsCount\':1,\'geonames\':[";
		if (list.size() > 0) {
			for (int j = 0; j < list.size(); j++) {
				jsonTemp = jsonTemp + "{'nodate':'yes',";
				for (int i = 0; i < fields.length; i++) {
					String fieldName = fields[i].toString();
					values[i] = fieldNametoValues(fieldName, list.get(j));
					jsonTemp = jsonTemp + "\'" + fieldName + "\'" + ":\'"
							+ values[i] + "\'";
					if (i != fields.length - 1) {
						jsonTemp = jsonTemp + ",";
					}
				}
				if (j != list.size() - 1) {
					jsonTemp = jsonTemp + "},";
				} else {
					jsonTemp = jsonTemp + "}";
				}
			}
		} else {
			jsonTemp += "{'nodate':'数据不存在'}";
		}
		jsonTemp = jsonTemp + "]}";
		return JSONObject.fromObject(jsonTemp).toString();
	}

	/**
	 * 循环LIST对象拼接DATATABLE格式的JSON数据
	 * 
	 * @param fields
	 * @param total
	 * @param list
	 */
	private static String datatable(String field, int total, List list)
			throws Exception {
		String[] fields = field.split(",");
		Object[] values = new Object[fields.length];
		String jsonTemp = "{\'iTotalDisplayRecords\':" + total
				+ ",\'iTotalRecords\':" + total + ",\'aaData\':[";
		for (int j = 0; j < list.size(); j++) {
			jsonTemp = jsonTemp + "{";
			for (int i = 0; i < fields.length; i++) {
				String fieldName = fields[i].toString();
				values[i] = fieldNametoValues(fieldName, list.get(j));
				jsonTemp = jsonTemp + "\'" + fieldName + "\'" + ":\'"
						+ values[i] + "\'";
				if (i != fields.length - 1) {
					jsonTemp = jsonTemp + ",";
				}
			}
			if (j != list.size() - 1) {
				jsonTemp = jsonTemp + "},";
			} else {
				jsonTemp = jsonTemp + "}";
			}
		}
		jsonTemp = jsonTemp + "]}";
		return jsonTemp;
	}

	/**
	 * 返回列表JSONObject对象
	 * 
	 * @param field
	 * @param dataGrid
	 * @return
	 */
	private static JSONObject getJson(DataGrid dg, boolean isTree) {
		JSONObject jObject = null;
		try {
			// update-begin--Author:zhaojunfu Date:20130520 for：TASK #109
			// datagrid标签没有封装合计功能
			if (!StringUtils.isEmpty(dg.getFooter())) {
				jObject = JSONObject.fromObject(listtojson(
						dg.getField().split(","), dg.getTotal(),
						dg.getReaults(), dg.getFooter(), isTree));
			} else {
				jObject = JSONObject.fromObject(listtojson(
						dg.getField().split(","), dg.getTotal(),
						dg.getReaults(), null, isTree));
			}
			// update-end--Author:zhaojunfu Date:20130520 for：TASK #109
			// datagrid标签没有封装合计功能
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jObject;

	}

	/**
	 * 返回列表JSONObject对象
	 * 
	 * @param field
	 * @param dataGrid
	 * @return
	 */
	private static JSONObject getJson(DataTableReturn dataTable, String field) {
		JSONObject jObject = null;
		try {
			jObject = JSONObject
					.fromObject(datatable(field,
							dataTable.getiTotalDisplayRecords(),
							dataTable.getAaData()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jObject;

	}

	/**
	 * 获取指定字段类型 <b>Summary: </b> getColumnType(请用一句话描述这个方法的作用)
	 * 
	 * @param fileName
	 * @param fields
	 * @return
	 */
	public static String getColumnType(String fileName, Field[] fields) {
		String type = "";
		if (fields.length > 0) {
			for (int i = 0; i < fields.length; i++) {
				String name = fields[i].getName(); // 获取属性的名字
				String filedType = fields[i].getGenericType().toString(); // 获取属性的类型
				if (fileName.equals(name)) {
					if (filedType.equals("class java.lang.Integer")) {
						filedType = "int";
						type = filedType;
					} else if (filedType.equals("class java.lang.Short")) {
						filedType = "short";
						type = filedType;
					} else if (filedType.equals("class java.lang.Double")) {
						filedType = "double";
						type = filedType;
					} else if (filedType.equals("class java.util.Date")) {
						filedType = "date";
						type = filedType;
					} else if (filedType.equals("class java.lang.String")) {
						filedType = "string";
						type = filedType;
					} else if (filedType.equals("class java.sql.Timestamp")) {
						filedType = "Timestamp";
						type = filedType;
					} else if (filedType.equals("class java.lang.Character")) {
						filedType = "character";
						type = filedType;
					} else if (filedType.equals("class java.lang.Boolean")) {
						filedType = "boolean";
						type = filedType;
					} else if (filedType.equals("class java.lang.Long")) {
						filedType = "long";
						type = filedType;
					}

				}
			}
		}
		return type;
	}

	/**
	 * 
	 * <b>Summary: </b> getSortColumnIndex(获取指定字段索引)
	 * 
	 * @param fileName
	 * @param fieldString
	 * @return
	 */
	protected static String getSortColumnIndex(String fileName,
			String[] fieldString) {
		String index = "";
		if (fieldString.length > 0) {
			for (int i = 0; i < fieldString.length; i++) {
				if (fileName.equals(fieldString[i])) {
					int j = i + 1;
					index = ConvertUtils.getString(j);
				}
			}
		}
		return index;

	}

	// JSON返回页面MAP方式
	public static void ListtoView(HttpServletResponse response,
			PageList pageList) {
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", pageList.getCount());
		map.put("rows", pageList.getResultList());
		ObjectMapper mapper = new ObjectMapper();
//		JSONObject jsonObject = JSONObject.fromObject(map);
		try {
			mapper.writeValue(response.getWriter(), map);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 控件类型：easyui 返回datagrid JSON数据
	 * 
	 * @param response
	 * @param dataGrid
	 */
	public static void datagrid(HttpServletResponse response, DataGrid dg) {
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		JSONObject object = TagUtil.getJson(dg, true);
		try {
			PrintWriter pw = response.getWriter();
			if (object != null) {
				pw.write(object.toString());
			}
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void datagridJackson(HttpServletResponse response, String json) {
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		try {
			PrintWriter pw = response.getWriter();
			pw.write(json);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 控件类型：easyui 返回datagrid JSON数据
	 * 
	 * @param response
	 * @param dataGrid
	 */
	public static void datagrid(HttpServletResponse response, DataGrid dg,
			Boolean isTree) {
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		JSONObject object = TagUtil.getJson(dg, isTree);
		try {
			PrintWriter pw = response.getWriter();
			pw.write(object.toString());
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 控件类型：datatable 返回datatable JSON数据
	 * 
	 * @param response
	 * @param datatable
	 */
	public static void datatable(HttpServletResponse response,
			DataTableReturn dataTableReturn, String field) {
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		JSONObject object = TagUtil.getJson(dataTableReturn, field);
		try {
			response.getWriter().write(object.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 手工拼接JSON
	 */
	public static String getComboBoxJson(List<TSRole> list, List<TSRole> roles) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		for (TSRole node : list) {
			if (roles.size() > 0) {
				buffer.append("{\"id\":" + node.getId() + ",\"text\":\""
						+ node.getRoleName() + "\"");
				for (TSRole node1 : roles) {
					if (node.getId() == node1.getId()) {
						buffer.append(",\"selected\":true");
					}
				}
				buffer.append("},");
			} else {
				buffer.append("{\"id\":" + node.getId() + ",\"text\":\""
						+ node.getRoleName() + "\"},");
			}

		}
		buffer.append("]");

		// 将,\n]替换成\n]

		String tmp = buffer.toString();
		tmp = tmp.replaceAll(",]", "]");
		return tmp;

	}

	/**
	 * 根据模型生成JSON
	 * 
	 * @param all
	 *            全部对象
	 * @param in
	 *            已拥有的对象
	 * @param comboBox
	 *            模型
	 * @return
	 */
	public static List<ComboBox> getComboBox(List all, List in,
			ComboBox comboBox) {
		List<ComboBox> comboxBoxs = new ArrayList<ComboBox>();
		String[] fields = new String[] { comboBox.getId(), comboBox.getText() };
		Object[] values = new Object[fields.length];
		for (Object node : all) {
			ComboBox box = new ComboBox();
			ReflectHelper reflectHelper = new ReflectHelper(node);
			for (int i = 0; i < fields.length; i++) {
				String fieldName = fields[i].toString();
				values[i] = reflectHelper.getMethodValue(fieldName);
			}
			box.setId(values[0].toString());
			box.setText(values[1].toString());
			if (in != null) {
				for (Object node1 : in) {
					ReflectHelper reflectHelper2 = new ReflectHelper(node);
					if (node1 != null) {
						String fieldName = fields[0].toString();
						String test = reflectHelper2.getMethodValue(fieldName)
								.toString();
						if (values[0].toString().equals(test)) {
							box.setSelected(true);
						}
					}
				}
			}
			comboxBoxs.add(box);
		}
		return comboxBoxs;

	}

	/**
	 * 获取自定义函数名
	 * 
	 * @param functionname
	 * @return
	 */
	public static String getFunction(String functionname) {
		int index = functionname.indexOf("(");
		if (index == -1) {
			return functionname;
		} else {
			return functionname.substring(0, functionname.indexOf("("));
		}
	}

	/**
	 * 获取自定义函数的参数
	 * 
	 * @param functionname
	 * @return
	 */
	public static String getFunParams(String functionname) {
		int index = functionname.indexOf("(");
		String param = "";
		if (index != -1) {
			String testparam = functionname.substring(
					functionname.indexOf("(") + 1, functionname.length() - 1);
			if (StringUtils.isNotEmpty(testparam)) {
				String[] params = testparam.split(",");
				for (String string : params) {
					param += "'\"+rec." + string + "+\"',";
				}
			}
		}
		param += "'\"+index+\"'";// 传出行索引号参数
		return param;
	}
}