<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>ClientVersion</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: auto;" >
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="tSClientVersionController.do?saveTSClientVersion">
		<input id="id" name="id" type="hidden" value="${tSClientVersionPage.id }">
			<div class="form">
		      <label class="Validform_label">版本编码:</label>
		      <input class="inputxt" id="versionCode" name="versionCode" ignore="ignore"
					 value="${tSClientVersionPage.versionCode}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">版本号:</label>
		      <input class="inputxt" id="versionNum" name="versionNum" ignore="ignore"
					 value="${tSClientVersionPage.versionNum}" datatype="n">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">版本说明:</label>
		      <% request.setAttribute("vEnter", "\r\n"); //换行在el中不能用常量表达    %>      
           		<textarea rows="4" class="inputxt" style="width:80%" id=versionMemo name="versionMemo"  datatype="s0-1000">${fn:replace(tSClientVersionPage.versionMemo,"<br/>",vEnter)}</textarea>	
				<span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">是否强制升级:</label>
		      <input class="inputxt" id="isForce" name="isForce" ignore="ignore"
					 value="${tSClientVersionPage.isForce}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">强制版本范围:</label>
		      <input class="inputxt" id="forceVersionArrang" name="forceVersionArrang" ignore="ignore"
					 value="${tSClientVersionPage.forceVersionArrang}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">强制升级代码:</label>
		      <input class="inputxt" id="forceVersionCode" name="forceVersionCode" ignore="ignore"
					 value="${tSClientVersionPage.forceVersionCode}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">发布状态:</label>
		      <input class="inputxt" id="publishStatus" name="publishStatus" ignore="ignore"
					 value="${tSClientVersionPage.publishStatus}">
		      <span class="Validform_checktip"></span>
		    </div>
  </t:formvalid>
 </body>