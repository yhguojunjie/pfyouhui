<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
 <head>
  <title>活动</title>
  <t:base type="jquery,easyui,tools"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:datagrid name="pluginActRecList" title="活动列表" actionUrl="actRecController.do?pluginActListGrid" idField="id" checkbox="false" showRefresh="false">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="活动名称" field="title" ></t:dgCol>
   <t:dgCol title="插件名称" field="pluginName"></t:dgCol>
  </t:datagrid>
<div id="pluginActRecListtb" style="padding: 3px; height: 25px">
	 <div align="right">
	         活动名称:
	    <input type="text" class="searchtext" name="title" id="title" style="width: 80px"/>
	    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="pluginActRecListsearch()">查询</a>
	 </div>
</div>
 </body>
</html>
