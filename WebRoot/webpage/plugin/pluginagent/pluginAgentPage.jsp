<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>PluginAgent</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
  <body style="overflow-x: hidden" >
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="pluginAgentController.do?savePluginAgent">
		<input id="id" name="id" type="hidden" value="${pluginAgentPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">插件id:</label>
		      <input class="inputxt" id="pluginId" name="pluginId" ignore="ignore"
					 value="${pluginAgentPage.pluginId}" datatype="n">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">代理商id:</label>
		      <input class="inputxt" id="agentId" name="agentId" ignore="ignore"
					 value="${pluginAgentPage.agentId}" datatype="n">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">销售价格:</label>
		      <input class="inputxt" id="salePrice" name="salePrice" ignore="ignore"
					 value="${pluginAgentPage.salePrice}" datatype="d">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">0 上架，1下架:</label>
		      <input class="inputxt" id="onlineState" name="onlineState" ignore="ignore"
					 value="${pluginAgentPage.onlineState}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">创建时间:</label>
		      <input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px"  id="createTime" name="createTime" ignore="ignore"
					   value="<fmt:formatDate value='${pluginAgentPage.createTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">修改时间:</label>
		      <input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px"  id="updateTime" name="updateTime" ignore="ignore"
					   value="<fmt:formatDate value='${pluginAgentPage.updateTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">0 未删除，1已删除:</label>
		      <input class="inputxt" id="delState" name="delState" ignore="ignore"
					 value="${pluginAgentPage.delState}">
		      <span class="Validform_checktip"></span>
		    </div>
	   </fieldset>
  </t:formvalid>
 </body>