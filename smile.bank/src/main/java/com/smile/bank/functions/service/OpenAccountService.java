package com.smile.bank.functions.service;

import com.smile.bank.exception.SmileException;
import com.smile.bank.model.OpenAccount;

public interface OpenAccountService {
	public int openChecking(OpenAccount open) throws SmileException;

	public int openSavings(OpenAccount open) throws SmileException;

	public int quickfindID(String email) throws SmileException;

}
