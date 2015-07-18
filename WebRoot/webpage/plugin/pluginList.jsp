<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
  <t:datagrid name="pluginList" title="插件列表" actionUrl="templateController.do?pluginGrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" ></t:dgCol>
   <t:dgCol title="插件图标" field="icon" image="true" thumbnail="true"></t:dgCol>
   <t:dgCol title="插件名称" field="name" ></t:dgCol>
   <t:dgCol title="成本价" field="price" ></t:dgCol>
   <t:dgCol title="状态" field="status" replace="下架_0,上架_1" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgFunOpt title="下架" funname="onlineOrOffline(id,status)"  exp="status#eq#1"></t:dgFunOpt>
   <t:dgFunOpt title="上架" funname="onlineOrOffline(id,status)"  exp="status#eq#0"></t:dgFunOpt>
   <t:dgFunOpt title="编辑" funname="editPlugin(id)" ></t:dgFunOpt>
   <t:dgFunOpt title="插件图片" funname="uploadPluginImg(id)" ></t:dgFunOpt>
  </t:datagrid>
    <div id="pluginListtb" style="padding: 3px; height: 25px">
     <div style="float:left;">
	    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="add('新增','templateController.do?addorupdatePlugin','pluginList','660px','450px')">新增</a>
     </div>
	 <div align="right">
	          插件名称:
	    <input type="text" class="searchtext" name="name" id="name" style="width: 80px"/>
	    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="pluginListsearch()">查询</a>
	 </div>
  </div>
 
<script type="text/javascript">
function getLoadImage(obj){
	 obj.src="img/noimg150150.jpg";
}
function editPlugin(id){
	createwindow("编辑","templateController.do?addorupdatePlugin&id="+id,"650px","450px");
}
function uploadPluginImg(id){
	createwindow("上传插件图片","templateController.do?uploadPluginImg&id="+id,"650px","450px");
}
function onlineOrOffline(id,status){
	var msg="";
	if(status == 0)msg="确定要上架吗?";
	if(status == 1)msg="确定要下架吗?";
	 $.dialog.confirm(msg, function(){
		 doSubmit("templateController.do?onlineOrOffline&id="+id,"pluginList");
	}, function(){
	});
}
</script>