package com.yoxi.jgframework.core.tag;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.jsp.PageContext;

import com.yoxi.jgframework.constant.Globals;
import com.yoxi.jgframework.ui.tag.vo.easyui.ColumnValue;
import com.yoxi.jgframework.ui.tag.vo.easyui.DateGridColumn;
import com.yoxi.jgframework.ui.tag.vo.easyui.DateGridUrl;
import com.yoxi.jgframework.ui.tag.vo.easyui.OptTypeDirection;
import com.yoxi.jgframework.util.ResourceUtil;
import com.yoxi.jgframework.core.utils.StringUtils;
import com.yoxi.jgframework.core.utils.ConvertUtils;
import com.yoxi.jgframework.core.utils.SystemUtils;

@SuppressWarnings("unchecked")
public class DataGridTagUtils {
	public static void setConfUrl(PageContext pageContext, String url,
			String title, String message, String exp, String operationCode,
			List<DateGridUrl> urlList) {
		setConfUrlTag(pageContext, url, title, message, exp, operationCode,
				urlList);
	}

	/**
	 * 设置询问操作URL
	 */
	private static void setConfUrlTag(PageContext pageContext, String url,
			String title, String message, String exp, String operationCode,
			List<DateGridUrl> urlList) {
		DateGridUrl dateGridUrl = new DateGridUrl();
		dateGridUrl.setTitle(title);
		dateGridUrl.setUrl(url);
		dateGridUrl.setType(OptTypeDirection.Confirm);
		dateGridUrl.setMessage(message);
		dateGridUrl.setExp(exp);
		// ----------------------------------------------------------------
		// update-start--Author:anchao Date:20130415 for：按钮权限控制
		// ----------------------------------------------------------------
		/*if (ResourceUtil.getSessionUserName().getUserName().equals("admin")
				|| !Globals.BUTTON_AUTHORITY_CHECK) {
			urlList.add(dateGridUrl);
		} else*/ 
			if (!ConvertUtils.isEmpty(operationCode)) {
			Set<String> operationCodes = (Set<String>) pageContext.getRequest()
					.getAttribute("operationCodes");
			if (null != operationCodes) {
				for (String MyoperationCode : operationCodes) {
					if (MyoperationCode.equals(operationCode)) {
						urlList.add(dateGridUrl);
					}
				}
			}
		} else {
			urlList.add(dateGridUrl);
		}
		// urlList.add(dateGridUrl);
		// ----------------------------------------------------------------
		// update-end--Author:anchao Date:20130415 for：按钮权限控制
		// ----------------------------------------------------------------
	}

	/**
	 * 设置删除操作URL
	 */
	public static void setDelUrl(PageContext pageContext,
			List<DateGridUrl> urlList, String url, String title,
			String message, String exp, String funname, String operationCode) {
		setDelUrlTag(pageContext, urlList, url, title, message, exp, funname,
				operationCode);
	}

	/**
	 * 设置删除操作URL
	 */
	private static void setDelUrlTag(PageContext pageContext,
			List<DateGridUrl> urlList, String url, String title,
			String message, String exp, String funname, String operationCode) {
		DateGridUrl dateGridUrl = new DateGridUrl();
		dateGridUrl.setTitle(title);
		dateGridUrl.setUrl(url);
		dateGridUrl.setType(OptTypeDirection.Del);
		dateGridUrl.setMessage(message);
		dateGridUrl.setExp(exp);
		dateGridUrl.setFunname(funname);
		// ----------------------------------------------------------------
		// update-start--Author:anchao Date:20130415 for：按钮权限控制
		// ----------------------------------------------------------------
	/*	if (ResourceUtil.getSessionUserName().getUserName().equals("admin")
				|| !Globals.BUTTON_AUTHORITY_CHECK) {
			urlList.add(dateGridUrl);
		} else*/ 
			if (!ConvertUtils.isEmpty(operationCode)) {
			Set<String> operationCodes = (Set<String>) pageContext.getRequest()
					.getAttribute("operationCodes");
			if (null != operationCodes) {
				for (String MyoperationCode : operationCodes) {
					if (MyoperationCode.equals(operationCode)) {
						urlList.add(dateGridUrl);
					}
				}
			}
		} else {
			urlList.add(dateGridUrl);
		}
		// urlList.add(dateGridUrl);
		// ----------------------------------------------------------------
		// update-end--Author:anchao Date:20130415 for：按钮权限控制
		// ----------------------------------------------------------------
	}

	/**
	 * 设置默认操作URL
	 */
	public static void setDefUrl(PageContext pageContext,
			List<DateGridUrl> urlList, String url, String title, String exp,
			String operationCode) {
		setDefUrlTag(pageContext, urlList, url, title, exp, operationCode);
	}

	/**
	 * 设置默认操作URL
	 */
	private static void setDefUrlTag(PageContext pageContext,
			List<DateGridUrl> urlList, String url, String title, String exp,
			String operationCode) {
		DateGridUrl dateGridUrl = new DateGridUrl();
		dateGridUrl.setTitle(title);
		dateGridUrl.setUrl(url);
		dateGridUrl.setType(OptTypeDirection.Deff);
		dateGridUrl.setExp(exp);
		// ----------------------------------------------------------------
		// update-start--Author:anchao Date:20130415 for：按钮权限控制
		// ----------------------------------------------------------------
		/*if (ResourceUtil.getSessionUserName().getUserName().equals("admin")
				|| !Globals.BUTTON_AUTHORITY_CHECK) {
			urlList.add(dateGridUrl);
		} else*/ 
			if (!ConvertUtils.isEmpty(operationCode)) {
			Set<String> operationCodes = (Set<String>) pageContext.getRequest()
					.getAttribute("operationCodes");
			if (null != operationCodes) {
				for (String MyoperationCode : operationCodes) {
					if (MyoperationCode.equals(operationCode)) {
						urlList.add(dateGridUrl);
					}
				}
			}
		} else {
			urlList.add(dateGridUrl);
		}
		// urlList.add(dateGridUrl);
		// ----------------------------------------------------------------
		// update-end--Author:anchao Date:20130415 for：按钮权限控制
		// ----------------------------------------------------------------
	}

	/**
	 * 设置工具条
	 */
	public static void setToolbar(PageContext pageContext,
			List<DateGridUrl> toolBarList, String url, String title,
			String icon, String exp, String onclick, String funname,
			String operationCode,String popWidth,String popHeight) {
		setToolbarTag(pageContext, toolBarList, url, title, icon, exp, onclick,
				funname, operationCode,popWidth,popHeight);
	}

