package com.revature.Project1;

public class PendingBankAccount {
private int customer_id;
private int account_number;
private int balance;
@Override
public String toString() {
	return "[customer_id=" + customer_id + ", account_number=" + account_number + ", balance="
			+ balance + "]";
}
public PendingBankAccount(int customer_id, int account_number, int balance) {
	super();
	this.customer_id = customer_id;
	this.account_number = account_number;
	this.balance = balance;
}


}
