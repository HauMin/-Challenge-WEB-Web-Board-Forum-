<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.haumin.model.bean.User"%>
<%@page import="com.haumin.model.bean.Post"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
.wrapper{
	width:838px;
	margin: 10px auto;
}

</style>
<title>Diễn đàn</title>
</head>
<body>
	<div class="wrapper">
		<%if(session.getAttribute("objUserLogin") != null){
          User objUserLogin = (User) session.getAttribute("objUserLogin");
        %>

		Chào, <a
			href="<%=request.getContextPath()%>/personal?uid=<%=objUserLogin.getId_user()%>"><%=objUserLogin.getDisplay_name()%></a>
		<%} %>
		<a href="<%=request.getContextPath()%>/logout">Logout</a>

		<h1>Diễn đàn chia sẽ những ý tưởng của bạn</h1>
		<form class="formpost" action="<%=request.getContextPath()%>/post"
			method="post">
			<textarea id="content_post" name="content_post" rows="5" cols="60"
				maxlength="300" placeholder="Chia sẽ ý tưởng của bạn"></textarea>
			<br /> <input type="submit" name="submit" value="Chia sẻ" />
		</form>
		<div class="listpost">
			<ul>
				<%
					ArrayList<Post> arraylistpost = (ArrayList<Post>) request.getAttribute("arraylistpost");
					if(arraylistpost != null){
						for(Post item: arraylistpost){%>
				<li>
					<h3>
						<a
							href="<%=request.getContextPath()%>/comment?nid=<%=item.getId_post()%>"
							title=""><%=item.getContent_post() %></a>
						<h6>Người đăng: <%=item.getUser().getDisplay_name() %></h6>
					</h3>
				</li>
				<%}} %>

			</ul>
		</div>
	</div>
</body>
</html>