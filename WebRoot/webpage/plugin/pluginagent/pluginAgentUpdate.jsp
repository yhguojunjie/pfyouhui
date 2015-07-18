<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>Plugin</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-x: hidden" >
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="pluginAgentController.do?savePluginAgent" >
		<input id="id" name="id" type="hidden" value="${pluginAgentPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">插件名称:</label>
		      ${pluginAgentPage.plugin.name }
		    </div>
		    <div class="form">
				<label class="Validform_label">插件图标:</label>
				<img alt="" width="100px;" height="100px;" src="${server}/${ pluginAgentPage.plugin.icon}">
		    </div>
			<div class="form">
		      <label class="Validform_label">成本价:</label>
		    	  ￥${price}/月
		    </div>
		    <div class="form">
		      <label class="Validform_label">类型:</label>
		    	  <input type="checkbox" value=""  name="negotiPrice" id="negotiPrice" >&nbsp;&nbsp;是否价格面议
		    </div>
		    <div class="form" id="mingma">
		      <label class="Validform_label">销售价:</label>
		      <input class="inputxt" id="salePrice" name="salePrice" ignore="false"  datatype="n,float" min="${price }" value="${salePrice }" width="50px" >/月
		      <span class="Validform_checktip"></span>
		      <br>
		      <span style="margin-left: 100px;"></span>
		    </div>
		    <div class="form" id="mianyi" style="display: none;">
		      <label class="Validform_label">销售价:</label>
		      <input class="inputxt"  name="salePrice" readonly="readonly"  value="0" disabled="disabled">
		      <span class="Validform_checktip"></span>
		      <br>
		      <span style="margin-left: 100px;"></span>
		    </div>
	   </fieldset>
  </t:formvalid>
 </body>
 <script type="text/javascript">
   $(function(){
		$("#negotiPrice").bind('click',function(){
			if($("#negotiPrice:checked").length>0){
				$("#mingma").hide().find("input").attr("disabled",true)
				$("#mianyi").show().find("input").attr("disabled",false);
			}else{
				$("#mingma").show().find("input").attr("disabled",false)
				$("#mianyi").hide().find("input").attr("disabled",true);
			}
		});
 	});
 </script>