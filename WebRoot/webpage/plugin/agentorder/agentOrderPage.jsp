<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>AgentOrder</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
  <body style="overflow-x: hidden" >
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="agentOrderController.do?saveAgentOrder">
		<input id="id" name="id" type="hidden" value="${agentOrderPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">插件名称:</label>
		      	${agentOrderPage.plugin.name }
		    </div>   
		    <c:if test="${not empty agentOrderPage.salePrice}">
			   <div class="form">
			      <label class="Validform_label">单价:</label>
			      ${salePrice}
			    </div>    
		    </c:if>
		    <div class="form">
		      <label class="Validform_label">数量:</label>
		      ${agentOrderPage.buyNum}
		    </div>
		     <c:if test="${not empty agentOrderPage.salePrice}">
			    <div class="form">
			      <label class="Validform_label">应付:</label>
			      ${price }
			    </div>
		    </c:if>
		    <div class="form">
		      <label class="Validform_label">成本价:</label>
		     ${costPrice}
		    </div>
			<div class="form">
		      <label class="Validform_label">实付:</label>
		      <input class="inputxt" id="charge" name="charge" ignore="false" value="" datatype="n"  >
		      <span class="Validform_checktip">大于等于0,为0时客户无需支付，交易直接成功</span>
		    </div>
	   </fieldset>
  </t:formvalid>
 </body>