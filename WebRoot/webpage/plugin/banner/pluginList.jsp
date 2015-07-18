<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
 <head>
  <title>模版</title>
  <t:base type="jquery,easyui,tools"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:datagrid name="pluginList" title="模版列表" actionUrl="bannerController.do?pluginGrid" idField="objId" checkbox="false" showRefresh="false">
   <t:dgCol title="模版id" field="objId"  ></t:dgCol>
   <t:dgCol title="模版名称" field="name"></t:dgCol>
  </t:datagrid>
<div id="pluginListtb" style="padding: 3px; height: 25px">
	 <div align="right">
	         模版名称:
	    <input type="text" class="searchtext" name="name" id="name" style="width: 80px"/>
	    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="pluginListsearch()">查询</a>
	 </div>
</div>
 </body>
</html>