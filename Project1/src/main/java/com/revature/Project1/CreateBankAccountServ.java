package com.revature.Project1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateBankAccountServ
 */
public class CreateBankAccountServ extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String cid = (request.getParameter("cid"));
		String sb = (request.getParameter("sb"));
		int cid1 = Integer.parseInt(cid);
		int sb1 = Integer.parseInt(sb);
		Account acc = new Account();
		boolean b = acc.createBankAccount(cid1 , sb1);
		if(b) {
			out.println("Account Created..Awaiting aprroval" + "<br/>");
		}else {
			out.println("Account Creation failed...try again" + "<br/>");
		}
		RequestDispatcher rd = request.getRequestDispatcher("/CustomerOptions.html");
		rd.include(request, response);
	}

}
