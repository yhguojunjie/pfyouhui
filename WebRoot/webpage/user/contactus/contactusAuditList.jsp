<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="contactusAudtList" title="客服列表" actionUrl="contactusController.do?contactusAuditGrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="客服电话" field="servicePhone" ></t:dgCol>
   <t:dgCol title="粉丝QQ群" field="qqGroup" ></t:dgCol>
   <t:dgCol title="客服QQ" field="serviceqq" ></t:dgCol>
   <t:dgCol title="邮箱" field="email" ></t:dgCol>
   <t:dgCol title="提交时间" field="applyTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="审核状态" field="auditState" replace="未审核_0,通过_1,不通过_2"></t:dgCol>
   <t:dgCol title="审核时间" field="auditTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="审核人" field="auditUserName" ></t:dgCol>
   <t:dgCol title="状态" field="status" replace="禁用_0,启用_1" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
    <t:dgFunOpt title="审核" funname="auditNotOrThrough(id)" exp="auditState#eq#0"></t:dgFunOpt>
  </t:datagrid>
  </div>
 </div>
 
<script type="text/javascript">
function auditNotOrThrough(id){
	createwindow("审核","contactusController.do?toAudit&id="+id,"300px","50px");
}
 </script>