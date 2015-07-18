<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
  <t:datagrid name="platformUserList" title="所有用户列表" actionUrl="platformUserController.do?platformUserGrid" idField="userId" fit="true">
   <t:dgCol title="用户ID" field="userId" query="true"></t:dgCol>
   <t:dgCol title="账号" field="account" ></t:dgCol>
   <t:dgCol title="呢称" field="nickName" ></t:dgCol>
   <t:dgCol title="头像url" field="headimgUrl" image="true" thumbnail="true" ></t:dgCol>
   <t:dgCol title="代理商" field="agentInfo_companyName"></t:dgCol>
   <t:dgCol title="创建时间" field="createTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
  </t:datagrid>
  <div id="platformUserListtb" style="padding: 3px; height: 25px">
	 <div align="right">
	        用户ID:
	     <input type="text" class="searchtext" maxlength="10" onkeyup="this.value=this.value.replace(/\D/g,'')" name="userId" id="userId" style="width: 80px"/>
	          账号:
	    <input type="text" class="searchtext" name="account" id="account" style="width: 80px"/>
	       昵称:
	    <input type="text" class="searchtext" name="nickName" id="nickName" style="width: 80px"/>
	      代理商:
	    <input type="text" class="searchtext" name="companyName" id="companyName" style="width: 80px"/>
	    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="platformUserListsearch()">查询</a>
	 </div>
  </div>
<script type="text/javascript">
function getLoadImage(obj){
	 obj.src="img/noimg150150.jpg";
}
function aaa(){
alert("");
return ;
}
</script>
  