	/**
	 * 设置工具条
	 */
	private static void setToolbarTag(PageContext pageContext,
			List<DateGridUrl> toolBarList, String url, String title,
			String icon, String exp, String onclick, String funname,
			String operationCode,String popWidth, String popHeight) {
		DateGridUrl dateGridUrl = new DateGridUrl();
		dateGridUrl.setTitle(title);
		dateGridUrl.setUrl(url);
		dateGridUrl.setType(OptTypeDirection.ToolBar);
		dateGridUrl.setIcon(icon);
		dateGridUrl.setOnclick(onclick);
		dateGridUrl.setExp(exp);
		dateGridUrl.setFunname(funname);
		dateGridUrl.setWidth(popWidth);
		dateGridUrl.setHeight(popHeight);
		// ----------------------------------------------------------------
		// update-start--Author:anchao Date:20130415 for：按钮权限控制
		// ----------------------------------------------------------------
		/*if (ResourceUtil.getSessionUserName().getUserName().equals("admin")
				|| !Globals.BUTTON_AUTHORITY_CHECK) {
			toolBarList.add(dateGridUrl);
		} else */
			if (!ConvertUtils.isEmpty(operationCode)) {
			Set<String> operationCodes = (Set<String>) pageContext.getRequest()
					.getAttribute("operationCodes");
			if (null != operationCodes) {
				for (String MyoperationCode : operationCodes) {
					if (MyoperationCode.equals(operationCode)) {
						toolBarList.add(dateGridUrl);
					}
				}
			}
		} else {
			toolBarList.add(dateGridUrl);
		}
		// toolBarList.add(dateGridUrl);
		// ----------------------------------------------------------------
		// update-end--Author:anchao Date:20130415 for：按钮权限控制
		// ----------------------------------------------------------------
	}

	/**
	 * 设置自定义函数操作URL
	 */
	public static void setFunUrl(PageContext pageContext,
			List<DateGridUrl> urlList, String title, String exp,
			String funname, String operationCode) {
		setFunUrlTag(pageContext, urlList, title, exp, funname, operationCode);
	}

	/**
	 * 设置自定义函数操作URL
	 */
	private static void setFunUrlTag(PageContext pageContext,
			List<DateGridUrl> urlList, String title, String exp,
			String funname, String operationCode) {
		DateGridUrl dateGridUrl = new DateGridUrl();
		dateGridUrl.setTitle(title);
		dateGridUrl.setType(OptTypeDirection.Fun);
		dateGridUrl.setExp(exp);
		dateGridUrl.setFunname(funname);
		// ----------------------------------------------------------------
		// update-start--Author:anchao Date:20130415 for：按钮权限控制
		// ----------------------------------------------------------------
		/*if (ResourceUtil.getSessionUserName().getUserName().equals("admin")
				|| !Globals.BUTTON_AUTHORITY_CHECK) {
			urlList.add(dateGridUrl);
		} else*/
			if (!ConvertUtils.isEmpty(operationCode)) {
			Set<String> operationCodes = (Set<String>) pageContext.getRequest()
					.getAttribute("operationCodes");
			if (null != operationCodes) {
				for (String MyoperationCode : operationCodes) {
					if (MyoperationCode.equals(operationCode)) {
						urlList.add(dateGridUrl);
					}
				}
			}
		} else {
			urlList.add(dateGridUrl);
		}
		// urlList.add(dateGridUrl);
		// ----------------------------------------------------------------
		// update-end--Author:anchao Date:20130415 for：按钮权限控制
		// ----------------------------------------------------------------

	}

	/**
	 * 设置自定义函数操作URL
	 */
	public static void setOpenUrl(PageContext pageContext,
			List<DateGridUrl> urlList, String url, String title, String width,
			String height, String exp, String operationCode, String funname) {
		setOpenUrlTag(pageContext, urlList, url, title, width, height, exp,
				operationCode, funname);
	}

	/**
	 * 设置自定义函数操作URL
	 */
	private static void setOpenUrlTag(PageContext pageContext,
			List<DateGridUrl> urlList, String url, String title, String width,
			String height, String exp, String operationCode, String funname) {
		DateGridUrl dateGridUrl = new DateGridUrl();
		dateGridUrl.setTitle(title);
		dateGridUrl.setUrl(url);
		dateGridUrl.setWidth(width);
		dateGridUrl.setHeight(height);
		dateGridUrl.setType(OptTypeDirection.OpenWin);
		dateGridUrl.setExp(exp);
		dateGridUrl.setFunname(funname);
		// ----------------------------------------------------------------
		// update-start--Author:anchao Date:20130415 for：按钮权限控制
		// ----------------------------------------------------------------
		/*if (ResourceUtil.getSessionUserName().getUserName().equals("admin")
				|| !Globals.BUTTON_AUTHORITY_CHECK) {
			urlList.add(dateGridUrl);
		} else */
			if (!ConvertUtils.isEmpty(operationCode)) {
			Set<String> operationCodes = (Set<String>) pageContext.getRequest()
					.getAttribute("operationCodes");
			if (null != operationCodes) {
				for (String MyoperationCode : operationCodes) {
					if (MyoperationCode.equals(operationCode)) {
						urlList.add(dateGridUrl);
					}
				}
			}
		} else {
			urlList.add(dateGridUrl);
		}
		// urlList.add(dateGridUrl);
		// ----------------------------------------------------------------
		// update-end--Author:anchao Date:20130415 for：按钮权限控制
		// ----------------------------------------------------------------
	}

	/**
	 * 
	 * <b>Summary: </b> setColumn(设置字段替换值)
	 * 
	 * @param name
	 * @param text
	 * @param value
	 */
	public static void setColumn(List<ColumnValue> columnValueList,
			String name, String text, String value) {
		setColumnTag(columnValueList, name, text, value);
	}

	/**
	 * 
	 * <b>Summary: </b> setColumn(设置字段替换值)
	 * 
	 * @param name
	 * @param text
	 * @param value
	 */
	private static void setColumnTag(List<ColumnValue> columnValueList,
			String name, String text, String value) {
		ColumnValue columnValue = new ColumnValue();
		columnValue.setName(name);
		columnValue.setText(text);
		columnValue.setValue(value);
		columnValueList.add(columnValue);
	}

	/**
	 * datatables构造方法
	 * 
	 * @return
	 */
	public static StringBuffer datatables(String name,
			List<DateGridColumn> columnList, List<DateGridUrl> urlList,
			String idField, String style) {
		return datatablesTag(name, columnList, urlList, idField, style);
	}

