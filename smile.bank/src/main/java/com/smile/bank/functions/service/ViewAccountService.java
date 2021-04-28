package com.smile.bank.functions.service;

import com.smile.bank.exception.SmileException;
import com.smile.bank.model.ViewAccount;

import java.util.List;

public interface ViewAccountService { //might remove or merge with QuickFind
	public List<ViewAccount>viewAccount(int ID, String account_type) throws SmileException;
}
