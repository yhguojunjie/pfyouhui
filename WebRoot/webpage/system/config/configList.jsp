<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:datagrid name="configList" title="系统配置列表" 
  actionUrl="configController.do?configGrid" idField="id"
  fit="true" fitColumns="true">
 <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
 <t:dgCol title="名称" field="name" query="true"></t:dgCol>
 <t:dgCol title="编码" field="code"></t:dgCol>
 <t:dgCol title="内容" field="contents"></t:dgCol>
 <t:dgCol title="用途" field="note"></t:dgCol>
 <t:dgCol title="操作" field="opt"></t:dgCol>
 <t:dgDelOpt url="configController.do?delConfig&id={id}" title="删除"></t:dgDelOpt>
 <t:dgToolBar title="配置信息录入" icon="icon-add" url="configController.do?addorupdateConfig" funname="add"></t:dgToolBar>
 <t:dgToolBar title="配置信息编辑" icon="icon-edit" url="configController.do?addorupdateConfig" funname="update"></t:dgToolBar>
</t:datagrid>
