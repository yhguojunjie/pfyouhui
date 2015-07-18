<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>ActClassic</title>
 <t:base type="jquery,easyui,tools,DatePicker"></t:base>
<link rel="stylesheet" href="plug-in/imageZoom/jquery.imageZoom.css"
	type="text/css" />
<script type="text/javascript"
	src="plug-in/imageZoom/jquery.imageZoom.js"></script>
 </head>
 <body style="overflow-x: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="agentClassicController.do?saveAuditAgentAct">
        <input id="id" name="id" type="hidden" value="${actClassicPage.id }">
		<fieldset class="step">
		    <div class="form">
		      <label class="Validform_label">活动名称:</label>
		      ${actClassicPage.pluginAct.title }
		    </div>
			<div class="form">
		     	<label class="Validform_label">品牌logo:</label>
		     	<div class="aa">
		     	<a href="${server }/${actClassicPage.bannerLogo}">
		     	<img alt="" src="${server }/${actClassicPage.bannerLogo}" width="90px;" height="90px;">
		     	</a>
				</div>
		    </div>
		    <div class="form">
		      <label class="Validform_label">品牌名称:</label>
		      ${actClassicPage.bannerName }
		    </div>
		    <div class="form">
		     <label class="Validform_label">预览图:</label>点击图片查看原图
		    </div>
		    <div class="form">
		    <c:forEach  var="pic" items="${actClassicPage.actClassicPic}" varStatus="status">
		     	 <div style="float: left;text-align: center;padding:5px;" class="aa">
			     <a href="${server }/${pic.url }"><img alt="点击图片查看原图" style="width:100px;height:100px;background-color:#ccc;border:1px solid #333" src="${server }/${pic.url }"/></a><br>
		     </div>
		    </c:forEach>
		    </div>
		    <div class="form">
		      <label class="Validform_label">审核状态:</label>
		      <input type="radio" name="auditState" value="1" <c:if test="${actClassicPage.auditState == 1 }">checked="checked"</c:if> />通过&nbsp;&nbsp;&nbsp;&nbsp;
		      <input type="radio" name="auditState" value="2" <c:if test="${actClassicPage.auditState == 2 }">checked="checked"</c:if> />不通过
		    </div>
	   </fieldset>
  </t:formvalid>
 </body>
  <script type="text/javascript">
 $(".aa a").imageZoom();
 </script>