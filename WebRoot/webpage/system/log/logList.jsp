<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<t:datagrid title="日志管理" name="logList" 
  actionUrl="logController.do?logGrid" idField="id" 
  sortName="operateTime" sortOrder="desc">
 <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
 <t:dgCol title="日志内容" field="logContent" query="true" ></t:dgCol>
 <t:dgCol title="操作人IP" field="note" width="40"></t:dgCol>
 <t:dgCol title="对象类型" field="objectType" width="80"></t:dgCol>
 <t:dgCol title="对象ID" field="objectId" width="40"></t:dgCol>
 <t:dgCol title="耗时" field="takeTime" width="20"></t:dgCol>
 <t:dgCol title="浏览器" field="broswer" width="40"></t:dgCol>
 <t:dgCol title="操作时间 " field="operateTime" query="true" formatter="yyyy-MM-dd hh:mm:ss" width="60"></t:dgCol>
 <t:dgCol title="日志类型 " field="operateType" query="true" ></t:dgCol>
</t:datagrid>

<script type="text/javascript">
$('#operateType').combobox({
	onSelect:function(){
		logListsearch();
	}
});
</script>
<div id="logListtb" style="padding:3px; height:25px">
 <div name="searchColums" style="float:right;padding-right:15px;">
 操作日期范围：
 <input name="beginOperateTime" id="beginOperateTime" class="easyui-datebox"  /> 
 --
 <input name="endOperateTime" id="beginOperateTime" class="easyui-datebox"  />
 日志内容:
 <input name="logContent" id="logContent" type="text" class="searchtext"  style="width:100px"/>
   日志类型:
   <!-- update---Author:宋双旺  Date:20130414 for：改变值进行查询 -->
   <select name="operateType" id="operateType" class="easyui-combobox" style="width:130px">
    <option value="" selected="selected"> 全部   </option>
    <option value="1" > 登陆    </option>
    <option value="2"> 退出   </option>
    <option value="3"> 插入   </option>
    <option value="4"> 删除   </option>
    <option value="5"> 更新   </option>
    <option value="6"> 上传   </option>
    <option value="7"> 其他    </option>
   </select>
   <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="logListsearch();">查询</a>
 </div>
</div>

