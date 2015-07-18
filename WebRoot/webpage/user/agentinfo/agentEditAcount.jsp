<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>AgentInfo</title>
    <t:base type="jquery,easyui,tools,DatePicker"></t:base>
      <script type="text/javascript"
	src="plug-in/province/province.js"></script>
	<script type="text/javascript" src="plug-in/province/bankFormat.js"></script>
 </head>
 <body style="overflow:hidden">
  <input type="hidden" id="fileServer" value="${server }" />
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" refresh="true" action="agentInfoController.do?saveAgentInfo">
		<input id="id" name="id" type="hidden" value="${agentInfo.id }">
		<c:if test="${param.type == 0 }">
			<fieldset class="step" style="width: 400px;">
				<div class="form">
			      <label class="Validform_label">支付宝账号:</label>
			      <input class="inputxt" id="alipay" name="alipay" ignore="false" datatype="*" maxlength="30"
						 value="${agentInfo.alipay}">
			      <span class="Validform_checktip"></span>
			    </div>
			    </fieldset>
		    </c:if>
		    <c:if test="${param.type == 1 }">
			    <fieldset class="step">
				<div class="form">
			     <label class="Validform_label" style="float:none;display:block;margin-bottom:10px;">储蓄卡-借记卡:</label>
			     <input id="bankType" name="bankType" type="hidden" value="${agentInfo.bankType }">
			     <div id="card_img">
			      <label style="float: left;width:33%;"> <input type="radio" onclick="clickCard('1_中国光大银行');" name="aaa" style="vertical-align:15px;margin-right:5px;" value="1_中国光大银行"><img alt="" src="plug-in/accordion/images/gd.jpg"></label>
			      <label style="float: left;width:33%;"> <input type="radio" onclick="clickCard('2_广东发展银行');" name="aaa" style="vertical-align:15px;margin-right:5px;" value="2_广东发展银行"><img alt="" src="plug-in/accordion/images/gf.jpg"></label>
			      <label style="float: left;width:33%;"> <input type="radio" onclick="clickCard('3_中国工商银行');" name="aaa" style="vertical-align:15px;margin-right:5px;" value="3_中国工商银行"><img alt="" src="plug-in/accordion/images/gs.jpg"></label>
			      <label style="float: left;width:33%;"> <input type="radio" onclick="clickCard('4_广州市商业银行');" name="aaa" style="vertical-align:15px;margin-right:5px;" value="4_广州市商业银行"><img alt="" src="plug-in/accordion/images/gz.jpg"></label>
			      <label style="float: left;width:33%;"> <input type="radio" onclick="clickCard('5_华夏银行');" name="aaa" style="vertical-align:15px;margin-right:5px;" value="5_华夏银行"><img alt="" src="plug-in/accordion/images/hx.jpg"></label>
			      <label style="float: left;width:33%;"> <input type="radio" onclick="clickCard('6_中国建设银行');" name="aaa" style="vertical-align:15px;margin-right:5px;" value="6_中国建设银行"><img alt="" src="plug-in/accordion/images/js.jpg"></label>
			      <label style="float: left;width:33%;"> <input type="radio" onclick="clickCard('7_交通银行');" name="aaa" style="vertical-align:15px;margin-right:5px;" value="7_交通银行"><img alt="" src="plug-in/accordion/images/jt.jpg"></label>
			      <label style="float: left;width:33%;"> <input type="radio" onclick="clickCard('8_中国民生银行');" name="aaa" style="vertical-align:15px;margin-right:5px;" value="8_中国民生银行"><img alt="" src="plug-in/accordion/images/ms.jpg"></label>
			      <label style="float: left;width:33%;"> <input type="radio" onclick="clickCard('9_农村信用社');" name="aaa" style="vertical-align:15px;margin-right:5px;" value="9_农村信用社"><img alt="" src="plug-in/accordion/images/nc.jpg"></label>
			      <label style="float: left;width:33%;"> <input type="radio" onclick="clickCard('10_中国农业银行');" name="aaa" style="vertical-align:15px;margin-right:5px;" value="10_中国农业银行"><img alt="" src="plug-in/accordion/images/ny.jpg"></label>
			      <label style="float: left;width:33%;"> <input type="radio" onclick="clickCard('11_上海浦东发展银行');" name="aaa" style="vertical-align:15px;margin-right:5px;" value="11_上海浦东发展银行"><img alt="" src="plug-in/accordion/images/pf.jpg"></label>
			      <label style="float: left;width:33%;"> <input type="radio" onclick="clickCard('12_上海银行');" name="aaa" style="vertical-align:15px;margin-right:5px;" value="12_上海银行"><img alt="" src="plug-in/accordion/images/sh.jpg"></label>
			      <label style="float: left;width:33%;"> <input type="radio" onclick="clickCard('13_深圳市商业银行');" name="aaa" style="vertical-align:15px;margin-right:5px;" value="13_深圳市商业银行"><img alt="" src="plug-in/accordion/images/sz.jpg"></label>
			      <label style="float: left;width:33%;"> <input type="radio" onclick="clickCard('14_福建兴业银行');" name="aaa" style="vertical-align:15px;margin-right:5px;" value="14_福建兴业银行"><img alt="" src="plug-in/accordion/images/xy.jpg"></label>
			      <label style="float: left;width:33%;"> <input type="radio" onclick="clickCard('15_中国邮政');" name="aaa" style="vertical-align:15px;margin-right:5px;" value="15_中国邮政"><img alt="" src="plug-in/accordion/images/yz.jpg"></label>
			      <label style="float: left;width:33%;"> <input type="radio" onclick="clickCard('16_中国银行');" name="aaa" style="vertical-align:15px;margin-right:5px;" value="16_中国银行"><img alt="" src="plug-in/accordion/images/zg.jpg"></label>
			      <label style="float: left;width:33%;"> <input type="radio" onclick="clickCard('17_招商银行');" name="aaa" style="vertical-align:15px;margin-right:5px;" value="17_招商银行"><img alt="" src="plug-in/accordion/images/zs.jpg"></label>
			      <label style="float: left;width:33%;"> <input type="radio" onclick="clickCard('18_中信实业银行');" name="aaa" style="vertical-align:15px;margin-right:5px;" value="18_中信实业银行"><img alt="" src="plug-in/accordion/images/zx.jpg"></label>
			      <div style="clear:both;">&nbsp;</div>
			     </div>
			     <div id="netButton" style="display: none;">
			     <input type="button" value="下一步" name="netbutton" onclick="netButton();"/>
			     </div>
			     <span class="Validform_checktip"></span>
			    </div>
			    <div class="form" style="display: none;" id="cardNum">
			     <label class="Validform_label" style="float:none;display:block;margin-bottom:10px;width: 150px"><span id="textCard"></span>卡号:</label>
			     <input class="inputxt" id="bankAccount" name="bankAccount"  ajaxurl="agentInfoController.do?checkCard"  datatype="*0-30" value="${agentInfoPage.bankAccount}" style="width: 200px;">
			     <span class="Validform_checktip"></span>
			    </div>
			    </fieldset>
		    </c:if>
	   
  </t:formvalid>
 </body>
 
 <script type="text/javascript">
  $(function(){
		$("#bankAccount").inputFormat("account");
 });
  function clickCard(obj){
	  var n = obj.split("_")[0];
	  var text = obj.split("_")[1];
	  if(n==1){
		  $("#netButton").show();
		  $("#bankType").val(1);
		  $("#textCard").text(text);
	  }
	  if(n==2){
		  $("#netButton").show();
		  $("#bankType").val(2);
		  $("#textCard").text(text);
	  }
	  if(n==3){
		  $("#netButton").show();
		  $("#bankType").val(3);
		  $("#textCard").text(text);
	  }
	  if(n==4){
		  $("#netButton").show();
		  $("#bankType").val(4);
		  $("#textCard").text(text);
	  }
	  
	  if(n==5){
		  $("#netButton").show();
		  $("#bankType").val(5);
		  $("#textCard").text(text);
	  }
	  if(n==6){
		  $("#netButton").show();
		  $("#bankType").val(6);
		  $("#textCard").text(text);
	  }
	  if(n==7){
		  $("#netButton").show();
		  $("#bankType").val(7);
		  $("#textCard").text(text);
	  }
	  if(n==8){
		  $("#netButton").show();
		  $("#bankType").val(8);
		  $("#textCard").text(text);
	  }
	  if(n==9){
		  $("#netButton").show();
		  $("#bankType").val(9);
		  $("#textCard").text(text);
	  }
	  if(n==10){
		  $("#netButton").show();
		  $("#bankType").val(10);
		  $("#textCard").text(text);
	  }
	  if(n==11){
		  $("#netButton").show();
		  $("#bankType").val(11);
		  $("#textCard").text(text);
	  }
	  if(n==12){
		  $("#netButton").show();
		  $("#bankType").val(12);
		  $("#textCard").text(text);
	  }
	  if(n==13){
		  $("#netButton").show();
		  $("#bankType").val(13);
		  $("#textCard").text(text);
	  }
	  if(n==14){
		  $("#netButton").show();
		  $("#bankType").val(14);
		  $("#textCard").text(text);
	  }
	  if(n==15){
		  $("#netButton").show();
		  $("#bankType").val(15);
		  $("#textCard").text(text);
	  }
	  if(n==16){
		  $("#netButton").show();
		  $("#bankType").val(16);
		  $("#textCard").text(text);
	  }
	  if(n==17){
		  $("#netButton").show();
		  $("#bankType").val(17);
		  $("#textCard").text(text);
	  }
	  if(n==18){
		  $("#netButton").show();
		  $("#bankType").val(18);
		  $("#textCard").text(text);
	  }
	  
  }
  function netButton(){
	  $("#cardNum").show();
  }





  </script>
