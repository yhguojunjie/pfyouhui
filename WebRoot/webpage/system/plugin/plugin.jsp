<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<title>客户端插件</title> 
		<t:base type="jquery,easyui,tools"></t:base>
	</head>		 
	<body style="overflow-y: hidden" scroll="no">
	<t:formvalid formid="formobj" layout="div" dialog="true" action="pluginController.do?savePlugin" >
	    <input name="id" type="hidden" value="${plugin.id}">		 
	  	<table style="width:100%;" cellpadding="0" cellspacing="1" class="formtable">
	  	 <tr>
	     <td align="right" width="15%" nowrap>
	         <label class="Validform_label">客户端程序名:</label>
	     </td>
	     <td class="value" width="85%" >
		     <input name="clientName" id="clientName" class="inputxt" value="${plugin.clientName}" datatype="s2-50"  style="width:280px">
			 <span class="Validform_checktip">客户端程序名在2~50位字符，且不许为空</span>
	     </td>
	    </tr>
	  
	    <tr>
	     <td align="right" width="15%" nowrap>
	         <label class="Validform_label">客户端版本:</label>
	     </td>
	     <td class="value" width="85%" >
		     <input name="clientVersion" id="clientVersion" class="inputxt" value="${plugin.clientVersion}" datatype="s3-30">
			 <span class="Validform_checktip">客户端版本在3~30位字符，且不许为空</span>
	     </td>
	    </tr>
	     
	    <tr> 
	    <td align="right" width="15%" nowrap>
	        <label class="Validform_label">插件名称:</label>
	     </td>
	     <td class="value" width="85%" >		    
			<input name="pluginName" id="pluginName" class="inputxt" value="${plugin.pluginName}" datatype="s2-50"  style="width:280px">
			<span class="Validform_checktip">插件名称在2~50位字符，且不许为空</span>
	     </td>
	    </tr>
	    
	    <tr>
	    <td align="right" width="15%" nowrap>
	        <label class="Validform_label">插件版本:</label>			
	     </td>
	     <td class="value" width="85%" >
		     <input name="pluginVersion" id="pluginVersion" class="inputxt" value="${plugin.pluginVersion}"  datatype="s3-30">
			<span class="Validform_checktip">插件版本在3~30位字符，且不许为空</span>
	     </td>
	    </tr>	
	    
	    <tr>    
	    <td align="right" width="15%" nowrap>
	        <label class="Validform_label">插件包名:</label>			
	     </td>
	     <td class="value" width="85%" >
			<input name="pluginPackName" id="pluginPackName" class="inputxt" value="${plugin.pluginPackName}" datatype="*0-100" style="width:280px"> 
			<span class="Validform_checktip">插件包名在0-100个字符，允许空</span>  
	     </td>
	    </tr>
	    
	    <tr>
	    <td align="right" width="15%" nowrap>
	        <label class="Validform_label">插件描述:</label>			
	     </td>
	     <td class="value" width="85%" >
	        <% request.setAttribute("vEnter", "\r\n"); //换行在el中不能用常量表达    %>      
            <textarea rows="6" class="inputxt" style="width:80%" name="pluginDesc" id="pluginDesc">${fn:replace(plugin.pluginDesc,"<br/>",vEnter)}</textarea>
		    <!--<input name="pluginDesc" id="pluginDesc" class="inputxt" value="${plugin.pluginDesc}" datatype="*0-100" style="width:280px"> 
			<span class="Validform_checktip">插件描述在0~100位字符，允许空</span>-->  
	     </td>
	    </tr>
	    
	    <tr>
	    <td align="right" width="15%" nowrap>
	         <label class="Validform_label">插件图片路径:</label>			
	     </td>
	     <td class="value" width="85%" >
		     <input name="pluginIconUrl" id="pluginIconUrl" class="inputxt" value="${plugin.pluginIconUrl}"  datatype="*3-200" style="width:280px">
			<span class="Validform_checktip">插件图片路径在3~200位字符，且不许为空</span>
	     </td>
	    </tr>
	    
	    <tr>
	    <td align="right" width="15%" nowrap>
	         <label class="Validform_label">插件图片上传:</label>			
	     </td>
	     <td class="value">
	     	<t:upload name="imagefileplugin" id="imagefileplugin" buttonText="上传到服务器" dialog="false"  auto="true" multi="false" 
		     	 uploader="systemController.do?saveFiles&uploadLocation=server" 
		     	 extend="pic" queueID="picId" showUrlId="pluginIconUrl" ></t:upload>
		    <span id="picId" ></span>
	     </td>
	    </tr>	
	    
	    <tr>
	    <td align="right" width="15%" nowrap>
	         <label class="Validform_label">插件文件路径:</label>			
	     </td>
	     <td class="value" width="85%" >
		     <input name="pluginFileUrl" id="pluginFileUrl" class="inputxt" value="${plugin.pluginFileUrl}"  datatype="*3-100" style="width:280px">
			<span class="Validform_checktip">插件文件路径在3~200位字符，且不许为空</span>
	     </td>
	    </tr>
	    
	    <tr>
	    <td align="right" width="15%" nowrap>
	         <label class="Validform_label">插件文件上传:</label>			
	     </td>
	     <td class="value">
		     <t:upload name="fielsplugin" id="fielsplugin" buttonText="上传到服务器" dialog="false" auto="true" multi="false" 
		         uploader="systemController.do?saveFiles&uploadLocation=server" 
		         extend="*.so;*.dex;*.jar;*.apk" queueID="androidId" showUrlId="pluginFileUrl" ></t:upload>
		     <span id="androidId" ></span>
	     </td>
	    </tr>		 	
    	</table>	
	</t:formvalid>			 			  
	</body>
</html>

