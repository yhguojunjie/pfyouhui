<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>Plugin</title>
 <t:base type="jquery,easyui,tools,DatePicker"></t:base>
<link rel="stylesheet" href="plug-in/imageZoom/jquery.imageZoom.css"
	type="text/css" />
<script type="text/javascript"
	src="plug-in/imageZoom/jquery.imageZoom.js"></script>
 </head>
 <body style="overflow-x: hidden" >
  <input type="hidden" id="fileServer" value="${server }" />
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="templateController.do?savePluginImg" >
		<input id="id" name="id" type="hidden" value="${pluginPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">插件名称:</label>
		      ${pluginPage.name }
		    </div>
		    <div class="form">
				<label class="Validform_label">插件图片:</label> 
				<input class="inputxt" id="coverPath_a" name="imgPath" value="" ignore="ignore" readonly="readonly" hidden="true"/>
				<span class="Validform_checktip"></span>
				<div id="previewImg" style="margin-top:15px"></div>
					
				<t:upload name="fielsicon" id="fielsicon" buttonText="上传到服务器"
				dialog="false" auto="true" multi="true" 
				uploader="systemController.do?saveFiles&uploadLocation=server"
				extend="pic" queueID="filediv" showUrlId="coverPath_a" 
				onUploadSuccess="showImage()"></t:upload>
			   <span class="Validform_checktip">建议尺寸:320px*560px(或等比)，80KB</span>
			   <span id="filediv"></span>		         
		    </div>
		    <div class="form">
		     <label class="Validform_label">插件预览图:</label>点击图片查看原图
		    </div>
		    <div class="form">
		   
		    <c:forEach  var="pic" items="${pluginPage.pluginPic}" varStatus="status">
		     <div style="float: left;text-align: center;padding:5px;" class="aa">
			     <a href="${server }/${pic.url }"><img alt="点击图片查看原图" style="width:80px;height:100px;background-color:#ccc;border:1px solid #333" src="${server }/${pic.url }"/></a><br>
			     <a href="#" class="easyui-linkbutton" onclick="deletePluginImg('${pic.id}','${pic.url }',this)">删除</a>
			     
		     </div>
		    </c:forEach>
		    </div>
		    <input type="hidden" name="url" id="videoPath" value=""/>
	   </fieldset>
  </t:formvalid>
 </body>
 
 <script type="text/javascript">
 //删除插件图片
 function deletePluginImg(id,url,obj){
	 $.post("pluginPicController.do?delPluginPic", { id:id, url:url },function(data) {
			   $(obj).parent("div").remove();
	 });
 }
 function showImage() { 
		var serverIp = $("#fileServer").val();
		var value = $("#coverPath_a").val();
		var videoPath = $("#videoPath").val();
		var url = value+","+videoPath;
		$("#videoPath").val(url);
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
				html = html+'<a href="'+aurl+'"><img style="width:80px;height:100px;background-color:#ccc;border:1px solid #333" src="'+path+'"/></a>';
			}
			$("#previewImg").html(html);
		}
		$("#previewImg a").imageZoom();
	}
 $(".aa a").imageZoom();
 </script>