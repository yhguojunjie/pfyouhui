<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<html>
 <head>
  <t:base type="jquery,easyui,tools"></t:base>
 </head>
 <body >
<t:tabs id="typeGroupTabs" iframe="false" tabPosition="bottom">
 <c:forEach items="${typegroupList}" var="typegroup">
  <t:tab iframe="typeController.do?type&typegroupid=${typegroup.id}" icon="icon-add" title="${typegroup.typeName}" id="${typegroup.id}"></t:tab>
 </c:forEach>
</t:tabs>
</body>
</html>