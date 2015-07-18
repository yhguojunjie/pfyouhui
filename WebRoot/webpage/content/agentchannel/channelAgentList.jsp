<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
 <head>
  <title>活动</title>
   <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow: hidden" scroll="no">
 <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" beforeSubmit="getSelectCheckbox();" action="agentChannelController.do?saveChannel">
<input type="hidden" name="ids" id="ids"/>
<div style="height: 450px;">
  <t:datagrid name="channelAgentList" title="渠道列表" actionUrl="agentChannelController.do?channelAgentListGrid" idField="id" checkbox="true"  fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="渠道名称" field="name" ></t:dgCol>
   <t:dgCol title="粉丝数" field="fansNum" ></t:dgCol>
   <t:dgCol title="价位" field="price" ></t:dgCol>
   <t:dgCol title="创建时间" field="createTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
  </t:datagrid>
  <div id="channelAgentListtb" style="padding: 3px; height: 25px">
	 <div align="right">
	    <span style="padding-right: 20px;">
	    	<input type="checkbox" name="myChannelAgent" id="myChannelAgent" onclick="channelButton()">我的
	    </span>
	    <span>渠道名称:<input type="text" class="searchtext" name="name" id="name" style="width: 80px"/>
	   	 <a href="#" id="btnCheck" class="easyui-linkbutton" iconCls="icon-search" onclick="channelAgentListsearch()">查询</a>
	    </span>
	 </div>
  </div>
  </div>
</t:formvalid>
</body>
<script type="text/javascript">
function getSelectCheckbox(){
	var getSelections = $("#channelAgentList").datagrid("getSelections");
	var ids ="";
	for(var i=0 ; i<getSelections.length;i++){
	    ids = ids+getSelections[i].id+",";
	}
	$("#ids").val(ids);
}
function channelButton(){
	if($("#myChannelAgent:checked").length>0){
		$("#myChannelAgent").val("1");
	}else{
		$("#myChannelAgent").val("");
	}
    $("#btnCheck").click();
}
</script>

</html>
