package com.revature.Project1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Pending {
	public ArrayList<viewPendingCustomer> viewPending() {
		ArrayList<viewPendingCustomer> pending = new ArrayList<viewPendingCustomer>();
		try {
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/Project0", "postgres",
					"Ejux8521");
			PreparedStatement stmt = conn.prepareStatement("select * from pending_user");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				viewPendingCustomer vp = new viewPendingCustomer(rs.getInt("customer_id"), rs.getString("username"),
						rs.getString("password"), rs.getString("first_name"), rs.getString("last_name"));
				pending.add(vp);
			}
		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();

		}
		return pending;
	}
	public ArrayList<PendingBankAccount> viewPendingBank() {
		ArrayList<PendingBankAccount> accounts = new ArrayList<PendingBankAccount>();
		try {
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/Project0", "postgres",
					"Ejux8521");
			PreparedStatement stmt = conn.prepareStatement("select * from account where account_status = false");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				PendingBankAccount pa = new PendingBankAccount(rs.getInt("customer_id"), rs.getInt("account_number"),
						rs.getInt("balance"));
				accounts.add(pa);
			}

		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
		return accounts;
	}
}
