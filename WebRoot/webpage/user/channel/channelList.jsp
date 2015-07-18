<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="channelList" title="渠道列表" actionUrl="channelController.do?channelGrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="顺序" field="seq" ></t:dgCol>
   <t:dgCol title="渠道名称" field="name" ></t:dgCol>
   <t:dgCol title="渠道类型" field="type" replace="微信订阅号_1,微信服务号_2,微信个人号_3,微博账号_4,网站_5,APP_6,其他_0"></t:dgCol>
   <t:dgCol title="提交时间" field="createTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="审核时间" field="updateTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="审核状态 " field="auditState" replace="未审核_0,通过_1,不通过_2" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgFunOpt title="查看" funname="viewChannel(id)" ></t:dgFunOpt>
  </t:datagrid>
  </div>
 </div>
 
 <script type="text/javascript">
	function viewChannel(id){
		createwindow("编辑","channelController.do?addorupdateChannel&id="+id,"650px","450px");
	}
</script>