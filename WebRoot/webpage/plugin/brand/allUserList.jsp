<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
 <head>
  <title>用户</title>
     <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
 <t:formvalid formid="formobj" dialog="true" refresh="true" usePlugin="password" layout="div" beforeSubmit="getSelectCheckbox();" action="brandController.do?saveAddBrand">
  <input type="hidden" name="userIds" id="userIds"/>
 <div style="height: 450px;">
  <t:datagrid name="allUserList" title="用户列表" actionUrl="brandController.do?userGrid" idField="userId" checkbox="true" fit="true">
   <t:dgCol title="用户id" field="userId" ></t:dgCol>
   <t:dgCol title="用户昵称" field="nickName"></t:dgCol>
  </t:datagrid>
<div id="allUserListtb" style="padding: 3px; height: 25px">
	 <div align="right">
	         用户昵称:
	    <input type="text" class="searchtext" name="nickName" id="nickName" style="width: 80px"/>
	    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="allUserListsearch()">查询</a>
	 </div>
</div>
</div>
</t:formvalid>
 </body>
 <script type="text/javascript">
function getSelectCheckbox(){
	var getSelectionss = $("#allUserList").datagrid("getSelections");
	var userIds ="";
	for(var i=0 ; i<getSelectionss.length;i++){
		userIds = userIds+getSelectionss[i].userId+",";
	}
	$("#userIds").val(userIds);
}

</script>
</html>
