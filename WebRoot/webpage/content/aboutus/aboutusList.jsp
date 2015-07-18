<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
  <t:datagrid name="aboutusList" title="关于我们" actionUrl="aboutusController.do?aboutusGrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="创建时间" field="createTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="修改时间" field="updateTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="状态 " field="hideState" replace="显示_0,隐藏_1"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="aboutusController.do?delAboutus&id={id}" />
  </t:datagrid>
   <div id="aboutusListtb" style="padding: 3px; height: 25px">
     <div style="float:left;">
	    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="add('新增','aboutusController.do?addorupdateAboutus','aboutusList','660px','450px')">新增</a>
     	<a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="updateEntity('编辑','aboutusController.do?addorupdateAboutus','aboutusList','660px','450px')">编辑</a>
     </div>
  </div>