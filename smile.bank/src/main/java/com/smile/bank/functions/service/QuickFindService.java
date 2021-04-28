package com.smile.bank.functions.service;

import com.smile.bank.exception.SmileException;
import com.smile.bank.model.Account;

import java.util.List;

public interface QuickFindService {
    int findID(int acc_num, String account_type) throws SmileException;

    public int findID(String email) throws SmileException;

    public List<Account> findAccounts(String account_type, int customer_id) throws SmileException;
}
