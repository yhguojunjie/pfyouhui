<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
 <head>
  <title>活动</title>
   <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>

 <body style="overflow: hidden" scroll="no">
 <t:formvalid formid="formobj" dialog="true" refresh="true" usePlugin="password" layout="div" beforeSubmit="getSelectCheckbox();" action="agentClassicController.do?saveAddActClassic">
<input type="hidden" name="ids" id="ids"/>
<div style="height: 450px;">
  <t:datagrid name="agentActClassicList" title="活动列表" actionUrl="agentClassicController.do?agentActClassicListGrid" idField="id" checkbox="true"  fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="活动名称" field="title" ></t:dgCol>
   <t:dgCol title="品牌名称" field="bannerName"></t:dgCol>
   <t:dgCol title="访问量" field="browseNum"></t:dgCol>
   <t:dgCol title="创建时间" field="createTime"></t:dgCol>
  </t:datagrid>
  <div id="agentActClassicListtb" style="padding: 3px; height: 25px">
	 <div align="right">
	        品牌名称:
	    <input type="text" class="searchtext" name="bannerName" id="bannerName" style="width: 80px"/>
	    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="agentActClassicListsearch()">查询</a>
	 </div>
  </div>
  </div>
</t:formvalid>
</body>

<script type="text/javascript">
function getSelectCheckbox(){
	var getSelections = $("#agentActClassicList").datagrid("getSelections");
	var ids ="";
	for(var i=0 ; i<getSelections.length;i++){
	    ids = ids+getSelections[i].id+",";
	}
	$("#ids").val(ids);
}

</script>
</html>
