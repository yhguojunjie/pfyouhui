<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>操作信息</title>
  <t:base type="jquery,easyui,tools"></t:base>
  <script type="text/javascript">
  $(function() { 
	  $('#tsIconId').combobox({
		url:'iconController.do?getComboxIcons&id=${operation.TSIcon.id}',
		valueField:'id',  
	    textField:'text'
	});
  });
  </script>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" layout="div" dialog="true" action="operationController.do?saveOperation">
    <input name="id" type="hidden" value="${operation.id}">
    <fieldset class="step">
     <div class="form">
      <label class="Validform_label"> 操作名称: </label>
      <input name="operationName" class="inputxt"  value="${operation.operationName}" datatype="s2-20">
      <span class="Validform_checktip"> 操作名称范围2~20位字符</span>
     </div>
     <div class="form">
      <label class="Validform_label">  操作代码:  </label>
      <input name="operationCode" class="inputxt"  value="${operation.operationCode}">
     </div>
     <div class="form">
       <label class="Validform_label">  图标名称: </label>
       <select id="tsIconId" name="TSIcon.id" style="width:155px" class="easyui-combobox"></select>
       <%-- <select name="TSIcon.id">
       <c:forEach items="${iconlist}" var="icon">
        <option value="${icon.id}" <c:if test="${icon.id==function.TSIcon.id }">selected="selected"</c:if>>
         ${icon.iconName}
        </option>
       </c:forEach>
        </select> --%>
     </div>
     <%--   update-start--Author:anchao  Date:20130415 for：按钮权限控制--%>
	 <input name="TSFunction.id" value="${functionId}" type="hidden">
     <%--   update-start--Author:anchao  Date:20130415 for：按钮权限控制--%>
     <div class="form">
       <label class="Validform_label"> 状态: </label>
      <input name="status" class="inputxt"  value="${operation.status}" datatype="n" ignore="ignore">
      <span class="Validform_checktip">必须为数字</span>
     </div>
    </fieldset>
  </t:formvalid>
 </body>
</html>
