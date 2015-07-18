<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>Banner</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <link rel="stylesheet" href="plug-in/imageZoom/jquery.imageZoom.css" type="text/css" />
  <script type="text/javascript" src="plug-in/imageZoom/jquery.imageZoom.js"></script>
 </head>
 <body style="overflow-x: hidden" scroll="no">
   <input type="hidden" id="fileServer" value="${server }" />
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="bannerController.do?saveBanner">
		<input id="id" name="id" type="hidden" value="${bannerPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">顺序:</label>
		      <input class="inputxt" id="seq" name="seq" ignore="false"
					 value="${bannerPage.seq}" datatype="*0-9">
		      <span class="Validform_checktip"></span>
		    </div>
		    <div class="form">
		         <c:if test="${bannerPage.pcLogo==null}">
				<label class="Validform_label">pc版本banner:</label> 
				<input class="inputxt" id="pcLogo_a" name="pcLogo" value="${bannerPage.pcLogo }" ignore="false" datatype="*" readonly="readonly" hidden="true"/> 
				<span class="Validform_checktip">插件图标路径不能为空</span>
				<div id="pcLogo_previewImg" style="margin-top:15px">
				
				</div>
				</c:if> 
				 <c:if test="${bannerPage.pcLogo!=null}">
				<label class="Validform_label">pc版本banner:</label> 
				<input class="inputxt" id="pcLogo_a" name="pcLogo" value="${bannerPage.pcLogo }" ignore="false" datatype="*" readonly="readonly" hidden="true"/> 
				<span class="Validform_checktip">插件图标路径不能为空</span>
				<div id="pcLogo_previewImg" style="margin-top:15px">
				<a href="${server }/${bannerPage.pcLogo}"><img style="width:200px;height:90px;background-color:#ccc;border:1px solid #333" src="${server }/${bannerPage.pcLogo}"/></a>
			
				</div>
				</c:if> 
				
				<t:upload name="pcLogo_fielsicon" id="pcLogo_fielsicon" buttonText="上传pcLogo"
				dialog="false" auto="true" multi="false"
				uploader="systemController.do?saveFiles&uploadLocation=server"
				extend="pic" queueID="pcLogo_filediv" showUrlId="pcLogo_a"
				onUploadSuccess="pcLogoShowImage()"></t:upload>
			   <span id="pcLogo_filediv"></span>
			   <span>建议尺寸：1190px*400px（或等比），小于150KB</span>
			 
			   
		    </div>
		    <c:if test="${(bannerPage.linkType==null)||(bannerPage.linkType==1)}">
			<div class="form" id="pcLinkk" style="display:none">
		      <label class="Validform_label">pc版链接:</label>
		      <input class="inputxt" id="pcLink" name="pcLink" ignore="false"  datatype="*0-150"
					 value="${bannerPage.pcLink}">
		      <span class="Validform_checktip"></span>
		    </div>
		    </c:if> 
		     <c:if test="${bannerPage.linkType==2}">
			<div class="form" id="pcLinkk" style="">
		      <label class="Validform_label">pc版链接:</label>
		      <input class="inputxt" id="pcLink" name="pcLink" ignore="false"  datatype="*0-150"
					 value="${bannerPage.pcLink}">
		      <span class="Validform_checktip"></span>
		    </div>
		    </c:if>
		   <div class="form">
		   <c:if test="${bannerPage.wxLogo==null}">
				<label class="Validform_label">wx版本banner:</label> 
				<input class="inputxt" id="wxLogo_a" name="wxLogo" value="${bannerPage.wxLogo }" ignore="false" datatype="*" readonly="readonly" hidden="true"/> 
				<span class="Validform_checktip">插件图标路径不能为空</span>
				<div id="wxLogo_previewImg" style="margin-top:15px">
				</div>
			</c:if> 
			 <c:if test="${bannerPage.wxLogo!=null}">
				<label class="Validform_label">wx版本banner:</label> 
				<input class="inputxt" id="wxLogo_a" name="wxLogo" value="${bannerPage.wxLogo }" ignore="false" datatype="*" readonly="readonly" hidden="true"/> 
				<span class="Validform_checktip">插件图标路径不能为空</span>
				<div id="wxLogo_previewImg" style="margin-top:15px">
				<a href="${server }/${bannerPage.wxLogo}"><img style="width:200px;height:90px;background-color:#ccc;border:1px solid #333" src="${server }/${bannerPage.wxLogo}"/></a>
			
				</div>
			</c:if>	
				<t:upload name="wxLogo_fielsicon" id="wxLogo_fielsicon" buttonText="上传wxLogo"
				dialog="false" auto="true" multi="false"
				uploader="systemController.do?saveFiles&uploadLocation=server"
				extend="pic" queueID="wxLogo_filediv" showUrlId="wxLogo_a"
				onUploadSuccess="wxLogoShowImage()"></t:upload>
			   <span id="wxLogo_filediv"></span>
			   <span>建议尺寸：1190px*400px（或等比），小于150KB</span>
		    </div>
		    <c:if test="${(bannerPage.linkType==null)||(bannerPage.linkType==1)}">
			<div class="form" id="wxLinkk" style="display:none">
		      <label class="Validform_label">wx版链接:</label>
		      <input class="inputxt" id="wxLink" name="wxLink"  ignore="false"  datatype="*0-150"
					 value="${bannerPage.wxLink}">
		      <span class="Validform_checktip"></span>
		    </div>
		    </c:if>	
		      <c:if test="${bannerPage.linkType==2}">
			<div class="form" id="wxLinkk" style="">
		      <label class="Validform_label">wx版链接:</label>
		      <input class="inputxt" id="wxLink" name="wxLink"  ignore="false"  datatype="*0-150"
					 value="${bannerPage.wxLink}">
		      <span class="Validform_checktip"></span>
		    </div>
		    </c:if>
		      <div class="form">
		      <label class="Validform_label">类型:</label>
		      <div id="needradioo"> 
		       <c:if test="${bannerPage.linkType==null}">   
			  <input   id="need" ignore="false" name="linkType" type="radio" value="1"  Checked />模版
			  &nbsp; &nbsp; &nbsp; &nbsp;
              <input   id="need" ignore="false" name="linkType" type="radio" value="2"  />网页
             </c:if> 
		     <c:if test="${bannerPage.linkType=='1'}">   
			  <input   id="need" ignore="false" name="linkType" type="radio" value="1"  Checked />模版
			  &nbsp; &nbsp; &nbsp; &nbsp;
              <input   id="need" ignore="false" name="linkType" type="radio" value="2"  />网页
             </c:if>
             <c:if test="${bannerPage.linkType=='2'}">   
			  <input   id="need" ignore="false" name="linkType" type="radio" value="1"  />模版
			  &nbsp; &nbsp; &nbsp; &nbsp;
              <input   id="need" ignore="false" name="linkType" type="radio" value="2"  Checked />网页
             </c:if>
             </div>
                <span class="Validform_checktip"></span>
		    </div>
		    <c:if test="${(bannerPage.linkType==null)||(bannerPage.linkType==1)}">
		    <div class="form" id="hjj">
		      <label class="Validform_label">模版ID:</label>
		      <input class="inputxt" id="objId" name="objId" readonly="readonly"  ignore="false"  datatype="*0-9"
					 value="${bannerPage.objId}">
			<t:choose   url="bannerController.do?pluginList" name="pluginList" icon="icon-choose" title="模版列表" textname="objId" isclear="true"></t:choose>
		      <span class="Validform_checktip"></span>
		    </div>
		    </c:if>
		    <c:if test="${bannerPage.linkType==2}">
		    <div class="form" id="hjj" style="display:none">
		      <label class="Validform_label">模版ID:</label>
		      <input class="inputxt" id="objId" name="objId" readonly="readonly"  ignore="false"  datatype="*0-9"
					 value="${bannerPage.objId}">
			<t:choose   url="bannerController.do?pluginList" name="pluginList" icon="icon-choose" title="模版列表" textname="objId" isclear="true"></t:choose>
		      <span class="Validform_checktip"></span>
		    </div>
		    </c:if>
	   </fieldset>
  </t:formvalid>
 </body>
 <script type="text/javascript">
 function pcLogoShowImage() {
		var serverIp = $("#fileServer").val();
		var value = $("#pcLogo_a").val();
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
			$("#pcLogo_previewImg").html('<a href="'+aurl+'"><img style="width:200px;height:90px;background-color:#ccc;border:1px solid #333" src="'+path+'"/></a>');
		}
		$("#pcLogo_previewImg a").imageZoom();
	}
	 $("#pcLogo_previewImg a").imageZoom();
function wxLogoShowImage() {
		var serverIp = $("#fileServer").val();
		var value = $("#wxLogo_a").val();
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
			$("#wxLogo_previewImg").html('<a href="'+aurl+'"><img  style="width:200px;height:90px;background-color:#ccc;border:1px solid #333" src="'+path+'"/></a>');
		}
		$("#wxLogo_previewImg a").imageZoom();
	}
	$("#wxLogo_previewImg a").imageZoom();

	 $(function(){
		  $("#needradioo :radio").change(function ()
		            {
		         var bannerState=$('input:radio[name="linkType"]:checked').val();
			     if(bannerState==1){
			    	 $("#pcLinkk").hide();
			    	 $("#wxLinkk").hide();
			    	 $("#objId").show();
			    	 $("#hjj").show();
			     }else{
			    	 $("#pcLinkk").show();
			    	 $("#wxLinkk").show();
			    	 $("#objId").hide();
			    	 $("#hjj").hide();
			    	 
			     }
		            });
		  }); 
	 
	
 </script>