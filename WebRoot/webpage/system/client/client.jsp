<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<title>版本维护</title> 
		<t:base type="jquery,easyui,tools"></t:base>
		<SCRIPT type="text/javascript">
		$(function(){
			onCheck();
		});
		//强制升级
		function onCheck(){
			var isForce = $("#isForce").attr("checked");
			if(isForce=='checked'){				
				$("#versionfw").show();
				$("#versiondm").show();	
				$("#forceVersionArrang").show();
				$("#forceVersionCode").show();
			}else{				
				$("#forceVersionArrang").val('');
				$("#forceVersionArrang").hide();
				
				$("#forceVersionCode").val('');
				$("#forceVersionCode").hide();	
				$("#versiondm").hide();	
				$("#versionfw").hide();	
			}		
		}
		</SCRIPT>
	</head>		 
	<body style="overflow-y:auto;overflow-x:hidden;">
	<t:formvalid formid="formobj" layout="div" dialog="true" action="clientController.do?saveClient" >
	    <input name="id" type="hidden" value="${client.id}">		 
	  	<table style="width:100%;height: 311px;" cellpadding="0" cellspacing="1" class="formtable">
	  	 <tr>
	     <td align="right" width="15%" nowrap>
	         <label class="Validform_label">客户端程序名:</label>
	     </td>
	     <td class="value" width="85%">
		     <input name="clientName" id="clientName" class="inputxt" value="${client.clientName}" datatype="s2-100" style="width:280px">
			 <span class="Validform_checktip">客户端程序名在2~100位字符，且不许为空</span>
	     </td>
	    </tr>
	    
	    <tr>
	     <td align="right" width="15%" nowrap>
	         <label class="Validform_label">版本代码:</label>
	     </td>
	     <td class="value" width="85%">
		     <input name="versionCode" id="versionCode" class="inputxt" value="${client.versionCode}" datatype="s3-30">
			 <span class="Validform_checktip">版本代码如“2.3.0”，且不能为空</span>
	     </td>
	    </tr>
	    
	    <tr>
	    <td align="right" width="15%" nowrap>
	        <label class="Validform_label">版本号:</label>
	     </td>
	     <td class="value" width="85%">		    
			<input name="versionNum" id="versionNum" class="inputxt" value="${client.versionNum}" datatype="numrange">
			<span class="Validform_checktip">版本号为自然数，如“5”，且不能为空</span>
	     </td>
	    </tr>
	    
	    <tr>
	    <td align="right" width="15%" nowrap>
	        <label class="Validform_label">版本说明:</label>			
	     </td>
	     <td class="value" width="85%">
	       <% request.setAttribute("vEnter", "\r\n"); //换行在el中不能用常量表达    %>      
           <textarea rows="6" class="inputxt" style="width:80%" id="versionMemo" name="versionMemo">${fn:replace(client.versionMemo,"<br/>",vEnter)}</textarea>		    <!--<input name="versionMemo" id="versionMemo" class="inputxt" value="${client.versionMemo}"  datatype="s4-500">			<span class="Validform_checktip">版本说明在4~500位字符，且不许为空</span>-->	     </td>
	    </tr>
	    
	    <tr>
	    <td align="right" width="15%" nowrap>
	         <label class="Validform_label">文件路径:</label>			
	     </td>
	     <td class="value" width="85%">
		     <input name="versionUrl" id="versionUrl" readonly="readonly" class="inputxt" value="${client.versionUrl}"  datatype="*3-200" style="width:310px">
			<span class="Validform_checktip">文件路径在3~200位字符，且不许为空</span>
	     </td>
	    </tr>
	    
	    <tr>
	    <td align="right" width="15%" nowrap>
	         <label class="Validform_label">文件上传:</label>			
	     </td>
	     <td class="value" width="85%">
		      <t:upload name="fielsplugin" id="fielsplugin" buttonText="上传到服务器" fileSizeLimit="200" dialog="false" auto="true" multi="false" 
		         uploader="systemController.do?saveFiles&uploadLocation=server" 
		         extend="android" queueID="androidId" showUrlId="versionUrl" ></t:upload>
		     <span id="androidId" ></span>	     </td>
	    </tr>
	    
	    <tr>
	    <td align="right" width="15%" nowrap>
	        <label class="Validform_label">强制升级:</label>			
	     </td>
	     <td class="value" width="85%">
		     <input name="isForce" id="isForce" class="rt2" type="checkbox" onclick="onCheck();"  <c:if test="${client.isForce}">checked</c:if>  value="${client.isForce}" >
			<span class="Validform_checktip">选择强制升级时，版本号范围或版本代码序列必填一个</span>
	     </td>
	    </tr>
	    
	    <tr id="versionfw" style="display: none;">
	    <td align="right" width="15%" nowrap>
	        <label class="Validform_label">版本号范围:</label>			
	     </td>
	     <td class="value" width="85%">
		    <input name="forceVersionArrang" id="forceVersionArrang" class="inputxt" ignore="ignore" value="${client.forceVersionArrang}" 
		         datatype="drange" >			<span class="Validform_checktip">版本号范围如“2-16”</span>	     </td>
	    </tr>
	    
	    <tr id="versiondm" style="display: none;">
	    <td align="right" width="15%" nowrap>
	        <label class="Validform_label">版本代码序列:</label>			
	     </td>
	     <td class="value" width="85%">
		    <input name="forceVersionCode" id="forceVersionCode" class="inputxt" ignore="ignore" datatype="/^([0-9].[0-9].[0-9|\*];?)+$/" value="${client.forceVersionCode}" >
			<span class="Validform_checktip">版本代码序列支持多项，通过分号隔开，如“2.3.*;2.4.0”</span>  
	     </td>
	    </tr>
	  </table>	
	</t:formvalid>			 			  
	</body>
</html>

