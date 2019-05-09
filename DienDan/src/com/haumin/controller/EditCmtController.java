package com.haumin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.haumin.model.dao.CommentDAO;
import com.haumin.model.dao.PostDAO;
import com.haumin.model.dao.UserDAO;
import com.haumin.model.bean.Comment;
import com.haumin.model.bean.Post;
import com.haumin.model.bean.User;

public class EditCmtController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentDAO commentDAO;
	private UserDAO userDAO;
	private PostDAO postDAO;

	public EditCmtController() {
		super();
		commentDAO = new CommentDAO();
		userDAO = new UserDAO();
		postDAO = new PostDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("cid");
		int cid = Integer.parseInt(id);
		System.out.println(cid);
		String id1 = request.getParameter("nid");
		int nid = Integer.parseInt(id1);

		Post layDTByID = postDAO.getDTByID(nid);
		request.setAttribute("layDTByID", layDTByID);

		ArrayList<Comment> arraylistcmt = commentDAO.getItiems(nid);
		request.setAttribute("arraylistcmt", arraylistcmt);

		Comment cmt = commentDAO.getItem(cid);
		request.setAttribute("cmt", cmt);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/editcomment.jsp");
		rd.forward(request, response);
		return;

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("cid");
		int cid = Integer.parseInt(id);
		System.out.println(cid);
		String id1 = request.getParameter("nid");
		int nid = Integer.parseInt(id1);

		String content_cmt = request.getParameter("content_cmt");

		HttpSession session = request.getSession();
		if (session.getAttribute("objUserLogin") != null) {
			User objUserLogin = (User) session.getAttribute("objUserLogin");
			System.out.println(objUserLogin.getId_user());
			Post post = new Post(nid, "", objUserLogin);
			Comment cmt = new Comment(cid, content_cmt, objUserLogin, post);
			if (commentDAO.editItem(cmt) > 0) {
				// thành công
				response.sendRedirect(request.getContextPath() + "/comment?nid=" + nid);
				return;
			}
		}

	}

}
