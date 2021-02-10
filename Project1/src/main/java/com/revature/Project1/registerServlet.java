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
 * Servlet implementation class registerServlet
 */
public class registerServlet extends HttpServlet {
	private static Logger logger = LogManager.getLogger(registerServlet.class.getName());

	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String username = (request.getParameter("runame"));
		String password = (request.getParameter("rpwd"));
		String cid = (request.getParameter("cid"));
		String fname = (request.getParameter("fname"));
		String lname = (request.getParameter("lname"));
		int cid1 = Integer.parseInt(cid);
		LoginMenu l = new LoginMenu();
		boolean b = l.createAccount(username, cid1, password, fname, lname);
		PrintWriter out = response.getWriter();
		if(b) {
			out.println("Account Created..Awaiting aprroval");
			logger.info("Created new User Account with username " + username);
		}else {
			out.println("Account Creation failed...try again");
			logger.info("User Creation failed");
		}
		RequestDispatcher rd = request.getRequestDispatcher("/Login.html");
		rd.include(request, response);
	}

}
