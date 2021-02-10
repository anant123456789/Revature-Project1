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
 * Servlet implementation class ViewPendingBankAccount
 */
public class ViewPendingBankAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(ViewPendingBankAccount.class.getName());

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Pending p = new Pending();
		ArrayList<PendingBankAccount> accounts = p.viewPendingBank();
		if (accounts.size() == 0) {
			out.println("No Bank Accounts found" + "<br/>");
			logger.info("No Pending Bank Accounts found");
			RequestDispatcher rd = request.getRequestDispatcher("/EmployeeOptions.html");
			rd.include(request, response);
		}
		for (PendingBankAccount account : accounts) {
			out.println(account.toString() + "<br/>");
		}
		logger.info("Succesfully viewed Pending Bank Accounts");
		RequestDispatcher rd = request.getRequestDispatcher("/PendingBank.html");
		rd.include(request, response);
	
	}

}