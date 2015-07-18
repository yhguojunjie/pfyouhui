<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

  <t:datagrid name="actClassicList" title="经典案例列表" actionUrl="actClassicController.do?actClassicGrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="排序" field="seq" ></t:dgCol>
   <t:dgCol title="活动名称" field="actTitle" ></t:dgCol>
   <t:dgCol title="品牌logo" field="bannerLogo" image="true" thumbnail="true"></t:dgCol>
   <t:dgCol title="品牌名称" field="bannerName" ></t:dgCol>
   <t:dgCol title="类型" field="type" replace="系统活动_1,手动添加_2"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgFunOpt title="修改" funname="editActClassic(id,type)" ></t:dgFunOpt>
    <t:dgDelOpt title="删除" url="actClassicController.do?delActClassic&id={id}" />
   
  </t:datagrid>
  <div id="actClassicListtb" style="padding: 3px; height: 25px">
    <div style="float:left;">
	    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="add('自动新增','actClassicController.do?addorupdateActClassic','actClassicList','670px','450px')">自动新增</a>
	    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="add('手动新增','actClassicController.do?classicManualAdd','actClassicList','670px','450px')">手动新增</a>
    </div>
	 <div align="right">
	          活动名称:
	    <input type="text" class="searchtext" name="actTitle" id="actTitle" style="width: 80px"/>
	         品牌名称:
	     <input type="text" class="searchtext" name="bannerName" id="bannerName" style="width: 80px"/>
	    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="actClassicListsearch()">查询</a>
	 </div>
  </div>

 <script type="text/javascript">
 function editActClassic(id,type){
	 if(type == 1)
		createwindow("修改","actClassicController.do?addorupdateActClassic&id="+id,"670px","450px");
	 if(type == 2)
		createwindow("修改","actClassicController.do?classicManualUpdate&id="+id,"670px","450px");
 }
 function getLoadImage(obj){
	 obj.src="img/noimg150150.jpg";
 }
 </script>