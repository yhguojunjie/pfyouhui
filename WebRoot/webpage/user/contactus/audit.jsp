<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>Contactus</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden;overflow-x: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="contactusController.do?auditStateSave" >
		<input id="id" name="id" type="hidden" value="${contactusPage.id }">
		<fieldset class="step" style="width: 290px">
			<div class="form">
		      <label class="Validform_label">审核状态:</label>
		    	<input type="radio" name="auditState" value="1" checked="checked">通过
	     		<input type="radio" name="auditState" value="2">不通过
		    </div>
	   </fieldset>
  </t:formvalid>
 </body>