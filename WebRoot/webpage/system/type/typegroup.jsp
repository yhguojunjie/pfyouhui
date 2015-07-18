<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>字典分类</title>
  <t:base type="jquery,easyui,tools"></t:base>
 
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" layout="div" dialog="true" action="typeController.do?saveTypeGroup" >
   <input name="idOld" type="hidden" value="${typegroup.id}">
   <fieldset class="step">
     <div class="form">
       <label class="Validform_label">分类编码: </label>
       <input name="id" class="inputxt" ajaxurl="typeController.do?checkTypeID&id=${typegroup.id}"  value="${typegroup.id}" datatype="s4-4">
       <span class="Validform_checktip">编码在4位字符,且不为空</span>
    </div>    
    <div class="form">
       <label class="Validform_label">分类名称:</label>
       <input name="typeName" class="inputxt"  value="${typegroup.typeName}" datatype="s3-100">
       <span class="Validform_checktip">名称范围在3~100位字符,且不为空</span>
    </div>
    <div class="form">
       <label class="Validform_label">分类说明:</label>
       <% request.setAttribute("vEnter", "\r\n"); //换行在el中不能用常量表达    %>      
       <textarea rows="6" class="inputxt" style="width:80%" name="typeMemo" id="note">${fn:replace(typegroup.typeMemo,"<br/>",vEnter)}</textarea>
        <!-- <input name="typeMemo" class="inputxt" style="width:280px" value="${typegroup.typeMemo}" datatype="s0-200"> 
       <span class="Validform_checktip">说明范围在0~200位字符,允许为空</span>-->
    </div>
     <div class="form">
       <label class="Validform_label">Name1标签: </label>
       <input name="codeName1Label" class="inputxt" value="${typegroup.codeName1Label}" datatype="s0-50">
       <span class="Validform_checktip">标签范围在0~50位字符，允许空</span>
    </div>
    <div class="form">
       <label class="Validform_label">Name2标签: </label>
       <input name="codeName2Label" class="inputxt" value="${typegroup.codeName2Label}" datatype="s0-50">
       <span class="Validform_checktip">标签范围在0~50位字符，允许空</span>
    </div>
    <div class="form">
       <label class="Validform_label">Name3标签: </label>
       <input name="codeName3Label" class="inputxt" value="${typegroup.codeName3Label}" datatype="s0-50">
       <span class="Validform_checktip">标签范围在0~50位字符，允许空</span>
    </div>
    <div class="form">
       <label class="Validform_label">Name4标签: </label>
       <input name="codeName4Label" class="inputxt" value="${typegroup.codeName4Label}" datatype="s0-50">
       <span class="Validform_checktip">标签范围在0~50位字符，允许空</span>
    </div>
    <div class="form">
     <label class="Validform_label">操作控制： </label>
     <c:choose>
	     <c:when test="${fn:length(typegroup.id)<=0}">
		     <input name="allowAdd"  class="rt2" id="allowAdd" type="checkbox" checked="checked" />允许新建&nbsp;&nbsp;&nbsp;&nbsp;
		     <input name="allowModify" class="rt2" id="allowModify"  type="checkbox"  checked="checked" />允许修改&nbsp;&nbsp;&nbsp;&nbsp;
		     <input name="allowDelete" class="rt2" id="allowDelete"  type="checkbox" checked="checked" />允许删除               
	     </c:when>
	     <c:otherwise>          
		     <input name="allowAdd" class="rt2" id="allowAdd" type="checkbox" <c:if test="${typegroup.allowAdd}">checked</c:if> />允许新建&nbsp;&nbsp;&nbsp;&nbsp;
		     <input name="allowModify" class="rt2" id="allowModify"  type="checkbox" <c:if test="${typegroup.allowModify}">checked</c:if>  />允许修改&nbsp;&nbsp;&nbsp;&nbsp;
		     <input name="allowDelete" class="rt2" id="allowDelete"  type="checkbox" <c:if test="${typegroup.allowDelete}">checked</c:if> />允许删除               
    	 </c:otherwise>
     </c:choose>
     <span class="Validform_checktip"></span>
   </div>
   </fieldset>
  </t:formvalid>
 </body>
</html>
