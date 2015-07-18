<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<t:datagrid name="departList" title="部门列表" actionUrl="departController.do?departTreeGrid" 
   fit="true" fitColumns="true" treegrid="true" idField="id" pagination="false">
 <t:dgCol title="部门编码" field="id" treefield="id" ></t:dgCol>
 <t:dgCol title="部门名称" field="departName" treefield="text" query="true" width="100"></t:dgCol>
 <t:dgCol title="职能代码" field="departCode" treefield="code" width="80"></t:dgCol>
 <t:dgCol title="职能描述" field="description" treefield="src"></t:dgCol>
 <t:dgCol title="类型" field="departType" treefield="icon" replace="部门_0,CP商_1,渠道_2,运营商_3"></t:dgCol>
 <t:dgCol title="顺序" field="departOrder" treefield="order" width="50"></t:dgCol>
 <t:dgCol title="操作" field="opt"></t:dgCol>
 <t:dgDelOpt url="departController.do?delDepart&id={id}" title="删除"></t:dgDelOpt> 
</t:datagrid>

<div id="departListtb" style="padding: 3px; height: 25px">
 <div style="float:left;">
  <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="add('新建部门','departController.do?addDepart','departList')">新建部门</a>
  <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="update('编辑部门','departController.do?updateDepart','departList')">编辑部门</a>
 </div>
 <div align="right">
    部门名称:
    <input type="text" class="searchtext" name="departName" id="departName" style="width: 80px"/>
    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="departListsearch()">查询</a>
 </div>
  
</div>

