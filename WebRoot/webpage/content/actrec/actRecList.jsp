<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
  <t:datagrid name="actRecList" title="活动推荐列表" actionUrl="actRecController.do?actRecGrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="排顺" field="seq" ></t:dgCol>
   <t:dgCol title="活动图标" field="asdf" ></t:dgCol>
   <t:dgCol title="活动ID" field="fff" ></t:dgCol>
   <t:dgCol title="活动名称" field="actId" ></t:dgCol>
   <t:dgCol title="创建时间" field="createTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="状态 " field="hideState" replace="0_显示,1_隐藏"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="actRecController.do?delActRec&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="actRecController.do?addorupdateActRec" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="actRecController.do?addorupdateActRec" funname="update"></t:dgToolBar>
  </t:datagrid>