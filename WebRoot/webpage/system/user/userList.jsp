<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!-- 
<div class="easyui-layout" fit="true">
<div region="center" style="padding:1px;">
 -->
<t:datagrid name="userList" title="用户管理" actionUrl="userController.do?userGrid" 
     fit="true" fitColumns="true" idField="id">
	<t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
	<t:dgCol title="用户名"  field="userName" query="true" width="50"></t:dgCol>
	<t:dgCol title="部门" field="TSDepart_departName" query="true"></t:dgCol>
	<t:dgCol title="真实姓名" field="realName" query="true"></t:dgCol>
	<t:dgCol title="状态" sortable="true" field="status" replace="启用_1,禁用_0"></t:dgCol>
	<t:dgCol title="操作" field="opt" width="100"></t:dgCol>
	<t:dgFunOpt title="禁用/启用" funname="userDisableEnable(id,status)" ></t:dgFunOpt>
</t:datagrid>
 
<div id="userListtb" style="padding: 3px; height: 25px">
 <div style="float:left;">
  <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="add('新建用户','userController.do?addorupdateUser','userList')">新建用户</a>
  <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="update('编辑用户','userController.do?addorupdateUser','userList')">编辑用户</a>
 </div>
 <div align="right">
      用户名:<input type="text" class="searchtext" name="userName" id="userName" style="width: 80px"/>
     真实姓名:<input type="text" class="searchtext" name="realName" id="realName" style="width: 80px"/>
     所属部门:<input type="text" class="searchtext" name="departName" id="departName" style="width: 80px"/>
   <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="userListsearch()">查询</a>
 </div>

</div>

<script type="text/javascript">
	function userDisableEnable(id,status){
		var msg="";
		if(status == 0)msg="确定要启用吗?";
		if(status == 1)msg="确定要禁用吗?";
		 $.dialog.confirm(msg, function(){
			 doSubmit("agentInfoController.do?userDisableEnable&id="+id+"&status="+status,"userList");
		}, function(){
		});
	}
</script>
