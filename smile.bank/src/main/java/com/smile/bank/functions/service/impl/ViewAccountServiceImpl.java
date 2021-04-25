package com.smile.bank.functions.service.impl;

import java.util.List;

import com.smile.bank.exception.SmileException;
import com.smile.bank.functions.dao.ViewAccountDAO;
import com.smile.bank.functions.dao.impl.ViewAccountDAOImpl;
import com.smile.bank.functions.service.ViewAccountService;
import com.smile.bank.model.ViewAccount;

public class ViewAccountServiceImpl implements ViewAccountService {
	ViewAccountDAO viewaccdao= new ViewAccountDAOImpl();
	
	@Override
	public List<ViewAccount> viewAccount(int ID, String account_type) throws SmileException {
		// TODO Auto-generated method stub
		return viewaccdao.viewAccount(ID, account_type);
	}

}
