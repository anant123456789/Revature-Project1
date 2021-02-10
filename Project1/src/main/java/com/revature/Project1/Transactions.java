package com.revature.Project1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Transactions {
	public CustomerAccount data;
	
	
	
	public Transactions(CustomerAccount data) {
		this.data = data;
	}
	
	
	public Transactions() {
		super();
	}


	public void updateAccount() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/Project0", "postgres",
					"Ejux8521");
			PreparedStatement stmt = conn.prepareStatement("select balance from account where account_number = ?");
			stmt.setInt(1, data.getAccountNumber());
			ResultSet rs = stmt.executeQuery();
			int balance = -1;
			if(rs.next()) {
				System.out.println("$ " + rs.getInt("balance"));
				balance = rs.getInt("balance");
			}
			if (balance >= 0) {
				data = new CustomerAccount(data.getAccountNumber(),balance);
				return;
			}

			// account_number = accountnumber;
		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
			data = null;
	}

	public void deposit(int amount) {
		// balance = balance + amount;
		try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/Project0", "postgres",
				"Ejux8521")) {
			PreparedStatement statement = conn
					.prepareStatement("select * from account where account_number = ?");
			statement.setInt(1, data.getAccountNumber());
			ResultSet rs = statement.executeQuery();
			
			if (!rs.next()) {
				System.out.println("Account not found");
				return;
			}
			
			PreparedStatement statement1 = conn
					.prepareStatement("update account set balance = balance + ? where account_number = ?");
			statement1.setInt(1, amount);
			statement1.setInt(2, data.getAccountNumber());
			statement1.executeUpdate();
			
			updateAccount();
		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
	}
	
	
	
	public void updateD(int amountchanged, int account_number) {
		try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/Project0", "postgres",
				"Ejux8521")) {
			PreparedStatement statement = conn.prepareStatement(
					"insert into transactions(amountchanged, account_number,transaction_type) values(?,?,'D')");
			statement.setInt(1, amountchanged);
			statement.setInt(2, account_number);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}

	}
	public boolean withdraw(int amount) {
		// balance = balance - amount;
		try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/Project0", "postgres",
				"Ejux8521")) {
			updateAccount();

			if (data.getBalance() - amount < 0) {
				System.out.println("Not enough Balance");
				return false;
			} 
			PreparedStatement statement1 = conn
					.prepareStatement("update account set balance = balance - ? where account_number = ?");
			statement1.setInt(1, amount);
			statement1.setInt(2, data.getAccountNumber());
			statement1.executeUpdate();
			
			updateAccount();
			return true;
			
		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
			return false;
		}
	}
	public void updateW(int amountchanged, int account_number) {
		try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/Project0", "postgres",
				"Ejux8521")) {
			PreparedStatement statement = conn.prepareStatement(
					"insert into transactions(amountchanged, account_number,transaction_type) values(?,?,'W')");
			statement.setInt(1, amountchanged);
			statement.setInt(2, account_number);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}

	}
	public void PendingTransfer(int from, int to, int amount, String comment) {
		try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/Project0", "postgres",
				"Ejux8521")) {
			PreparedStatement statement = conn
					.prepareStatement("insert into pending_transfer(t_from, t_to, t_amount, t_comment) values(?,?,?,?)");
			statement.setInt(1, from);
			statement.setInt(2, to);
			statement.setInt(3, amount);
			statement.setString(4, comment);
			
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
	}
	public ArrayList<PendingTransfer> viewPendingTransfer(int account_number) {
		ArrayList<PendingTransfer> transfer = new ArrayList<PendingTransfer>();
		try {
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/Project0", "postgres",
					"Ejux8521");
			PreparedStatement stmt = conn
					.prepareStatement("select * from pending_transfer where t_to = ?");
			stmt.setInt(1, account_number);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
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
	public ArrayList<viewPrevious> viewPrevious(int account_number) {
		ArrayList<viewPrevious> transaction = new ArrayList<viewPrevious>();
		try {
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/Project0", "postgres",
					"Ejux8521");
			PreparedStatement stmt = conn
					.prepareStatement("select * from transactions where account_number = ? order by transaction_id desc limit 1");
			stmt.setInt(1, account_number);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				viewPrevious vp = new viewPrevious(rs.getInt("amountchanged"), rs.getInt("account_number"),
						rs.getInt("transaction_id"), rs.getString("transaction_type"));
				transaction.add(vp);
			}
		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();

		}
		return transaction;
	}
	public ArrayList<viewTransactionLog> viewTransactions() {
		ArrayList<viewTransactionLog> transaction = new ArrayList<viewTransactionLog>();
		try {
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/Project0", "postgres",
					"Ejux8521");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from transactions");

			while (rs.next()) {
				viewTransactionLog vp = new viewTransactionLog(rs.getInt("account_number"), rs.getInt("transaction_id"),
						rs.getInt("amountchanged"), rs.getString("transaction_type"));
				transaction.add(vp);
			}

		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
		return transaction;
	}
	public void transfer(int transferFrom, int transferTo, int amount) {
		try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/Project0", "postgres",
				"Ejux8521")) {
			CallableStatement cstmt = conn.prepareCall("call transfer(?,?,?)");
			
			cstmt.setInt(1, transferFrom);
			cstmt.setInt(2, transferTo);
			cstmt.setInt(3, amount);
			cstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
	}
	public void DeclineTransfer(int transfer_id) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/Project0", "postgres",
					"Ejux8521");
			PreparedStatement stmt1 = conn.prepareStatement("delete from pending_transfer where t_id = ? ");
			stmt1.setInt(1, transfer_id);
			stmt1.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
	}
	public void updateTransfers(int amountchanged, int toAccount_Number, int fromAccount_Number) {
		try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/Project0", "postgres",
				"Ejux8521")) {
			PreparedStatement statement = conn
					.prepareStatement("insert into transactions(amountchanged, account_number,transaction_type) values(?,?,'T')");
			statement.setInt(1, amountchanged);
			statement.setInt(2, toAccount_Number);
			statement.executeUpdate();
			PreparedStatement statement1 = conn
					.prepareStatement("insert into transactions(amountchanged, account_number,transaction_type) values(?,?,'T')");
			statement1.setInt(1, amountchanged);
			statement1.setInt(2, fromAccount_Number);
			statement1.executeUpdate();
	}catch (SQLException e) {
		System.out.println("Connection failure.");
		e.printStackTrace();
	}
}
}
