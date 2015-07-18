<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>首页</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 <body style="overflow-x: hidden" >
  <table class="indexpage" cellspacing="10" cellspacing="10">
    <tr>
    	<td colspan="2">预存余额:
    	<a href="#" style="font-size:20px;color:#5cbeaa;" onclick="openwindow('充值','homePageController.do?chargeBlance');">充值</a>
    	<strong id="blance"><img alt="" src="img/loading.gif"></strong></br>
    	平台地址:<span id="mydomain"></span>
    	</td>
    	<td colspan="3">
    	   <div style="margin-left: 2px;">
	    	   <c:forEach  var="notice" items="${noticeVersionList}" varStatus="status">
	    	   		<div><span style="padding-left: 2px;">${notice.title }</span> <span> <fmt:formatDate value="${notice.createTime }" type="both" pattern="yyyy/MM/dd " /></span></div>
	    	   </c:forEach>
    	   </div>
    	</td>
    </tr>
    <tr>
	    <td>今日订单数:<strong id="todayOrderNum"><img alt="" src="img/loading.gif"></strong></td>
	    <td>待付款:<strong class="important" id="payMentNum"><img alt="" src="img/loading.gif"></strong></td>
    	<td>模板总数:<strong id="agentPluginNum"><img alt="" src="img/loading.gif"></strong></td>
    	<td>待定价:<strong class="important" id="withPricNum"><img alt="" src="img/loading.gif"></strong></td>
    	<td>总流量:<strong id="trafficNum"><img alt="" src="img/loading.gif"></strong></td>
    </tr>
    <tr>
    	<td>昨日成交订单数:<strong id="yesterdayOrderNum"><img alt="" src="img/loading.gif"></strong></td>
    	<td>昨日成交金额:<strong id="yesterdayAmount"><img alt="" src="img/loading.gif"></strong></td>
    	<td>昨日收益:<strong id="yesterdayEarnings"><img alt="" src="img/loading.gif"></strong></td>
    	<td>累计收益:<strong id="addEarnings"><img alt="" src="img/loading.gif"></strong></td>
    	<td>本月成交订单数:<strong id="monthOrder"><img alt="" src="img/loading.gif"></strong></td>
    </tr>
  </table>
  <style type="text/css">
  	.indexpage{width:1000px;height:600px;}
  	.indexpage td{text-align:center;font:18px/20px "微软雅黑";border:1px solid #ddd;border-radius:5px;color:#666;height:200px;width:200px;}
  	.indexpage td strong{display:block;font-size:30px;line-height:60px;color:#666;}
  	.indexpage td:hover{background:#f2f2f2;cursor:pointer;}
  	.indexpage td strong.important{color:#b00;}
  </style>
   <script type="text/javascript">
     //今日订单数
     $.post("homePageController.do?todayOrderNum", function(data) {
	   $("#todayOrderNum").html(data);
	 });
     //待付款
     $.post("homePageController.do?payMentNum", function(data) {
  	   $("#payMentNum").html(data);
  	 });
     //模板总数
     $.post("homePageController.do?agentPluginNum", function(data) {
    	   $("#agentPluginNum").html(data);
      });
     //带定价
     $.post("homePageController.do?withPricNum", function(data) {
  	   $("#withPricNum").html(data);
    });
     //总流量
     $.post("homePageController.do?trafficNum", function(data) {
    	   $("#trafficNum").html(data);
      });
     $.post("homePageController.do?yesterdayOrderNum", function(data) {
    	 var obj = jQuery.parseJSON(data);
    	//昨日成交订单数
  	   $("#yesterdayOrderNum").html(obj.yesterdayOrderNum);
    	//昨日成交金额
  	   $("#yesterdayAmount").html(obj.yesterdayAmount);
    	//昨日收益
  	   $("#yesterdayEarnings").html(obj.yesterdayEarnings);
    });
     //累计收益
     $.post("homePageController.do?addEarnings", function(data) {
    	var obj = jQuery.parseJSON(data);
  	   $("#addEarnings").html(obj.addEarnings);
  	   $("#mydomain").html("<a href='"+obj.mydomain+"' target='_blank'>"+obj.mydomain+"</a>")
  	   if(obj.blance>0)
  	  		 $("#blance").html(obj.blance);
  	   else
  		 $("#blance").html(obj.blance).css("color","red");
    });
     //本月成交订单数
     $.post("homePageController.do?monthOrder", function(data) {
    	   $("#monthOrder").html(data);
      });
   </script>
   
   
 </body>
 </html>
 