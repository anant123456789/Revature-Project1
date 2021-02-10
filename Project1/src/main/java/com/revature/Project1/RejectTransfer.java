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
 * Servlet implementation class RejectTransfer
 */
public class RejectTransfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(RejectTransfer.class.getName());

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String tid = (request.getParameter("tid"));
		int transfer_id = Integer.parseInt(tid);
		Transactions reject = new Transactions();
		reject.DeclineTransfer(transfer_id);
		out.println("Rejected transfer with transfer id " + transfer_id + "<br/>");
		logger.info("You rejected Transfer with transfer id " + transfer_id);

		RequestDispatcher rd = request.getRequestDispatcher("/CustomerOptions.html");
		rd.include(request, response);
	}

}
