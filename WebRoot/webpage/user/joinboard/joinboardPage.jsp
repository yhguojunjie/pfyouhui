<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>Joinboard</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="joinboardController.do?saveJoinboard">
		<input id="id" name="id" type="hidden" value="${joinboardPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">联系人:</label>
		      <input class="inputxt" id="contact" name="contact" ignore="false" datatype="*0-50"
					 value="${joinboardPage.contact}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">联系人手机:</label>
		      <input class="inputxt" id="mobile" name="mobile" ignore="false" datatype="*0-15"
					 value="${joinboardPage.mobile}">
		      <span class="Validform_checktip"></span>
		    </div>
		    <div class="form">
		      <label class="Validform_label">QQ:</label>
		      <input class="inputxt" id="qq" name="qq" ignore="false" datatype="*0-15"
					 value="${joinboardPage.qq}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">联系人email:</label>
		      <input class="inputxt" id="email" name="email" ignore="false" datatype="*0-40"
					 value="${joinboardPage.email}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">备注:</label>
		      <textarea rows="6" class="inputxt" style="width:80%" ignore="false" datatype="*0-500" name="remark" id="remark">${fn:replace(joinboardPage.remark,"<br/>",vEnter)}</textarea>
		      <span class="Validform_checktip"></span>
		    </div>
	   </fieldset>
  </t:formvalid>
 </body>