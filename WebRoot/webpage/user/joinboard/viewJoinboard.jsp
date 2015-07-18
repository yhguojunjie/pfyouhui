<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>Joinboard</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" >
		<input id="id" name="id" type="hidden" value="${joinboardPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">联系人:</label>${joinboardPage.contact}
		    </div>
			<div class="form">
		      <label class="Validform_label">联系人手机:</label>${joinboardPage.mobile}
		    </div>
		    <div class="form">
		      <label class="Validform_label">QQ:</label>${joinboardPage.qq}
		    </div>
			<div class="form">
		      <label class="Validform_label">联系人email:</label>${joinboardPage.email}
		    </div>
			<div class="form">
		      <label class="Validform_label">备注:</label>
		      <textarea rows="6" class="inputxt" style="width:80%"  name="remark" id="remark">${fn:replace(joinboardPage.remark,"<br/>",vEnter)}</textarea>
		      <span class="Validform_checktip"></span>
		    </div>
	   </fieldset>
  </t:formvalid>
 </body>