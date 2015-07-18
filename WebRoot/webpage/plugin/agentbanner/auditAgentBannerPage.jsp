<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>AgentBanner</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <link rel="stylesheet" href="plug-in/imageZoom/jquery.imageZoom.css" type="text/css" />
  <script type="text/javascript" src="plug-in/imageZoom/jquery.imageZoom.js"></script>
 </head>
 <body style="overflow-x: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="agentBannerController.do?changeAgentBanner">
		<input id="id" name="id" type="hidden" value="${agentBannerPage.id }">
		<fieldset class="step" >

            
            <div class="form">
		     	<label class="Validform_label">pc版Logo:</label>
		     	<div class="aa">
		     	<a href="${server }/${agentBannerPage.pcLogo}">
		     	<img alt="" src="${server }/${agentBannerPage.pcLogo}" width="90px;" height="90px;">
		     	</a>
				</div>
		    </div>
		    <div class="form">
		      <label class="Validform_label">pc版链接:</label>
		      ${agentBannerPage.pcLink }
		    </div>
		    <div class="form">
		     	<label class="Validform_label">wx版本Logo:</label>
		     	<div class="aa">
		     	<a href="${server }/${agentBannerPage.wxLogo}">
		     	<img alt="" src="${server }/${agentBannerPage.wxLogo}" width="90px;" height="90px;">
		     	</a>
				</div>
		    </div>
		    <div class="form">
		      <label class="Validform_label">wx版链接:</label>
		      ${agentBannerPage.wxLink }
		    </div>
		    <div class="form">
		      <label class="Validform_label">创建时间:</label>
		      ${agentBannerPage.createTime }
		    </div>
		    <div class="form">
		      <label class="Validform_label">审核时间:</label>
		      ${agentBannerPage.auditTime }
		    </div>

		    <div class="form" > 
		     <div> 
		    &nbsp; &nbsp; &nbsp;  &nbsp;&nbsp;  &nbsp; 
		    &nbsp; &nbsp; &nbsp;  &nbsp;&nbsp;  &nbsp;
		    </div>
		     <c:if test="${agentBannerPage.auditstate=='0'}">  
		     &nbsp; &nbsp; &nbsp;  &nbsp;&nbsp;  &nbsp;  
			  <input  id="need" ignore="false" name="auditstate" type="radio" value="1"  Checked />通过
			  &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; 
              <input  id="need" ignore="false" name="auditstate" type="radio" value="2"  />不通过
             </c:if> 
		     <c:if test="${agentBannerPage.auditstate=='1'}"> 
		     &nbsp; &nbsp; &nbsp;  &nbsp;&nbsp;  &nbsp;   
			  <input   id="need" ignore="false" name="auditstate" type="radio" value="1"  Checked />通过
			  &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; 
              <input   id="need" ignore="false" name="auditstate" type="radio" value="2"  />不通过
             </c:if>
             <c:if test="${agentBannerPage.auditstate=='2'}"> 
             &nbsp; &nbsp; &nbsp;  &nbsp;&nbsp;  &nbsp;   
			  <input  id="need" ignore="false" name="auditstate" type="radio" value="1"  />通过
			  &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; 
              <input  id="need" ignore="false" name="auditstate" type="radio" value="2"  Checked />不通过
             </c:if> 
		      <span class="Validform_checktip"></span>
		    </div>
	   </fieldset>
  </t:formvalid>
 </body>
 <script type="text/javascript">
 $(".aa a").imageZoom();
 </script>