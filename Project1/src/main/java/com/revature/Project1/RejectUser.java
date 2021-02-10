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
 * Servlet implementation class RejectUser
 */
public class RejectUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(RejectUser.class.getName());

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			String username = (request.getParameter("username"));
			reject(username);
			out.println("You rejected user with username: " + username + "<br/>");
			logger.info("You rejected username " + username);
			RequestDispatcher rd = request.getRequestDispatcher("/EmployeeOptions.html");
			rd.include(request, response);
	}
	public void reject(String username) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/Project0", "postgres",
					"Ejux8521");
			PreparedStatement stmt1 = conn.prepareStatement("delete from pending_user where username = ? ");
			stmt1.setString(1, username);
			stmt1.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
	}
}
