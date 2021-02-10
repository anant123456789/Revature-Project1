package com.revature.Project1;

public class CustomerAccount {
	private int accountNumber;
	private int balance;
	public CustomerAccount(int accountNumber, int balance) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		
		
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public int getBalance() {
		return balance;
	}
	
}
