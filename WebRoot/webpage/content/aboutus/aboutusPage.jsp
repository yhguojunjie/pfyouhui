<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>Aboutus</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <link rel="stylesheet" href="plug-in/kindeditor/themes/default/default.css" />
  <script charset="utf-8" src="plug-in/kindeditor/kindeditor-min.js"></script>
  <script charset="utf-8" src="plug-in/kindeditor/lang/zh_CN.js"></script>
 </head>
 <body style="overflow: hidden;" scroll="no" >
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="aboutusController.do?saveAboutus">
		<input id="id" name="id" type="hidden" value="${aboutusPage.id }">
		<fieldset class="step">
		     <div class="form">
		      <label class="Validform_label">内容:</label>
		       <% request.setAttribute("vEnter", "\r\n"); //换行在el中不能用常量表达    %>    
		      <textarea rows="6" class="inputxt" style="width:80%" name="content" id="content">${fn:replace(aboutusPage.content,"<br/>",vEnter)}</textarea>
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">状态:</label>
		      <c:if test="${ not empty aboutusPage.id }">
		      	<input type="radio" name="hideState" value="0" <c:if test="${aboutusPage.hideState =='0'}">checked="checked"</c:if> >显示
		     	<input type="radio" name="hideState" value="1" <c:if test="${aboutusPage.hideState =='1'}">checked="checked"</c:if> >隐藏
		      </c:if>
		      <c:if test="${ empty aboutusPage.id }">
		        <input type="radio" name="hideState" value="0" checked="checked" >显示
		     	<input type="radio" name="hideState" value="1"  >隐藏
		      </c:if>
		    </div>
	   </fieldset>
  </t:formvalid>
 </body>
 <script type="text/javascript">
 
 var editor;
	KindEditor.ready(function(K) {
		editor = K.create('textarea[name="content"]', {
			width : '600px',
			height : '300px',
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
	</script>