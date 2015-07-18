<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
<div region="center" style="padding:1px;">
	<t:datagrid name="vagentInfoList" title="代理商管理" actionUrl="agentInfoController.do?vagentInfoGrid" fit="true" fitColumns="true" idField="id" >
		<t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
		<t:dgCol title="用户名"  field="userName" query="true" width="50"></t:dgCol>
		<t:dgCol title="公司名"  field="companyName" query="true" width="50"></t:dgCol>
		<t:dgCol title="注册时间"  field="createTime" query="true" width="50"></t:dgCol>
		<t:dgCol title="运存余额"  field="blance" query="true" width="50"></t:dgCol>
		<t:dgCol title="上一次欠费时间"  field="debtTime" query="true" width="50"></t:dgCol>
		<t:dgCol title="状态" sortable="true" field="status" replace="启用_1,禁用_0" ></t:dgCol>
		<t:dgCol title="操作" field="opt" width="100"></t:dgCol>
		<t:dgFunOpt title="编辑" funname="editAgent(id)" ></t:dgFunOpt>
		<t:dgFunOpt title="禁用/启用" funname="userDisableEnable(id,status)" ></t:dgFunOpt>
	</t:datagrid>
</div>
</div>	
<script type="text/javascript">
	function editAgent(id){
		createwindow("编辑","agentInfoController.do?addorupdateAgentInfo&id="+id,"650px","450px");
	}
	function userDisableEnable(id,status){
		var msg="";
		if(status == 0)msg="确定要启用吗?";
		if(status == 1)msg="确定要禁用吗?";
		 $.dialog.confirm(msg, function(){
			 doSubmit("agentInfoController.do?userDisableEnable&id="+id+"&status="+status,"vagentInfoList");
		}, function(){
		});
	}
</script>
	
