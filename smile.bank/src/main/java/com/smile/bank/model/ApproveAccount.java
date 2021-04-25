package com.smile.bank.model;

public class ApproveAccount {
private int customer_id;
private String account_type;


public ApproveAccount() {
	
}

public ApproveAccount(int customer_id, String account_type) {
	super();
	this.customer_id = customer_id;
	this.account_type = account_type;
}

public int getCustomer_id() {
	return customer_id;
}

public void setCustomer_id(int customer_id) {
	this.customer_id = customer_id;
}

public String getAccount_status() {
	return account_type;
}

public void setAccount_status(String account_status) {
	this.account_type = account_status;
}

@Override
public String toString() {
	return "ApproveAccount [customer_id=" + customer_id + ", account_status=" + account_type + "]";
}


}
