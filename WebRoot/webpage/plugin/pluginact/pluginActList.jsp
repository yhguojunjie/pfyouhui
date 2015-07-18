<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
  <t:datagrid name="pluginActList" title="活动列表" actionUrl="pluginActController.do?pluginActGrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id"></t:dgCol>
   <t:dgCol title="活动图标" field="icon" image="true" thumbnail="true"></t:dgCol>
   <t:dgCol title="活动名称" field="title" ></t:dgCol>
   <t:dgCol title="浏览量" field="browseNum" ></t:dgCol>
   <t:dgCol title="活动时间" field="actTime"></t:dgCol>
   <t:dgCol title="发布者" field="nickName" ></t:dgCol>
   <t:dgCol title="状态" field="actOpen"  replace="公开_0,不公开_1" ></t:dgCol>
   <t:dgCol title="案例" field="actCase" replace="否_0,是_1"  ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgFunOpt title="修改" funname="uploadAct(id)" ></t:dgFunOpt>
  </t:datagrid>
    <div id="pluginActListtb" style="padding: 3px; height: 25px">
	 <div align="right">
	          活动名称:
	    <input type="text" class="searchtext" name="title" id="title" style="width: 80px"/>
	    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="pluginActListsearch()">查询</a>
	 </div>
  </div>
<script type="text/javascript">
function uploadAct(id){
	createwindow("修改","pluginActController.do?addorupdatePluginAct&id="+id,"350px","150px");
}
function getLoadImage(obj){
	 obj.src="img/noimg150150.jpg";
}
</script> 