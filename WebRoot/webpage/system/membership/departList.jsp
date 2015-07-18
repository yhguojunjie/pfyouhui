<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
	<head>
	<title>部门集合</title>
	<t:base type="jquery,easyui,tools"></t:base>
	</head> 
	<body>	
	<t:datagrid  name="departList" title="按部门选择 " actionUrl="systemController.do?datagridDepart" showRefresh="true" idField="id">
	<t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
	<t:dgCol title="部门名称" field="departname" width="100"></t:dgCol>	
	</t:datagrid>			
	</body>
</html>
