package com.smile.bank.functions.dao;

import java.util.List;

import com.smile.bank.exception.SmileException;
import com.smile.bank.model.ViewAccount;

public interface ViewAccountDAO {
	public List<ViewAccount> viewAccount(int ID,String account_type) throws SmileException;

}
