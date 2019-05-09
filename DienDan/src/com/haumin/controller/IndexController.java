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

import com.haumin.model.bean.Post;
import com.haumin.model.dao.CommentDAO;
import com.haumin.model.dao.PostDAO;
import com.haumin.model.dao.UserDAO;
import com.haumin.model.bean.User;

public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentDAO commentDAO;
	private UserDAO userDAO;
	private PostDAO postDAO;

	public IndexController() {
		super();
		commentDAO = new CommentDAO();
		userDAO = new UserDAO();
		postDAO = new PostDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Post> arraylistpost = postDAO.getItiems();
		request.setAttribute("arraylistpost", arraylistpost);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String content_post = request.getParameter("content_post");
		HttpSession session = request.getSession();
		if (session.getAttribute("objUserLogin") != null) {
			User objUserLogin = (User) session.getAttribute("objUserLogin");
			Post post = new Post(0, content_post, objUserLogin);
			if (postDAO.addPosst(post) > 0) {
				response.sendRedirect(request.getContextPath() + "/post");
				return;
			}

		}

	}

}
