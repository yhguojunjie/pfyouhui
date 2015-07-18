<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="versionList" title="版本管理" 
    actionUrl="versionController.do?versionList" idField="id"
    fit="true" fitColumns="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>      
   <t:dgCol title="客户端程序名" field="clientName" query="true" width="40"></t:dgCol>
   <t:dgCol title="版本编码" field="versionCode" width="40"></t:dgCol>
   <t:dgCol title="版本号" field="versionNum" width="20"></t:dgCol>
   <t:dgCol title="版本说明" field="versionMemo"></t:dgCol>
   <t:dgCol title="强制升级" field="isForce" replace="●_true, _false"></t:dgCol>
   <t:dgCol title="文件路径" field="versionUrl" width="80"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="40"></t:dgCol>
   <t:dgDelOpt url="versionController.do?delVersion&id={id}" title="删除"></t:dgDelOpt>
   <t:dgToolBar title="添加版本" icon="icon-add" url="versionController.do?aouVersion" funname="add"></t:dgToolBar>
   <t:dgToolBar title="修改版本" icon="icon-add" url="versionController.do?aouVersion" funname="update"></t:dgToolBar>
  </t:datagrid>
</div>
</div>
  