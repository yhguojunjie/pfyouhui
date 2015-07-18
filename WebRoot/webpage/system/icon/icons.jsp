<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>图标信息</title>
  <t:base type="jquery,easyui,tools"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" layout="div" dialog="true">
   <input name="iconId" type="hidden" value="${icon.iconId}">
   <fieldset class="step">
    <div class="form">
      <label class="Validform_label">图标名称:</label>
      <input name="iconName" datatype="s2-10" id="iconName" class="inputxt" >
      <span class="Validform_checktip">名称范围2~10位字符,且不为空</span>
    </div>
    <div class="form">
      <label class="Validform_label" > 图标类型:</label>
   	  <select name="iconType" id="iconType">
   	    <option value="1">系统图标</option>
   	    <option value="2">菜单图标</option>
     </select>
    </div>
      <div class="form" id="filediv">
    </div>
    <div class="form">
     <t:upload name="file_upload" uploader="iconController.do?saveIcon" extend="*.png;" id="file_upload" formData="iconName,iconType"></t:upload>
    </div>
   </fieldset>
  </t:formvalid>
 </body>
</html>
