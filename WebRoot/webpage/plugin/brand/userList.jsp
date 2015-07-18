<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
 <head>
  <title>用户</title>
  <t:base type="jquery,easyui,tools"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:datagrid name="userList" title="用户列表" actionUrl="brandController.do?userGrid" idField="id" checkbox="false" showRefresh="false">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="用户id" field="userId" ></t:dgCol>
   <t:dgCol title="用户昵称" field="nickName"></t:dgCol>
  </t:datagrid>
<div id="userListtb" style="padding: 3px; height: 25px">
	 <div align="right">
	         用户昵称:
	    <input type="text" class="searchtext" name="nickName" id="nickName" style="width: 80px"/>
	    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="userListsearch()">查询</a>
	 </div>
</div>
 </body>
</html>
