package com.smile.bank.functions.dao;

import com.smile.bank.exception.SmileException;
import com.smile.bank.model.Account;

import java.util.List;

public interface QuickFindDAO {

    public int findID(String email) throws SmileException;

    int findID(int acc_num, String account_type) throws SmileException;

    public List<Account> findAccounts(String account_type, int customer_id) throws SmileException;

}
