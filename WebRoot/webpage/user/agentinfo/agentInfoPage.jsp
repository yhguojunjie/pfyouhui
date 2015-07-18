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
  <input type="hidden" id="fileServer" value="${server }" />
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="agentInfoController.do?saveAgentInfo">
		<input id="id" name="id" type="hidden" value="${agentInfoPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">公司名称:</label>
		      <input class="inputxt" id="companyName" name="companyName" ignore="false" datatype="*0-100" errormsg="含有特殊字符并且2-100字符内"  style="width: 300px"
					 value="${agentInfoPage.companyName}">
		      <span class="Validform_checktip">公司名称围在2~100位字符</span>
		    </div>
			<div class="form">
		      <label class="Validform_label">省市区:</label>
		      <div id="province_city"></div>
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">详细地址:</label>
		      <input class="inputxt" id="address" name="address" ignore="false" style="width: 350px;" datatype="*0-100" errormsg="含有特殊字符并且2-100字符内"
					 value="${agentInfoPage.address}">
		      <span class="Validform_checktip">详细地址围在2~100位字符</span>
		    </div>
		    <div class="form">
		      <label class="Validform_label">联系人:</label>
		      <input class="inputxt" id="contract" name="contract" ignore="false"  datatype="*0-30" errormsg="含有特殊字符并且2-30字符内"
					 value="${agentInfoPage.contract}">
		      <span class="Validform_checktip">联系人范围2-30字符</span>
		    </div>
			<div class="form">
		      <label class="Validform_label">办公电话:</label>
		      <input class="inputxt" id="telephone" name="telephone" ignore="ignore"  maxlength="15" 
					 value="${agentInfoPage.telephone}">
		      <span class="Validform_checktip"></span>
		    </div>
		    <!-- 
			<div class="form">
		      <label class="Validform_label">传真号码:</label>
		      <input class="inputxt" id="fax" name="fax" ignore="ignore" maxlength="15"
					 value="${agentInfoPage.fax}">
		      <span class="Validform_checktip"></span>
		    </div>
		     -->
			<div class="form">
		      <label class="Validform_label">手机号:</label>
		      <input class="inputxt" id="mobile" name="mobile" ignore="false"  errormsg="手机号码不正确!"
					 value="${agentInfoPage.mobile}">
		      <span class="Validform_checktip"></span>
		    </div>
		    <div class="form">
		      <label class="Validform_label">QQ:</label>
		      <input class="inputxt" id="qq" name="qq" ignore="false"  datatype="s0-12" 
					 value="${agentInfoPage.qq}">
		      <span class="Validform_checktip"></span>
		    </div>
		    <div class="form">
		      <label class="Validform_label">邮箱:</label>
		      <input class="inputxt" id="email" name="email" ignore="false"  
					 value="${agentInfoPage.email}">
		      <span class="Validform_checktip"></span>
		    </div>
		    <div class="form">
		      <label class="Validform_label">客服电话:</label>
		      <input class="inputxt" id="servicePhone" name="servicePhone" ignore="false"   maxlength="14"
					 value="${agentInfoPage.servicePhone}">
		      <span class="Validform_checktip">客服电话14字符以内</span>
		    </div>
		   <div class="form">
		      <label class="Validform_label">客服邮箱:</label>
		      <input class="inputxt" id="serviceEmail" name="serviceEmail" ignore="false"  
					 value="${agentInfoPage.serviceEmail}">
		      <span class="Validform_checktip">客服邮箱20字符以内</span>
		    </div>
		    <div class="form">
		      <label class="Validform_label">客服QQ:</label>
		      <input class="inputxt" id="serviceqq" name="serviceqq" ignore="false"  maxlength="12"
					 value="${agentInfoPage.serviceqq}">
		      <span class="Validform_checktip">客服QQ12字符以内</span>
		    </div>
		    <div class="form">
		      <label class="Validform_label">客服QQ群:</label>
		      <input class="inputxt" id="qqGroup" name="qqGroup" ignore="false"  datatype="s0-12" errormsg="含有特殊字符" maxlength="12"
					 value="${agentInfoPage.qqGroup}">
		      <span class="Validform_checktip">客服QQ群12字符以内</span>
		    </div>
		    <div class="form">
		      <label class="Validform_label">网站备案:</label>
		      <input class="inputxt" id="webRecord" name="webRecord" ignore="false" datatype="*0-250" style="width: 350px;"
					 value="${agentInfoPage.webRecord}">
		      <span class="Validform_checktip">网站备案范围0-250字符</span>
		    </div>
			<div class="form">
		      <label class="Validform_label">公司图标:</label>
		        <input class="inputxt" id="coverPath_a" name="logo" value="${agentInfoPage.logo }" ignore="ignore" readonly="readonly" hidden="true"/>
				<span class="Validform_checktip"></span><span id="picsuccess"></span>
				<div id="previewImg" style="margin-top:15px">
				 <c:if test="${not empty agentInfoPage.logo }">
				 		<a href="${server }/${agentInfoPage.logo}"><img style="width:250px;height:50px;background-color:#ccc;border:1px solid #333" src="${server }/${agentInfoPage.logo}"/></a>
				 </c:if>
				 <c:if test="${empty agentInfoPage.logo }">
				 		<img style="width:250px;height:50px;background-color:#ccc;border:1px solid #333" src="img/noimg25050.jpg"/>
				 </c:if>
				</div>
				
				<t:upload name="fielsicon" id="fielsicon" buttonText="上传公司logo"
				dialog="false" auto="true" multi="false"
				uploader="systemController.do?saveFiles&uploadLocation=server"
				extend="pic" queueID="filediv" showUrlId="coverPath_a"
				onUploadSuccess="showImage()"></t:upload>
			   <span class="Validform_checktip">建议尺寸:250px*50px(或等比)，50KB</span> <span
				id="filediv"></span>
		    </div>
		    <!-- 
			<div class="form">
		      <label class="Validform_label">logo描述:</label>
		      <input class="inputxt" id="logoDesc" name="logoDesc" ignore="false" datatype="*2-50" 
					 value="${agentInfoPage.logoDesc}">
		      <span class="Validform_checktip">logo描述范围2-50字符</span>
		    </div>
		     -->
			<div class="form">
		      <label class="Validform_label">公众号二维码:</label>
		        <input class="inputxt" id="coverPath_a1" name="wxqrcode" value="${agentInfoPage.wxqrcode }" ignore="ignore" readonly="readonly" hidden="true"/>
				<span class="Validform_checktip"></span><span id=picsuccess1></span>
				<div id="previewImg1" style="margin-top:15px">
				<c:if test="${not empty agentInfoPage.wxqrcode  }">
					<a href="${server }/${agentInfoPage.wxqrcode}"><img style="width:80px;height:80px;background-color:#ccc;border:0px solid #333" src="${server }/${agentInfoPage.wxqrcode}"/></a>
				</c:if>
				<c:if test="${empty agentInfoPage.wxqrcode}">
					<img style="width:80px;height:80px;background-color:#ccc;border:1px solid #333" src="img/noimg150150.jpg"/>
				</c:if>
				</div>
				
				<t:upload name="fielsicon1" id="fielsicon1" buttonText="上传二维码"
				dialog="false" auto="true" multi="false"
				uploader="systemController.do?saveFiles&uploadLocation=server"
				extend="pic" queueID="filediv1" showUrlId="coverPath_a1"
				onUploadSuccess="wxqrcode()"></t:upload>
			   <span class="Validform_checktip">建议尺寸:160px*160px(或等比)，50KB</span> <span
				id="filediv1"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">关注地址:</label>
		      <input class="inputxt" id="focusAdd" name="focusAdd" ignore="ignore"  errormsg="请输入正确http://地址"
					 value="${agentInfoPage.focusAdd}">
		      <span class="Validform_checktip">http://,可填</span>
		    </div>
		    <div class="form">
		      <label class="Validform_label">版权信息:</label>
		      <input class="inputxt" id="version" name="version" ignore="false" datatype="*0-250" style="width: 350px;"
					 value="${agentInfoPage.version}">
		      <span class="Validform_checktip">版权信息范围0-250字符</span>
		    </div>
			<div class="form">
		      <label class="Validform_label">指向域名:</label>
		      <input class="inputxt" id="mydomain" name="mydomain"   ignore="false"  maxlength="50"
					 value="${agentInfoPage.mydomain}">
		      <span class="Validform_checktip">必填50字符以内</span>
		    </div>
		    <div class="form">
		      <label class="Validform_label">转发域名:</label>
		      <input class="inputxt" id="forwardDomain" name="forwardDomain"   ignore="false"   maxlength="50"
					 value="${agentInfoPage.forwardDomain}">
		      <span class="Validform_checktip">必填50字符以内(无:可同指向域名一样)</span>
		    </div>
		    <div class="form">
		      <label class="Validform_label">分享跳转域名:</label>
		      <input class="inputxt" id="redirecDomain" name="redirecDomain" ignore="false" 
					 value="${agentInfoPage.redirecDomain}">
		      <span class="Validform_checktip">可填</span>
		    </div>
		    <div class="form">
		     <label class="Validform_label">预存余额:</label>
		     <input class="inputxt" id="blance" name="blance" ignore="false" value="${agentInfoPage.blance}">
		     <span class="Validform_checktip"></span>
		    </div>
		    <div class="form">
		     <label class="Validform_label">支付宝-partner:</label>
		     <input class="inputxt" id="aliPartner" name="aliPartner" ignore="false" datatype="*0-50" value="${agentInfoPage.aliPartner}">
		     <span class="Validform_checktip"></span>
		    </div>
		    <div class="form">
		     <label class="Validform_label">支付宝-key:</label>
		     <input class="inputxt" id="aliKey" name="aliKey" ignore="false" datatype="*0-100" value="${agentInfoPage.aliKey}">
		     <span class="Validform_checktip"></span>
		    </div>
		    <div class="form">
		     <label class="Validform_label">支付宝-收款账号:</label>
		     <input class="inputxt" id="aliSellerAccount" name="aliSellerAccount" ignore="false" datatype="*0-60" value="${agentInfoPage.aliSellerAccount}">
		     <span class="Validform_checktip"></span>
		    </div>
		    <!-- 
		    <div class="form">
		     <label class="Validform_label">支付宝账号:</label>
		     <input class="inputxt" id="alipay" name="alipay" ignore="false" datatype="*0-30"  value="${agentInfoPage.alipay}">
		     <span class="Validform_checktip"></span>
		    </div>
		    <div class="form">
		     <label class="Validform_label" style="float:none;display:block;margin-bottom:10px;">储蓄卡-借记卡:</label>
		     <input id="bankType" name="bankType" type="hidden" value="${agentInfoPage.bankType }">
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
		     -->
		    <div class="form">
		     <label class="Validform_label">网站标题:</label>
		     <input class="inputxt" id="websiteTitle" name="websiteTitle" ignore="false" datatype="*0-30"  value="${agentInfoPage.websiteTitle}" style="width: 200px;">
		     <span class="Validform_checktip">30个字符以内</span>
		    </div>
		    <div class="form">
		      <label class="Validform_label">网站描述:</label>
		       <% request.setAttribute("vEnter", "\r\n"); //换行在el中不能用常量表达    %>    
		      <textarea rows="6" class="inputxt" style="width:80%" name="websiteDesc" ignore="false" datatype="*0-200" id="websiteDesc">${fn:replace(agentInfoPage.websiteDesc,"<br/>",vEnter)}</textarea>
		      <span class="Validform_checktip">200字符以内</span>
		    </div>
		    <div class="form">
		     <label class="Validform_label">网站关键字:</label>
		      <% request.setAttribute("vEnter", "\r\n"); //换行在el中不能用常量表达    %>    
		      <textarea rows="6" class="inputxt" style="width:80%" name="websiteKeyword" ignore="false" datatype="*0-200" id="websiteKeyword">${fn:replace(agentInfoPage.websiteKeyword,"<br/>",vEnter)}</textarea>
		     <span class="Validform_checktip">200字符以内</span>
		    </div>
		    <div class="form">
		      <label class="Validform_label">网站图标:</label>
		        <input class="inputxt" id="coverPath_a2" name="websiteIcon" value="${agentInfoPage.websiteIcon }" ignore="ignore" readonly="readonly" hidden="true"/>
				<span class="Validform_checktip"></span><span id=picsuccess2></span>
				<div id="previewImg2" style="margin-top:15px">
				<c:if test="${not empty agentInfoPage.websiteIcon }">
					<a href="${server }/${agentInfoPage.websiteIcon}"><img  style="width:80px;height:80px;background-color:#ccc;border:1px solid #333" src="${server }/${agentInfoPage.websiteIcon}"/></a>
				</c:if>
				<c:if test="${empty  agentInfoPage.websiteIcon}">
					<img style="width:80px;height:80px;background-color:#ccc;border:1px solid #333" src="img/noimg150150.jpg"/>
				</c:if>
				</div>
				<t:upload name="fielsicon2" id="fielsicon2" buttonText="上传网站图标"
				dialog="false" auto="true" multi="false"
				uploader="systemController.do?saveFiles&uploadLocation=server"
				extend="pic" queueID="filediv2" showUrlId="coverPath_a2"
				onUploadSuccess="webSite()"></t:upload>
			   <span class="Validform_checktip">建议尺寸:16px*16px，ico格式</span> <span
				id="filediv2"></span>
		    </div>
	   </fieldset>
  </t:formvalid>
 </body>
  <script type="text/javascript">
  //开源省市区联动表单
  $("#province_city").province_city_district("${agentInfoPage.province}","${agentInfoPage.city}","${agentInfoPage.district}");
  $(function(){
	     $("#province").removeAttr("datatype");
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
  function showImage() {
		var serverIp = $("#fileServer").val();
		var value = $("#coverPath_a").val();
		if (value != '') {
			var path = "";
			if (value.indexOf("http://") == -1) {
				path = serverIp + "/" + value;
			} else {
				path = value;
			}
			var aurl = path;
			if (path.indexOf("?") != -1) {
				aurl = path.split("?")[0];
			}
			$("#previewImg").html('<a href="'+aurl+'"><img  style="width:250px;height:50px;background-color:#ccc;border:1px solid #333" src="'+path+'"/></a>');
		}
		$("#picsuccess").append("<span>上传成功</span>");
		$("#previewImg a").imageZoom();
		
	}
$("#previewImg a").imageZoom();

function wxqrcode() {
	var serverIp = $("#fileServer").val();
	var value = $("#coverPath_a1").val();
	if (value != '') {
		var path = "";
		if (value.indexOf("http://") == -1) {
			path = serverIp + "/" + value;
		} else {
			path = value;
		}
		var aurl = path;
		if (path.indexOf("?") != -1) {
			aurl = path.split("?")[0];
		}
		$("#previewImg1").html('<a href="'+aurl+'"><img  style="width:80px;height:80px;background-color:#ccc;border:1px solid #333" src="'+path+'"/></a>');
	}
	$("#picsuccess1").append("<span>上传成功</span>");
	$("#previewImg1 a").imageZoom();
	
}
$("#previewImg1 a").imageZoom();

function webSite() {
	var serverIp = $("#fileServer").val();
	var value = $("#coverPath_a2").val();
	if (value != '') {
		var path = "";
		if (value.indexOf("http://") == -1) {
			path = serverIp + "/" + value;
		} else {
			path = value;
		}
		var aurl = path;
		if (path.indexOf("?") != -1) {
			aurl = path.split("?")[0];
		}
		$("#previewImg2").html('<a href="'+aurl+'"><img  style="width:80px;height:80px;background-color:#ccc;border:1px solid #333" src="'+path+'"/></a>');
	}
	$("#picsuccess2").append("<span>上传成功</span>");
	$("#previewImg2 a").imageZoom();
	
}
$("#previewImg2 a").imageZoom();
  </script>