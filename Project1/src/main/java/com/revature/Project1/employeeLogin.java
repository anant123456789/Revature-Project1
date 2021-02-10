package com.revature.Project1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet implementation class employeeLogin
 */
public class employeeLogin extends HttpServlet {
	private static Logger logger = LogManager.getLogger(employeeLogin.class.getName());

	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String eid = (request.getParameter("eid"));
		String username1 = (request.getParameter("euname"));
		String password1 = (request.getParameter("epwd"));
		int eid1 = Integer.parseInt(eid);
		LoginMenu l = new LoginMenu();
		if (l.checkLoginE(username1, password1, eid1) == true) {
			response.sendRedirect("EmployeeOptions.html");
			logger.info("Succesfully Logged in");
		} else {
			out.println("Yikes wrong login");
			RequestDispatcher rd = request.getRequestDispatcher("/login.html");
			rd.include(request, response);
			logger.info("Failed Login");
		}
	}

}
