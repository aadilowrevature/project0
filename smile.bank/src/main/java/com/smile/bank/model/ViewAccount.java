package com.smile.bank.model;

public class ViewAccount {
	private String account_type;

	private int acc_num;
	private double balance;
	private int customer_id;
	private String account_status;

	public ViewAccount() {
		// TODO Auto-generated constructor stub
	}

	public ViewAccount(String account_type, int acc_num, double balance, int customer_id, String account_status) {
		super();
		this.account_type = account_type;
		this.acc_num = acc_num;
		this.balance = balance;
		this.customer_id = customer_id;
		this.account_status = account_status;
	}

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	public int getAcc_num() {
		return acc_num;
	}

	public void setAcc_num(int acc_num) {
		this.acc_num = acc_num;
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

	public String getAccount_status() {
		return account_status;
	}

	public void setAccount_status(String account_status) {
		this.account_status = account_status;
	}

	@Override
	public String toString() {
		return "Account Type: " + account_type + " || " + "Acc_Num: " + acc_num + ", Balance: $"
				+ balance + " || customer_id: " + customer_id + " || account_status: " + account_status;
	}

}
