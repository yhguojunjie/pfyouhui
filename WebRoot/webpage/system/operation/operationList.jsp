<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:datagrid name="operationList" title="操作管理" actionUrl="operationController.do?operationGrid&functionId=${functionId}" idField="id">
 <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
 <t:dgCol title="操作名称" field="operationname" width="60"></t:dgCol>
 <t:dgCol title="代码" field="operationcode"></t:dgCol>
 <t:dgCol title="权限名称" field="TSFunction_functionName"></t:dgCol>
 <t:dgCol title="操作" field="opt" width="120"></t:dgCol>
 <t:dgDelOpt url="operationController.do?delOperation&id={id}" title="删除"></t:dgDelOpt> 
 <t:dgToolBar title="新建操作" icon="icon-add" url="operationController.do?addorupdateOperation&functionId=${functionId}" funname="add"></t:dgToolBar>
 <t:dgToolBar title="编辑操作" icon="icon-edit" url="operationController.do?addorupdateOperation&functionId=${functionId}" funname="update"></t:dgToolBar>
</t:datagrid>
<%--   update-start--Author:anchao  Date:20130415 for：按钮权限控制--%>
<script type="text/javascript">
function editoperation(operationId,operationname)
{
	
	createwindow("操作编辑","functionController.do?addorupdateop&functionId=${functionId}&id="+operationId);
}
</script>
<%--   update-end--Author:anchao  Date:20130415 for：按钮权限控制--%>
<!--  -->