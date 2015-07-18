<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>ActClassic</title>
 <t:base type="jquery,easyui,tools,DatePicker"></t:base>
<link rel="stylesheet" href="plug-in/imageZoom/jquery.imageZoom.css"
	type="text/css" />
<script type="text/javascript"
	src="plug-in/imageZoom/jquery.imageZoom.js"></script>
 </head>
 <body style="overflow-x: hidden">
   <input type="hidden" id="fileServer" value="${server }" />
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="actClassicController.do?saveActClassic">
		 <input type="hidden" id=type name="type" value="2" />
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">排序:</label>
		      <input class="inputxt" id="seq" name="seq" ignore="false" value="" datatype="n">
		      <span class="Validform_checktip"></span>
		    </div>
		    <div class="form">
		      <label class="Validform_label">活动名称:</label>
		      <input class="inputxt" id="title" name="title" ignore="false" value="" datatype="*2-50">
		      <span class="Validform_checktip"></span>
		    </div>
		    <!-- 
		     <div class="form">
		       <label class="Validform_label">选择用户:</label>
		       <input name="userId" type="hidden" value="" id="userId">
	           <input name="nickName" class="inputxt" id="nickName" readonly="readonly" datatype="*"  />
	           <t:choose hiddenName="userId" hiddenid="userId" url="actClassicController.do?userActList" name="userActList" icon="icon-choose" title="用户列表" textname="nickName" isclear="true"></t:choose>
		      <span class="Validform_checktip"></span>
		    </div>
		     -->
		    <div class="form">
		      <label class="Validform_label">选择插件:</label>
		      <select name="pluginId">
		        <c:forEach items="${pluginNameList}" var="pluginName">
		           <option value="${ fn:split(pluginName, '@')[0] }">${ fn:split(pluginName, '@')[1] } </option>
		        </c:forEach>
		      </select>
		      <span class="Validform_checktip"></span>
		    </div>
		   <div class="form">
		      <label class="Validform_label">开始时间:</label>
		      <input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px"  id="startTime" name="startTime" ignore="false" datatype="*"
					   value="<fmt:formatDate value='${pluginActPage.startTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">结束时间:</label>
		      <input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px"  id="endTime" name="endTime" ignore="false" datatype="*"
					   value="<fmt:formatDate value='${pluginActPage.endTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
		      <span class="Validform_checktip"></span>
		    </div>
		    <div class="form">
		      <label class="Validform_label">浏览量:</label>
		      <input class="inputxt" id="browseNum" name="browseNum" ignore="false" value="" datatype="n">
		      <span class="Validform_checktip"></span>
		    </div>
		    <div class="form">
		      <label class="Validform_label">访问量:</label>
		      <input class="inputxt" id="joinNum" name="joinNum" ignore="false" value="" datatype="n">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		     	<label class="Validform_label">品牌logo:</label>
				<input class="inputxt" id="coverPath_a" name="bannerLogo" value="" ignore="true" readonly="readonly" hidden="true"/> 
				<span class="Validform_checktip"></span>
				<div id="previewImg" style="margin-top:15px"></div>
				<t:upload name="fielsicon" id="fielsicon" buttonText="品牌logo"
				dialog="false" auto="true" multi="false"
				uploader="systemController.do?saveFiles&uploadLocation=server"
				extend="pic" queueID="filediv" showUrlId="coverPath_a"
				onUploadSuccess="showImage()"></t:upload>
			   <span class="Validform_checktip">建议尺寸:100px*100px(或等比)，30KB</span> <span
				id="filediv"></span>
		    </div>
		    <div class="form">
		      <label class="Validform_label">品牌名称:</label>
		      <input class="inputxt" id="bannerName" name="bannerName" ignore="ignore" value="" maxlength="50">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">截图:</label>
		        <input type="hidden" name="screePath" id="screePath" value=""/>
				<input class="inputxt" id="coverPath_a1" name="imgPath" value="" ignore="false" datatype="*" readonly="readonly" hidden="true"/>
				<span class="Validform_checktip"></span>
				<div id="screeImage" style="margin-top:15px"></div>
				<t:upload name="fielsicon1" id="fielsicon1" buttonText="截图"
				dialog="false" auto="true" multi="true" 
				uploader="systemController.do?saveFiles&uploadLocation=server"
				extend="pic" queueID="filediv1" showUrlId="coverPath_a1" callback="uploadfinished" 
				onUploadSuccess="screeImage()"></t:upload>
			   <span>建议尺寸:280px*480px(或等比)，50KB</span>
			   <span id="filediv1"></span>	
		    </div>
	   </fieldset>
  </t:formvalid>
 </body>
 
  <script type="text/javascript">
  function typeClick(n){
	  if(n == 2){
		  $("#title_name").show().find("select").attr("disabled",false);
		  $("#title_check").hide().find("input").attr("disabled",true).removeAttr("datatype");
	  }
	  if(n == 1){
		  $("#title_name").hide().find("select").attr("disabled",true);
		  $("#title_check").show().find("input").attr("disabled",false).attr("datatype","*");
	  }
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
			$("#previewImg").html('<a href="'+aurl+'"><img id="preImg" style="width:90px;height:90px;background-color:#ccc;border:1px solid #333" src="'+path+'"/></a>');
		}
		$("#previewImg a").imageZoom();
	}
 
 function screeImage() { 
		var serverIp = $("#fileServer").val();
		var value = $("#coverPath_a1").val();
		var screePath = $("#screePath").val();
		var url = value+","+screePath;
		$("#screePath").val(url);
		if (url != '') {
			var aa = url.split(",");
			var html="";
			for(var i =0;i<aa.length;i++){
				if(aa[i]=="")continue;
				var path = "";
				if (aa[i].indexOf("http://") == -1) {
					path = serverIp + "/" + aa[i];
				} else {
					path = aa[i];
				}
				var aurl = path;
				if (path.indexOf("?") != -1) {
					aurl = path.split("?")[0];
				}
				html = html+'<a href="'+aurl+'"><img id="preImg" style="width:80px;height:80px;background-color:#ccc;border:1px solid #333" src="'+path+'"/></a>';
			}
			$("#screeImage").html(html);
		}
		$("#screeImage a").imageZoom();
	}
 </script>