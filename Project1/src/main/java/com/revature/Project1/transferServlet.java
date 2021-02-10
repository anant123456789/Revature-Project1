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
 * Servlet implementation class transferServlet
 */
public class transferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int Balance;
	private static Logger logger = LogManager.getLogger(transferServlet.class.getName());

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			String amount = (request.getParameter("amount"));
			String Faccount_number =(request.getParameter("an1"));
			String Taccount_number =(request.getParameter("an2"));
			String comment =(request.getParameter("comment"));
			int amount1 = Integer.parseInt(amount);
			int account_number1 = Integer.parseInt(Faccount_number);
			int account_number2 = Integer.parseInt(Taccount_number);
			if(amount1 < 0) {
				out.println("Cannot transfer negative money");
				logger.info("Transfer Failed");
				RequestDispatcher rd = request.getRequestDispatcher("/CustomerOptions.html");
				rd.include(request, response);
			}
			if(transferCheck(account_number1) < amount1) {
				out.println("Not enough Balance");
				logger.info("Transfer Failed");
				RequestDispatcher rd = request.getRequestDispatcher("/CustomerOptions.html");
				rd.include(request, response);
			}
			Transactions pending_transfer = new Transactions();
			pending_transfer.PendingTransfer(account_number1, account_number2, amount1, comment);
			out.println("Transfer Request Sent..wait for Authorization" + "<br/>");
			logger.info("Transfer initiated");
			RequestDispatcher rd = request.getRequestDispatcher("/CustomerOptions.html");
			rd.include(request, response);
	}
	public int transferCheck(int account_number) {
		try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/Project0", "postgres",
				"Ejux8521")) {
			PreparedStatement statement = conn.prepareStatement("select balance from account where account_number = ?");
			statement.setInt(1, account_number);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Balance = rs.getInt("balance");
			}
			return Balance;
		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
			return 0;
		}
	}
}
