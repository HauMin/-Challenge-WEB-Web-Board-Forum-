package com.haumin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.haumin.model.dao.CommentDAO;
import com.haumin.model.dao.PostDAO;
import com.haumin.model.dao.UserDAO;

public class DeleteCmtController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentDAO commentDAO;
	private UserDAO userDAO;
	private PostDAO postDAO;

	public DeleteCmtController() {
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
		System.out.println(nid);
		if (commentDAO.deleteItem(cid) > 0) {

			response.sendRedirect(request.getContextPath() + "/comment?nid=" + nid);
		}
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
