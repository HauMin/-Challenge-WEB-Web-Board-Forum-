<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.haumin.model.bean.Post"%>
<%@page import="com.haumin.model.bean.Comment"%>
<%@page import="com.haumin.model.bean.User"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bình luận</title>
<style type="text/css">
.wrapper {
	width: 838px;
	margin: 10px auto;
}

.ytuong {
	height: auto;
	width: 730px;
	border: 1px dashed #b2b2b2;
	color: #222841
}

.comment {
	height: auto;
	width: 400px;
	border: 1px solid #b2b2b2;
	color: #222841 list-style-type: none;
}

.comment ul li {
	border-bottom: 1px solid #b2b2b2;
}
</style>
</head>
<body>
	<div class="wrapper">
		<%
			if (session.getAttribute("objUserLogin") != null) {
				User objUserLogin = (User) session.getAttribute("objUserLogin");
		%>

		Chào, <a
			href="<%=request.getContextPath()%>/personal?uid=<%=objUserLogin.getId_user()%>"><%=objUserLogin.getDisplay_name()%></a>
		<%
			}
		%>

		<a href="<%=request.getContextPath()%>/logout">Logout</a> <br /> <a
			href="<%=request.getContextPath()%>/post">Quay về diễn đàn</a>
		<h4>Ý tưởng :</h4>
		<div class="ytuong">

			<h4 style="color: red">
				<%
					Post post = (Post) request.getAttribute("layDTByID");
				%>
			</h4>
			<h4><%=post.getContent_post()%></h4>
		</div>
		<h6>Các bình luận :</h6>

		<div class="comment">
			<ul>
				<%
					ArrayList<Comment> arraylistcmt = (ArrayList<Comment>) request.getAttribute("arraylistcmt");
					if (arraylistcmt != null) {
						for (Comment item : arraylistcmt) {
				%>

				<li>
					<h6>
						<span style="color: blue"><%=item.getUser().getDisplay_name()%>:</span>
						<%=item.getContent_cmt()%></h6>
						<%if(session.getAttribute("objUserLogin") != null){
          					User objUserLogin = (User) session.getAttribute("objUserLogin");
          					if(objUserLogin.getId_user() == item.getUser().getId_user()){
        				%>
						<a href="<%=request.getContextPath() %>/edit?nid=<%=item.getPost().getId_post()%>&cid=<%=item.getId_cmt() %>">edit</a>
						<a href="<%=request.getContextPath() %>/delete?nid=<%=item.getPost().getId_post()%>&cid=<%=item.getId_cmt() %>">delete</a>
					<%}} %>
				</li>

				<%}}%>
			</ul>
		</div>

		<form class="formcomment"
			action="<%=request.getContextPath()%>/comment?nid=<%=post.getId_post()%>"
			method="post">
			<textarea id="content_cmt" name="content_cmt" rows="5" cols="60"
				maxlength="300" placeholder="Chia sẽ bình luận của bạn"></textarea>
			<br /> <input type="submit" name="submit" value="bình luận" />
		</form>

	</div>
</body>
</html>