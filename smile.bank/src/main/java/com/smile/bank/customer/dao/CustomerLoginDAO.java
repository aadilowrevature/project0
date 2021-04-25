package com.smile.bank.customer.dao;

import com.smile.bank.exception.SmileException;
import com.smile.bank.model.UserLogin;

public interface CustomerLoginDAO {
	public int customerLogin(UserLogin login) throws SmileException;
}
