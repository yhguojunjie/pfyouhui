<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
	<head>
		<title>角色集合</title>
		<t:base type="jquery,easyui,tools"></t:base>
	</head>
	<body style="overflow-y: hidden" scroll="no">		
				<t:datagrid name="roleList" title="按角色选择"
					actionUrl="membershipController.do?datagridRole" idField="roleid" checkbox="true" showRefresh="false" fit="true">
					<t:dgCol title="编号" field="roleid" ></t:dgCol>
					<t:dgCol title="角色名称" field="rolename" width="50"></t:dgCol>								
				</t:datagrid>							
	</body>
</html>
