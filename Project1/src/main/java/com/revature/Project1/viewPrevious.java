package com.revature.Project1;

public class viewPrevious {
private int amountchanged;
private int account_number;
private int transaction_id;
private String transaction_type;
@Override
public String toString() {
	return  "[amountchanged=" + amountchanged + ", account_number=" + account_number + ", transaction_id="
			+ transaction_id + ", transaction_type=" + transaction_type + "]";
}
public viewPrevious(int amountchanged, int account_number, int transaction_id, String transaction_type) {
	super();
	this.amountchanged = amountchanged;
	this.account_number = account_number;
	this.transaction_id = transaction_id;
	this.transaction_type = transaction_type;
}


}
