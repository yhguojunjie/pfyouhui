<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>用户信息</title>
  <t:base type="jquery,jqueryui,easyui,tools"></t:base>
 <script type="text/javascript">
<!--

//-->
</script>
<style>
  #sortable { list-style-type: none; margin: 0; padding: 0; width: 60%; }
  #sortable tr { margin: 0 3px 3px 3px; padding: 0.4em; padding-left: 1.5em; font-size: 1.4em; height: 18px; }
  #sortable tr td span { position: absolute; margin-left: -1.3em; }
  </style>
</head>
<body>
<div class="easyui-layout" fit="true">
<div region="center" style="padding:1px;">
<t:datagrid name="typeList${typegroup.id}" title="${typegroup.typeName}列表" sortable="true" sortUrl="typeController.do?saveOrder"
    actionUrl="typeController.do?typeGrid&typegroupid=${typegroup.id}" idField="id" sortName="codeOrder">
 <t:dgCol title="编号" field="id" ></t:dgCol>
 <t:dgCol title="名称" field="codeName" query="true" width="100"></t:dgCol>
 <t:dgCol title="代码" field="code" width="80"></t:dgCol>
 <c:if test="${fn:length(typegroup.codeName1Label)>0}">
    <t:dgCol title="${typegroup.codeName1Label}" field="codeName1"></t:dgCol>
 </c:if>
 <c:if test="${fn:length(typegroup.codeName2Label)>0}">
    <t:dgCol title="${typegroup.codeName2Label}" field="codeName2"></t:dgCol>
 </c:if>
 <c:if test="${fn:length(typegroup.codeName3Label)>0}">
    <t:dgCol title="${typegroup.codeName3Label}" field="codeName3"></t:dgCol>
 </c:if>
 <c:if test="${fn:length(typegroup.codeName4Label)>0}">
    <t:dgCol title="${typegroup.codeName4Label}" field="codeName4"></t:dgCol>
 </c:if>
 <t:dgCol title="说明" field="codeMemo" width="200"></t:dgCol>
 <t:dgCol title="排序" field="codeOrder" width="30" sortable="true"></t:dgCol>
 <t:dgCol title="删除" field="deleteFlag" width="30" replace="●_true, _false"></t:dgCol>
 <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
 <c:if test="${typegroup.allowDelete}">
 	<t:dgConfOpt url="typeController.do?delType&id={id}" exp="deleteFlag#eq#false" title="删除" message="您确定删除该记录吗?"></t:dgConfOpt>
 </c:if>
 <c:if test="${typegroup.allowDelete}">
 	<t:dgConfOpt url="typeController.do?restoreType&id={id}" exp="deleteFlag#eq#true" title="恢复" message="您确定恢复该记录吗?"></t:dgConfOpt>
 </c:if> 
</t:datagrid>

<div id="typeList${typegroup.id}tb" style="padding: 3px; height: 25px">
 <div style="float:left;">
 <c:if test="${typegroup.allowAdd}">
    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="add('新建${typegroup.typeName}','typeController.do?addorupdateType&typegroupid=${typegroup.id}','typeList${typegroup.id}')">新建${typegroup.typeName}</a>
	
 </c:if>
 <c:if test="${typegroup.allowModify}">
    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="update('编辑${typegroup.typeName}','typeController.do?addorupdateType&typegroupid=${typegroup.id}','typeList${typegroup.id}')">编辑${typegroup.typeName}</a>
 
 </c:if>  
 </div>
 <div align="right">
   名称   :<input type="text" class="searchtext" name="codeName" id="codeName" style="width: 80px"/>
     代码:<input type="text" class="searchtext" name="code" id="code" style="width: 80px"/>
   <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="typeList${typegroup.id}search()">查询</a>
 </div>

</div>

</div>
</div>
</body>
</html>


