<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>AgentWithdraw</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="agentWithdrawController.do?saveAgentWithdraw">
		<input id="id" name="id" type="hidden" value="${agentWithdrawPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">提现金额:</label>
		      <input class="inputxt" id="cash" name="cash" ignore="ignore"
					 value="${agentWithdrawPage.cash}" datatype="d">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">0 支付宝，1银行账号:</label>
		      <input class="inputxt" id="cashType" name="cashType" ignore="ignore"
					 value="${agentWithdrawPage.cashType}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">申请人:</label>
		      <input class="inputxt" id="applyUserId" name="applyUserId" ignore="ignore"
					 value="${agentWithdrawPage.applyUserId}" datatype="n">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">申请时间:</label>
		      <input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px"  id="applyTime" name="applyTime" ignore="ignore"
					   value="<fmt:formatDate value='${agentWithdrawPage.applyTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">审核人:</label>
		      <input class="inputxt" id="auditUserId" name="auditUserId" ignore="ignore"
					 value="${agentWithdrawPage.auditUserId}" datatype="n">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">审核状态(0未处理，1处理):</label>
		      <input class="inputxt" id="auditState" name="auditState" ignore="ignore"
					 value="${agentWithdrawPage.auditState}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">审核时间:</label>
		      <input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px"  id="auditTime" name="auditTime" ignore="ignore"
					   value="<fmt:formatDate value='${agentWithdrawPage.auditTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
		      <span class="Validform_checktip"></span>
		    </div>
	   </fieldset>
  </t:formvalid>
 </body>