<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
<div region="center" style="padding:1px;"> 

<t:datagrid name="functionList" title="菜单管理" 
   actionUrl="functionController.do?functionTreeGrid" 
   fit="true" fitColumns="true"
   idField="id" treegrid="true" pagination="false">
 <t:dgCol title="编号" field="id" treefield="id" hidden="false"></t:dgCol>
 <t:dgCol title="菜单名称" field="functionName" width="40" treefield="text" query="true"></t:dgCol>
 <t:dgCol title="图标" field="TSIcon_iconPath" treefield="icon" image="true" width="15"></t:dgCol>
 <t:dgCol title="菜单地址" field="functionUrl" treefield="src"></t:dgCol>
 <t:dgCol title="菜单顺序" field="functionOrder" treefield="order" width="15"></t:dgCol>
 <t:dgCol title="操作" field="opt" width="20"></t:dgCol>
 <t:dgDelOpt url="functionController.do?delFunction&id={id}" title="删除"></t:dgDelOpt>
 <t:dgFunOpt funname="operationDetail(id)" title="按钮设置"></t:dgFunOpt>
</t:datagrid>
</div>

<div region="east" style="width:500px; overflow: hidden;" split="true" border="false">
  <div class="easyui-panel" title="操作按钮" style="padding:1px;" fit="true" border="false" id="operationDetailpanel">
  </div>
</div>

</div>


<div id="functionListtb" style="padding: 3px; height: 25px">
 <div style="float:left;">
  <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="add('新建菜单','functionController.do?addorupdateFunction','functionList')">新建菜单</a>
  <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="update('编辑菜单','functionController.do?addorupdateFunction','functionList')">编辑菜单</a>
 </div>
 <div align="right">
    菜单名称:
    <input type="text" class="searchtext" name="functionName" id="functionName" style="width: 80px"/>
    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="functionListsearch()">查询</a>
 </div>  
</div>


<script type="text/javascript">
function operationDetail(functionId)
{
	$('#operationDetailpanel').panel("refresh", "operationController.do?operation&functionId=" +functionId);
}
</script>

