<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>信息界面</title>
<% String message = (String)request.getAttribute("message"); %>
</head>
<body>
	<h3><%=message %></h3>
	<a href="Upload.jsp">返回上传页面</a>
</body>
</html>