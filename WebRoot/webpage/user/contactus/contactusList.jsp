<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="contactusList" title="客服列表" actionUrl="contactusController.do?contactusGrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="客服电话" field="servicePhone" ></t:dgCol>
   <t:dgCol title="粉丝QQ群" field="qqGroup" ></t:dgCol>
   <t:dgCol title="客服QQ" field="serviceqq" ></t:dgCol>
   <t:dgCol title="邮箱" field="email" ></t:dgCol>
   <t:dgCol title="状态" field="status" replace="禁用_0,启用_1" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgFunOpt title="启/禁用" funname="disableOrEnable(id,status)" ></t:dgFunOpt>
   <t:dgToolBar title="录入" icon="icon-add" url="contactusController.do?addorupdateContactus" funname="add"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
<script type="text/javascript">
function disableOrEnable(id,status){
	 $.dialog.confirm("确定要此操作吗?", function(){
		 doSubmit("contactusController.do?disableOrEnable&id="+id+"&status="+status,"contactusList");
	}, function(){
	});
}
</script>