package com.yoxi.jgframework.util;

import java.util.List;

import com.yoxi.jgframework.system.entity.TSFunction;



/**
 * 动态菜单栏生成
 * 
 * @author Administrator
 * 
 */
public class ListtoMenu {
	/**
	 * 拼装easyui菜单JSON方式
	 * @param set
	 * @param set1
	 * @return
	 */
	public static String getMenu(List<TSFunction> set, List<TSFunction> set1) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{\"menus\":[");
		for (TSFunction node : set) {
			String iconClas = "default";//权限图标样式
			if (node.getTSIcon() != null) {
				iconClas = node.getTSIcon().getIconClas();
			}
			buffer.append("{\"menuid\":\"" + node.getId()
					+ "\",\"icon\":\"" + iconClas + "\","
					+ "\"menuname\":\"" + node.getFunctionName()
					+ "\",\"menus\":[");
			iterGet(set1, node.getId(), buffer);
			buffer.append("]},");
		}
		buffer.append("]}");

		// 将,\n]替换成\n]

		String tmp = buffer.toString();

		tmp = tmp.replaceAll(",\n]", "\n]");
		tmp = tmp.replaceAll(",]}", "]}");
		return tmp;

	}

	static int count = 0;

	/** 
	 * @param args
	 */

	static void iterGet(List<TSFunction> set1, Integer pid, StringBuffer buffer) {

		for (TSFunction node : set1) {

			// 查找所有父节点为pid的所有对象，然后拼接为json格式的数据
			count++;
			if (node.getTSFunction().getId().equals(pid))

			{
				buffer.append("{\"menuid\":\"" + node.getId()
						+ " \",\"icon\":\"" + node.getTSIcon().getIconClas()
						+ "\"," + "\"menuname\":\"" + node.getFunctionName()
						+ "\",\"url\":\"" + node.getFunctionUrl() + "\"");
				if (count == set1.size()) {
					buffer.append("}\n");
				}
				buffer.append("},\n");

			}
		}

	}
	/**
	 * 拼装Bootstarp菜单
	 * @param pFunctions
	 * @param functions
	 * @return
	 */
	public static String getBootMenu(List<TSFunction> pFunctions, List<TSFunction> functions) {
		StringBuffer menuString=new StringBuffer();
		menuString.append("<ul>");
		for (TSFunction pFunction : pFunctions) {
			menuString.append("<li><a href=\"#\"><span class=\"icon16 icomoon-icon-stats-up\"></span><b>"+pFunction.getFunctionName()+"</b></a>");
			int submenusize=pFunction.getTSFunctions().size();
			if(submenusize==0)
			{
				menuString.append("</li>");
			}
			if(submenusize>0)
			{
				menuString.append("<ul class=\"sub\">");
			}
			for (TSFunction function : functions) {
				
				if(function.getTSFunction().getId().equals(pFunction.getId()))
				{
					menuString.append("<li><a href=\""+function.getFunctionUrl()+"\" target=\"contentiframe\"><span class=\"icon16 icomoon-icon-file\"></span>"+function.getFunctionName()+"</a></li>");
				}
			}
			if(submenusize>0)
			{
				menuString.append("</ul></li>");
			}
		}
		menuString.append("</ul>");
		return menuString.toString();
		
	}
	/**
	 * 拼装EASYUI菜单
	 * @param pFunctions
	 * @param functions
	 * @return
	 */
	public static String getEasyuiMenu(List<TSFunction> pFunctions, List<TSFunction> functions) {
		StringBuffer menuString=new StringBuffer();
		for (TSFunction pFunction : pFunctions) {
			menuString.append("<div  title=\""+pFunction.getFunctionName()+"\" iconCls=\""+pFunction.getTSIcon().getIconClas()+"\">");
			int submenusize=pFunction.getTSFunctions().size();
			if(submenusize==0)
			{
				menuString.append("</div>");
			}
			if(submenusize>0)
			{
				menuString.append("<ul>");
			}
			for (TSFunction function : functions) {
				
				if(function!=null && function.getTSFunction()!=null &&
						function.getTSFunction().getId() != null &&
						function.getTSFunction().getId().equals(pFunction.getId()))
				{
					String icon="folder";
					if(function.getTSIcon()!=null)
					{
						icon=function.getTSIcon().getIconClas();
					}
					//menuString.append("<li><div> <a class=\""+function.getFunctionName()+"\" iconCls=\""+icon+"\" target=\"tabiframe\"  href=\""+function.getFunctionUrl()+"\"> <span class=\"icon "+icon+"\" >&nbsp;</span> <span class=\"nav\">"+function.getFunctionName()+"</span></a></div></li>");
					//menuString.append("<li><div onclick=\"addTab(\'"+function.getFunctionName()+"\',\'"+function.getFunctionUrl()+"\',\'"+icon+"\')\"  title=\""+function.getFunctionName()+"\" url=\""+function.getFunctionUrl()+"\" iconCls=\""+icon+"\"> <a class=\""+function.getFunctionName()+"\" href=\"#\" > <span class=\"icon "+icon+"\" >&nbsp;</span> <span class=\"nav\" >"+function.getFunctionName()+"</span></a></div></li>");
					menuString.append("<li><div onclick=\"addTab(\'"+function.getFunctionName()+"\',\'"+function.getFunctionUrl()+"&clickFunctionId="+function.getId()+"\',\'"+icon+"\')\"  title=\""+function.getFunctionName()+"\" url=\""+function.getFunctionUrl()+"\" iconCls=\""+icon+"\"> <a class=\""+function.getFunctionName()+"\" href=\"#\" > <span class=\"icon "+icon+"\" >&nbsp;</span> <span class=\"nav\" >"+function.getFunctionName()+"</span></a></div></li>");
				}
			}
			if(submenusize>0)
			{
				menuString.append("</ul></div>");
			}
		}
		return menuString.toString();
		
	}

}
