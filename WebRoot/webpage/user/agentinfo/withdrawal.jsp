<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>AgentInfo</title>
    <t:base type="jquery,easyui,tools,DatePicker"></t:base>
<link rel="stylesheet" href="plug-in/imageZoom/jquery.imageZoom.css"
	type="text/css" />
<script type="text/javascript"
	src="plug-in/imageZoom/jquery.imageZoom.js"></script>
  <script type="text/javascript"
	src="plug-in/province/province.js"></script>
	<script type="text/javascript" src="plug-in/province/bankFormat.js"></script>
 </head>
 <body style="overflow-y:auto;overflow-x:hidden;">
  <t:formvalid formid="formobj" dialog="false" usePlugin="password" layout="div" btnsub="btnsub" beforeSubmit="submitted();" callback="toSuccess(data);" action="agentWithdrawController.do?saveAgentWithdraw">
		<input id="applyUserId" name="applyUserId" type="hidden" value="${agentInfo.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">累计收益:</label>     
		      ${totalIncome }
		    </div>
			<div class="form">
		      <label class="Validform_label">累计提现:</label>     
		      ${totalCash}
		    </div>
		    <div class="form">
		      <label class="Validform_label">可提现金额:</label>     
		      ${availableCash}
		    </div>
		    <div class="form">
		      <label class="Validform_label">选择类型:</label>   
		        <input type="radio" id="1" name="cashType" value="1" checked="checked" onclick="showPay(1);">储蓄卡
	     		<input type="radio" id="2" name="cashType" value="0" onclick="showPay(2);">支付宝
		    </div>
		   <div id="bankDiv">
			    <div class="form">
			      <label class="Validform_label">账户类型:</label>  
			          <c:if test="${agentInfo.bankType ==1 }">中国光大银行</c:if>
			          <c:if test="${agentInfo.bankType ==2 }">广东发展银行</c:if>
			          <c:if test="${agentInfo.bankType ==3 }">中国工商银行</c:if>
			          <c:if test="${agentInfo.bankType ==4 }">广州市商业银行</c:if>
			          <c:if test="${agentInfo.bankType ==5 }">华夏银行</c:if>
			          <c:if test="${agentInfo.bankType ==6 }">中国建设银行</c:if>
			          <c:if test="${agentInfo.bankType ==7 }">交通银行</c:if>
			          <c:if test="${agentInfo.bankType ==8 }">中国民生银行</c:if>
			          <c:if test="${agentInfo.bankType ==9 }">农村信用社</c:if>
			          <c:if test="${agentInfo.bankType ==10 }">中国农业银行</c:if>
			          <c:if test="${agentInfo.bankType ==11 }">上海浦东发展银行</c:if>
			          <c:if test="${agentInfo.bankType ==12 }">上海银行</c:if>
			          <c:if test="${agentInfo.bankType ==13 }">深圳市商业银行</c:if>
			          <c:if test="${agentInfo.bankType ==14 }">福建兴业银行</c:if>
			          <c:if test="${agentInfo.bankType ==15 }">中国邮政</c:if>
			          <c:if test="${agentInfo.bankType ==16 }">中国银行</c:if>
			          <c:if test="${agentInfo.bankType ==17 }">招商银行</c:if>
			          <c:if test="${agentInfo.bankType ==18 }">中信实业银行</c:if>		
			         
			    </div>
			    <div class="form">
			      <label class="Validform_label">提现账号:</label> 
			      ${agentInfo.bankAccount }    
			       <a href="#" onclick="editPayAcount(1);"><font color="blue">修改</font></a>    
			    </div>
		    </div>
		    <div id="payDiv" style="display: none;">
			    <div class="form">
				      <label class="Validform_label">账户类型:</label> 
				    	 支付宝 
				 </div>
				  <div class="form">
				      <label class="Validform_label">提现账号:</label> 
				      ${agentInfo.alipay }    
				       <a href="#" onclick="editPayAcount(0);"><font color="blue">修改</font></a>
				 </div>
			 </div>
		    <div class="form">
		      <label class="Validform_label">提现金额:</label>   
		      <input class="inputxt" id="cash" name="cash" ignore="false" datatype="n" max=" ${availableCash }  "  value="">
		      <span class="Validform_checktip"></span>  
		    </div>
		    <div class="form">
		      <label class="Validform_label">&nbsp;&nbsp;&nbsp;&nbsp;</label>    
		      <input type="button" class="btnsub" value="提交" id="btnsub" style="width: 100px;height: 40px;"/> 
		    </div>
	   </fieldset>
  </t:formvalid>
  <script type="text/javascript">
	function editPayAcount(type){
		if(type == 1)
		   createwindow("编辑贮蓄卡账号","agentInfoController.do?editPayAcount&type="+type);
		else 
		   createwindow("编辑支付账号","agentInfoController.do?editPayAcount&type="+type,"410px","60px");
	}
  function toSuccess(data){
	  if(data.success){
		  $.dialog({id:"button_1",title:"提示",content:data.msg,icon:"/success.gif"});
	  }else{
		  $.dialog({id:"button_2",title:"提示",content:data.msg,icon:"/error.gif"});
	  }
	   $("#btnsub").val("提交");
	   $("#btnsub").attr("disabled",false);
	   $("#cash").attr("readonly",false);
  }
  /**正在提交**/
  function submitted(){
	  $("#btnsub").val("正在提交申请...");
	  $("#btnsub").attr("disabled",true);
	  $("#cash").attr("readonly",true);
  }
   function showPay(n) {
	  if(n == 1){
		  $("#bankDiv").show();
		  $("#payDiv").hide();
	  }
	  if(n == 2){
		  $("#bankDiv").hide();
		  $("#payDiv").show();
	  }
	}
 </script>
 </body>
