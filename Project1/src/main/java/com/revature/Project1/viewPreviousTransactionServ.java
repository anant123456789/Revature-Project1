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

import jdk.internal.org.jline.utils.Log;


 
public class viewPreviousTransactionServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(viewPreviousTransactionServ.class.getName());

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String account_number =(request.getParameter("an"));
		int account_number1 = Integer.parseInt(account_number);
		
		Transactions a = new Transactions();
		ArrayList<viewPrevious> transaction = a.viewPrevious(account_number1);
		for (viewPrevious trans : transaction) {
			out.println(trans.toString());
		}
		logger.info("You viewed your previous transaction");
	
	
}
}
