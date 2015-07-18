<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

 <t:tabs id="tt" iframe="false">
  <t:tab href="logController.do?userBroswer&reportType=line" icon="icon-search" title="用户分析line" id="pnode"></t:tab>
  <t:tab href="logController.do?userBroswer&reportType=pie" icon="icon-search" title="用户分析pie" id="pnode"></t:tab>
  <t:tab href="logController.do?userBroswer&reportType=column" icon="icon-search" title="用户分析column" id="pnode"></t:tab>
 
 </t:tabs>
