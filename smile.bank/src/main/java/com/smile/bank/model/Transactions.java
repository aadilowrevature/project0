package com.smile.bank.model;

public class Transactions {
	private int target_customer_id; 
	private int target_acc_num; 
	private String target_account_type; 
	private int customer_id;
	private int acc_num; 
	private String account_type; 
	private double balance;
	private double amount;
	
	public Transactions() {
		// TODO Auto-generated constructor stub
	}

	public Transactions(int target_customer_id, int target_acc_num, String target_account_type, int customer_id,
			int acc_num, String account_type, double balance, double amount) {
		super();
		this.target_customer_id = target_customer_id;
		this.target_acc_num = target_acc_num;
		this.target_account_type = target_account_type;
		this.customer_id = customer_id;
		this.acc_num = acc_num;
		this.account_type = account_type;
		this.balance = balance;
		this.amount = amount;
	}

	public int getTarget_customer_id() {
		return target_customer_id;
	}

	public void setTarget_customer_id(int target_customer_id) {
		this.target_customer_id = target_customer_id;
	}

	public int getTarget_acc_num() {
		return target_acc_num;
	}

	public void setTarget_acc_num(int target_acc_num) {
		this.target_acc_num = target_acc_num;
	}

	public String getTarget_account_type() {
		return target_account_type;
	}

	public void setTarget_account_type(String target_account_type) {
		this.target_account_type = target_account_type;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getAcc_num() {
		return acc_num;
	}

	public void setAcc_num(int acc_num) {
		this.acc_num = acc_num;
	}

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Transactions [target_customer_id=" + target_customer_id + ", target_acc_num=" + target_acc_num
				+ ", target_account_type=" + target_account_type + ", customer_id=" + customer_id + ", acc_num="
				+ acc_num + ", account_type=" + account_type + ", balance=" + balance + ", amount=" + amount + "]";
	}
	
	


}
