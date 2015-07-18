<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="agentWithdrawList" title="提现列表" actionUrl="agentWithdrawController.do?agentWithdrawGrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="提现金额" field="cash" ></t:dgCol>
   <t:dgCol title="类型" field="cashType" replace="支付宝_0,银行账号_1"></t:dgCol>
   <t:dgCol title="申请人" field="applyName" ></t:dgCol>
   <t:dgCol title="申请时间" field="applyTime" formatter="yyyy-MM-dd hh:mm:ss" sortable="true"></t:dgCol>
   <t:dgCol title="审核人" field="auditName" ></t:dgCol>
   <t:dgCol title="审核状态" field="auditState" replace="未处理_0,已处理_1" sortable="true"></t:dgCol>
   <t:dgCol title="审核时间" field="auditTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgFunOpt title="审核" funname="auditWithdraw(id)"  exp="auditState#eq#0"></t:dgFunOpt>
  </t:datagrid>
  </div>
 </div>
  <script type="text/javascript">
 function auditWithdraw(id){
	 $.dialog.confirm("确定要申请通过吗?", function(){
		 doSubmit("agentWithdrawController.do?auditWithdraw&id="+id,"agentWithdrawList");
	}, function(){
	});
 }
 </script>