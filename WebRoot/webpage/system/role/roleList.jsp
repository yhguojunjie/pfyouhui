<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<div class="easyui-layout" fit="true" >
 <div region="center" style="padding: 1px;">
  <t:datagrid name="roleList" title="角色列表"  actionUrl="roleController.do?roleGrid" 
     fit="true" fitColumns="true" idField="id">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="角色编码" field="rolecode"></t:dgCol>
   <t:dgCol title="角色名称" field="roleName" width="50" query="true"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="30"></t:dgCol>
   <t:dgFunOpt funname="delRole(id)" title="删除"></t:dgFunOpt>
   <t:dgFunOpt funname="setfunbyrole(id,roleName)" title="权限设置"></t:dgFunOpt>
   <t:dgToolBar title="新建角色" icon="icon-add" url="roleController.do?addorupdateRole" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑角色" icon="icon-edit" url="roleController.do?addorupdateRole" funname="update"></t:dgToolBar>
  </t:datagrid>
 </div>
 <div region="east" style="width: 400px;" split="true">
  <div tools="#tt" class="easyui-panel" title="菜单权限" style="padding: 10px;" fit="true" border="false" id="function-panel">
  	
  </div>
 </div>
 <div id="tt">
<%--update by zjf 20130327 改用easyui panel方法实现以修复有时不显示的问题  <a href="#" class="icon-save" onclick="mysubmit();"></a>
 --%>
 </div>
</div>
<script type="text/javascript">
function setfunbyrole(id,roleName) {
	$("#function-panel").panel(
		{
			title:roleName+":当前权限",
			tools:[{iconCls:'icon-save',handler:function(){mysubmit();}}],
			href:"roleController.do?roleFunctionSet&roleId=" + id
		}
	);
}
//----------------------------------------------------------------
//update--Author:宋双旺  Date:20130414 for：删除角色，点击保存权限报错
//删除角色
function delRole(id){
	var tabName= 'roleList';
	var url= 'roleController.do?delRole&id='+id;
	$.dialog.confirm('确定删除该记录吗', function(){
		doSubmit(url,tabName);
		rowid = '';
		$("#function-panel").html("");//删除角色后，清空对应的权限
	}, function(){
	});
	
}
</script>
