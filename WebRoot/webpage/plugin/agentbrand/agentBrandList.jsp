<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>代理商入驻品牌管理</title>
 </head>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
   <t:datagrid name="agentBrandList" title="代理商入驻品牌管理" actionUrl="agentBrandController.do?agentBrandGrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="排序" field="seq" ></t:dgCol>
   <t:dgCol title="品牌logo" field="logo"  image="true" banner="true" ></t:dgCol>
   <t:dgCol title="用户id" field="userId" ></t:dgCol>
   <t:dgCol title="用户呢称" field="nickName" ></t:dgCol>
  <%--  <t:dgCol title="创建时间" field="createTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="更新时间" field="updateTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
    --%><t:dgCol title="状态 " field="hideState" replace="显示_0,隐藏_1" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
    <%-- <t:dgToolBar title="新增" icon="icon-add" url="agentBrandController.do?addorupdateAgentBrand" funname="add" popWidth="660" popHeight="450"></t:dgToolBar> --%>
   <t:dgToolBar title="添加" icon="icon-add" url="agentBrandController.do?allUserList" funname="add" popWidth="660" popHeight="450"></t:dgToolBar>
   <t:dgFunOpt title="修改" funname="editAgentBrand(id)" ></t:dgFunOpt>
   <t:dgDelOpt title="删除" url="agentBrandController.do?delAgentBrand&id={id}" />
  
  </t:datagrid>
  <script type="text/javascript">
  function editAgentBrand(id){
	createwindow("修改","agentBrandController.do?addorupdateAgentBrand&id="+id,"660px","450px");
  }
   function getLoadImage(obj){
	 obj.src="img/noimg150150.jpg";
 }
</script>