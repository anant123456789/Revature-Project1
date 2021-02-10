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
 * Servlet implementation class RejectBank
 */
public class RejectBank extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(RejectBank.class.getName());

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String account_number = (request.getParameter("ac"));
		int account_number1 = Integer.parseInt(account_number);
		rejectBank(account_number1);
		out.println("You rejected bank account number: " + account_number1 + "<br/>");
		logger.info("You rejected bank account number " + account_number1);

		RequestDispatcher rd = request.getRequestDispatcher("/EmployeeOptions.html");
		rd.include(request, response);
		
	}
	public void rejectBank(int account_number) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/Project0", "postgres",
					"Ejux8521");
			PreparedStatement stmt1 = conn.prepareStatement("delete from account where account_number = ? ");
			stmt1.setInt(1, account_number);
			stmt1.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
	}
}