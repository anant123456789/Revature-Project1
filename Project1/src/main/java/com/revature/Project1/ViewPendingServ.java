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
 * Servlet implementation class ViewPendingServ
 */
public class ViewPendingServ extends HttpServlet {
	private static Logger logger = LogManager.getLogger(ViewPendingServ.class.getName());

	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String account_number =(request.getParameter("an"));
		int account_number1 = Integer.parseInt(account_number);
		Transactions viewpend = new Transactions();
		ArrayList<PendingTransfer> transfers = viewpend.viewPendingTransfer(account_number1);
		if(transfers.size() == 0) {
			out.println("No Transfer Requests Found");
			logger.info("No Transfer Found");
			RequestDispatcher rd = request.getRequestDispatcher("/CustomerOptions.html");
			rd.include(request, response);
		}
		for (PendingTransfer transfer : transfers) {
			out.println(transfer.toString() + "<br/>");
		}
		out.print("<br/>");
		logger.info("Viewed Transfer Requests");
		RequestDispatcher rd = request.getRequestDispatcher("/PendingTransfer.html");
		rd.include(request, response);
		
	}
	

}
