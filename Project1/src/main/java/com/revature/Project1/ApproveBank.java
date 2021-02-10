package com.revature.Project1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet implementation class ApproveBank
 */
public class ApproveBank extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(ApproveBank.class.getName());

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String account_number = (request.getParameter("ac"));
		int account_number1 = Integer.parseInt(account_number);
		approveBank(account_number1);
		out.println("You approved bank account number: " + account_number1 + "<br/>");
		logger.info("You approved Bank account number " + account_number1);
		RequestDispatcher rd = request.getRequestDispatcher("/EmployeeOptions.html");
		rd.include(request, response);
		
	}
	public void approveBank(int account_number) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/Project0", "postgres",
					"Ejux8521");
			PreparedStatement stmt = conn
					.prepareStatement("update account set account_status = true where account_number = ?");
			stmt.setInt(1, account_number);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
	}
}