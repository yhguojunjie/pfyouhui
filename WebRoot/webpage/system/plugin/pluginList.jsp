<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="pluginList" title="客户端插件"
   actionUrl="pluginController.do?pluginList" idField="id"
   fit="true" fitColumns="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>      
   <t:dgCol title="客户端名称" field="clientName"  query="true" width="40"></t:dgCol>
   <t:dgCol title="客服端版本" field="clientVersion" width="25"></t:dgCol>
   <t:dgCol title="插件名称" field="pluginName" width="40"></t:dgCol>
   <t:dgCol title="插件版本" field="pluginVersion" width="25"></t:dgCol>
   <t:dgCol title="插件包名" field="pluginPackName"></t:dgCol>
   <t:dgCol title="插件描述" field="pluginDesc" ></t:dgCol>
   <t:dgCol title="插件图片" field="pluginIconUrl" image="true" width="30"></t:dgCol>
   <t:dgCol title="插件文件地址" field="pluginFileUrl" width="80"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="40"></t:dgCol>
   <t:dgDelOpt url="pluginController.do?delPlugin&id={id}" title="删除"></t:dgDelOpt>
   <t:dgToolBar title="添加插件" icon="icon-add" url="pluginController.do?aouPlugin" funname="add"></t:dgToolBar>
   <t:dgToolBar title="修改插件" icon="icon-add" url="pluginController.do?aouPlugin" funname="update"></t:dgToolBar>
  </t:datagrid>
  
</div>
</div>
  