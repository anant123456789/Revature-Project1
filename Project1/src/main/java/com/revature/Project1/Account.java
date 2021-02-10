package com.revature.Project1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class Account {

	public ArrayList<viewAccounts> viewCAccounts(int customer_id) {
		ArrayList<viewAccounts> accounts = new ArrayList<viewAccounts>();
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/Project0", "postgres",
					"Ejux8521");
			PreparedStatement stmt = conn.prepareStatement(
					"select * from account where customer_id = ? and account_status = true order by account_number");
			stmt.setInt(1, customer_id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				viewAccounts pa = new viewAccounts(rs.getInt("account_number"), rs.getInt("customer_id"),
						rs.getInt("balance"));
				accounts.add(pa);
			}
		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
			
		}
		return accounts;
	}
	public boolean createBankAccount(int customer_id, int starting_balance) {
		Random rand = new Random();
		int account_number = rand.nextInt(999);
		try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/Project0", "postgres",
				"Ejux8521")) {
			PreparedStatement statement = conn.prepareStatement(
					"insert into account(account_number, customer_id, balance, account_status) values(?,?,?,false)");
			statement.setInt(1, account_number);
			statement.setInt(2, customer_id);
			statement.setInt(3, starting_balance);
			
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
			return false;
		}
	}
	public ArrayList<viewAccount> viewAccount() {
		ArrayList<viewAccount> accounts = new ArrayList<viewAccount>();
		try {
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/Project0", "postgres",
					"Ejux8521");
			PreparedStatement stmt = conn.prepareStatement("select * from account where account_status = true order by account_number");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				viewAccount pa = new viewAccount(rs.getInt("account_number"), rs.getInt("balance"),
						rs.getInt("customer_id"));
				accounts.add(pa);
				
			}
		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();

		}
		return accounts;
	}
}
