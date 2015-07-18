<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <% String bannerState = (String)request.getAttribute("bannerState");
 %>
  <t:datagrid name="agentBannerList2" title="代理商Banner" actionUrl="agentBannerController.do?agentBannerGrid2" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="顺序" field="seq" ></t:dgCol>
   <t:dgCol title="pc版Logo" field="pcLogo" image="true" banner="true" thumbnail="true" ></t:dgCol>
   <t:dgCol title="pc版链接" field="pcLink" ></t:dgCol>
   <t:dgCol title="微信版Logo" field="wxLogo" image="true" banner="true" thumbnail="true"></t:dgCol>
   <t:dgCol title="微信版链接" field="wxLink" ></t:dgCol>
   <t:dgCol title="创建时间" field="createTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
    <t:dgCol title="审核时间" field="auditTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
<%--    <t:dgCol title="是否显示" field="hideState" replace="是_0,否_1" ></t:dgCol> --%>
   <t:dgCol title="审核状态" field="auditState" replace="未审核_0,通过_1,未通过_2" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgFunOpt title="审核" funname="changeAgentBanner(id)" exp="auditState#empty#false"></t:dgFunOpt>
  
  <%--  <t:dgConfOpt url="agentBannerController.do?changeAgentBanner&id={id}&auditState=1"  title="通过审核" message="确定审核通过吗?"></t:dgConfOpt>
   <t:dgConfOpt url="agentBannerController.do?changeAgentBanner&id={id}&auditState=2"  title="审核不通过" message="确定审核不通过吗?"></t:dgConfOpt>
  --%> </t:datagrid>
  
  <script type="text/javascript">
  $(function(){
	  $("#needradio :radio").change(function ()
	            {
		         var bannerState=$('input:radio[name="need"]:checked').val();
		        	 $.ajax({
		     			type: "POST",
		     			url : "agentBannerController.do?changeAgentContSwitch",
		     			data:{bannerState:bannerState},
		     			dataType: "json",
		     			success: function(data){
		     			
		     			}
		     			  
		     		});  
	            });
	  }); 
//更改打开调用函数
  function changeObj(url, name) {
  	createdialog('更改确认 ', '确定开启更新吗 ?', url, name);
  }
  //更改关闭调用函数
  function changeCloseObj(url, name) {
  	createdialog('更改确认 ', '确定关闭更新吗 ?', url, name);
  }
  function changeAgentBanner(id){
		createwindow("审核","agentBannerController.do?auditAddorupdateAgentBanner&id="+id,"660px","450px");
  }
</script>