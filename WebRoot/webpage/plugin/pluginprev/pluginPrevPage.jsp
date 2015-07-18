<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>PluginPrev</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="pluginPrevController.do?savePluginPrev">
		<input id="id" name="id" type="hidden" value="${pluginPrevPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">模板名称:</label>
		      <input class="inputxt" id="name" name="name" ignore="false" datatype="s2-50"
					 value="${pluginPrevPage.name}">
		      <span class="Validform_checktip">模板50字符以内</span>
		    </div>
		    <div class="form">
		      <label class="Validform_label">简介:</label>
		      <textarea rows="6" class="inputxt" style="width:80%" ignore="false" datatype="*0-200" name="description" id="description">${fn:replace(pluginPrevPage.description,"<br/>",vEnter)}</textarea>
		      <span class="Validform_checktip">简介200字符以内</span>
		    </div>
			<div class="form">
		      <label class="Validform_label">预计上架时间:</label>
		      <input class="Wdate" onClick="WdatePicker()"  style="width: 150px" id="shelTime" name="shelTime" ignore="false" datatype="*"
					 value="<fmt:formatDate value='${pluginPrevPage.shelTime}' type="date" pattern="yyyy-MM-dd"/>">
		      <span class="Validform_checktip">必填</span>
		    </div>
			<div class="form">
		      <label class="Validform_label">顺序:</label>
		      <input class="inputxt" id="seq" name="seq" ignore="false" 
					 value="${pluginPrevPage.seq}" datatype="n">
		      <span class="Validform_checktip">填写整数</span>
		    </div>
	   </fieldset>
  </t:formvalid>
 </body>