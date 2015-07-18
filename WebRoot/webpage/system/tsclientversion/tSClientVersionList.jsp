<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="tSClientVersionList" title="定制列表" actionUrl="tSClientVersionController.do?tSClientVersionGrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="版本编码" field="versionCode" ></t:dgCol>
   <t:dgCol title="版本号" field="versionNum" ></t:dgCol>
   <t:dgCol title="版本说明" field="versionMemo" ></t:dgCol>
   <t:dgCol title="是否强制升级" field="isForce" ></t:dgCol>
   <t:dgCol title="强制版本范围" field="forceVersionArrang" ></t:dgCol>
   <t:dgCol title="强制升级代码" field="forceVersionCode" ></t:dgCol>
   <t:dgCol title="创建者" field="createName" ></t:dgCol>
   <t:dgCol title="发布状态" field="publishStatus" ></t:dgCol>
   <t:dgCol title="创建时间" field="createDate" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="更新时间" field="updateDate" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="tSClientVersionController.do?delTSClientVersion&id={id}" />
   <t:dgToolBar title="添加" icon="icon-add" url="tSClientVersionController.do?addorupdateTSClientVersion" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="tSClientVersionController.do?addorupdateTSClientVersion" funname="update"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>