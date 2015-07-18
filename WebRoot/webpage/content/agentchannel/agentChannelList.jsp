<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

 <t:datagrid name="agentChannelList" title="代理商渠道列表" actionUrl="agentChannelController.do?agentChannelGrid" idField="id" fit="true">
  <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
  <t:dgCol title="排序" field="seq" ></t:dgCol>
  <t:dgCol title="渠道logo" field="channel_logo" image="true" thumbnail="true"></t:dgCol>
  <t:dgCol title="渠道名称" field="channel_name" ></t:dgCol>
  <t:dgCol title="类型" field="channel_type" replace="微信订阅号_1,微信服务号_2,微信个人号_3,微博账号_4,网站_5,APP_6"></t:dgCol>
  <t:dgCol title="粉丝数" field="channel_fansNum" ></t:dgCol>
  <t:dgCol title="价位" field="channel_price" ></t:dgCol>
  <t:dgCol title="状态" field="hideState" replace="显示_0,隐藏_1" ></t:dgCol>
  <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
  <t:dgDelOpt title="删除" url="agentChannelController.do?delAgentChannel&id={id}" />
  <t:dgFunOpt title="编辑" funname="editAgentChannel(id)" ></t:dgFunOpt>
 </t:datagrid>
   <div id="agentChannelListtb" style="padding: 3px; height: 25px">
     <div style="float:left;">
    	 <c:if test="${empty agentContSwitch  }">
	         <input type="radio" name="channelConType"  checked="checked" value="0" onclick="channelConType(0);"/>使用平台统一数据
	         <input type="radio" name="channelConType" value="1" onclick="channelConType(1);"/>自己管理
	         <span id="button_addchannel_grid" style="display: none;">
        	 <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="addAgentChannel()">添加</a>
        	 </span>
         </c:if>
         <c:if test="${not empty  agentContSwitch}">
         	<input type="radio" name="channelConType" value="0" onclick="channelConType(0);" <c:if test="${agentContSwitch.channelConType == '0' }">checked="checked"</c:if> />使用平台统一数据
	        <input type="radio" name="channelConType" value="1" onclick="channelConType(1);" <c:if test="${agentContSwitch.channelConType == '1' }">checked="checked"</c:if>/>自己管理
        	<span id="button_addchannel_grid">
        	 <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="addAgentChannel()">添加</a>
        	 </span>
         </c:if>
   	   
     </div>
  </div>
  
<script type="text/javascript">
$(function(){
    var boolObj = $("input[name=channelConType]:checked").val();
	if(boolObj == 0)
		$("#button_addchannel_grid").hide();
	if(boolObj == 1)
		$("#button_addchannel_grid").show();
});
function addAgentChannel(){
	add("渠道列表","agentChannelController.do?channelAgentList","agentChannelList","650px","450px");
}
function editAgentChannel(id){
	createwindow("编辑","agentChannelController.do?addorupdateAgentChannel&id="+id,"650px","350px");
}
function channelConType(n){
	doSubmit("agentContSwitchController.do?saveAgentChannel&channelConType="+n,"agentChannelList");
	if(n == 0){
	    $("#button_addchannel_grid").hide();
	}
	if(n == 1){
	  $("#button_addchannel_grid").show();
	}
}
</script>