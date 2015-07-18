<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="agentWithdrawRecordList" title="提现记录" actionUrl="agentInfoController.do?agentWithdrawRecordGrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="提现金额" field="cash" ></t:dgCol>
   <t:dgCol title="类型" field="cashType" replace="支付宝_0,银行账号_1"></t:dgCol>
   <t:dgCol title="申请人" field="applyName" ></t:dgCol>
   <t:dgCol title="申请时间" field="applyTime" formatter="yyyy-MM-dd hh:mm:ss" sortable="true"></t:dgCol>
   <t:dgCol title="审核状态" field="auditState"  sortable="true"></t:dgCol>
  </t:datagrid>
  </div>
 </div>