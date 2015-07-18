<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <t:base type="jquery,easyui,tools"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" layout="div" dialog="true" action="typeController.do?saveType">
   <input name="id" type="hidden" " value="${type.id }">
   <input name="TSCodeType.id" type="hidden" value="${typegroupid}">
   <fieldset class="step">
    <div class="form">
	     <label class="Validform_label">代码:</label>
	     <input name="code" class="inputxt"  value="${type.code}"  datatype="s1-20" width="60">
	     <span class="Validform_checktip">代码在1~20位字符,且不为空</span>
    </div>   
    <div class="form">
	     <label class="Validform_label">名称:</label>
	     <input name="codeName" class="inputxt"  value="${type.codeName }" datatype="s2-100" width="80">
	     <span class="Validform_checktip">名称范围在2~100位字符,且不为空</span>
    </div>
    <c:if test="${fn:length(typegroup.codeName1Label)>0}">
	    <div class="form">
		     <label class="Validform_label">${typegroup.codeName1Label }:</label>
		     <input name="codeName1" class="inputxt"  value="${type.codeName1}"  datatype="s0-100">
		     <span class="Validform_checktip">${typegroup.codeName1Label }在0~200位字符,允许为空</span>
	    </div>
 	</c:if>
 	 <c:if test="${fn:length(typegroup.codeName2Label)>0}">
	    <div class="form">
		     <label class="Validform_label">${typegroup.codeName2Label }:</label>
		     <input name="codeName2" class="inputxt"  value="${type.codeName2}"  datatype="s0-100">
		     <span class="Validform_checktip">${typegroup.codeName2Label }在0~200位字符,允许为空</span>
	    </div>
 	</c:if>
 	 <c:if test="${fn:length(typegroup.codeName3Label)>0}">
	    <div class="form">
		     <label class="Validform_label">${typegroup.codeName3Label }:</label>
		     <input name="codeName3" class="inputxt"  value="${type.codeName3}"  datatype="s0-100">
		     <span class="Validform_checktip">${typegroup.codeName3Label }在0~200位字符,允许为空</span>
	    </div>
 	</c:if>
 	 <c:if test="${fn:length(typegroup.codeName4Label)>0}">
	    <div class="form">
		     <label class="Validform_label">${typegroup.codeName4Label }:</label>
		     <input name="codeName4" class="inputxt"  value="${type.codeName4}"  datatype="s0-100">
		     <span class="Validform_checktip">${typegroup.codeName4Label }在0~200位字符,允许为空</span>
	    </div>
 	</c:if>
    <div class="form">
	     <label class="Validform_label">说明:</label>
	     <input name="codeMemo" class="inputxt" style="width:280px" value="${type.codeMemo}"  datatype="s0-200">
	     <span class="Validform_checktip">说明在0~200位字符,允许为空</span>
    </div>
    <div class="form">
	     <label class="Validform_label">排序:</label>
	     <input name="codeOrder" class="inputxt" style="width:280px" value="${type.codeOrder}"  datatype="numrange">
	     <span class="Validform_checktip">排序输入整型数字，且不为空</span>
    </div>    
   </fieldset>
  </t:formvalid>
 </body>
</html>
