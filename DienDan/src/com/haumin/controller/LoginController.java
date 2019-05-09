package com.haumin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.haumin.model.bean.User;
import com.haumin.model.dao.UserDAO;

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;

	public LoginController() {
		super();
		userDAO = new UserDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (userDAO.checkLogin(username, password) != null) {
			User objUser = (User) userDAO.checkLogin(username, password);
			HttpSession session = request.getSession();
			session.setAttribute("objUserLogin", objUser);
			response.sendRedirect(request.getContextPath() + "/post");
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/?msg=0");
			return;
		}

	}

}
