package com.smile.bank.functions.service.impl;

import com.smile.bank.exception.SmileException;
import com.smile.bank.functions.dao.NewTransactionDAO;
import com.smile.bank.functions.dao.impl.NewTransactionDAOImpl;
import com.smile.bank.functions.service.NewTransactionService;

public class NewTransactionServiceImpl implements NewTransactionService {
	private NewTransactionDAO ntdao = new NewTransactionDAOImpl();

	@Override
	public int withdrawAcc(int customer_id, int acc_num, String account_type, double balance, double amount)
			throws SmileException {
		if (!Validator.isValidTransaction(balance, amount)) {
			throw new SmileException("Insufficient funds!");
		}
		return ntdao.withdrawAcc(customer_id, acc_num, account_type, balance, amount);
	}

	@Override
	public int depositAcc(int customer_id, int acc_num, String account_type, double balance, double amount)
			throws SmileException {

		return ntdao.depositAcc(customer_id, acc_num, account_type, balance, amount);
	}

	@Override
	public int transferAcc(int customer_id, int acc_num, String account_type, int target_acc_num,
			String target_account_type, double balance, double amount) throws SmileException {
		if (!Validator.isValidTransaction(balance, amount)) {
			throw new SmileException("Insufficient funds!");
		}
		return ntdao.transferAcc(customer_id, acc_num, account_type, target_acc_num, target_account_type, balance,
				amount);
	}

	@Override
	public int sendMoney(int target_customer_id, int target_acc_num, String target_account_type, int customer_id,
			int acc_num, String account_type, double balance, double amount) throws SmileException {
		if (!Validator.isValidTransaction(balance, amount)) {
			throw new SmileException("Insufficient funds!");
		}
		return ntdao.sendMoney(target_customer_id, target_acc_num, target_account_type, customer_id, acc_num,
				account_type, balance, amount);
	}

}
