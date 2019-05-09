<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import="java.util.ArrayList"%>
<%@page import="com.haumin.model.bean.User"%>
<%@page import="com.haumin.model.bean.Post"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Trang Cá Nhân</title>
</head>
	<body>
		<div>
		<%if(session.getAttribute("objUserLogin") != null){
          User objUserLogin = (User) session.getAttribute("objUserLogin");
        %>
           
			Chào, <a href="<%=request.getContextPath()%>/personal"><%=objUserLogin.getDisplay_name()%></a><%} %>	
			 <a href="<%=request.getContextPath()%>/logout">Logout</a>	
		
			<h1>Diễn đàn chia sẽ những ý tưởng của bạn</h1>
			<form class="formpost" action="<%=request.getContextPath()%>/personal" method="post">
				<textarea id="content_post" name="content_post" rows="5" cols="60" maxlength="300" placeholder="Chia sẽ ý tưởng của bạn"></textarea>
				<br/>
				<input type="submit" name="submit" value="Chia sẻ"/>
			</form>
			<div class="main-content items-new">
				<ul>
				<%
					ArrayList<Post> listpostpersonal = (ArrayList<Post>) request.getAttribute("listpostpersonal");
					if(listpostpersonal != null){
						for(Post item: listpostpersonal){%>
					<li>
						<h3>
							<a href="<%=request.getContextPath()%>/comment?nid=<%=item.getId_post()%>" title=""><%=item.getContent_post() %></a>
							
						</h3>
					</li>
					<%}} %>
					
				</ul>
			</div>
		</div>		
	</body>
</html>