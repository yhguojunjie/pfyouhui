<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
  <t:datagrid name="agentUserList" title="用户列表" actionUrl="platformUserController.do?agentUserGrid" idField="userId" fit="true">
   <t:dgCol title="用户ID" field="userId" hidden="false"></t:dgCol>
   <t:dgCol title="账号" field="account" ></t:dgCol>
   <t:dgCol title="呢称" field="nickName" ></t:dgCol>
   <t:dgCol title="头像url" field="headimgUrl" image="true" thumbnail="true" ></t:dgCol>
   <t:dgCol title="创建时间" field="createTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
  </t:datagrid>
  <div id="agentUserListtb" style="padding: 3px; height: 25px">
	 <div align="right">
	          账号:
	    <input type="text" class="searchtext" name="account" id="account" style="width: 80px"/>
	       昵称:
	    <input type="text" class="searchtext" name="nickName" id="nickName" style="width: 80px"/>
	    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="agentUserListsearch()">查询</a>
	 </div>
  </div>
<script type="text/javascript">
function getLoadImage(obj){
	 obj.src="img/noimg150150.jpg";
}
</script>