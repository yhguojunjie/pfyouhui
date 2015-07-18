<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
  <t:datagrid name="agentClassicList" title="代理商经典案例" actionUrl="agentClassicController.do?agentClassicGrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="代理商id" field="agentId" hidden="false"></t:dgCol>
   <t:dgCol title="排序" field="seq" ></t:dgCol>
   <t:dgCol title="品牌logo" field="actClassic_bannerLogo" image="true"  thumbnail="true"></t:dgCol>
   <t:dgCol title="品牌名" field="actClassic_bannerName" ></t:dgCol>
   <t:dgCol title="活动名称" field="actClassic_title" ></t:dgCol>
   <t:dgCol title="访问量" field="actClassic_joinNum" ></t:dgCol> 
   <t:dgCol title="代理商" field="actClassic_agentInfo_id" hidden="false"></t:dgCol>   
   <t:dgCol title="状态" field="hideState" replace="显示_0,隐藏_1"></t:dgCol>
   <t:dgCol title="审核" field="actClassic_auditState" replace="未审核_0,通过_1,不通过_2" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="agentClassicController.do?delAgentClassic&id={id}"  />
   <t:dgFunOpt title="编辑" funname="editAgentClassic(id)" exp="agentId#eqAttribute#actClassic_agentInfo_id" ></t:dgFunOpt>
   <t:dgFunOpt title="更改" funname="updateAgentSeqSort(id)" ></t:dgFunOpt>
  </t:datagrid>
    <div id="agentClassicListtb" style="padding: 3px; height: 25px">
     <div style="float:left;">
         <c:if test="${empty agentContSwitch  }">
	         <input type="radio" name="caseConType"  checked="checked" value="0" onclick="showAdd(0);"/>使用平台统一数据
	         <input type="radio" name="caseConType" value="1" onclick="showAdd(1);"/>自己管理
	         <span id="button_add_grid" style="display: none;">
			    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="add('新增','agentClassicController.do?addorupdateAgentClassic','agentClassicList','660px','450px')">新增</a>
		   	    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="actClassicList()">添加</a>
	     	 </span>
         </c:if>
         <c:if test="${not empty agentContSwitch }">
             <input type="radio" name="caseConType" value="0" onclick="showAdd(0);" <c:if test="${agentContSwitch.caseConType == '0' }">checked="checked"</c:if> />使用平台统一数据
	         <input type="radio" name="caseConType" value="1" onclick="showAdd(1);" <c:if test="${agentContSwitch.caseConType == '1' }">checked="checked"</c:if>/>自己管理
	        <span id="button_add_grid">
			    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="add('新增','agentClassicController.do?addorupdateAgentClassic','agentClassicList','660px','450px')">新增</a>
		   	    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="actClassicList()">添加</a>
	     	</span>
		</c:if>
     </div>
  </div>
<script type="text/javascript">
$(function(){
    var boolObj = $("input[name=caseConType]:checked").val();
	if(boolObj == 0)
		$("#button_add_grid").hide();
	if(boolObj == 1)
		$("#button_add_grid").show();
});
function getLoadImage(obj){
	 obj.src="img/noimg150150.jpg";
}
function editAgentClassic(id){
	createwindow("编辑","agentClassicController.do?addorupdateAgentClassic&id="+id,"650px","450px");
}
function updateAgentSeqSort(id){
	createwindow("更改","agentClassicController.do?updateAgentSeqSort&id="+id,"350px","100px");
}
function showAdd(n){
	doSubmit("agentContSwitchController.do?saveOrUpdateAgentContSwitch&caseConType="+n,"agentClassicList");
	if(n == 0){
	    $("#button_add_grid").hide();
	}
	if(n == 1){
	  $("#button_add_grid").show();
	}
}

function actClassicList(){
  //  createwindowGrid(title, addurl, height,gridname)
	add("经典案列","agentClassicController.do?agentActClassicList","agentClassicList","650px","450px");
}
</script>