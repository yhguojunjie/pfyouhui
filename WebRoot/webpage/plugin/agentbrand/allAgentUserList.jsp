<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
 <head>
  <title>用户</title>
  <t:base type="jquery,easyui,tools"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
 <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" beforeSubmit="getSelectCheckbox2();" action="agentBrandController.do?saveAddAgentBrand">
  <input type="hidden" name="userIds" id="userIds"/>
 <div style="height: 450px;">
  <t:datagrid name="allAgentUserList" title="用户列表" actionUrl="agentBrandController.do?userGrid" idField="userId" checkbox="true" showRefresh="false">
   <t:dgCol title="用户id" field="userId" ></t:dgCol>
   <t:dgCol title="用户昵称" field="nickName"></t:dgCol>
    <t:dgCol title="注册时间" field="createTime"></t:dgCol>
  </t:datagrid>
<div id="allAgentUserListtb" style="padding: 3px; height: 25px">
	 <div align="right">
	         用户ID:
	    <input type="text" class="searchtext" name="userId" id="userId" style="width: 80px"/>
	         用户昵称:
	    <input type="text" class="searchtext" name="nickName" id="nickName" style="width: 80px"/>
	    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="allAgentUserListsearch()">查询</a>
	 </div>
</div>
</div>
</t:formvalid>
 </body>
 <script type="text/javascript">
function getSelectCheckbox2(){
	var getSelections = $("#allAgentUserList").datagrid("getSelections");
	var userIds ="";
	for(var i=0 ; i<getSelections.length;i++){
		userIds = userIds+getSelections[i].userId+",";
	}
	$("#userIds").val(userIds);

}

</script>
</html>
