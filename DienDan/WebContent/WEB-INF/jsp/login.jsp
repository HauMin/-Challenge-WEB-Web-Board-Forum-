<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
 	<div class="">
 		<h1>Diễn đàn chia sẽ những ý tưởng của bạn</h1>
 		<h2>Login</h2>
		<form action="<%=request.getContextPath()%>/" method="post">
				<label>Username</label>
				<input type="text" name="username" value="" class="input-medium" />
			</p>
			<p>
				<label>Password</label>
				<input type="password" name="password" value="" class="input-medium" />
			</p>
			
				<input name="login" type="submit" value="Login" /> 
				<a href="<%=request.getContextPath()%>/create">Creat accout</a>
		</form>
	 </div>
</body>
</html>