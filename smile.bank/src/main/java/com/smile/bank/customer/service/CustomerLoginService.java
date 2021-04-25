package com.smile.bank.customer.service;

import com.smile.bank.exception.SmileException;
import com.smile.bank.model.UserLogin;

public interface CustomerLoginService {

	public int customerLogin(UserLogin login) throws SmileException;
}
