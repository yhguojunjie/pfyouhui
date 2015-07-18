
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
 <head>
  <title>${sessionScope.organization.organizaName }</title>
  <base>
  <t:base type="jquery,easyui,tools"></t:base>
  <script type="text/javascript">
	jq(function() {
		jq('#tt').tree({
			animate : true,
			onClick : function(node) {
				jq('#function-panel').panel("refresh", "membershipController.do?chooseDepart&nodeid=" + node.id);
			}
		});
	});
</script>
 </head>
 <body class="easyui-layout">
  <!-- 左侧-->
  <div region="west" split="true" title="导航菜单" style="width: 150px; padding: 1px;">
   <div id="nav" class="easyui-accordion" fit="true" border="false">
    <ul id="tt" class="easyui-tree">
     <li id="depart">
      <span>选择部门</span>
     </li>
     <li id="role">
      <span>选择角色</span>
     </li>
    </ul>
   </div>
  </div>
  <!-- 中间-->
  <div id="maintt" region="right" style="overflow: hidden; width: 30%;height:100%">
   <div class="easyui-panel" style="padding: 10px;" fit="true" border="false" id="function-panel">
    <t:datagrid name="departList" title="按部门选择 " actionUrl="membershipController.do?datagridDepart" showRefresh="true" idField="id">
     <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
     <t:dgCol title="部门名称" field="departName" width="100"></t:dgCol>
    </t:datagrid>
   </div>
  </div>
 </body>
</html>