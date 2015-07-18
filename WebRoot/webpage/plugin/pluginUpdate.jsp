<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>Plugin</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-x: hidden" >
  <input type="hidden" id="fileServer" value="${server }" />
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="templateController.do?savePlugin" >
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">插件名称:</label>
		      ${pluginPage.name }
		    </div>
		    <div class="form">
				<label class="Validform_label">插件图标:</label>
				<img alt="" src="${server}/${ pluginPage.icon}">
		    </div>
			<div class="form">
		      <label class="Validform_label">成本价:</label>
		    	  ￥${pluginPage.price }/月
		    </div>
		    <div class="form">
		      <label class="Validform_label">销售价:</label>
		      <input class="inputxt" id="description" name="description" ignore="ignore" value="" width="50px" >/月
		      <span class="Validform_checktip"></span>
		    </div>
		    <div class="form">
		      <label class="Validform_label">状态:</label>
		      <input type="radio" name="sex" value="1" <c:if test="${pluginPage.status==0}">checked</c:if> >下架	
	      	  <input type="radio" name="sex" value="2" <c:if test="${pluginPage.status==1}">checked</c:if> >上架
		      <span class="Validform_checktip"></span>
		    </div>
	   </fieldset>
  </t:formvalid>
 </body>