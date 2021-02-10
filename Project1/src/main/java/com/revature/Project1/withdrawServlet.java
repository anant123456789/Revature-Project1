package com.revature.Project1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet implementation class withdrawServlet
 */
public class withdrawServlet extends HttpServlet {
	private static Logger logger = LogManager.getLogger(withdrawServlet.class.getName());

	private static final long serialVersionUID = 1L;
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			String amount = (request.getParameter("amount"));
			String account_number =(request.getParameter("an"));
			int amount1 = Integer.parseInt(amount);
			int account_number1 = Integer.parseInt(account_number);
			CustomerAccount account = getAccount(account_number1);
			if(amount1 < 0) {
				out.println("Cannot withdraw negative money" + "<br/>");
				logger.info("Withdraw Failed");
				RequestDispatcher rd = request.getRequestDispatcher("/CustomerOptions.html");
				rd.include(request, response);
			}else {
			Transactions with = new Transactions(account);
			if(with.withdraw(amount1) == false) {
				out.println("Not enough Balance" + "<br/>");
				RequestDispatcher rd = request.getRequestDispatcher("/CustomerOptions.html");
				rd.include(request, response);}else {
			
			with.updateW(amount1, account_number1);
			out.println("Withdrawn " + amount1 + " from account no. " + account_number1 + "<br/>");
			logger.info("Withdraw Succesful for " + amount1);
			RequestDispatcher rd = request.getRequestDispatcher("/CustomerOptions.html");
			rd.include(request, response);}
	}
	}

	public CustomerAccount getAccount(int accountnumber) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/Project0", "postgres",
					"Ejux8521");
			PreparedStatement stmt = conn.prepareStatement("select balance from account where account_number = ?");
			stmt.setInt(1, accountnumber);
			ResultSet rs = stmt.executeQuery();
			int balance = -1;
			if (rs.next()) {
				balance = rs.getInt("balance");
			}
			if (balance >= 0) {
				return new CustomerAccount(accountnumber, balance);
			}

			// account_number = accountnumber;
		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
		return null;
	}
}