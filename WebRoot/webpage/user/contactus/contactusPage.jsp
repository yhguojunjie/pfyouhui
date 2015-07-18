<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>Contactus</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="contactusController.do?saveContactus">
		<input id="id" name="id" type="hidden" value="${contactusPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">客服电话:</label>
		      <input class="inputxt" id="servicePhone" name="servicePhone" ignore="false" datatype="*"  maxlength="14"
					 value="${contactusPage.servicePhone}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">粉丝QQ群:</label>
		      <input class="inputxt" id="qqGroup" name="qqGroup" ignore="false" datatype="*" maxlength="12"
					 value="${contactusPage.qqGroup}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">客服QQ:</label>
		      <input class="inputxt" id="serviceqq" name="serviceqq" ignore="false" datatype="*" maxlength="12"
					 value="${contactusPage.serviceqq}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">邮箱:</label>
		      <input class="inputxt" id="email" name="email" ignore="false" datatype="e" maxlength="20"
					 value="${contactusPage.email}">
		      <span class="Validform_checktip"></span>
		    </div>
	   </fieldset>
  </t:formvalid>
 </body>