package com.smile.bank.functions.service.impl;

import com.smile.bank.exception.SmileException;
import com.smile.bank.functions.dao.OpenAccountDAO;
import com.smile.bank.functions.dao.impl.OpenAccountDAOImpl;
import com.smile.bank.functions.service.OpenAccountService;
import com.smile.bank.model.OpenAccount;

public class OpenAccountServiceImpl implements OpenAccountService {
private OpenAccountDAO openaccdao = new OpenAccountDAOImpl();

	@Override
	public int openChecking(OpenAccount open) throws SmileException {
		
		return openaccdao.openChecking(open);
	}

	@Override
	public int openSavings(OpenAccount open) throws SmileException {

		return openaccdao.openSavings(open);
	}

	@Override
	public int quickfindID(String email) throws SmileException {
		
		return openaccdao.quickfindID(email);
	}

}
