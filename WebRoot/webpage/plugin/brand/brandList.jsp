<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
  <t:datagrid name="brandList" title="入驻品牌管理" actionUrl="brandController.do?brandGrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="logo" field="logo"  image="true" banner="true" ></t:dgCol>
   <t:dgCol title="用户id" field="userId" ></t:dgCol>
   <t:dgCol title="用户呢称" field="nickName" ></t:dgCol>
   <t:dgCol title="顺序" field="seq" ></t:dgCol>
   <t:dgCol title="创建时间" field="createTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="更新时间" field="updateTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="隐藏状态 " field="hideState" replace="显示_0,隐藏_1" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="brandController.do?delBrand&id={id}" />
   <t:dgToolBar title="新增" icon="icon-add" url="brandController.do?addorupdateBrand" funname="add" popWidth="660" popHeight="450"></t:dgToolBar>
   <t:dgToolBar title="添加" icon="icon-add" url="brandController.do?allUserList" funname="add" popWidth="660" popHeight="450"></t:dgToolBar>
   <t:dgFunOpt title="修改" funname="editBrand(id)" ></t:dgFunOpt>
  </t:datagrid>
  <script type="text/javascript">
  function editBrand(id){
	createwindow("修改","brandController.do?addorupdateBrand&id="+id,"660px","450px");
  }
   function getLoadImage(obj){
	 obj.src="img/noimg150150.jpg";
 }
</script>