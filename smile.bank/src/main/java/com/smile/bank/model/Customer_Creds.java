package com.smile.bank.model;

public class Customer_Creds {

	private String email;
	private String password;
	private int customer_id;
	
	
	public Customer_Creds() {

	}


	public Customer_Creds(String email, String password, int customer_id) {
		super();
		this.email = email;
		this.password = password;
		this.customer_id=customer_id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getCustomerid() {
		return customer_id;
	}


	public void setCustomerid(int customer_id) {
		this.customer_id = customer_id;
	}


	@Override
	public String toString() {
		return "Customer_Creds [email=" + email + ", password=" + password + ", customer_id=" + customer_id + "]";
	}
	
}
