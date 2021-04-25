package com.smile.bank.customer.service.impl;

import com.smile.bank.customer.service.RegisterCustomerService;
import com.smile.bank.exception.SmileException;
import com.smile.bank.model.Customer;
import com.smile.bank.model.Customer_Creds;

import com.smile.bank.customer.dao.RegisterCustomerDAO;
import com.smile.bank.customer.dao.impl.RegisterCustomerDAOImpl;
import com.smile.bank.log.SmileLog;

public class RegisterCustomerServiceImpl implements RegisterCustomerService {
	SmileLog smile=new SmileLog();
	private RegisterCustomerDAO customerregdao = new RegisterCustomerDAOImpl();

	@Override
	public int createCustomer(Customer customer) throws SmileException {
		if (!CustomerValidation.isValidName(customer.getName())) {
			throw new SmileException(customer.getName() + " is not a valid name.");
		}
		if (!CustomerValidation.isValidSSN(customer.getSsn())) {
			throw new SmileException(customer.getSsn() + " is not a valid SSN.");
		}
		return customerregdao.createCustomer(customer);
	}

	@Override
	public int createCustomerCreds(Customer_Creds customercreds) throws SmileException {
		if (!CustomerValidation.isValidEmail(customercreds.getEmail())) {
			throw new SmileException(customercreds.getEmail() + " is not a valid Email.");
		}
		if (!CustomerValidation.isValidPassword(customercreds.getPassword())) {
			throw new SmileException(customercreds.getPassword() + " is not a valid Password.");
		}
		return customerregdao.createCustomerCreds(customercreds);
	}
	@Override
	public int purge(int purgeme, int id) throws SmileException{
		if(purgeme==1) {
		smile.message("If this got called, your data got deleted from Database. Customer ID: " +id);
		return customerregdao.purge(purgeme, id);
		}
		if(purgeme==0) {
			smile.message("Customer ID: " +id);
		}
		return 0;
	}

}
