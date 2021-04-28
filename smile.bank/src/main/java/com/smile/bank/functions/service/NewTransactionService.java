package com.smile.bank.functions.service;

import com.smile.bank.exception.SmileException;

public interface NewTransactionService {
	public int withdrawAcc(int customer_id, int acc_num, String account_type, double balance, double amount)
			throws SmileException;

	public int depositAcc(int customer_id, int acc_num, String account_type, double amount)
			throws SmileException;

	public int sendMoney(int target_customer_id,int target_acc_num, String target_account_type, int customer_id,
			int acc_num, String account_type, double balance, double amount) throws SmileException;

}
