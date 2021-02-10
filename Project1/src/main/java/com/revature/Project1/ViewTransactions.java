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
 * Servlet implementation class ViewTransactions
 */
public class ViewTransactions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(ViewTransactions.class.getName());

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Transactions a = new Transactions();
		ArrayList<viewTransactionLog> transaction = a.viewTransactions();
		for (viewTransactionLog trans : transaction) {
			out.println(trans.toString() + "<br/>");
		}
		logger.info("Succesfully viewed Transaction log");
	}
}

	