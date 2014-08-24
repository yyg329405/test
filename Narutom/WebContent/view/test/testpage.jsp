<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'hello.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">

<%@ include file="/view/common/include.jsp"%>
<script>
document.write("<scr"+"ipt type='text/javascript' src='<%=path%>/view/js/test/ajaxIteation.js?a="+Math.random()+"'></scr"+"ipt>");
</script>

</head>

<body>
	sss
</body>
</html>