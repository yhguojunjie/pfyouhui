<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>录入</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
   <link rel="stylesheet" href="plug-in/imageZoom/jquery.imageZoom.css" type="text/css" />
  <script type="text/javascript" src="plug-in/imageZoom/jquery.imageZoom.js"></script>
 </head>
 <body style="overflow-y: hidden" scroll="no">
     <input type="hidden" id="fileServer" value="${server }" />
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="agentBrandController.do?saveAgentBrand">
		<input id="id" name="id" type="hidden" value="${agentBrandPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">排序:</label>
		      <input class="inputxt" id="seq" name="seq" ignore="false"
					 value="${agentBrandPage.seq}" datatype="n">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
			 <c:if test="${agentBrandPage.userId==null}">
		      <label class="Validform_label">用户id:</label>
		      <input class="inputxt" id="userId"    name="userId" ignore="false"
					 value="${agentBrandPage.userId}" datatype="n">
			 <t:choose hiddenName="userId" hiddenid="id" url="agentBrandController.do?userList" name="userList" icon="icon-choose" title="用户列表" textname="userId,nickName" isclear="true"></t:choose>
		      <span class="Validform_checktip"></span>
		      </c:if>
		       <c:if test="${agentBrandPage.userId!=null}">
		      <label class="Validform_label">用户id:</label>
		      <input class="inputxt" id="userId"  readonly="readonly"  name="userId" ignore="false"
					 value="${agentBrandPage.userId}" >
			  <span class="Validform_checktip"></span>
		      </c:if>
		    </div>
		    <c:if test="${agentBrandPage.nickName==null}">
			<div class="form">
		      <label class="Validform_label">用户呢称:</label>
		      <input class="inputxt"  id="nickName" name="nickName" ignore="false"
					 value="${agentBrandPage.nickName}">
		      <span class="Validform_checktip"></span>
		    </div>
		    </c:if>
		    <c:if test="${agentBrandPage.nickName!=null}">
			<div class="form">
		      <label class="Validform_label">用户呢称:</label>
		      <input class="inputxt" readonly="readonly" id="nickName" name="nickName" ignore="false"
					 value="${agentBrandPage.nickName}">
		      <span class="Validform_checktip"></span>
		    </div>
		    </c:if>
		    <div class="form">
				<label class="Validform_label">品牌logo:</label> 
				<input class="inputxt" id="wxLogo_a" name="logo" value="${agentBrandPage.logo }" ignore="false" datatype="*" readonly="readonly" hidden="true"/> 
				<span class="Validform_checktip">插件图标路径不能为空</span>
				<div id="wxLogo_previewImg" style="margin-top:15px">
				<a href="${server }/${agentBrandPage.logo}"><img style="width:200px;height:90px;background-color:#ccc;border:1px solid #333" src="${server }/${agentBrandPage.logo}"/></a>
				</div>
				<t:upload name="wxLogo_fielsicon" id="wxLogo_fielsicon" buttonText="上传品牌logo"
				dialog="false" auto="true" multi="false"
				uploader="systemController.do?saveFiles&uploadLocation=server"
				extend="pic" queueID="wxLogo_filediv" showUrlId="wxLogo_a"
				onUploadSuccess="wxLogoShowImage()"></t:upload>
			   <span id="wxLogo_filediv"></span>
			   <span>建议尺寸:2000px*800px(或等比)，500KB</span>
		    </div>
		    <div class="form">
		      <label class="Validform_label">状态:</label>
		     <c:if test="${agentBrandPage.hideState==null}">   
			  <input   id="need" ignore="false" name="hideState" type="radio" value="0"  Checked />显示
			  &nbsp; &nbsp; &nbsp; &nbsp;
              <input   id="need" ignore="false" name="hideState" type="radio" value="1"  />隐藏
             </c:if> 
		     <c:if test="${agentBrandPage.hideState=='0'}">   
			  <input   id="need" ignore="false" name="hideState" type="radio" value="0"  Checked />显示
			  &nbsp; &nbsp; &nbsp; &nbsp;
              <input   id="need" ignore="false" name="hideState" type="radio" value="1"  />隐藏
             </c:if>
             <c:if test="${agentBrandPage.hideState=='1'}">   
			  <input   id="need" ignore="false" name="hideState" type="radio" value="0"  />显示
			  &nbsp; &nbsp; &nbsp; &nbsp;
              <input   id="need" ignore="false" name="hideState" type="radio" value="1"  Checked />隐藏
             </c:if> 
		      <span class="Validform_checktip"></span>
		    </div>
	   </fieldset>
  </t:formvalid>
 </body>
 <script type="text/javascript">
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