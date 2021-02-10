package com.revature.Project1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet implementation class viewPendingUser
 */
public class viewPendingUser extends HttpServlet {
	private static Logger logger = LogManager.getLogger(viewPendingUser.class.getName());

	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Pending a = new Pending();
		ArrayList<viewPendingCustomer> pending = a.viewPending();
		for (viewPendingCustomer account : pending) {
			out.println(account.toString() + "<br/>");
		}
		logger.info("You viewed Pending Users");
			RequestDispatcher rd = request.getRequestDispatcher("/PendingUser.html");
			rd.include(request, response);
		
		
	}
}
	