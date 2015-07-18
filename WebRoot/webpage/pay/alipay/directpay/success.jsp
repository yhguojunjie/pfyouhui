<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
 
  <title>充值</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
<body onload="doneskip()">
	<div class="paysuc">
		<h3>
			<img alt="" src="../img/paysuc.jpg">
			<strong>恭喜你，充值成功！</strong>
		</h3>
		<ul>
			<li>
				<strong>支付方式：</strong>
				<span>支付宝</span>
			</li>
			<li>
				<strong>充值余额：</strong>
				<span>${directPaySyncBack.total_fee }</span>
			</li>
			<li>
				<strong>订单号：</strong>
				<span>${directPaySyncBack.trade_no}</span>
			</li>
		</ul>
		<div class="doneskip">
			<p>请不要关闭浏览器，<strong id="doneskip">5</strong>秒后跳转<a href="#">账户管理</a>。</p>
		</div>
	</div>
	<style type="text/css">
		body,h1,h2,h3,h4,h5,h6,ul,ol,li,p,form,dt,dd,input,textarea,th,td,fieldset,legend,dl,dt,dd,figure{margin:0;padding:0;}
		ul,ol,li{list-style:none}
		.paysuc{width:600px;margin:0 auto;padding:50px;}
		.paysuc h3{font:30px/50px "微软雅黑";color:#63c7bb;text-align:center;margin-bottom:30px;}
		.paysuc img{width:60px;vertical-align:middle;margin-right:20px;}
		.paysuc li{font:14px/40px "微软雅黑";color:#666;}
		.paysuc li strong{float:left;width:250px;text-align:right;margin-right:10px;}
		.paysuc li span{color:#f80;}
		.doneskip{text-align:center;font:14px/40px "微软雅黑";color:#666;margin-top:20px;}
		.doneskip a{color:#63c7bb;}
	</style>
</body>
</html>
<script type="text/javascript">
	//自动跳转
	function doneskip(){
		var count = 5;
		var countdown = setInterval(function(){
			document.getElementById('doneskip').innerHTML = count;
			if(count == 0){
				javascript:window.open("${webUrl}loginController.do?login","_top");
			//	location.href="${webUrl}loginController.do?login";
				clearInterval(countdown);
			//	javascript:window.close();
			}
			count--;
		},1000);
	}
</script>