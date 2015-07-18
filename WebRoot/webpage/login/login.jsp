<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>互动推-代理商管理平台</title>
<link rel="shortcut icon" href="resources/fc/images/icon/icon.ico">
<!--[if lt IE 9]>
   <script src="plug-in/login/js/html5.js"></script>
  <![endif]-->
<!--[if lt IE 7]>
  <script src="plug-in/login/js/iepng.js" type="text/javascript"></script>
  <script type="text/javascript">
	EvPNG.fix('div, ul, img, li, input'); //EvPNG.fix('包含透明PNG图片的标签'); 多个标签之间用英文逗号隔开。
</script>
  <![endif]-->
<link href="plug-in/login/css/zice.style.css" rel="stylesheet"
	type="text/css" />
<link href="plug-in/login/css/buttons.css" rel="stylesheet"
	type="text/css" />
<link href="plug-in/login/css/icon.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="plug-in/login/css/tipsy.css" media="all" />
<style type="text/css">
html {
	background-image: none;
}

label.iPhoneCheckLabelOn span {
	padding-left: 0px
}

#versionBar {
/**
	background-color: #212121;
	**/
	position: fixed;
	width: 100%;
	height: 35px;
	bottom: 0;
	left: 0;
	text-align: center;
	line-height: 35px;
	z-index: 11;
	-webkit-box-shadow: black 0px 10px 10px -10px inset;
	-moz-box-shadow: black 0px 10px 10px -10px inset;
	box-shadow: black 0px 10px 10px -10px inset;
}

.copyright {
	text-align: center;
	font-size: 10px;
	color: #000000;
}

.copyright a {
	color: #A31F1A;
	text-decoration: none
}

/*update-begin--Author:tanghong  Date:20130419 for：【是否】按钮错位*/
.on_off_checkbox {
	width: 0px;
}
/*update-end--Author:tanghong  Date:20130419 for：【是否】按钮错位*/
#login .logo {
	width: 400px;
	height: 50px;
}
</style>
</head>
<body>
	<div id="alertMessage"></div>
	<div id="successLogin"></div>
	<div class="text_success">
		<img src="plug-in/login/images/loader_green.gif" alt="Please wait" />
		<span>登陆成功!请稍后....</span>
	</div>
	<div id="login">
		<!-- <div class="ribbon" style="background-image:url(plug-in/login/images/typelogin.png);"></div> -->
		<div class="inner">
			<div class="logo">
	 				<img src="plug-in/login/images/hdtlogo.png" />
			</div>
			<div class="formLogin">
				<form name="formLogin" id="formLogin"
					action="loginController.do?login"
					check="loginController.do?checkuser" method="post">
					<input name="userKey" type="hidden" id="userKey"
						value="D1B5CC2FE46C4CC983C073BCA897935608D926CD32992B5900" />
					<div class="tip">
						<input class="userName" name="userName" type="text" id="userName"
							title="用户名" iscookie="true" nullmsg="请输入用户名!" />
					</div>
					<div class="tip">
						<input class="password" name="password" type="password"
							id="password" value="" title="密码" nullmsg="请输入密码!" />
					</div>
					<div class="loginButton" style="width:46%;"">

						<div style="float: left; margin-left: -10px;">
							<input type="checkbox" id="on_off" name="remember" checked="ture"
								class="on_off_checkbox" value="0" />
						</div>
						<div style="float: left; margin-left: 8px;">
							<span class="f_help"><font size="1" style="line-height:22px;">记住用户名 ?</font>
							</span>
						</div>
						<div style="float: right; padding: 3px 0; margin-right: -12px;">
							<div>
								<ul class="uibutton-group">
									<li><a class="uibutton normal" href="#" id="but_login">登录</a>
									</li>
									<li><a class="uibutton normal" href="#" id="forgetpass">重置</a>
									</li>
								</ul>
							</div>
							<div style="float: left; margin-left: 30px;">
								<!-- 
       <a href="loginController.do?goPwdInit"><span class="f_help">是否初始化admin的密码</span></a>
        -->
							</div>
						</div>
						<div class="clear"></div>
					</div>
				</form>
			</div>
		</div>
		<div class="shadow"></div>
	</div>
	<!--Login div-->
	<div class="clear"></div>
	<div id="versionBar">
		<div class="copyright">
			&copy; 版权所有 <span class="tip"><a href="http://www.tchajian.com"
				title="tchajian">厦门市有戏网络科技有限公司</a>
				(推荐使用IE8+,谷歌浏览器可以获得更快,更安全的页面响应速度)</span>
		</div>
	</div>
	<!-- Link JScript-->
	<script type="text/javascript" src="plug-in/jquery/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="plug-in/jquery/jquery.cookie.js"></script>
	<script type="text/javascript" src="plug-in/login/js/jquery-jrumble.js"></script>
	<script type="text/javascript" src="plug-in/login/js/jquery.tipsy.js"></script>
	<script type="text/javascript" src="plug-in/login/js/iphone.check.js"></script>
	<script type="text/javascript" src="plug-in/login/js/login.js"></script>
</body>
</html>