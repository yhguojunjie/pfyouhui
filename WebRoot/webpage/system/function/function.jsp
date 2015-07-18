<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<title>菜单信息</title>
		<t:base type="jquery,easyui,tools"></t:base>
		<script type="text/javascript">	
	$(function() {
		$('#cc').combotree({
			url : 'functionController.do?getFunctionTree',
	          //update-begin--Author:liutao  Date:20130413 for：为菜单选择一级,二级添加额外的限制
			onBeforeSelect :  function(node){
				if($('#functionLevel').val() == '1'){
					try{
						var pnode = $('#cc').tree('getParent', node.target);
						alert("父级节点不能选择二级菜单!");
						return false;
						
					}catch(e){}
				}
			}
		});
		
		if($('#functionLevel').val()=='1'){
			$('#pfun').show();
		}else{
			$('#pfun').hide();
		}
		
		
		$('#functionLevel').change(function(){
			if($(this).val()=='1'){
				$('#pfun').show();
				var t = $('#cc').combotree('tree');
				var nodes = t.tree('getRoots');
				for(var i=0;i<nodes.length;i++){
					$('#cc').combotree('setValue', nodes[i].id);
					break;
				}
			}else{
				var t = $('#cc').combotree('tree');
				var node = t.tree('getSelected');
				if(node){
					$('#cc').combotree('setValue', null);
				}
				$('#pfun').hide();
			}
		});
		
		$('#tsIconId').combobox({
			url:'iconController.do?getComboxIcons&id=${function.TSIcon.id}',
			valueField:'id',  
		    textField:'text',
		    onSelect:function(obj){
					$('#tsIconId').val(obj.id);
				}
		});
	});
	//update-end--Author:liutao  Date:20130413 for：为菜单选择一级,二级添加额外的限制
</script>
	</head>
	<body style="overflow-y: hidden" scroll="no">
		<t:formvalid formid="formobj" layout="div" dialog="true"
			action="functionController.do?saveFunction">
			<%--
				<input type="hidden" name="functionOrder" value="${function.functionOrder}">
			--%>
			<input name="id" type="hidden" value="${function.id}">
			<fieldset class="step">
				<div class="form">
					<label class="Validform_label">菜单名称:</label>
					<input name="functionName" class="inputxt" value="${function.functionName}" datatype="s4-20">
					<span class="Validform_checktip">菜单名称范围4~20位字符,且不为空</span>
				</div>
				<div class="form">
					<label class="Validform_label">菜单等级:</label>
					<select name="functionLevel"  class="easyui-combobox" id="functionLevel" datatype="*" style="width:155px">
						<option value="0" 
						    <c:if test="${function.functionLevel eq 0}">selected="selected"</c:if>>一级菜单
						</option>
						<option value="1"
							<c:if test="${function.functionLevel eq 1}">selected="selected"</c:if>>二级菜单
						</option>
					</select>
					<span class="Validform_checktip"></span>
				</div>
				<div class="form" id="pfun">
					<label class="Validform_label">父菜单:</label>
					<input id="cc" name="TSFunction.id" value="${function.TSFunction.id}" class="easyui-combobox" style="width:155px">					
				</div>
				<div class="form" id="funurl">
					<label class="Validform_label">菜单地址:</label>
					<input name="functionUrl" class="inputxt" value="${function.functionUrl}" style="width:400px;">
				</div>
				<div class="form">
					<label class="Validform_label">图标名称:</label>
					<select id="tsIconId" name="TSIcon.id" style="width:155px" class="easyui-combobox"></select>
				</div>
				<div class="form" id="funorder">
					<label class="Validform_label">菜单顺序:</label>
					<input name="functionOrder" class="inputxt" value="${function.functionOrder}" datatype="n 1-3">
				</div>
			</fieldset>
		</t:formvalid>
	</body>
</html>
