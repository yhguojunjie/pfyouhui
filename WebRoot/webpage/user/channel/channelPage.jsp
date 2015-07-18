<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>Channel</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <link rel="stylesheet" href="plug-in/imageZoom/jquery.imageZoom.css"
	type="text/css" />
<script type="text/javascript"
	src="plug-in/imageZoom/jquery.imageZoom.js"></script>
 </head>
 <body style="overflow-x: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="channelController.do?saveChannel">
		<input id="id" name="id" type="hidden" value="${channelPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">渠道类型:</label>
		      <c:choose>
		      	<c:when test="${channelPage.type == 1}">微信订阅号</c:when>
		      	<c:when test="${channelPage.type == 2}">微信服务号</c:when>
		      	<c:when test="${channelPage.type == 3}">微信个人号</c:when>
		      	<c:when test="${channelPage.type == 4}">微博账号</c:when>
		      	<c:when test="${channelPage.type == 5}">网站</c:when>
		      	<c:when test="${channelPage.type == 6}">APP</c:when>
		      	<c:otherwise>其他</c:otherwise>
		      </c:choose>
		    </div>
			<div class="form">
		      <label class="Validform_label">渠道名称:</label>${channelPage.name}
		    </div>
			<div class="form">
		      <label class="Validform_label">LOGO:</label>
		      <div class="aa" style="margin-top:15px">
		        <c:if test="${not empty channelPage.logo }">
		        	<a href="${server }/${channelPage.logo}"><img onerror="getLoadImage(this)" style="width:80px;height:80px;background-color:#ccc;border:1px solid #333" src="${server }/${channelPage.logo}"/></a>
		        </c:if>
		        <c:if test="${empty channelPage.logo  }">
		        	<img style="width:80px;height:80px;background-color:#ccc;border:1px solid #333" src="img/noimg150150.jpg"/>
		        </c:if>
		      </div>
		   	   <span class="Validform_checktip">点击图片查看原图</span>
		    </div>
			<div class="form">
		      <label class="Validform_label">简介:</label> ${channelPage.introduce}"
		    </div>
			<div class="form">
		      <label class="Validform_label">二维码:</label>
		      <div class="aa" style="margin-top:15px">
		         <c:if test="${not empty  channelPage.qrcode}">
		         	  <a href="${server }/${channelPage.qrcode}"><img onerror="getLoadImage(this)" style="width:80px;height:80px;background-color:#ccc;border:1px solid #333" src="${server }/${channelPage.qrcode}"/></a>
		         </c:if>
		         <c:if test="${empty channelPage.qrcode }">
		       		  <img style="width:80px;height:80px;background-color:#ccc;border:1px solid #333" src="img/noimg150150.jpg"/>
		         </c:if>
		      </div>
		      <span class="Validform_checktip">点击图片查看原图</span>
		    </div>
			<div class="form">
		      <label class="Validform_label">网址:</label>${channelPage.website}
		    </div>
			<div class="form">
		      <label class="Validform_label">粉丝数:</label> 
		      ${channelPage.fansNum}W
		    </div>
			<div class="form">
		      <label class="Validform_label">发布价位 :</label>${channelPage.price}元/条左右
		    </div>
			<div class="form">
		      <label class="Validform_label">联系人QQ号:</label>${channelPage.qq}
		    </div>
		    <div class="form">
		      <label class="Validform_label">电话 :</label>${channelPage.mobile}
		    </div>
			<div class="form">
		      <label class="Validform_label">邮箱:</label>${channelPage.email}
		    </div>
		    <div class="form">
		        <label class="Validform_label">审核:</label>   
		        <input type="radio" name="auditState" value="1" <c:if test="${channelPage.auditState ==1 }">checked="checked"</c:if> >通过
	     		<input type="radio" name="auditState" value="2" <c:if test="${channelPage.auditState ==2 }">checked="checked"</c:if>>不通过
		    </div>
	   </fieldset>
  </t:formvalid>
 </body>
 
  <script type="text/javascript">
 $(".aa a").imageZoom();
 
 function getLoadImage(obj){
	 obj.src="img/noimg150150.jpg";
}
 </script>