package com.revature.Project1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet implementation class ApproveTransfer
 */
public class ApproveTransfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(ApproveTransfer.class.getName());

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int transferFrom = 0;
		int transferTo = 0;
		int amount1 = 0;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String tid = (request.getParameter("tid"));
		int transfer_id = Integer.parseInt(tid);
		ArrayList<PendingTransfer> transfers = viewPendingTransfer2(transfer_id);
		for (PendingTransfer transfer : transfers) {
			if (transfer.getTransfer_id() == transfer_id) {
				transferFrom = transfer.getFrom();
				transferTo = transfer.getTo();
				amount1 = transfer.getAmount();
			}
			}
			Transactions transfer1 = new Transactions();
			transfer1.transfer(transferFrom, transferTo, amount1);
			transfer1.DeclineTransfer(transfer_id);
			transfer1.updateTransfers(amount1, transferTo, transferFrom);
			out.println("Approved transfer of amount " + amount1 + " from account no " + transferFrom + "<br/>");
			logger.info("You approved Transfer with transfer id " + transfer_id);
			RequestDispatcher rd = request.getRequestDispatcher("/CustomerOptions.html");
			rd.include(request, response);
		}
		
	
public ArrayList<PendingTransfer> viewPendingTransfer2(int transfer_id){
	ArrayList<PendingTransfer> transfer = new ArrayList<PendingTransfer>();
	try {
		Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/Project0", "postgres",
				"Ejux8521");
		PreparedStatement stmt = conn
				.prepareStatement("select * from pending_transfer where t_id = ?");
		stmt.setInt(1, transfer_id);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			PendingTransfer pt = new PendingTransfer(rs.getInt("t_id"), rs.getInt("t_from"), rs.getInt("t_to"),
					rs.getInt("t_amount"), rs.getString("t_comment"));
			transfer.add(pt);
		}

	} catch (SQLException e) {
		System.out.println("Connection failure.");
		e.printStackTrace();
		}
	return transfer;
	}
}

