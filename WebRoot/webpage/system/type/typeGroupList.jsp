<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
<div region="center" style="padding:1px;">
<t:datagrid name="typeGroupList" title="字典分类"
  actionUrl="typeController.do?typeGroupGrid" idField="id" sortName="id"
  fit="true" fitColumns="true">
 <t:dgCol title="编号" field="id" width="80" query="true" sortable="true"></t:dgCol>
 <t:dgCol title="字典分类名称" field="typeName" query="true" width="100"></t:dgCol>
 <t:dgCol title="字典分类说明" field="typeMemo" width="100"></t:dgCol>
 <t:dgCol title="分类Name1标签" field="codeName1Label"></t:dgCol>
 <t:dgCol title="分类Name2标签" field="codeName2Label"></t:dgCol>
 <t:dgCol title="分类Name3标签" field="codeName3Label"></t:dgCol>
 <t:dgCol title="分类Name4标签" field="codeName4Label"></t:dgCol>
 <t:dgCol title="允许新建" field="allowAdd" replace="●_true, _false"></t:dgCol>
 <t:dgCol title="允许修改" field="allowModify" replace="●_true, _false"></t:dgCol>
 <t:dgCol title="允许删除" field="allowDelete" replace="●_true, _false"></t:dgCol>
 <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
 <t:dgDelOpt url="typeController.do?delTypeGroup&id={id}" title="删除"></t:dgDelOpt>
 <t:dgToolBar title="新建分类" icon="icon-add" url="typeController.do?aouTypeGroup" funname="add"></t:dgToolBar>
 <t:dgToolBar title="编辑分类" icon="icon-edit" url="typeController.do?aouTypeGroup" funname="update"></t:dgToolBar>
</t:datagrid>
</div>
</div>