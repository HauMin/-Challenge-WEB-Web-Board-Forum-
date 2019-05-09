<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Creat Account</title>
</head>
<body>
	<div>
		<form action="<%=request.getContextPath()%>/create" method="post">
			<label>Username</label>
			<input type="text" id="username" name="username"/> <br/>
			<label>Password</label>
			<input type="password" id="password" name="password"/> <br/>
			<label>Display Name</label>
			<input type="text" id="display_name" name="display_name"/> <br/>
			<input name="them" type="submit" value="create" /> 
			
		</form>
		<a href="<%=request.getContextPath()%>/">Return</a>
	</div>
</body>
</html>