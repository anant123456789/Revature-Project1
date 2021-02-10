package com.revature.Project1;

public class viewTransactionLog {
	private int transaction_id;
	private int account_number;
	private int amount_changed;
	private String transaction_type;
	@Override
	public String toString() {
		return "[transaction_id=" + transaction_id + ", account_number=" + account_number
				+ ", amount_changed=" + amount_changed + ", transaction_type=" + transaction_type + "]";
	}
	public viewTransactionLog(int transaction_id, int account_number, int amount_changed, String transaction_type) {
		super();
		this.transaction_id = transaction_id;
		this.account_number = account_number;
		this.amount_changed = amount_changed;
		this.transaction_type = transaction_type;
	}
	
	
	


}
