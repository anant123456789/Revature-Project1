package com.revature.Project1;

public class viewPendingCustomer {
private int customer_id;
private String username;
private String password;
private String first_name;
private String last_name;
@Override
public String toString() {
	return "[customer_id=" + customer_id + ", username=" + username + ", password=" + password
			+ ", first_name=" + first_name + ", last_name=" + last_name + "]";
}
public viewPendingCustomer(int customer_id, String username, String password, String first_name, String last_name) {
	super();
	this.customer_id = customer_id;
	this.username = username;
	this.password = password;
	this.first_name = first_name;
	this.last_name = last_name;
}


}
