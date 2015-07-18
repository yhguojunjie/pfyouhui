<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>PlatformUser</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="platformUserController.do?savePlatformUser">
		<input id="id" name="id" type="hidden" value="${platformUserPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">userId:</label>
		      <input class="inputxt" id="userId" name="userId" 
					 value="${platformUserPage.userId}" datatype="n">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">account:</label>
		      <input class="inputxt" id="account" name="account" ignore="ignore"
					 value="${platformUserPage.account}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">email:</label>
		      <input class="inputxt" id="email" name="email" ignore="ignore"
					 value="${platformUserPage.email}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">手机号:</label>
		      <input class="inputxt" id="mobile" name="mobile" ignore="ignore"
					 value="${platformUserPage.mobile}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">手机号是否公众(0不公开,1公开):</label>
		      <input class="inputxt" id="mobileOpen" name="mobileOpen" ignore="ignore"
					 value="${platformUserPage.mobileOpen}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">qq号码:</label>
		      <input class="inputxt" id="qqAccount" name="qqAccount" ignore="ignore"
					 value="${platformUserPage.qqAccount}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">QQ号是否公众(0不公开,1公开):</label>
		      <input class="inputxt" id="qqOpen" name="qqOpen" ignore="ignore"
					 value="${platformUserPage.qqOpen}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">微信账号:</label>
		      <input class="inputxt" id="weixinAccount" name="weixinAccount" ignore="ignore"
					 value="${platformUserPage.weixinAccount}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">微信号是否公众(0不公开,1公开):</label>
		      <input class="inputxt" id="weixinOpen" name="weixinOpen" ignore="ignore"
					 value="${platformUserPage.weixinOpen}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">呢称:</label>
		      <input class="inputxt" id="nickName" name="nickName" ignore="ignore"
					 value="${platformUserPage.nickName}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">头像url:</label>
		      <input class="inputxt" id="headimgUrl" name="headimgUrl" ignore="ignore"
					 value="${platformUserPage.headimgUrl}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">1普通商家用户，2开发者:</label>
		      <input class="inputxt" id="role" name="role" ignore="ignore"
					 value="${platformUserPage.role}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">性别(0,男性 1 女性):</label>
		      <input class="inputxt" id="sex" name="sex" ignore="ignore"
					 value="${platformUserPage.sex}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">真实姓名:</label>
		      <input class="inputxt" id="realName" name="realName" ignore="ignore"
					 value="${platformUserPage.realName}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">联系地址:</label>
		      <input class="inputxt" id="address" name="address" ignore="ignore"
					 value="${platformUserPage.address}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">身份证号:</label>
		      <input class="inputxt" id="personIdCard" name="personIdCard" ignore="ignore"
					 value="${platformUserPage.personIdCard}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">来源(1,用户自行注册，2 QQ，3微信 4新浪微博 5腾讯微博):</label>
		      <input class="inputxt" id="source" name="source" ignore="ignore"
					 value="${platformUserPage.source}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">代币:</label>
		      <input class="inputxt" id="repreCoin" name="repreCoin" ignore="ignore"
					 value="${platformUserPage.repreCoin}" datatype="d">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">朋友数:</label>
		      <input class="inputxt" id="friendNum" name="friendNum" ignore="ignore"
					 value="${platformUserPage.friendNum}" datatype="n">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">二维码地址:</label>
		      <input class="inputxt" id="qcode" name="qcode" ignore="ignore"
					 value="${platformUserPage.qcode}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">简介描述:</label>
		      <input class="inputxt" id="introduce" name="introduce" ignore="ignore"
					 value="${platformUserPage.introduce}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">已拥有插件数:</label>
		      <input class="inputxt" id="pluginNum" name="pluginNum" ignore="ignore"
					 value="${platformUserPage.pluginNum}" datatype="n">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">省:</label>
		      <input class="inputxt" id="province" name="province" ignore="ignore"
					 value="${platformUserPage.province}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">市:</label>
		      <input class="inputxt" id="city" name="city" ignore="ignore"
					 value="${platformUserPage.city}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">区:</label>
		      <input class="inputxt" id="district" name="district" ignore="ignore"
					 value="${platformUserPage.district}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">创建时间:</label>
		      <input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px"  id="createTime" name="createTime" ignore="ignore"
					   value="<fmt:formatDate value='${platformUserPage.createTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">修改时间:</label>
		      <input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px"  id="updateTime" name="updateTime" ignore="ignore"
					   value="<fmt:formatDate value='${platformUserPage.updateTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">密码:</label>
		      <input class="inputxt" id="password" name="password" ignore="ignore"
					 value="${platformUserPage.password}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">0 正常使用，1禁用:</label>
		      <input class="inputxt" id="state" name="state" ignore="ignore"
					 value="${platformUserPage.state}">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">所属代理商id:</label>
		      <input class="inputxt" id="agentId" name="agentId" ignore="ignore"
					 value="${platformUserPage.agentId}" datatype="n">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">成交订单数:</label>
		      <input class="inputxt" id="dealOrederNum" name="dealOrederNum" ignore="ignore"
					 value="${platformUserPage.dealOrederNum}" datatype="n">
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">成交金额:</label>
		      <input class="inputxt" id="dealAmount" name="dealAmount" ignore="ignore"
					 value="${platformUserPage.dealAmount}" datatype="d">
		      <span class="Validform_checktip"></span>
		    </div>
	   </fieldset>
  </t:formvalid>
 </body>