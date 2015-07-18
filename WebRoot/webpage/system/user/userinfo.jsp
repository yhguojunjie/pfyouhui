<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
 <t:base type="jquery,tools"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" layout="div" dialog="true">
    <fieldset class="step">
     <legend>用户信息  </legend>
	     <div class="form">
	         <label class="form">用户名:</label>${user.userName }
	     </div>
	     <div class="form">
	      	<label class="form">真实姓名:</label>${user.realName}
	     </div>
    </fieldset>
   </form>
  </t:formvalid>
 </body>
</html>

