package com.smile.bank.model;

public class Customer {

	private String name;
	private String ssn;
	private int customer_id;

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(String name, String ssn, int customer_id) {
		super();
		this.name = name;
		this.ssn = ssn;
		this.customer_id = customer_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public int getCustomerid() {
		return customer_id;
	}

	public void setCustomerid(int customer_id) {
		this.customer_id=customer_id;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", ssn=" + ssn + ", customer_id=" + customer_id + "]";
	}

}
