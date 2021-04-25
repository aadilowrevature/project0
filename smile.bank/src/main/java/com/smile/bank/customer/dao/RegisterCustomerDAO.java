package com.smile.bank.customer.dao;

import com.smile.bank.exception.SmileException;
import com.smile.bank.model.Customer;
import com.smile.bank.model.Customer_Creds;

public interface RegisterCustomerDAO {
	
	  public int createCustomer(Customer customer) throws SmileException;
	  
	  public int createCustomerCreds(Customer_Creds customercreds) throws
	  SmileException;
	  
	  public int purge(int purgeme,int id) throws SmileException;
}
