<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>部门信息</title>
  <t:base type="jquery,easyui,tools"></t:base>
  <script type="text/javascript">
	$(function() {
		$('#cc').combotree({
			url : 'departController.do?getPDepart'
		});
	});
</script>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" layout="div" dialog="true" action="departController.do?saveDepart">
   <input name="idOld" type="hidden" value="${depart.id }">
   <fieldset class="step">
	<c:if test="${depart.id!=null }">
     <div class="form">
	     <label class="Validform_label">部门编码:</label>
	     <input name="id" class="inputxt" value="${depart.id }" readonly="readonly">
    </div>
    </c:if>
    <div class="form">
	     <label class="Validform_label">部门名称:</label>
	     <input name="departName" class="inputxt" value="${depart.departName }" datatype="*2-50">
	     <span class="Validform_checktip">部门名称在2~50位字符</span>
    </div>
    <div class="form">
	     <label class="Validform_label">类别:</label>
	     <c:if test="${depart.id == null }">
		     <select name="departType" id="departType" class="easyui-combobox" style="width:130px" disable="true">
			    <option value="0" <c:if test="${depart.departType==0 }">selected</c:if>> 部门    </option>
			    <option value="1" <c:if test="${depart.departType==1 }">selected</c:if>> CP商  </option>
			    <option value="2" <c:if test="${depart.departType==2 }">selected</c:if>>渠道   </option>
			    <option value="3" <c:if test="${depart.departType==3 }">selected</c:if>> 运营商   </option>
			   </select>
	     </c:if>  
	       <c:if test="${depart.id != null }">
	        <c:if test="${depart.departType==0 }">部门</c:if>
	        <c:if test="${depart.departType==1 }">CP商</c:if>
	        <c:if test="${depart.departType==2 }">渠道</c:if>
	        <c:if test="${depart.departType==3 }">运营商</c:if>
	        <input type="hidden" name="departType" value="${depart.departType }"/>
	     </c:if>
	  
    </div>
        <div class="form">
	     <label class="Validform_label">部门代码:</label>
	     <input name="departCode" class="inputxt" value="${depart.departCode }" datatype="*2-20" <c:if test="${depart.id!=null }">readonly="readonly"</c:if>>
	     <span class="Validform_checktip">部门代码在2~20位字符</span>
    </div>
    <div class="form">
	     <label class="Validform_label">职能描述:</label>
	     <input name="description" class="inputxt" value="${depart.description }">
    </div>
    <div class="form">
	     <label class="Validform_label">上级部门:</label>
	     <input id="cc" name="TSPDepart.id" value="${depart.TSPDepart.id}" style="width:155px;" <c:if test="${depart.id!=null }">disabled="disabled"</c:if>>
    </div>
     <div class="form">
	     <label class="Validform_label">排序:</label>
	     <input name="departOrder" class="inputxt"  value="${depart.departOrder}"  datatype="numrange">
	     <span class="Validform_checktip">排序输入整型数字，且不为空</span>
    </div>
   </fieldset>
  </t:formvalid>
 </body>
</html>
