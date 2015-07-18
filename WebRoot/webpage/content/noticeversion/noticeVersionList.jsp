<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="noticeVersionList" title="版本公告列表" actionUrl="noticeVersionController.do?noticeVersionGrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="标题" field="title" ></t:dgCol>
   <t:dgCol title="链接地址" field="titleLink" ></t:dgCol>
   <t:dgCol title="顺序" field="seq" ></t:dgCol>
   <t:dgCol title="创建时间" field="createTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="noticeVersionController.do?delNoticeVersion&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="noticeVersionController.do?addorupdateNoticeVersion" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="noticeVersionController.do?addorupdateNoticeVersion" funname="update"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>