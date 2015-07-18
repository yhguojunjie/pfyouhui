<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
  <t:datagrid name="bannerList" title="Banner" actionUrl="bannerController.do?bannerGrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="顺序" field="seq" ></t:dgCol>
   <t:dgCol title="pc版Logo" field="pcLogo" image="true" banner="true" ></t:dgCol>
   <t:dgCol title="pc版链接" field="pcLink" ></t:dgCol>
   <t:dgCol title="微信版Logo" field="wxLogo" image="true" banner="true"></t:dgCol>
   <t:dgCol title="微信版链接" field="wxLink" ></t:dgCol>
   <t:dgCol title="创建时间" field="createTime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="链接类型" field="linkType" replace="模板_1,站外URL链接_2"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="bannerController.do?delBanner&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="bannerController.do?addorupdateBanner" funname="add" popWidth="660" popHeight="450"></t:dgToolBar>
   <t:dgFunOpt title="编辑" funname="editBanner(id)" ></t:dgFunOpt>
  </t:datagrid>
  <script type="text/javascript">
  function editBanner(id){
	createwindow("编辑","bannerController.do?addorupdateBanner&id="+id,"660px","450px");
  }
   function getLoadImage(obj){
	 obj.src="img/noimg150150.jpg";
 }
</script>