<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:datagrid name="auditAgentActList" title="经典案例列表" actionUrl="agentClassicController.do?auditAgentActListGrid" idField="id" fit="true">
 <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
 <t:dgCol title="活动名称" field="pluginAct_title" ></t:dgCol>
 <t:dgCol title="品牌名称" field="bannerName" ></t:dgCol>
 <t:dgCol title="代理商" field="agentInfo_companyName" ></t:dgCol>
 <t:dgCol title="状态" field="auditState" replace="未审核_0,通过_1,不通过_2"></t:dgCol>
 <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
 <t:dgFunOpt title="查看" funname="auditAgentAct(id)" ></t:dgFunOpt>
</t:datagrid>

<script type="text/javascript">
function auditAgentAct(id){
	createwindow("审核","agentClassicController.do?auditAgentActPage&id="+id,"650px","450px");
}
</script>