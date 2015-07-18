<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<c:forEach items="${operationList}" var="operation" >
	 <c:if test="${fn:contains(operationcodes, operation.operationCode)}"> 
	 <span class="icon ${operation.TSIcon.iconClas}">&nbsp;</span><input style="width:20px;" type="checkbox" name="operationCheckbox" value="${operation.operationCode}" checked="checked"/>${operation.operationName}
	 </c:if>
	  <c:if test="${!fn:contains(operationcodes, operation.operationCode)}"> 
	 <span class="icon group_add">&nbsp;</span><input style="width:20px;" type="checkbox" name="operationCheckbox" value="${operation.operationCode}" />${operation.operationName}
	 </c:if>
	<br>
</c:forEach>
<script type="text/javascript">
function submitOperation() {
	var functionId = "${functionId}";
	var roleId = $("#rid").val();
	var operationcodes = "";
	$("input[name='operationCheckbox']").each(function(i){
		   if(this.checked){
			   operationcodes+=this.value+",";
		   }
	 });
	doSubmit("roleController.do?updateOperation&functionId=" + functionId + "&roleId=" + roleId+"&operationcodes="+operationcodes);
}
</script>
