<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>充值</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <style>
 	.payway{width:450px;height:200px;margin:0;padding:0;background:url(img/payway.jpg) no-repeat right bottom;background-size:75px;}
 	.payway li{font:14px/50px "微软雅黑";height:50px;list-style:none;border-top:1px solid #ddd;}
 	.payway li:first-child{border-top:0;}
 	.payway li strong{float:left;font:14px/40px "微软雅黑";width:100px;text-align:right;margin-right:10px;}
 	.payway li p{float:left;width:300px;padding:0;margin:0;color:#666;}
 	.payway li p label{margin-right:30px;}
 	.payway li p label input[type="radio"]{vertical-align:-1px;}
 	.payway li p input[type="submit"]{width:80px;color:#fff;border-radius:3px;font:14px/14px "微软雅黑";margin-top:20px;height:30px;border:0;background:#fa0;}
 	.payway li p input[type="submit"]:hover{cursor:pointer;background:#f80;}
 </style>
 <body style="overflow-x: hidden">
    <form action="alipayController.do?pay" method="post" target="_top">
	 	<ul class="payway">
		 	<li>
		 		<strong>当前余额：</strong>
		 		<p>${agentInfo.blance }</p>
		 	</li>
		 	<li>
		 		<strong>选择充值：</strong>
		 		<p>
		 			<label><input type="radio"  name="charge" value="5000" >5000</label>
		 			<label><input type="radio"  name="charge" value="10000" >10000</label>
		 			<label><input type="radio"  name="charge" value="20000" checked="checked" >20000</label>
				</p>
		 	</li>
		 	<li>
		 		<strong>&nbsp;</strong>
		 		<p><input type="submit" name="button" value="充值" width="500px"/></p>
		 	</li>
		</ul>
	</form>
 </body>