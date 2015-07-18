<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
  <t:datagrid name="agentOrderList" title="订单列表" actionUrl="agentOrderController.do?agentOrderGrid" idField="id" fit="true" >
   <t:dgCol title="订单号" field="id" width="30" sortable="true"></t:dgCol>
   <t:dgCol title="用户ID" field="userId" hidden="false" ></t:dgCol>
   <t:dgCol title="插件图标" field="pluginIcon" image="true" width="30" thumbnail="true"></t:dgCol>
   <t:dgCol title="插件名称" field="pluginName" width="30" ></t:dgCol>
   <t:dgCol title="用户" field="userName" width="30" ></t:dgCol>
   <t:dgCol title="单价" field="salePrice" width="20" sortable="true"></t:dgCol>
   <t:dgCol title="数量(月)" field="buyNum" width="20" sortable="true"></t:dgCol>
   <t:dgCol title="交易金额" field="amount" width="25" ></t:dgCol>
   <t:dgCol title="订单时间" field="orderTime" formatter="yyyy-MM-dd hh:mm:ss" width="50" sortable="true"></t:dgCol>
   <t:dgCol title="支付状态" field="payOrderStatus" replace="未支付_0,已支付_1,支付失败_2" width="25" sortable="true"></t:dgCol>
   <t:dgCol title="状态" field="tradeState" replace="等待付款_0,交易关闭_1,交易成功_2" width="25" sortable="true"></t:dgCol>
   <t:dgCol title="购买类型" field="productType" width="25" replace="购买新模板_1,模板续费购买_2"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   
   <t:dgFunOpt title="定价" funname="updateOrder(id)"  exp="tradeState#eq#0&&salePrice#empty#true"></t:dgFunOpt>
   <t:dgFunOpt title="修改价格" funname="updateOrder(id)"  exp="tradeState#eq#0&&salePrice#empty#false"></t:dgFunOpt>
   
   <t:dgFunOpt title="关闭交易" funname="showDownOrder(id)" exp="tradeState#eq#0"></t:dgFunOpt>
   <t:dgFunOpt title="联系方式" funname="contactOrder(userId)" ></t:dgFunOpt>
  </t:datagrid>
 <div id="agentOrderListtb" style="padding: 3px; height: 25px">
	 <div align="right">
	          订单号:
	    <input type="text" class="searchtext" maxlength="10" onkeyup="this.value=this.value.replace(/\D/g,'')" name="id" id="name" style="width: 80px"/>
	    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="agentOrderListsearch()">查询</a>
	 </div>
  </div>
<script type="text/javascript">
function getLoadImage(obj){
	 obj.src="img/noimg150150.jpg";
}
function showDownOrder(id){
	 $.dialog.confirm("确定要关闭此交易吗?", function(){
		 doSubmit("agentOrderController.do?showDownOrder&id="+id,"agentOrderList");
	}, function(){
	});
}
function updateOrder(id){
	createwindow("修改","agentOrderController.do?addorupdateAgentOrder&id="+id,"655px","400px");
}
function contactOrder(userId){
	var api = $.dialog({id:"lianxifangshi",title:"联系方式",max:false, width:200, height:150,timer:2 });
	$.ajax({
	    url:"agentOrderController.do?contactOrder&userId="+userId,
	    success:function(data){
	    	var obj = jQuery.parseJSON(data);
	    	var mobile = obj.mobile==null?"":obj.mobile;
	    	var qqAccount =  obj.qqAccount==null?"":obj.qqAccount;
	    	var weixinAccount = obj.weixinAccount==null?"":obj.weixinAccount;
	    	var email = obj.email==null?"":obj.email;
	    	var html="<div><img style='vertical-align:middle;margin-right:5px;' src='img/ico_phone.png'/>手机："+mobile+"</div><br>"+
	 		 		 "<div><img style='vertical-align:middle;margin-right:5px;' src='img/ico_qq.png'/>QQ："+qqAccount+"<a href='http://wpa.qq.com/msgrd?v=3&uin="+qqAccount+"&site=qq&menu=yes' target='_blank'>发消息</a></div><br>"+
	    			 "<div><img style='vertical-align:middle;margin-right:5px;' src='img/ico_wechat.png'/>微信："+weixinAccount+"</div><br>"+
	    	 		 "<div><img style='vertical-align:middle;margin-right:5px;' src='img/ico_mail.png'/>邮箱："+email+"</div>";
	        api.content(html);
	    },
	});
	//$.dialog({ id:"aaa", html:"<h3>我是jQuery方式调用的窗口</h3>" });
	//createwindow("联系方式","agentOrderController.do?contactOrder&userId="+userId,"700px","200px");
}
</script> 