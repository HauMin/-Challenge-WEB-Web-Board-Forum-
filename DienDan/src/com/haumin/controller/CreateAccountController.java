package com.haumin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.haumin.model.bean.User;
import com.haumin.model.dao.UserDAO;

public class CreateAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;

	public CreateAccountController() {
		super();
		userDAO = new UserDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/createaccount.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String display_name = request.getParameter("display_name");

		if ("".equals(username) || "".equals(password) || "".equals(display_name)) {
			response.sendRedirect(request.getContextPath() + "/create");
			return;
		}
		User user = new User(0, username, password, display_name);
		if (userDAO.addUser(user) > 0) {
			response.sendRedirect(request.getContextPath() + "/");
		} else {
			// thất bại

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/createaccount.jsp");
			rd.forward(request, response);
			return;
		}

	}
}
