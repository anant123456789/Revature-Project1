package com.revature.Project1;

public class viewAccount {
private int account_number;
private int balance;
private int customer_id;
@Override
public String toString() {
	return  "[account_number=" + account_number + ", balance=" + balance + ", customer_id=" + customer_id
			+ "]";
}
public viewAccount(int account_number, int balance, int customer_id) {
	super();
	this.account_number = account_number;
	this.balance = balance;
	this.customer_id = customer_id;
}


}
