<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>Plugin</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <link rel="stylesheet" href="plug-in/imageZoom/jquery.imageZoom.css" type="text/css" />
  <script type="text/javascript" src="plug-in/imageZoom/jquery.imageZoom.js"></script>
  <link rel="stylesheet" href="plug-in/kindeditor/themes/default/default.css" />
  <script charset="utf-8" src="plug-in/kindeditor/kindeditor-min.js"></script>
  <script charset="utf-8" src="plug-in/kindeditor/lang/zh_CN.js"></script>
 </head>
 <body style="overflow-x: hidden" >
  <input type="hidden" id="fileServer" value="${server }" />
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="templateController.do?savePlugin" >
  		<input class="inputxt" id="status" name="status" ignore="ignore" value="0" hidden="hidden">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">插件名称:</label>
		      <input class="inputxt" id="name" name="name" ignore="false" value="" datatype="*2-50">
		      <span class="Validform_checktip">插件名称围在2~50位字符</span>
		    </div>
		    <div class="form">
		      <label class="Validform_label">类型:</label>
		        <input type="radio"  name="type" value="1" checked="checked"> &nbsp;即插即用型
		        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	     		<input type="radio"  name="type" value="2"> &nbsp;用户定制
		    </div>
		    <div class="form">
		       <label class="Validform_label">开发者:</label>
		       <input name="userId" type="hidden" value="" id="userId">
	           <input name="nickName" class="inputxt" id="nickName" readonly="readonly" />
	           <t:choose hiddenName="userId" hiddenid="userId" url="templateController.do?developers" name="developersList" icon="icon-choose" title="开发者列表" textname="nickName"  isclear="true"></t:choose>
		      <span class="Validform_checktip"></span>
		    </div>
		    <div class="form">
				<label class="Validform_label">插件图标:</label> 
				<input class="inputxt" id="coverPath_a" name="icon" value="" ignore="false" datatype="*" readonly="readonly" hidden="true"/> 
				<span class="Validform_checktip">插件图标路径不能为空</span>
				<div id="previewImg" style="margin-top:15px"></div>
				
				<t:upload name="fielsicon" id="fielsicon" buttonText="上传到服务器"
				dialog="false" auto="true" multi="false"
				uploader="systemController.do?saveFiles&uploadLocation=server"
				extend="pic" queueID="filediv" showUrlId="coverPath_a"
				onUploadSuccess="showImage()"></t:upload>
			   <span id="filediv"></span>
			   <span>建议尺寸:200px*200px(或等比)，30KB</span>
		    </div>
			<div class="form">
		      <label class="Validform_label">插件描述:</label>
		      <input class="inputxt" id="description" name="description" style="width: 500px" ignore="false" value="" datatype="*10-250">
		      <span class="Validform_checktip" style="float: right;">插件描述在2~250位字符</span>
		    </div>
		    <div class="form">
		      <label class="Validform_label">成本价:</label>
		      <input class="inputxt" id="price" name="price" ignore="false" value="0" datatype="n,float">￥/月
		      <span class="Validform_checktip"></span>
		    </div>
		    <input class="inputxt" id="valid" name="valid" ignore="false" value="1" datatype="n" hidden="hidden">
		    <!-- 
		    <div class="form">
		      <label class="Validform_label">有效期:</label>
		      <input class="inputxt" id="valid" name="valid" ignore="false" value="1" datatype="n" hidden="hidden">月
		      <span class="Validform_checktip">填写整数</span>
		    </div>
		     -->
		    <div class="form">
		      <label class="Validform_label">使用说明:</label>
		       <% request.setAttribute("vEnter", "\r\n"); //换行在el中不能用常量表达    %>    
		      <textarea rows="6" class="inputxt" style="width:80%" name="guide" id="guide">${fn:replace(typegroup.typeMemo,"<br/>",vEnter)}</textarea>
		      <span class="Validform_checktip"></span>
		    </div>
		    <div class="form">
		      <label class="Validform_label">视频录像地址:</label>  
		       <input class="inputxt" id="videoUrl" name="videoUrl" ignore="false" style="width: 500px"  datatype="*0-200" value="${pluginPage.videoUrl }">
		      <span class="Validform_checktip" style="float: right;">输入例如http://www.hudongtui.com</span>
		    </div>
		    <div class="form">
		      <label class="Validform_label">新增活动地址:</label>  
		       <input class="inputxt" id="actAddUrl" name="actAddUrl" ignore="false" style="width: 500px"  datatype="*0-100" value="${pluginPage.actAddUrl }">
		      <span class="Validform_checktip" style="float: right;">输入例如http://www.hudongtui.com</span>
		    </div>
		    <div class="form">
		      <label class="Validform_label">编辑活动地址:</label>  
		       <input class="inputxt" id="actEditUrl" name="actEditUrl" ignore="false" style="width: 500px"  datatype="*0-100" value="${pluginPage.actEditUrl }">
		      <span class="Validform_checktip" style="float: right;">输入例如http://www.hudongtui.com</span>
		    </div>
		    <div class="form">
		      <label class="Validform_label">活动访问地址:</label>  
		       <input class="inputxt" id="actAccessUrl" name="actAccessUrl" ignore="false" style="width: 500px"  datatype="*0-100" value="${pluginPage.actAccessUrl }">
		      <span class="Validform_checktip" style="float: right;">输入例如http://www.hudongtui.com</span>
		    </div>
		    <!-- 
		    <div class="form">
		      <label class="Validform_label">演示地址:</label>  
		       <input class="inputxt" id="showUrl" name="showUrl" ignore="false" style="width: 500px"  datatype="*0-150" value="${pluginPage.showUrl }">
		      <span class="Validform_checktip" style="float: right;">输入例如http://www.hudongtui.com</span>
		    </div>
		     -->
		    <div class="form">
		      <label class="Validform_label">演示活动ID:</label>  
		       <input class="inputxt" id="showActId" name="showActId" ignore="false"  datatype="n0-10" value="${pluginPage.showActId }">
		      <span class="Validform_checktip">输入整数</span>
		    </div>
		    <div class="form">
		      <label class="Validform_label">版本信息:</label>  
		       <input class="inputxt" id="version" name="version" ignore="false" style="width: 500px"  datatype="*0-20" value="${pluginPage.version }">
		      <span class="Validform_checktip" style="float: right;"></span>
		    </div>
		    <div class="form">
		      <label class="Validform_label">版权信息:</label>  
		       <input class="inputxt" id="copyright" name="copyright" ignore="false" style="width: 500px"  datatype="*0-50" value="${pluginPage.copyright }">
		      <span class="Validform_checktip" style="float: right;"></span>
		    </div>
		     <div class="form">
				<label class="Validform_label">代码文件:</label> 
				<input class="inputxt" id="filePath" name=filePath value="${ pluginPage.filePath}" ignore="ignore" readonly="readonly" hidden="true"/>
				<span class="Validform_checktip"></span><span id="filePathmsg"></span>
				<t:upload name="fielsicons" id="fielsicons" buttonText="上传代码文件"
				dialog="false" auto="true" multi="true" 
				uploader="systemController.do?saveFiles&uploadLocation=server"
				extend="office" queueID="filediv1" showUrlId="filePath"
				onUploadSuccess="filePath(d,file,response)" fileSizeLimit="500"></t:upload>
			   <span class="Validform_checktip">文件格式为zip</span>
			   <span id="filediv1"></span>		         
		    </div>
	   </fieldset>
  </t:formvalid>
 </body>
 
 <script type="text/javascript">
 
 var editor;
	KindEditor.ready(function(K) {
		editor = K.create('textarea[name="guide"]', {
			width : '600px',
			height : '200px',
			resizeType : 2,
			allowPreviewEmoticons : false,
			allowFileManager : false,
			urlType : 'absolute',
			allowImageUpload : true,
			afterChange : function(){
				this.sync();
			},
			afterBlur : function(){
				this.sync();
			},
			uploadJson :'mpWxJokeController.do?editerImg&uploadLocation=server',
			items : [
				'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
				'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
				'insertunorderedlist', '|', 'emoticons', 'image', 'link','fullscreen','media']
		});
	});
	
 function filePath(d,file,response){
	 $("#filePathmsg").after("<span><font color='red'>文件："+file.name+""+ d.msg+"</font></span>"); 
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
			$("#previewImg")
					.html(
							'<a href="'+aurl+'"><img id="preImg" style="width:90px;height:90px;background-color:#ccc;border:1px solid #333" src="'+path+'"/></a>');
		}
		$("#previewImg a").imageZoom();
	}
 </script>