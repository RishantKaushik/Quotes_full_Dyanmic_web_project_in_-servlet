package com.quotes.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quotes.database.database_user_query;
import com.quotes.encapsulation.user_data;



public class User_service extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String opt = req.getParameter("opt");
		if (opt != null) {
			int choice = Integer.parseInt(opt);
			switch (choice) {
			case 1:
				register(req, resp);
				break;
			case 2:
				login(req, resp);
				break;
			case 3:
				HttpSession hs = req.getSession(false);
				if (hs != null) {
					hs.removeAttribute("user");
					hs.invalidate();
					resp.sendRedirect("index.jsp");
				}
				break;
			}
		}
	}

	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		String email = req.getParameter("email");
		String pass = req.getParameter("pass");

		user_data user =database_user_query.getUserDao().login(email, pass);
		if (user != null) {
			HttpSession hs = req.getSession();
			hs.setAttribute("user", user);
			resp.sendRedirect("index.jsp");
		} else {
			resp.getWriter().print("Registration Failed");
		}
		req.getRequestDispatcher("index.jsp").include(req, resp);
	}

	private void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String pass = req.getParameter("pass");

		user_data user = new user_data();
		user.setName(name);
		user.setEmail(email);
		user.setPass(pass);

		int i = database_user_query.getUserDao().insert(user);
		if (i > 0) {
			resp.getWriter().print("Successfully Registration");
		} else {
			resp.getWriter().print("Registration Failed");
		}
		req.getRequestDispatcher("index.jsp").include(req, resp);

	}

}


