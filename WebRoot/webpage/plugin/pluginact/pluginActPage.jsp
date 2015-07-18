<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>PluginAct</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
  <body style="overflow: hidden" >
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="pluginActController.do?savePluginAct">
		<input id="id" name="id" type="hidden" value="${pluginActPage.id }">
		<fieldset class="step" style="width: 340px;">
		   <div class="form">
		       <label class="Validform_label">活动名称:</label>
		       ${pluginActPage.title}
		    </div>
			<div class="form">
		      <label class="Validform_label">状态:</label>
		      <input type="radio" name="actOpen" value="0" <c:if test="${pluginActPage.actOpen==0}">checked</c:if> >公开
	          <input type="radio" name="actOpen" value="1" <c:if test="${pluginActPage.actOpen==1}">checked</c:if> >不公开
		    </div>
		    <div class="form">
		      <label class="Validform_label">浏览量:</label>
		      <input class="inputxt" id="browseNum" name="browseNum" ignore="false" value="${pluginActPage.browseNum }" datatype="n" style="width: 80px">
		      <span class="Validform_checktip">必填数字</span>
		    </div>
		    <!-- 
			<div class="form">
		      <label class="Validform_label">案例:</label>
		      <input type="radio" name="actCase" value="0" <c:if test="${actClassicNum==0}">checked</c:if> >否&nbsp;&nbsp;&nbsp;&nbsp;
	          <input type="radio" name="actCase" value="1" <c:if test="${actClassicNum==1}">checked</c:if> >是
		    </div>
		     -->
	   </fieldset>
  </t:formvalid>
 </body>