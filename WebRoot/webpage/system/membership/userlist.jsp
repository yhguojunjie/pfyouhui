<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
 <head>
  <t:base type="jquery,easyui,tools"></t:base>
  <title>用户集合</title>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:datagrid name="userList" title="用户管理" actionUrl="membershipController.do?datagridUser&roleid=${roleid }&departid=${departid}" idField="userid">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="用户名" field="username" width="100"></t:dgCol>
  </t:datagrid>
 </body>
</html>
