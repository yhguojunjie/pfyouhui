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
 <body style="overflow-x: hidden" scroll="no">
   <input type="hidden" id="fileServer" value="${server }" />
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="agentClassicController.do?saveActClassic">
        <input id="id" name="id" type="hidden" value="${agentClassicPage.actClassic.id }">
       <input type="hidden" id=type name="type" value="1" />
		<fieldset class="step">
		    <div class="form">
		      <label class="Validform_label">活动名称:</label>
		      ${agentClassicPage.actClassic.pluginAct.title }
		    </div>
			<div class="form">
		     	<label class="Validform_label">品牌logo:</label>
				<input class="inputxt" id="coverPath_a" name="bannerLogo" value="${agentClassicPage.actClassic.bannerLogo }" ignore="true" readonly="readonly" hidden="true"/> 
				<span class="Validform_checktip"></span>
				<div id="previewImg" style="margin-top:15px">
				   <c:if test="${not empty agentClassicPage.actClassic.bannerLogo }">
				   			<a href="${server }/${agentClassicPage.actClassic.bannerLogo}"><img style="width:80px;height:80px;background-color:#ccc;border:1px solid #333" src="${server }/${agentClassicPage.actClassic.bannerLogo}"/></a>
				   </c:if>
				   <c:if test="${empty agentClassicPage.actClassic.bannerLogo}">
				   <img style="width:80px;height:80px;background-color:#ccc;border:1px solid #333" src="img/noimg150150.jpg"/>
				   </c:if>
				</div>
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
		      <input class="inputxt" id="bannerName" name="bannerName" ignore="ignore" value="${agentClassicPage.actClassic.bannerName }" maxlength="50">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">截图:</label>
		        <input type="hidden" name="screePath" id="screePath" value=""/>
				<input class="inputxt" id="coverPath_a1" name="imgPath" value="" ignore="ignore" readonly="readonly" hidden="true"/>
				<span class="Validform_checktip"></span>
				<div id="screeImage" style="margin-top:15px"></div>
				<t:upload name="fielsicon1" id="fielsicon1" buttonText="截图"
				dialog="false" auto="true" multi="true" 
				uploader="systemController.do?saveFiles&uploadLocation=server"
				extend="pic" queueID="filediv1" showUrlId="coverPath_a1" callback="uploadfinished" 
				onUploadSuccess="screeImage()"></t:upload>
			   <span class="Validform_checktip">建议尺寸:280px*480px(或等比)，50KB</span>
			   <span id="filediv1"></span>	
		    </div>
		    <div class="form">
		     <label class="Validform_label">预览图:</label>点击图片查看原图
		    </div>
		    <div class="form">
		    <c:forEach  var="pic" items="${agentClassicPage.actClassic.actClassicPic}" varStatus="status">
		     	 <div style="float: left;text-align: center;padding:5px;" class="aa">
			     <a href="${server }/${pic.url }"><img alt="点击图片查看原图" style="width:100px;height:100px;background-color:#ccc;border:1px solid #333" src="${server }/${pic.url }"/></a><br>
			     <a href="#" class="easyui-linkbutton" onclick="deletePluginImg('${pic.id}','${pic.url }',this)">删除</a>
		     </div>
		    </c:forEach>
		    </div>
	   </fieldset>
  </t:formvalid>
 </body>
 
  <script type="text/javascript">
  //删除插件图片
  function deletePluginImg(id,url,obj){
 	 $.post("actClassicController.do?delActClassicPic", { id:id, url:url },function(data) {
 			   $(obj).parent("div").remove();
 	 });
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
			$("#previewImg").html('<a href="'+aurl+'"><img  style="width:90px;height:90px;background-color:#ccc;border:1px solid #333" src="'+path+'"/></a>');
		}
		$("#previewImg a").imageZoom();
	}
 $("#previewImg a").imageZoom();
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
				html = html+'<a href="'+aurl+'"><img  style="width:80px;height:80px;background-color:#ccc;border:1px solid #333" src="'+path+'"/></a>';
			}
			$("#screeImage").html(html);
		}
		$("#screeImage a").imageZoom();
	}
 $(".aa a").imageZoom();
 </script>