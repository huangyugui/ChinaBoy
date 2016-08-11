<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
		<form action="login" method="post">	
			用户名：<input name="j_username" value=""/>
			密码：<input name="j_password" value=""/>
			<input type="submit" value="登录"/>
			<input type="reset" value="重置"/>
		</form>
	</div>
</body>
</html>
