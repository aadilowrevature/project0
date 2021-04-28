package com.smile.bank.functions.service.impl;

import com.smile.bank.exception.SmileException;
import com.smile.bank.functions.dao.QuickFindDAO;
import com.smile.bank.functions.dao.impl.QuickFindDAOImpl;
import com.smile.bank.functions.service.QuickFindService;
import com.smile.bank.model.Account;

import java.util.List;

public class QuickFindServiceImpl implements QuickFindService {
    private QuickFindDAO find = new QuickFindDAOImpl();
    @Override
    public int findID(String email) throws SmileException {

        return find.findID(email);
    }

    @Override
    public List<Account> findAccounts(String account_type, int customer_id) throws SmileException{
        return find.findAccounts(account_type,customer_id);
    }
}
