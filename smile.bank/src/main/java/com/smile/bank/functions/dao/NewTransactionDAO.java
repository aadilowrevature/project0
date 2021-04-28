package com.smile.bank.functions.dao;

import com.smile.bank.exception.SmileException;

public interface NewTransactionDAO {
	public int withdrawAcc(int customer_id, int acc_num, String account_type, double balance, double amount) throws SmileException;
	
	public int depositAcc(int customer_id, int acc_num, String account_type, double amount) throws SmileException;

	
	public int sendMoney(int target_customer_id, int target_acc_num, String target_account_type, int customer_id,
			int acc_num, String account_type, double balance, double amount) throws SmileException;
}
