<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
  <t:datagrid name="joinboardList" title="加盟列表" actionUrl="joinboardController.do?joinboardGrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="联系人" field="contact" ></t:dgCol>
   <t:dgCol title="联系人手机" field="mobile" ></t:dgCol>
   <t:dgCol title="QQ" field="qq" ></t:dgCol>
   <t:dgCol title="邮箱" field="email" ></t:dgCol>
   <t:dgCol title="创建时间" field="createTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="joinboardController.do?delJoinboard&id={id}" />
   <t:dgFunOpt title="查看" funname="viewJoin(id)" ></t:dgFunOpt>
   <t:dgToolBar title="编辑" icon="icon-edit" url="joinboardController.do?addorupdateJoinboard" funname="update"></t:dgToolBar>
  </t:datagrid>
   <div id="joinboardListtb" style="padding: 3px; height: 25px">
	 <div align="right">
	          联系人:
	    <input type="text" class="searchtext" name="contact" id="contact" style="width: 80px"/>
	        手机号:
	    <input type="text" class="searchtext" name="mobile" id="mobile" style="width: 80px"/>
	    邮箱：
	    <input type="text" class="searchtext" name="email" id="email" style="width: 80px"/>
	    <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="joinboardListsearch()">查询</a>
	 </div>
  </div>
<script type="text/javascript">
function viewJoin(id){
	openwindow("查看","joinboardController.do?viewJoinboard&id="+id);
}
</script> 
  
