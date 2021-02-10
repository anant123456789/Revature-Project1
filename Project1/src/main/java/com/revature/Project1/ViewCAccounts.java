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
 * Servlet implementation class customerOptions
 */
public class ViewCAccounts extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(ViewCAccounts.class.getName());

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String cid = (request.getParameter("cid"));
		int cid1 = Integer.parseInt(cid);
		Account a = new Account();
		ArrayList<viewAccounts> accounts = a.viewCAccounts(cid1);
		for (viewAccounts account : accounts) {
			out.println(account.toString() + "<br/>");
		}
		logger.info("You viewed your accounts");
	
	}

	

}
