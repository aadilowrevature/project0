package com.smile.bank.functions.dao;

import com.smile.bank.exception.SmileException;
import com.smile.bank.model.OpenAccount;

public interface OpenAccountDAO {
	public int openChecking(OpenAccount open) throws SmileException;

	public int openSavings(OpenAccount open) throws SmileException;
	

}
