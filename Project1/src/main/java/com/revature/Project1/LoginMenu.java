package com.revature.Project1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginMenu {

	public boolean checkLoginC(String username1, String password1) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
		try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/Project0", "postgres",
				"Ejux8521")) {
			PreparedStatement statement = conn
					.prepareStatement("select * from public.user where username = ? and password = ?");
			statement.setString(1, username1);
			statement.setString(2, password1);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkLoginE(String username1, String password1, int employee_id) {
		try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/Project0", "postgres",
				"Ejux8521")) {
			PreparedStatement statement = conn.prepareStatement(
					"select * from public.user where username = ? and password = ? and employee_id = ?");
			statement.setString(1, username1);
			statement.setString(2, password1);
			statement.setInt(3, employee_id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean createAccount(String username, int customer_id, String password, String first_name, String last_name) {

		try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/Project0", "postgres",
				"Ejux8521")) {
			PreparedStatement statement = conn.prepareStatement(
					"insert into pending_user(customer_id, username, password, first_name, last_name) values(?, ?, ?, ?, ?)");
			statement.setInt(1, customer_id);
			statement.setString(2, username);
			statement.setString(3, password);
			statement.setString(4, first_name);
			statement.setString(5, last_name);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
			return false;
		}
	}
}
