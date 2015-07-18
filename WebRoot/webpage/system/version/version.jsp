<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<title>版本维护</title> 
		<t:base type="jquery,easyui,tools"></t:base>
		<SCRIPT type="text/javascript">
		function uploadfinished() {
			tip('上传完毕,我是回调函数');
		}
		</SCRIPT>
	</head>		 
	<body style="overflow-y: hidden" scroll="no">
	<t:formvalid formid="formobj" layout="div" dialog="true" action="versionController.do?saveVersion" >
	    <input name="id" type="hidden" value="${version.id}">		 
	  	<table style="width:100%;" cellpadding="0" cellspacing="1" class="formtable">
	  	 <tr>
	     <td align="right" width="15%" nowrap>
	         <label class="Validform_label">客户端程序名:</label>
	     </td>
	     <td class="value" width="85%">
		     <input name="clientName" id="clientName" class="inputxt" value="${version.clientName}" datatype="s2-100">
			 <span class="Validform_checktip">客户端程序名在2~100位字符，且不许为空</span>
	     </td>
	    </tr>
	    <tr>
	     <td align="right" width="15%" nowrap>
	         <label class="Validform_label">版本代码:</label>
	     </td>
	     <td class="value" width="85%">
		     <input name="versionCode" id="versionCode" class="inputxt" value="${version.versionCode}" datatype="s3-30">
			 <span class="Validform_checktip">版本说明在3~30位字符，且不许为空</span>
	     </td>
	    </tr>
	    <td align="right" width="15%" nowrap>
	        <label class="Validform_label">版本号:</label>
	     </td>
	     <td class="value" width="85%">		    
			<input name="versionNum" id="versionNum" class="inputxt" value="${version.versionNum}" datatype="numrange">
			<span class="Validform_checktip">版本号输入整型，且不许为空</span>
	     </td>
	    </tr>
	    <td align="right" width="15%" nowrap>
	        <label class="Validform_label">版本说明:</label>			
	     </td>
	     <td class="value" width="85%">
		     <input name="versionMemo" id="versionMemo" class="inputxt" value="${version.versionMemo}"  datatype="s4-500">
			<span class="Validform_checktip">版本说明在4~500位字符，且不许为空</span>
	     </td>
	    </tr>
	    <td align="right" width="15%" nowrap>
	        <label class="Validform_label">强制升级:</label>			
	     </td>
	     <td class="value" width="85%">
		     <input name="isForce" id="isForce" class="rt2" type="checkbox"  <c:if test="${version.isForce}">checked</c:if>  value="${version.isForce}" >
			<span class="Validform_checktip">选择强制升级时，版本号范围或版本代码序列必填一个</span>
	     </td>
	    </tr>
	    <td align="right" width="15%" nowrap>
	        <label class="Validform_label">版本号范围:</label>			
	     </td>
	     <td class="value" width="85%">
		    <input name="forceVersionArrang" id="forceVersionArrang" class="inputxt" value="${version.forceVersionArrang}" 
		         datatype="drange">
			<span class="Validform_checktip">版本号范围在0-30个字符，允许空,eg:1-50</span>
	     </td>
	    </tr>
	    <td align="right" width="15%" nowrap>
	        <label class="Validform_label">版本代码序列:</label>			
	     </td>
	     <td class="value" width="85%">
		    <input name="forceVersionCode" id="forceVersionCode" class="inputxt" value="${version.forceVersionCode}" >
			<span class="Validform_checktip">版本代码序列在0~500位字符，多个用;分隔，允许空,eg:2.2.*;2.3.*</span>  
	     </td>
	    </tr>
	    <td align="right" width="15%" nowrap>
	         <label class="Validform_label">文件路径:</label>			
	     </td>
	     <td class="value" width="85%">
		     <input name="versionUrl" id="versionUrl" class="inputxt" value="${version.versionUrl}"  datatype="s3-200">
			<span class="Validform_checktip">文件路径在3~200位字符，且不许为空</span>
	     </td>
	    </tr>
	    <td align="right" width="15%" nowrap>
	         <label class="Validform_label">文件上传:</label>			
	     </td>
	     <td class="value" width="85%">
		     <t:upload name="fiels" buttonText="上   传" dialog="true" callback="uploadfinished"  
		         uploader="systemController.do?saveFiles" 
		         extend="android" id="default" formData="documentTitle"></t:upload>
	     </td>
	    </tr>		 	
    	</table>	
	</t:formvalid>			 			  
	</body>
</html>

