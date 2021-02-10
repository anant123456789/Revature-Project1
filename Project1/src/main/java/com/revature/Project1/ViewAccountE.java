package com.revature.Project1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet implementation class ViewAccountE
 */
public class ViewAccountE extends HttpServlet {
	private static Logger logger = LogManager.getLogger(ViewAccountE.class.getName());

	private static final long serialVersionUID = 1L;
  
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
			PrintWriter out = response.getWriter();
		 Account a = new Account();
			ArrayList<viewAccount> accounts = a.viewAccount();
			for (viewAccount account : accounts) {
				out.println(account.toString() + "<br/>");
			}
			logger.info("You viewed all customer accounts");
	}

	
}