	/**
	 * datatables构造方法
	 * 
	 * @return
	 */
	private static StringBuffer datatablesTag(String name,
			List<DateGridColumn> columnList, List<DateGridUrl> urlList,
			String idField, String style) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script type=\"text/javascript\">");
		sb.append("$(document).ready(function() {");
		sb.append("var oTable = $(\'#" + name + "\').dataTable({");
		// sb.append(
		// "\"sDom\" : \"<\'row\'<\'span6\'l><\'span6\'f>r>t<\'row\'<\'span6\'i><\'span6\'p>>\",");
		sb.append("\"bProcessing\" : true,");// 当datatable获取数据时候是否显示正在处理提示信息"
		sb.append("\"bPaginate\" : true,"); // 是否分页"
		sb.append("\"sPaginationType\" : \"full_numbers\",");// 分页样式full_numbers,"
		sb.append("\"bFilter\" : true,");// 是否使用内置的过滤功能"
		sb.append("\"bSort\" : true, ");// 排序功能"
		sb.append("\"bAutoWidth\" : true,");// 自动宽度"
		sb.append("\"bLengthChange\" : true,");// 是否允许用户自定义每页显示条数"
		sb.append("\"bInfo\" : true,");// 页脚信息"
		sb.append("\"sAjaxSource\" : \"userController.do?test\",");
		sb.append("\"bServerSide\" : true,");// 指定从服务器端获取数据
		sb.append("\"oLanguage\" : {" + "\"sLengthMenu\" : \" _MENU_ 条记录\","
				+ "\"sZeroRecords\" : \"没有检索到数据\","
				+ "\"sInfo\" : \"第 _START_ 至 _END_ 条数据 共 _TOTAL_ 条\","
				+ "\"sInfoEmtpy\" : \"没有数据\","
				+ "\"sProcessing\" : \"正在加载数据...\"," + "\"sSearch\" : \"搜索\","
				+ "\"oPaginate\" : {" + "\"sFirst\" : \"首页\","
				+ "\"sPrevious\" : \"前页\", " + "\"sNext\" : \"后页\","
				+ "\"sLast\" : \"尾页\"" + "}" + "},"); // 汉化
		// 获取数据的处理函数 \"data\" : {_dt_json : JSON.stringify(aoData)},
		sb.append("\"fnServerData\" : function(sSource, aoData, fnCallback, oSettings) {");
		// + "\"data\" : {_dt_json : JSON.stringify(aoData)},"
		sb.append("oSettings.jqXHR = $.ajax({" + "\"dataType\" : \'json\',"
				+ "\"type\" : \"POST\"," + "\"url\" : sSource,"
				+ "\"data\" : aoData," + "\"success\" : fnCallback" + "});},");
		// sb.append("\"aaSorting\": [[0 , \"desc\" ],[1 , \"asc\" ]],");
		// sb.append("\"aoColumnDefs\":[");
		// sb.append("{\"sClass\" : \"center\",\"aTargets\" : [ 0, 1, 2, 3 ]},");
		// sb.append("{\"bSearchable\":false,\"aTargets\" : [ 0, 1 ]},");
		// sb.append("{\"bVisible\" : false,\"aTargets\" : [ 0 ]},");
		// sb.append("{\"bSortable\" : true,\"aTargets\" : [ 0, 1 ]} ],");
		sb.append("\"aoColumns\" : [ ");
		int i = 0;
		for (DateGridColumn column : columnList) {
			i++;
			sb.append("{");
			sb.append("\"sTitle\":\"" + column.getTitle() + "\"");
			if (column.getField().equals("opt")) {
				sb.append(",\"mData\":\"" + idField + "\"");
				sb.append(",\"sWidth\":\"20%\"");
				sb.append(",\"bSortable\":false");
				sb.append(",\"bSearchable\":false");
				sb.append(",\"mRender\" : function(data, type, rec) {");
				getOptUrl(sb, urlList, name);
				sb.append("}");
			} else {
				int colwidth = (column.getWidth() == null) ? column.getTitle()
						.length() * 15 : column.getWidth();
				sb.append(",\"sName\":\"" + column.getField() + "\"");
				sb.append(",\"mDataProp\":\"" + column.getField() + "\"");
				sb.append(",\"mData\":\"" + column.getField() + "\"");
				sb.append(",\"sWidth\":\"" + colwidth + "\"");
				sb.append(",\"bSortable\":" + column.isSortable() + "");
				sb.append(",\"bVisible\":" + column.isHidden() + "");
				sb.append(",\"bSearchable\":" + column.isQuery() + "");
			}
			sb.append("}");
			if (i < columnList.size())
				sb.append(",");
		}

		sb.append("]" + "});" + "});" + "</script>");
		sb.append("<table width=\"100%\"  class=\"" + style + "\" id=\"" + name
				+ "\" toolbar=\"#" + name + "tb\"></table>");
		return sb;
	}

	/**
	 * 拼装操作地址
	 * 
	 * @param sb
	 */
	public static void getOptUrl(StringBuffer sb, List<DateGridUrl> urlList,
			String name) {
		getOptUrlTag(sb, urlList, name);
	}

	/**
	 * 拼装操作地址
	 * 
	 * @param sb
	 */
	@SuppressWarnings({ "rawtypes", "static-access" })
	private static void getOptUrlTag(StringBuffer sb,
			List<DateGridUrl> urlList, String name) {
		// update-begin--Author:zhaojunfu Date:20130520 for：TASK #109
		// datagrid标签没有封装合计功能
		// 注：操作列表会带入合计列中去，故加此判断 引起非ID主键无法显示问题
		sb.append("if(rec.statsMode_foot){return '';}");
		// sb.append("if(!rec.id){return '';}");
		// update-begin--Author:zhaojunfu Date:20130520 for：TASK #109
		// datagrid标签没有封装合计功能
		List<DateGridUrl> list = urlList;
		sb.append("var href='';");
		for (DateGridUrl dateGridUrl : list) {
			String url = dateGridUrl.getUrl();
			MessageFormat formatter = new MessageFormat("");
			if (dateGridUrl.getValue() != null) {
				String[] testvalue = dateGridUrl.getValue().split(",");
				List value = new ArrayList<Object>();
				for (String string : testvalue) {
					value.add("\"+rec." + string + " +\"");
				}
				url = formatter.format(url, value.toArray());
			}
			if (url != null && dateGridUrl.getValue() == null) {

				url = formatUrl(url);
			}
			String exp = dateGridUrl.getExp();// 判断显示表达式
			if (StringUtils.isNotEmpty(exp)) {
				String[] ShowbyFields = exp.split("&&");
				for (String ShowbyField : ShowbyFields) {
					int beginIndex = ShowbyField.indexOf("#");
					int endIndex = ShowbyField.lastIndexOf("#");
					String exptype = ShowbyField.substring(beginIndex + 1,
							endIndex);// 表达式类型
					String field = ShowbyField.substring(0, beginIndex);// 判断显示依据字段
					String[] values = ShowbyField.substring(endIndex + 1,
							ShowbyField.length()).split(",");// 传入字段值
					String value = "";
					for (int i = 0; i < values.length; i++) {
						value += "'" + "" + values[i] + "" + "'";
						if (i < values.length - 1) {
							value += ",";
						}
					}
					if ("eq".equals(exptype)) {
						sb.append("if($.inArray(rec." + field + ",[" + value
								+ "])>=0){");
					}
					if("eqAttribute".equals(exptype)){
						String field1 = ShowbyField.substring(endIndex + 1,ShowbyField.length());// 判断显示依据字段
						sb.append("if($.inArray(rec." + field + ",[rec." + field1+"])>=0){");
					}
					if ("ne".equals(exptype)) {
						sb.append("if($.inArray(rec." + field + ",[" + value
								+ "])<0){");
					}
					if ("empty".equals(exptype) && value.equals("'true'")) {
						sb.append("if(rec." + field + "==''){");
					}
					if ("empty".equals(exptype) && value.equals("'false'")) {
						sb.append("if(rec." + field + "!=''){");
					}
				}
			}

			if (OptTypeDirection.Confirm.equals(dateGridUrl.getType())) {
				sb.append("href+=\"[<a href=\'#\' onclick=confirm(\'" + url
						+ "\',\'" + dateGridUrl.getMessage() + "\',\'" + name
						+ "\')> \";");
			}
			if (OptTypeDirection.Del.equals(dateGridUrl.getType())) {
				sb.append("href+=\"[<a href=\'#\' onclick=delObj(\'" + url
						+ "\',\'" + name + "\')>\";");
			}
			if (OptTypeDirection.Fun.equals(dateGridUrl.getType())) {
				String nameStr = TagUtil.getFunction(dateGridUrl.getFunname());
				String parmars = TagUtil.getFunParams(dateGridUrl.getFunname());
				sb.append("href+=\"[<a href=\'#\' onclick=" + nameStr + "("
						+ parmars + ")>\";");
			}
			if (OptTypeDirection.OpenWin.equals(dateGridUrl.getType())) {
				if (StringUtils.isNotBlank(dateGridUrl.getFunname())) {
					sb.append("href+=\"[<a href=\'#\' onclick=openwindow('"
							+ dateGridUrl.getTitle() + "','" + url + "','"
							+ name + "','" + dateGridUrl.getWidth() + "','"
							+ dateGridUrl.getHeight() + "','"
							+ dateGridUrl.getFunname() + "')>\";");
				} else {
					sb.append("href+=\"[<a href=\'#\' onclick=openwindow('"
							+ dateGridUrl.getTitle() + "','" + url + "','"
							+ name + "','" + dateGridUrl.getWidth() + "','"
							+ dateGridUrl.getHeight() + "')>\";");
				}
			}
			if (OptTypeDirection.Deff.equals(dateGridUrl.getType())) {
				sb.append("href+=\"[<a href=\'" + url + "' title=\'"
						+ dateGridUrl.getTitle() + "\'>\";");
			}
			sb.append("href+=\"" + dateGridUrl.getTitle() + "</a>]\";");

			if (StringUtils.isNotEmpty(exp)) {
				for (int i = 0; i < exp.split("&&").length; i++) {
					sb.append("}");
				}

			}
		}
		sb.append("return href;");
	}

	/**
	 * 格式化URL
	 * 
	 * @return
	 */
	public static String formatUrl(String url) {
		return formatUrlTag(url);
	}

	/**
	 * 格式化URL
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "static-access" })
	private static String formatUrlTag(String url) {
		MessageFormat formatter = new MessageFormat("");
		String parurlvalue = "";
		if (url.indexOf("&") >= 0) {
			String beforeurl = url.substring(0, url.indexOf("&"));// 截取请求地址
			String parurl = url.substring(url.indexOf("&") + 1, url.length());// 截取参数
			String[] pras = parurl.split("&");
			List value = new ArrayList<Object>();
			int j = 0;
			for (int i = 0; i < pras.length; i++) {
				if (pras[i].indexOf("{") >= 0 || pras[i].indexOf("#") >= 0) {
					String field = pras[i].substring(pras[i].indexOf("{") + 1,
							pras[i].lastIndexOf("}"));
					parurlvalue += "&"
							+ pras[i].replace("{" + field + "}", "{" + j + "}");
					value.add("\"+rec." + field + " +\"");
					j++;
				} else {
					parurlvalue += "&" + pras[i];
				}
			}
			url = formatter.format(beforeurl + parurlvalue, value.toArray());
		}
		return url;
	}

	/**
	 * 列自定义函数
	 * 
	 * @param sb
	 * @param column
	 */
	public static void getFun(StringBuffer sb, DateGridColumn column) {
		getFunTag(sb, column);
	}

	/**
	 * 列自定义函数
	 * 
	 * @param sb
	 * @param column
	 */
	private static void getFunTag(StringBuffer sb, DateGridColumn column) {
		String url = column.getUrl();
		url = DataGridTagUtils.formatUrl(url);
		sb.append("var href=\"<a style=\'color:red\' href=\'#\' onclick="
				+ column.getFunname() + "('" + column.getTitle() + "','" + url
				+ "')>\";");
		sb.append("return href+value+\'</a>\';");
	}

	/**
	 * 拼接字段
	 * 
	 * @param sb
	 * @frozen 0 冰冻列 1 普通列
	 */
	public static void getField(StringBuffer sb, List<DateGridUrl> urlList,
			String name, int frozen, boolean checkbox,
			List<DateGridColumn> columnList, boolean treegrid,
			List<ColumnValue> columnValueList) {
		getFieldTag(sb, urlList, name, frozen, checkbox, columnList, treegrid,
				columnValueList);
	}

	/**
	 * 拼接字段
	 * 
	 * @param sb
	 * @frozen 0 冰冻列 1 普通列
	 */
	private static void getFieldTag(StringBuffer sb, List<DateGridUrl> urlList,
			String name, int frozen, boolean checkbox,
			List<DateGridColumn> columnList, boolean treegrid,
			List<ColumnValue> columnValueList) {
		// 复选框
		if (checkbox && frozen == 0) {
			sb.append("{field:\'ck\',checkbox:\'true\'},");
		}
		int i = 0;
		for (DateGridColumn column : columnList) {
			i++;
			if ((column.isFrozenColumn() && frozen == 0)
					|| (!column.isFrozenColumn() && frozen == 1)) {
				String field;
				if (treegrid) {
					field = column.getTreefield();
				} else {
					field = column.getField();
				}
				sb.append("{field:\'" + field + "\',title:\'"
						+ column.getTitle() + "\',");
				int colwidth = (column.getWidth() == null) ? column.getTitle()
						.length() * 15 : column.getWidth();
				sb.append("width:" + colwidth + "");
				if(column.getAlign()!=null){
					sb.append(",align:\'"+column.getAlign()+"\'");
				}

				// 隐藏字段
				if (!column.isHidden()) {
					sb.append(",hidden:true");
				}
				if (!treegrid) {
					// 字段排序
					if ((column.isSortable())
							&& (field.indexOf("_") <= 0 && field != "opt")) {
						sb.append(",sortable:" + column.isSortable() + "");
					}
				}
				// 显示图片
				if (column.isImage()) {
					sb.append(",formatter:function(value,rec,index){");
					if(column.isBanner()){
						sb.append(" return '<img width=\"120px\" onerror=\"getLoadImage(this)\" height=\"60px\" border=\"0\" src=\"'+value+'\"/>'}");
					}else{
						if(column.isThumbnail())
							sb.append(" return '<img width=\"60px\" onerror=\"getLoadImage(this)\" height=\"60px\" border=\"0\" src=\"'+value+'\"/>'}");
						else
							sb.append(" return '<img border=\"0\" src=\"'+value+'\"/>'}");
					}
				}
				// 自定义链接
				if (column.getUrl() != null) {
					sb.append(",formatter:function(value,rec,index){");
					getFun(sb, column);
					sb.append("}");
				}
				// 自定义标签函数
				if (column.getLink() != null) {
					sb.append(",formatter:function(value,rec,index){");
					getLinkFun(sb, column);
					sb.append("}");
				}
				if (column.getFormatter() != null) {
					sb.append(",formatter:function(value,rec,index){");
					sb.append(" if(rec.statsMode_foot){");
					sb.append("if(value!=''&&value!=null){");
					sb.append("if('#'=='" + column.getFormatter()
							+ "'){return(new Number(value).toFixed());}");
					sb.append("if('#.##'=='" + column.getFormatter()
							+ "'){return(new Number(value).toFixed(2));}");
					sb.append("}");
					sb.append("return value;");
					sb.append("}else{");
					sb.append("if(value!=''&&value!=null){");
					sb.append("if('#'=='" + column.getFormatter()
							+ "'){return(new Number(value).toFixed());}");
					sb.append("if('#.##'=='" + column.getFormatter()
							+ "'){return(new Number(value).toFixed(2));}");
					sb.append(" return new Date().format('"
							+ column.getFormatter() + "',value);}");
					sb.append("}");
					sb.append("}");
				}
				if (StringUtils.isNotBlank(column.getChildField())) {
					sb.append(",formatter:function(value,rec,index){");
					sb.append("if(value!=null && value."
							+ column.getChildField() + "!=null){");
					sb.append("return value." + column.getChildField() + ";}");
					sb.append("else{return '';}");
					sb.append("}");
				}
				// 加入操作
				if (column.getField().equals("opt")) {
					sb.append(",formatter:function(value,rec,index){");
					// sb.append("return \"");
					getOptUrl(sb, urlList, name);
					sb.append("}");
				}
				// 值替換
				if (columnValueList.size() > 0
						&& !column.getField().equals("opt")) {
					String testString = "";
					for (ColumnValue columnValue : columnValueList) {
						if (columnValue.getName().equals(column.getField())) {
							String[] value = columnValue.getValue().split(",");
							String[] text = columnValue.getText().split(",");
							sb.append(",formatter:function(value,rec,index){");
							for (int j = 0; j < value.length; j++) {
								testString += "if(value=='" + value[j]
										+ "'){return \'" + text[j] + "\'}";
							}
							sb.append(testString);
							sb.append("else{return value}");
							sb.append("}");
						}
					}

				}
				sb.append("}");
				// 去除末尾,
				if (i < columnList.size()) {
					sb.append(",");
				}
			}
		}
	}

	/**
	 * 列自定义函数
	 * 
	 * @param sb
	 * @param column
	 */
	public static void getLinkFun(StringBuffer sb, DateGridColumn column) {
		getLinkFunTag(sb, column);
	}

	/**
	 * 列自定义函数
	 * 
	 * @param sb
	 * @param column
	 */
	private static void getLinkFunTag(StringBuffer sb, DateGridColumn column) {
		String link = column.getLink();
		String[] args = link.split("-");
		if (args.length == 2) {
			if (args[1].equalsIgnoreCase("fun")) {
				sb.append("var href=\"<a href=\'\"+value+\"\' onclick='"
						+ args[0] + "(this)'>\";");
				sb.append("return href+\"下载\"+\'</a>\';");
			}
		}
	}

	/**
	 * 设置分页条信息
	 * 
	 * @param sb
	 */
	public static void setPager(StringBuffer sb, String grid, String name,
			boolean showRefresh, boolean showText, int pageSize,
			boolean showPageList) {
		setPagerTag(sb, grid, name, showRefresh, showText, pageSize,
				showPageList);
	}

	/**
	 * 设置分页条信息
	 * 
	 * @param sb
	 */
	private static void setPagerTag(StringBuffer sb, String grid, String name,
			boolean showRefresh, boolean showText, int pageSize,
			boolean showPageList) {
		sb.append("$(\'#" + name + "\')." + grid
				+ "(\'getPager\').pagination({");
		sb.append("beforePageText:\'\'," + "afterPageText:\'/{pages}\',");
		if (showText) {
			sb.append("displayMsg:\'{from}-{to}共{total}条\',");
		} else {
			sb.append("displayMsg:\'\',");
		}
		if (showPageList == true) {
			sb.append("showPageList:true,");
			sb.append("pageList:[" + pageSize * 1 + "," + pageSize * 2 + ","
					+ pageSize * 3 + "],");
		} else {
			sb.append("showPageList:false,");
		}
		sb.append("showRefresh:" + showRefresh + "");
		sb.append("});");// end getPager
		sb.append("$(\'#" + name + "\')." + grid
				+ "(\'getPager\').pagination({");
		sb.append("onBeforeRefresh:function(pageNumber, pageSize){$(this).pagination(\'loading\');$(this).pagination(\'loaded\'); }");
		sb.append("});");
	}

	// 列表查询框函数
	public static void searchboxFun(StringBuffer sb, String grid, String name) {
		searchboxFunTag(sb, grid, name);
	}

	// 列表查询框函数
	private static void searchboxFunTag(StringBuffer sb, String grid,
			String name) {
		sb.append("function " + name + "searchbox(value,name){");
		sb.append("var queryParams=$(\'#" + name
				+ "\').datagrid('options').queryParams;");
		sb.append("queryParams[name]=value;queryParams.searchfield=name;$(\'#"
				+ name + "\')." + grid + "(\'reload\');}");
		sb.append("$(\'#" + name + "searchbox\').searchbox({");
		sb.append("searcher:function(value,name){");
		sb.append("" + name + "searchbox(value,name);");
		sb.append("},");
		sb.append("menu:\'#" + name + "mm\',");
		sb.append("prompt:\'请输入查询关键字\'");
		sb.append("});");
	}

	/**
	 * easyui构造方法
	 * 
	 * @return
	 */
	public static StringBuffer end(String name, String queryMode, boolean showRefresh,
			boolean showText, boolean showPageList, List<DateGridUrl> urlList,
			List<ColumnValue> columnValueList, String actionUrl, String fields,
			boolean checkbox, List<DateGridUrl> toolBarList, String onClick,
			String onDblClick, List<DateGridColumn> columnList,
			boolean fitColumns, String sortUrl, String onLoadSuccess,
			boolean sortable, boolean pagination, String sortOrder,
			String sortName, String footer, int pageSize, boolean fit,
			String idField, String title, boolean autoLoadData, String width,
			String height, boolean treegrid,boolean flipDeselect) {
		return endTag(name, queryMode, showRefresh, showText, showPageList, urlList, columnValueList, actionUrl, fields, checkbox, toolBarList, onClick, onDblClick, columnList, fitColumns, sortUrl, onLoadSuccess, sortable, pagination, sortOrder, sortName, footer, pageSize, fit, idField, title, autoLoadData, width, height, treegrid,flipDeselect);
	}
	/**
	 * easyui构造方法
	 * 
	 * @return
	 */
	public static StringBuffer endTag(String name, String queryMode, boolean showRefresh,
			boolean showText, boolean showPageList, List<DateGridUrl> urlList,
			List<ColumnValue> columnValueList, String actionUrl, String fields,
			boolean checkbox, List<DateGridUrl> toolBarList, String onClick,
			String onDblClick, List<DateGridColumn> columnList,
			boolean fitColumns, String sortUrl, String onLoadSuccess,
			boolean sortable, boolean pagination, String sortOrder,
			String sortName, String footer, int pageSize, boolean fit,
			String idField, String title, boolean autoLoadData, String width,
			String height, boolean treegrid,boolean flipDeselect) {

		String grid = "";
		StringBuffer sb = new StringBuffer();
		width = (width == null) ? "auto" : width;
		height = (height == null) ? "auto" : height;
		sb.append("<script type=\"text/javascript\">");
		sb.append("$(function(){");
		if (treegrid) {
			grid = "treegrid";
			sb.append("$(\'#" + name + "\').treegrid({");
			sb.append("idField:'id',");
			sb.append("treeField:'text',");
		} else {
			grid = "datagrid";
			sb.append("$(\'#" + name + "\').datagrid({");
			sb.append("idField: '" + idField + "',");
		}
		if (title != null) {
			sb.append("title: \'" + title + "\',");
		}
		// --------begin ------------author:邢双阳 ---date：2013-5-13
		// for：【103】页面载入时，数据是否载入采取可配置模式

		if (autoLoadData) {
			sb.append("url:\'" + actionUrl + "\',");
			sb.append("queryParams:{\'field\':\'" + fields + "\',\'footer\':\'"
					+ footer + "\'},");
		} else {
			sb.append("url:'',");
		}
		// --------end ------------author:邢双阳 ---date：2013-5-13
		// for：【103】页面载入时，数据是否载入采取可配置模式
		if (fit) {
			sb.append("fit:true,");
		} else {
			sb.append("fit:false,");
		}
		sb.append("loadMsg: \'数据加载中...\',");
		sb.append("pageSize: " + pageSize + ",");
		sb.append("pagination:" + pagination + ",");
		// -----update-begin---author:zhangdaihao date:20130206 for:排序字段---
		if (StringUtils.isNotBlank(sortName)) {
			sb.append("sortName:'" + sortName + "',");
		}
		// -----update-end---author:zhangdaihao date:20130206 for:排序字段---
		sb.append("sortOrder:'" + sortOrder + "',");
		sb.append("rownumbers:true,");
		sb.append("singleSelect:" + !checkbox + ",");
		if (fitColumns) {
			sb.append("fitColumns:true,");
		} else {
			sb.append("fitColumns:false,");
		}
		// update-begin--Author:zhaojunfu Date:20130520 for：TASK #109
		// datagrid标签没有封装合计功能
		sb.append("showFooter:true,");
		// update-end--Author:zhaojunfu Date:20130520 for：TASK #109
		// datagrid标签没有封装合计功能
		// ---begin--- author:邢双阳 ---date:2013-05-18 ---for:[106]实现冰冻列
		sb.append("frozenColumns:[[");
		getField(sb, urlList, name, 0, checkbox, columnList, treegrid,
				columnValueList);
		sb.append("]],");
		// ---end--- author:邢双阳 ---date:2013-05-18 ---for:[106]实现冰冻列

		sb.append("columns:[[");
		getField(sb, urlList, name, 1, checkbox, columnList, treegrid,
				columnValueList);
		sb.append("]],");
		if(flipDeselect){
		 sb.append("onBeforeLoad:function(data){$(\'#" + name + "\')." + grid +
				 "(\'unselectAll\');},");
		}
		if (sortable) {
			if (StringUtils.isNotEmpty(onLoadSuccess)) {
				sb.append("onLoadSuccess:function(data){" + onLoadSuccess
						+ "(data); gridloadSort('" + name + "','" + sortUrl
						+ "');},");
			} else {
				sb.append("onLoadSuccess:function(data){ gridloadSort('" + name
						+ "','" + sortUrl + "');},");
				// onLoadSuccess="gridload('typeList${typegroup.id}')"
			}
		} else if (StringUtils.isNotEmpty(onLoadSuccess)) {
			sb.append("onLoadSuccess:function(data){" + onLoadSuccess
					+ "(data);},");
		}
		if (StringUtils.isNotEmpty(onDblClick)) {
			sb.append("onDblClickRow:function(rowIndex,rowData){" + onDblClick
					+ "(rowIndex,rowData);},");
		}
		if (treegrid) {
			sb.append("onClickRow:function(rowData){");
		} else {
			sb.append("onClickRow:function(rowIndex,rowData){");
		}
		/** 行记录赋值 */
		sb.append("rowid=rowData.id;");
		sb.append("gridname=\'" + name + "\';");
		if (StringUtils.isNotEmpty(onClick)) {
			sb.append("" + onClick + "(rowIndex,rowData);");
		}
		sb.append("}");
		sb.append("});");
		setPager(sb, grid, name, showRefresh, showText, pageSize, showPageList);
		sb.append("});");
		sb.append("function reloadTable(){ $(\'#\'+gridname)." + grid
				+ "(\'unselectAll\');");
		sb.append("$(\'#\'+gridname)." + grid + "(\'reload\');" + "}");
		sb.append("function reload" + name + "(){$(\'#\'+gridname)." + grid
				+ "(\'unselectAll\');" + "$(\'#" + name + "\')." + grid
				+ "(\'reload\');" + "}");
		sb.append("function get" + name
				+ "Selected(field){return getSelected(field);}");
		sb.append("function getSelected(field){"
				+ "var row = $(\'#\'+gridname)." + grid + "(\'getSelected\');"
				+ "if(row!=null)" + "{" + "value= row[field];" + "}" + "else"
				+ "{" + "value=\'\';" + "}" + "return value;" + "}");
		sb.append("function get" + name + "Selections(field){"
				+ "var ids = [];" + "var rows = $(\'#" + name + "\')." + grid
				+ "(\'getSelections\');" + "for(var i=0;i<rows.length;i++){"
				+ "ids.push(rows[i][field]);" + "}" + "ids.join(\',\');"
				+ "return ids" + "};");
		if (columnList.size() > 0) {
			sb.append("function " + name + "search(){");
			sb.append("var queryParams=$(\'#" + name
					+ "\').datagrid('options').queryParams;");
			// update-begin--Author:zhaojunfu Date:20130420 for：解决tab间取查询条件冲突
			// for (DateGridColumn col : columnList) {
			// if (col.isQuery()) {
			// sb.append("queryParams." + col.getField() + "= $(\'#" +
			// col.getField() + "\').val();");
			// }
			// }
			// update-begin--Author: sun Date:20130506
			// for：解决查询条件取值非input类型是，取值为空的bug 注：20130507赵俊夫进一步修复
			sb.append("$(\'#"
					+ name
					+ "tb\').find('*').each(function(){queryParams[$(this).attr('name')]=$(this).val();});");

			sb.append("queryParams[\'field\']=\'" + fields + "\';");
			sb.append("queryParams[\'footer\']=\'" + footer + "\';");

			// update-end--Author: sun Date:20130506
			// for：解决查询条件取值非input类型是，取值为空的bug
			// update-end--Author:zhaojunfu Date:20130420 for：解决tab间取查询条件冲突
			// sb.append("$(\'#" + name + "\')." + grid + "({url:'" + actionUrl
			// + "'});" + "}");
			sb.append("$(\'#" + name + "\')." + grid + "('load');" + "}");
			// 高级查询执行方法
			sb.append("function dosearch(params){");
			sb.append("var jsonparams=$.parseJSON(params);");

			sb.append("jsonparams[\'field\']=\'" + fields + "\';");
			sb.append("jsonparams[\'footer\']=\'" + footer + "\';");
			sb.append("$(\'#" + name + "\')." + grid + "('reload');" + "}");
			// sb.append("$(\'#" + name + "\')." + grid + "({url:'" + actionUrl
			// + "',queryParams:jsonparams});" + "}");
			if (toolBarList.size() > 0) {
				// searchbox框执行方法
				searchboxFun(sb, grid, name);
			}
			// 生成重置按钮功能js
			sb.append("function searchReset(name){");
			// update-begin--Author: sun Date:20130506
			// for：解决查询条件重置非input类型时，失效的问题
			sb.append(" $(\'#" + name
					+ "\').datagrid('unselectAll'); $(\"#\"+name+\"tb\").find(\":input\").val(\"\");");
			String func = name.trim() + "search();";
			sb.append(func);
			// update-end--Author: sun Date:20130506 for：解决查询条件重置非input类型时，失效的问题
			sb.append("}");
		}
		sb.append("</script>");
		sb.append("<table width=\"100%\"   id=\"" + name + "\" toolbar=\"#"
				+ name + "tb\"></table>");
		if (toolBarList.size() > 0) {

			sb.append("<div id=\"" + name + "tb\" style=\"padding:3px; \">");
			// update-begin--Author:zhaojunfu Date:20130419
			// for：生成组合查询，当没有查询字段时不生成查询框

			if (hasQueryColum(columnList)) {

				sb.append("<div name=\"searchColums\">");
				// 如果表单是组合查询
				if ("group".equals(queryMode)) {
//					int count = 0;
					for (DateGridColumn col : columnList) {
						if (col.isQuery()) {
							sb.append("<span style=\"display:-moz-inline-box;display:inline-block;\">");
							if ("noteq".equals(col.getQueryMode())) {
								sb.append("<span style=\"display:-moz-inline-box;display:inline-block;with: 80px;text-align:right;\">"
										+ col.getTitle() + " != </span>");
							} else {
								sb.append("<span style=\"display:-moz-inline-box;display:inline-block;with: 80px;text-align:right;\">"
										+ col.getTitle() + "：</span>");
							}
							if ("single".equals(col.getQueryMode())) {
								// update-begin--Author:zhaojunfu Date:20130510
								// for：【TASK #95】生成select选择框
								if (!StringUtils.isEmpty(col.getReplace())) {
									sb.append("<select name=\""
											+ col.getField()
											+ "\" WIDTH=\"100\" class=\""
											+ col.getQueryClass()
											+ "\" style=\"width: 104px\"> ");
									sb.append("<option value =\"\" >---请选择---</option>");
									String[] test = col.getReplace().split(",");
									String text = "";
									String value = "";
									for (String string : test) {
										text = string.split("_")[0];
										value = string.split("_")[1];
										sb.append("<option value =\"" + value
												+ "\">" + text + "</option>");
									}
									sb.append("</select>");
								} else {
									sb.append("<input type=\"text\" name=\""
											+ col.getField() + "\" class=\""
											+ col.getQueryClass()
											+ "\" style=\"width: 100px\"/>");
								}
								// update-begin--Author:zhaojunfu Date:20130510
								// for：【TASK #95】生成select选择框
							} else if ("group".equals(col.getQueryMode())) {
								sb.append("<input type=\"text\" name=\"conditionMap[\'"
										+ col.getField()
										+ "_begin']\" class=\""
										+ col.getQueryClass()
										+ "\"  style=\"width: 94px\"/>");
								sb.append("<span style=\"display:-moz-inline-box;display:inline-block;width: 8px;text-align:right;\">~</span>");
								sb.append("<input type=\"text\" name=\"conditionMap[\'"
										+ col.getField()
										+ "_end']\"  class=\""
										+ col.getQueryClass()
										+ "\" style=\"width: 94px\"/>");
							} else if ("glt".equals(col.getQueryMode())) {
								sb.append("<input type=\"text\" name=\"conditionMap[\'"
										+ col.getField()
										+ "_gt']\" class=\""
										+ col.getQueryClass()
										+ "\"  style=\"width: 94px\"/>");
								sb.append("<span style=\"display:-moz-inline-box;display:inline-block;width: 8px;text-align:right;\">~</span>");
								sb.append("<input type=\"text\" name=\"conditionMap[\'"
										+ col.getField()
										+ "_lt']\"  class=\""
										+ col.getQueryClass()
										+ "\" style=\"width: 94px\"/>");
							} else if ("gle".equals(col.getQueryMode())) {
								sb.append("<input type=\"text\" name=\"conditionMap[\'"
										+ col.getField()
										+ "_ge']\" class=\""
										+ col.getQueryClass()
										+ "\"  style=\"width: 94px\"/>");
								sb.append("<span style=\"display:-moz-inline-box;display:inline-block;width: 8px;text-align:right;\">~</span>");
								sb.append("<input type=\"text\" name=\"conditionMap[\'"
										+ col.getField()
										+ "_le']\"  class=\""
										+ col.getQueryClass()
										+ "\" style=\"width: 94px\"/>");
							} else if ("noteq".equals(col.getQueryMode())) {
								sb.append("<input type=\"text\" name=\"conditionMap[\'"
										+ col.getField()
										+ "_noteq']\" class=\""
										+ col.getQueryClass()
										+ "\"  style=\"width: 94px\"/>");
							}
							sb.append("</span>");
						}
					}

				}
				sb.append("</div>");
			}
			// update-begin--Author:zhaojunfu Date:20130526 for：131
			// IE兼容性问题一单查询的查询按钮无效
			sb.append("<div style=\"height:30px;\" class=\"datagrid-toolbar\">");
			// update-end--Author:zhaojunfu Date:20130526 for：131
			// IE兼容性问题一单查询的查询按钮无效
			sb.append("<span style=\"float:left;\" >");
			for (DateGridUrl toolBar : toolBarList) {
				sb.append("<a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" icon=\""
						+ toolBar.getIcon() + "\" ");
				if (StringUtils.isNotEmpty(toolBar.getOnclick())) {
					sb.append("onclick=" + toolBar.getOnclick() + "");
				} else {
					sb.append("onclick=\"" + toolBar.getFunname() + "(");
					if (!toolBar.getFunname().equals("doSubmit")) {
						sb.append("\'" + toolBar.getTitle() + "\',");
					}
					sb.append("\'" + toolBar.getUrl() + "\',\'" + name
							+ "\',"+toolBar.getWidth()+","+toolBar.getHeight()+")\"");
				}
				sb.append(">" + toolBar.getTitle() + "</a>");
			}
			sb.append("</span>");
			if ("group".equals(queryMode) && hasQueryColum(columnList)) {// 如果表单是组合查询
				sb.append("<span style=\"float:right\">");
				sb.append("<a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-search\" onclick=\""
						+ name + "search()\">查询</a>");
				sb.append("<a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-reload\" onclick=\"searchReset('"
						+ name + "')\">重置</a>");
				sb.append("</span>");
			} else if ("single".equals(queryMode) && hasQueryColum(columnList)) {// 如果表单是单查询
				sb.append("<span style=\"float:right\">");
				sb.append("<input id=\""
						+ name
						+ "searchbox\" class=\"easyui-searchbox\"  data-options=\"searcher:"
						+ name + "searchbox,prompt:\'请输入关键字\',menu:\'#" + name
						+ "mm\'\"></input>");
				sb.append("<div id=\"" + name + "mm\" style=\"width:120px\">");
				for (DateGridColumn col : columnList) {
					if (col.isQuery()) {
						sb.append("<div data-options=\"name:\'"
								+ col.getField() + "\',iconCls:\'icon-ok\'\">"
								+ col.getTitle() + "</div>  ");
					}
				}
				sb.append("</div>");
				sb.append("</span>");
			}
			sb.append("</div>");

			// sb.append("<div align=\"right\">");
			// sb.append("<input id=\""+name+"searchbox\" class=\"easyui-searchbox\"  data-options=\"searcher:"+name+"searchbox,prompt:\'请输入关键字\',menu:\'#"+name+"mm\'\"></input>");
			// sb.append("<div id=\""+name+"mm\" style=\"width:120px\">");
			// for (DateGridColumn col : columnList) {
			// if (col.isQuery()) {
			// sb.append("<div data-options=\"name:\'"+col.getField()+"\',iconCls:\'icon-ok\'\">"+col.getTitle()+"</div>  ");
			// }
			// }
			// sb.append("</div>");
			// sb.append("</div>");
			// update-end--Author:zhaojunfu Date:20130419
			// for：生成组合查询，当没有查询字段时不生成查询框
			sb.append("</div>");
		}
		SystemUtils.dealReturn(sb);
		return sb;
	}

	public static boolean hasQueryColum(List<DateGridColumn> columnList) {
		return hasQueryColumTag(columnList);
	}
	
	private static boolean hasQueryColumTag(List<DateGridColumn> columnList) {
		boolean hasQuery = false;
		for (DateGridColumn col : columnList) {
			if (col.isQuery()) {
				hasQuery = true;
			}
		}
		return hasQuery;
	}
}
