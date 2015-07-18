<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>NoticeVersion</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="noticeVersionController.do?saveNoticeVersion">
		<input id="id" name="id" type="hidden" value="${noticeVersionPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">标题:</label>
		      <input class="inputxt" id="title" name="title"  style="width: 300px;"
					 value="${noticeVersionPage.title}" datatype="*0-100">
		      <span class="Validform_checktip">填写100字符以内</span>
		    </div>
			<div class="form">
		      <label class="Validform_label">链接地址:</label>
		      <input class="inputxt" id="titleLink" name="titleLink" style="width: 300px;"
					 value="${noticeVersionPage.titleLink}" datatype="*0-100">
		      <span class="Validform_checktip">如:http://www.hudongtui.com</span>
		    </div>
			<div class="form">
		      <label class="Validform_label">顺序:</label>
		      <input class="inputxt" id="seq" name="seq" 
					 value="${noticeVersionPage.seq}" datatype="n">
		      <span class="Validform_checktip"></span>
		    </div>
			
	   </fieldset>
  </t:formvalid>
 </body>