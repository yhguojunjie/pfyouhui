<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>ActClassic</title>
 <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 <link rel="stylesheet" href="plug-in/imageZoom/jquery.imageZoom.css" type="text/css" />
 <script type="text/javascript" src="plug-in/imageZoom/jquery.imageZoom.js"></script>
 </head>
 <body style="overflow: hidden">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="agentClassicController.do?saveAgentClassic">
		<input id="id" name="id" type="hidden" value="${agentClassicPage.id }">
		<fieldset class="step" style="width: 340px">
			<div class="form">
		      <label class="Validform_label">排序:</label>
		      <input class="inputxt" id="seq" name="seq" ignore="false" value="${agentClassicPage.seq }" datatype="n" style="width: 80px">
		      <span class="Validform_checktip">值越小越靠前</span>
		    </div>
		    <div class="form">
		      <label class="Validform_label">状态:</label>
		      <input type="radio" name="hideState" value="0" <c:if test="${agentClassicPage.hideState == '0' }">checked="checked"</c:if> >显示&nbsp;&nbsp;&nbsp;
		      <input type="radio" name="hideState" value="1" <c:if test="${agentClassicPage.hideState == '1' }">checked="checked"</c:if> >隐藏
		    </div>
	   </fieldset>
  </t:formvalid>
 </body>