<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
  <head>
  	<base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<title>Spring Security 登录</title>

</head>
<body>
	<div style="text-align: center;margin-top: 10%">
		<form action="securityLogin" method="post">	
			用户名：<input name="s_username" value="sky"/>
			密码：<input name="s_password" value="sky"/>
			记住登录：<input name="spring_security_remember_me" type="checkbox" value="on">
			<input type="submit" value="登录"/>
			<input type="reset" value="重置"/>
		</form>
	</div>
</body>
</html>
