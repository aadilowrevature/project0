package com.smile.bank.model;

public class OpenAccount {
private double balance;
private int customer_id;

public OpenAccount() {
	
}

public OpenAccount(double balance, int customer_id) {
	super();
	this.balance = balance;
	this.customer_id = customer_id;
}

public double getBalance() {
	return balance;
}

public void setBalance(double balance) {
	this.balance = balance;
}

public int getCustomer_id() {
	return customer_id;
}

public void setCustomer_id(int customer_id) {
	this.customer_id = customer_id;
}

@Override
public String toString() {
	return "OpenAccount [balance=" + balance + ", customer_id=" + customer_id + "]";
}


}
