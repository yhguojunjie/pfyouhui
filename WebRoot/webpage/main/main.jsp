
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
 <head>
  <title>互动推-代理商管理平台</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <link rel="shortcut icon" href="images/icon.ico">
  <style type="text/css">
a {
	color: Black;
	text-decoration: none;
}

a:hover {
	color: black;
	text-decoration: none;
}
.panel-header.panel-header-noborder{background:#3a3633;color:#fff;}
.panel-header.panel-header-noborder .panel-title{color:#fff;}
#layout_north_kzmbMenu{float:right;width:285px;margin-right:10px;height:27px;margin-top:3px;}
#layout_north_kzmbMenu div{float:left;width:80px;font:12px/27px "微软雅黑";text-align:center;background:#bbb;color:#fff;border-radius:3px;margin-left:15px;}
#layout_north_kzmbMenu div:hover{cursor:pointer;background:#999;}
</style>
  <SCRIPT type="text/javascript">
	$(function() {
		$('#layout_east_calendar').calendar({
			fit : true,
			current : new Date(),
			border : false,
			onSelect : function(date) {
				$(this).calendar('moveTo', new Date());
			}
		});

	});
</SCRIPT>
 </head>
 <body class="easyui-layout" style="overflow-y: hidden" scroll="no">
  <!-- 顶部-->
  <div region="north" border="false" title="" 
        style="height:58px; padding: 1px; overflow: hidden;background-color:#f3f3f3;background-image:-moz-linear-gradient(top,#f3f3f3,#e2e2e2);background-image:-webkit-gradient(linear,0 0,0 100%,from(#f3f3f3),to(#e2e2e2));background-image:-webkit-linear-gradient(top,#f3f3f3,#e2e2e2);background-image:-o-linear-gradient(top,#f3f3f3,#e2e2e2);background-image:linear-gradient(to bottom,#f3f3f3,#e2e2e2);background-repeat:repeat-x;">        
   <table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
     <td align="left" style="vertical-align:text-bottom" width="320">
      <img src="plug-in/login/images/hdtlogomsg.png" style="height:40px;margin:8px;" />
     </td>
     <td align="left" nowrap>
      <table style="width:100%;">
       <tr>
        <td style="font-size:14px;">
         <span style="color:#999">当前用户：</span><span style="color:#5cbeaa;margin-right:30px;">(${userName })</span>
         <span style="color:#999">职务：</span><span style="color:#5cbeaa;margin-right:30px;">${roleName }</span>
         <!-- 
         <span style="color:#999">平台地址：</span><span style="color:#5cbeaa;margin-right:30px;"><a href="#" target="_blank"><font color="#5cbeaa">${mydomain }</font></a></span>
       	 -->
        </td>
        <td>
        <div id="layout_north_kzmbMenu">
	         <div onclick="openwindow('用户信息','userController.do?userinfo')">个人信息  </div>
	         <div onclick="add('修改密码','userController.do?changePassword')">修改密码</div>
             <div onclick="exit('loginController.do?logout','确定退出该系统吗 ?',1);">退出系统 </div>
        </div>
        </td>
       </tr>
       <td>
        <!--<div style="position: absolute; right: 0px; bottom: 0px;">
             <a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_kzmbMenu" iconCls="icon-control">控制面板</a>
              <a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_zxMenu" iconCls="icon-back">注销</a>
        </div> -->
         <!-- 
        <div id="layout_north_zxMenu" style="width: 100px; display: none;">
         <div class="menu-sep"></div>
         <div onclick="exit('loginController.do?logout','确定退出该系统吗 ?',1);"> 退出系统   </div>
          -->
        </div>
       </td>
       </tr>
      </table>
     </td>
    </tr>
   </table>
  </div>
  <!-- 左侧-->
  <div region="west" split="true" href="loginController.do?left" title="导航菜单" style="width:220px; padding: 1px;">
  
  
  </div>
  <!-- 中间-->
  <div id="mainPanle" region="center" style="overflow: hidden;">
   <div id="maintabs" class="easyui-tabs" fit="true" border="false">
    <div class="easyui-tab" title="首页" href="homePageController.do?homePage" style="padding:2px; overflow-y:auto;">
   				 
    </div>
    <c:if test="${map=='1'}">
     <div class="easyui-tab" title="地图" style="padding: 1px; overflow: hidden;">
      <iframe name="myMap" id="myMap" scrolling="no" frameborder="0" src="mapController.do?map" style="width: 100%; height: 99.5%;"></iframe>
     </div>
    </c:if>
   </div>
  </div>
  <!-- 右侧 -->
  <div collapsed="true"  region="east" iconCls="icon-reload" title="辅助工具" split="true" style="width: 190px;">
   <div id="tabs"  class="easyui-tabs" border="false" style="height: 240px">
    <div title="日历" style="padding: 0px; overflow: hidden; color: red;">
     <div id="layout_east_calendar"></div>
    </div>
   </div>
   <div id="tabs" class="easyui-tabs" border="false">
    <div title="在线人员" style="padding: 20px; overflow: hidden; color: red;">
       
    </div>
   </div>
  </div>
  <!-- 底部 -->
  <div region="south" border="false" style="height: 25px; overflow: hidden;">
    <div align="center" style="color: #CC99FF; padding-top: 2px;">
    &copy; 版权所有
     <span class="tip"><a href="#" title="Lectek JWhat">厦门有戏网络科技有限公司</a> (推荐谷歌浏览器，获得更快响应速度)</span>
   </div>
  </div>
  <div id="mm" class="easyui-menu" style="width:150px;">
        <div id="mm-tabclose">关闭</div>
        <div id="mm-tabcloseall">全部关闭</div>
        <div id="mm-tabcloseother">除此之外全部关闭</div>
        <div class="menu-sep"></div>
        <div id="mm-tabcloseright">当前页右侧全部关闭</div>
        <div id="mm-tabcloseleft">当前页左侧全部关闭</div>
        
   </div>

 </body>
</html>