package com.smile.bank.functions.service;

import java.util.List;

import com.smile.bank.exception.SmileException;
import com.smile.bank.model.ViewAccount;

public interface ViewAccountService {
	public List<ViewAccount>viewAccount(int ID, String account_type) throws SmileException;
}
