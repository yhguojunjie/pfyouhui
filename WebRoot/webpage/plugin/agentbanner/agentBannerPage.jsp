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
   <input type="hidden" id="fileServer" value="${server }" />
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="agentBannerController.do?saveAgentBanner">
		<input id="id" name="id" type="hidden" value="${agentBannerPage.id }">
		<fieldset class="step">
		 <c:if test="${agentBannerPage.seq==null}">
			<div class="form">
		      <label class="Validform_label">顺序:</label>
		      <input class="inputxt" id="seq" name="seq" ignore="false"
					 value="${agentBannerPage.seq}" datatype="n">
		      <span class="Validform_checktip"></span>
		    </div>
		 </c:if>

		    <div class="form">
		     	<c:if test="${agentBannerPage.pcLogo==null}">
				<label class="Validform_label">pc版本banner:</label> 
				
				<input class="inputxt" id="pcLogo_a" name="pcLogo" value="${agentBannerPage.pcLogo }" value="" ignore="false" datatype="*" readonly="readonly" hidden="true"/> 
				
				<span class="Validform_checktip">插件图标路径不能为空</span>
				<div id="pcLogo_previewImg" style="margin-top:15px">
				
				</div>
				  </c:if>
				  	<c:if test="${agentBannerPage.pcLogo!=null}">
				<label class="Validform_label">pc版本banner:</label> 
				
				<input class="inputxt" id="pcLogo_a" name="pcLogo" value="${agentBannerPage.pcLogo }" value="" ignore="false" datatype="*" readonly="readonly" hidden="true"/> 
				
				<span class="Validform_checktip">插件图标路径不能为空</span>
				<div id="pcLogo_previewImg" style="margin-top:15px">
				<a href="${server }/${agentBannerPage.pcLogo}"><img style="width:200px;height:90px;background-color:#ccc;border:1px solid #333" src="${server }/${agentBannerPage.pcLogo}"/></a>
			
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
		
          <c:if test="${agentBannerPage.sbannerId==null}">
			<div class="form">
		      <label class="Validform_label">pc版链接:</label>
		      <input class="inputxt" id="pcLink" name="pcLink" ignore="false"  datatype="*0-150"
					 value="${agentBannerPage.pcLink}">
		      <span class="Validform_checktip"></span>   
		    </div>
		    </c:if>
		    
		   <div class="form">
		    <c:if test="${agentBannerPage.wxLogo==null}">
				<label class="Validform_label">wx版本banner:</label>
				 
				<input class="inputxt" id="wxLogo_a" name="wxLogo"  value="${agentBannerPage.wxLogo }" ignore="false" datatype="*" readonly="readonly" hidden="true"/> 
				
				<span class="Validform_checktip">插件图标路径不能为空</span>
				
				<div id="wxLogo_previewImg" style="margin-top:15px"></div>
				</c:if> 
				<c:if test="${agentBannerPage.wxLogo!=null}">
				<label class="Validform_label">wx版本banner:</label>
				 
				<input class="inputxt" id="wxLogo_a" name="wxLogo"  value="${agentBannerPage.wxLogo }" ignore="false" datatype="*" readonly="readonly" hidden="true"/> 
				
				<span class="Validform_checktip">插件图标路径不能为空</span>
				
				<div id="wxLogo_previewImg" style="margin-top:15px">
				<a href="${server }/${agentBannerPage.wxLogo}"><img style="width:200px;height:90px;background-color:#ccc;border:1px solid #333" src="${server }/${agentBannerPage.wxLogo}"/></a>
			
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
		 
		     <c:if test="${agentBannerPage.sbannerId==null}">
			<div class="form">
		      <label class="Validform_label">wx版链接:</label>
		      <input class="inputxt" id="wxLink" name="wxLink"  ignore="false"  datatype="*0-150"
					 value="${agentBannerPage.wxLink}">
		      <span class="Validform_checktip"></span>
		    </div>
		     </c:if>
		     <c:if test="${agentBannerPage.hideState==null}">
		    <div class="form">
		      <label class="Validform_label">状态:</label>
		     <c:if test="${agentBannerPage.hideState==null}">   
			  <input   id="need" ignore="false" name="hideState" type="radio" value="0"  Checked />显示
			  &nbsp; &nbsp; &nbsp; &nbsp;
              <input   id="need" ignore="false" name="hideState" type="radio" value="1"  />隐藏
             </c:if> 
		     <c:if test="${agentBannerPage.hideState=='0'}">   
			  <input   id="need" ignore="false" name="hideState" type="radio" value="0"  Checked />显示
			  &nbsp; &nbsp; &nbsp; &nbsp;
              <input   id="need" ignore="false" name="hideState" type="radio" value="1"  />隐藏
             </c:if>
             <c:if test="${agentBannerPage.hideState=='1'}">   
			  <input   id="need" ignore="false" name="hideState" type="radio" value="0"  />显示
			  &nbsp; &nbsp; &nbsp; &nbsp;
              <input   id="need" ignore="false" name="hideState" type="radio" value="1"  Checked />隐藏
             </c:if> 
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
 </script>