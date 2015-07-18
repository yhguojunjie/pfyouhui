<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="clientList" title="客户端升级" 
   actionUrl="clientController.do?clientList" idField="id"
   fit="true" fitColumns="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>      
   <t:dgCol title="客户端程序名" field="clientName" query="true" width="40"></t:dgCol>
   <t:dgCol title="版本编码" field="versionCode" width="40"></t:dgCol>
   <t:dgCol title="版本号" field="versionNum" width="20"></t:dgCol>
   <t:dgCol title="版本说明" field="versionMemo"></t:dgCol>
   <t:dgCol title="强制升级" field="isForce" replace="●_true, _false" width="20"></t:dgCol>
   <t:dgCol title="文件路径" field="versionUrl" width="80"></t:dgCol>
   <t:dgCol title="发布" field="publishStatus" replace="●_true, _false" width="20"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="40"></t:dgCol>
   <t:dgDelOpt url="clientController.do?delClient&id={id}" exp="publishStatus#eq#false"  title="删除"></t:dgDelOpt>
   <t:dgConfOpt url="clientController.do?publicClient&id={id}" exp="publishStatus#eq#false" title="发布" message="您确定发布该版本吗?"></t:dgConfOpt>
   <t:dgToolBar title="添加版本" icon="icon-add" url="clientController.do?aouClient" funname="add"></t:dgToolBar>
   <t:dgToolBar title="修改版本" icon="icon-add" url="clientController.do?aouClient" funname="update"></t:dgToolBar>
  </t:datagrid>
</div>
</div>
  