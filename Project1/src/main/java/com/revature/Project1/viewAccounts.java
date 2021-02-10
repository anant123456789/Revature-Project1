package com.revature.Project1;

public class viewAccounts {
private int accountNumber;
private int customerID;
private int balance;
@Override
public String toString() {
	return "[accountNumber=" + accountNumber + ", customerID=" + customerID + ", balance=" + balance + "]";
}
public viewAccounts(int accountNumber, int customerID, int balance) {
	super();
	this.accountNumber = accountNumber;
	this.customerID = customerID;
	this.balance = balance;
}


}
