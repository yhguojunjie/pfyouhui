<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="pluginPrevList" title="模板预告" actionUrl="pluginPrevController.do?pluginPrevGrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="模板名称" field="name" ></t:dgCol>
   <t:dgCol title="简介" field="description" ></t:dgCol>
   <t:dgCol title="预计上架时间" field="shelTime" formatter="yyyy-MM-dd"></t:dgCol>
   <t:dgCol title="创建时间" field="createTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="状态" field="status" replace="已上架_1, _0"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="pluginPrevController.do?delPluginPrev&id={id}" />
   <t:dgToolBar title="添加" icon="icon-add" url="pluginPrevController.do?addorupdatePluginPrev" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="pluginPrevController.do?addorupdatePluginPrev" funname="update"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>