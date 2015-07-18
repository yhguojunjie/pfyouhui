<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
 <% String bannerState = (String)request.getAttribute("bannerState");
 %>
  <t:datagrid name="agentBannerList" title="代理商Banner" actionUrl="agentBannerController.do?agentBannerGrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="顺序" field="seq" ></t:dgCol>
   <t:dgCol title="pc版Logo" field="pcLogo" image="true" banner="true" thumbnail="true" ></t:dgCol>
   <t:dgCol title="pc版链接" field="pcLink" ></t:dgCol>
   <t:dgCol title="微信版Logo" field="wxLogo" image="true" banner="true" thumbnail="true"></t:dgCol>
   <t:dgCol title="微信版链接" field="wxLink" ></t:dgCol>
   <t:dgCol title="创建时间" field="createTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
    <t:dgCol title="链接类型" field="linkType" replace="模板_1,站外URL链接_2"></t:dgCol>
   <t:dgCol title="审核" field="auditState" replace="审核中_0,通过_1,不通过_2"></t:dgCol> 
   <t:dgCol title="是否显示" field="hideState" replace="是_0,否_1" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="agentBannerController.do?delAgentBanner&id={id}" />
   <t:dgFunOpt title="编辑" funname="editAgentBanner(id)" exp="auditState#empty#false"></t:dgFunOpt>
   <t:dgFunOpt title="更改" funname="updateAgentSeqSort(id)" ></t:dgFunOpt>
  </t:datagrid>
  
  
  <div id="agentBannerListtb" style="padding: 3px; height: 25px">
     <div style="float:left;">
	   	  <!--   <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="changeObj('agentBannerController.do?delAgentBanner&id=1','agentBannerList')">开启自动更新</a>
	    <a href="#" class="easyui-linkbutton" plain="true" icon="icon-remove" onclick="changeCloseObj('agentBannerController.do?delAgentBanner&id=1','agentBannerList')">关闭自动更新</a>
         -->
        <div id="needradio"> 
    <%--     <input  id="need" name="need" type="radio" value="0" <%= bannerState=="0"?"Checked":"" %>  onclick="changeObj('agentBannerController.do?delAgentBanner&id=1','agentBannerList')"/>开启自动更新
        <input  id="need" name="need" type="radio" value="1" <%= bannerState=="1"?"Checked":"" %> onclick="changeCloseObj('agentBannerController.do?delAgentBanner&id=1','agentBannerList')"/>关闭自动更新 
         --%>
        <c:if test="${bannerState=='2'}">             	
        <input  id="need" name="need" type="radio" value="0"  Checked />开启自动更新
        <input  id="need" name="need" type="radio" value="1"  />关闭自动更新 
       </c:if>
       <c:if test="${bannerState=='0'}">          	
        <input  id="need" name="need" type="radio" value="0"  Checked />开启自动更新
        <input  id="need" name="need" type="radio" value="1"  />关闭自动更新 
       </c:if>
       <c:if test="${bannerState=='1'}">          	
        <input  id="need" name="need" type="radio" value="0"   />开启自动更新
        <input  id="need" name="need" type="radio" value="1" Checked />关闭自动更新 
       </c:if>
        <span id="button_add_grid">
         <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="add('新增','agentBannerController.do?addorupdateAgentBanner','agentBannerList','660px','450px')">新增</a>
        </span>
        </div>
         
     </div>
	
  </div>
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
  function editAgentBanner(id){
	createwindow("编辑","agentBannerController.do?addorupdateAgentBanner&id="+id,"660px","450px");
  }
  function updateAgentSeqSort(id){
		createwindow("更改","agentBannerController.do?updateAgentSeqSort&id="+id,"350px","100px");
	}
</script>