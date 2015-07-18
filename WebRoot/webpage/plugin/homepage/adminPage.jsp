<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>管理员首页</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 <body style="overflow-x: hidden" >
  <table class="indexpage" cellspacing="10" cellspacing="10">
    <tr>
	    <td>模板总数:<strong id="pluginNum"><img alt="" src="img/loading.gif"></strong></td>
	    <td>代理商数:<strong class="important" id="agentNum"><img alt="" src="img/loading.gif"></strong></td>
    	<td>提现审核未处理:<strong id="agentWithdraw"><img alt="" src="img/loading.gif"></strong></td>
    	<td>经典案例:<strong id="actClassic"><img alt="" src="img/loading.gif"></strong></td>
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
     //模板总数
     $.post("adminPageController.do?pluginNum", function(data) {
	   $("#pluginNum").html(data);
	 });
     //代理商数
     $.post("adminPageController.do?agentNum", function(data) {
  	   $("#agentNum").html(data);
  	 });
     
     //提现审核未处理
     $.post("adminPageController.do?agentWithdraw", function(data) {
  	   $("#agentWithdraw").html(data);
  	 });
     
     //经典案例数
     $.post("adminPageController.do?actClassic", function(data) {
  	   $("#actClassic").html(data);
  	 });
   </script>
   
 </body>
 </html>
 