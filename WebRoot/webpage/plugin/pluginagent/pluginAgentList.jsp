<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
  <t:datagrid name="pluginAgentList" title="代理商插件" actionUrl="pluginAgentController.do?pluginAgentGrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id"></t:dgCol>
   <t:dgCol title="插件图标" field="icon" image="true" thumbnail="true"></t:dgCol>
   <t:dgCol title="插件名称" field="name"></t:dgCol>
   <t:dgCol title="成本价" field="price"></t:dgCol>
   <t:dgCol title="销售价格" field="salePrice" ></t:dgCol>
   <t:dgCol title="状态" field="onlineState" replace="下架_1,上架_0"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgFunOpt title="修改" funname="updatePluginAgent(id)" operationCode="AGENT_UPDATE" />
   <t:dgConfOpt url="pluginAgentController.do?onlineOrOffline&id={id}&onlineState={onlineState}" message="确定要此操作吗?" title="上/下架" operationCode="AGENT_ONLINE_OFF"></t:dgConfOpt>
  </t:datagrid>
 <div id="pluginAgentListtb" style="padding: 3px; height: 25px">
	 <div align="right">
	          插件名称:
	    <input type="text" class="searchtext" name="name" id="name" style="width: 80px"/>
	    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="pluginAgentListsearch()">查询</a>
	 </div>
  </div>

 
<script type="text/javascript">
function updatePluginAgent(id){
	createwindow("修改","pluginAgentController.do?addorupdatePluginAgent&id="+id);
}
function getLoadImage(obj){
	 obj.src="img/noimg150150.jpg";
}
</script>