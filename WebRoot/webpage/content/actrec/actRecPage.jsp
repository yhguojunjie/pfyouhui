<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>ActRec</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="actRecController.do?saveActRec">
		<input id="id" name="id" type="hidden" value="${actRecPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">排序:</label>
		      <input class="inputxt" id="seq" name="seq" ignore="ignore"
					 value="${actRecPage.seq}" datatype="n">
		      <span class="Validform_checktip"></span>
		    </div>
		    <div class="form">
		       <label class="Validform_label">活动名称:</label>
		       <input name="actId" type="hidden" value="" id="actId">
	           <input name="title" class="inputxt" id="title" readonly="readonly" datatype="*" errormsg="请选择活动" />
	           <t:choose hiddenName="actId" hiddenid="id" url="actRecController.do?pluginActRecList" name="pluginActRecList" icon="icon-choose" title="活动列表" textname="title" isclear="true"></t:choose>
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">状态 :</label>
				<input type="radio" name="hideState" value="0" checked="checked" >显示
		     	<input type="radio" name="hideState" value="1"  >隐藏
		      <span class="Validform_checktip"></span>
		    </div>
	   </fieldset>
  </t:formvalid>
 </body>