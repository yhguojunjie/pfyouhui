<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>AgentChannel</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <link rel="stylesheet" href="plug-in/imageZoom/jquery.imageZoom.css"
	type="text/css" />
<script type="text/javascript"
	src="plug-in/imageZoom/jquery.imageZoom.js"></script>
 </head>
 <body style="overflow-x: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="agentChannelController.do?saveAgentChannel">
		<input id="id" name="id" type="hidden" value="${agentChannelPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">顺序:</label>
		      <input class="inputxt" id="seq" name="seq" ignore="false"
					 value="${agentChannelPage.seq}" datatype="n">
		      <span class="Validform_checktip">值越小越靠前</span>
		    </div>
		    <div class="form">
		      <label class="Validform_label">渠道名称:</label>
				${agentChannelPage.channel.name }
		    </div>
		    <div class="form">
		      <label class="Validform_label">渠道logo:</label>
		        <a href="${server }/${agentChannelPage.channel.logo }">
		        <img alt="" src="${server }/${agentChannelPage.channel.logo }" width="90px" height="90px"/>
		        </a>
		        <span class="Validform_checktip">点击图片查看原图</span>
		    </div>
			<div class="form">
		      <label class="Validform_label">状态:</label>
		      <input type="radio" name="hideState" value="0" <c:if test="${agentChannelPage.hideState == '0' }">checked="checked"</c:if> > 显示&nbsp;&nbsp;&nbsp;
		      <input type="radio" name="hideState" value="1" <c:if test="${agentChannelPage.hideState == '1' }">checked="checked"</c:if> > 隐藏
		    </div>
			
			
	   </fieldset>
  </t:formvalid>
 </body>
 <script type="text/javascript">
 $("a").imageZoom();
 </script>