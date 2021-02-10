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
 * Servlet implementation class loginServlet
 */
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(loginServlet.class.getName());

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String username = (request.getParameter("cuname"));
		String password = (request.getParameter("cpwd"));
		
		LoginMenu l = new LoginMenu();
		

			if (l.checkLoginC(username, password) == true) {
				response.sendRedirect("CustomerOptions.html");
				logger.info("Login Succesful");
				
			} 
			
			else {
				out.println("Yikes wrong login");
				RequestDispatcher rd = request.getRequestDispatcher("/Login.html");
				rd.include(request, response);
				logger.info("Login Failed");
			}
			
		
			
		
	}

}
