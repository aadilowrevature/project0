package com.smile.bank.functions.service.impl;

import com.smile.bank.exception.SmileException;
import com.smile.bank.functions.dao.QuickFindDAO;
import com.smile.bank.functions.dao.impl.QuickFindDAOImpl;
import com.smile.bank.functions.service.QuickFindService;
import com.smile.bank.model.Account;
import com.smile.bank.model.Transactions;

import java.util.List;

public class QuickFindServiceImpl implements QuickFindService {


    private QuickFindDAO find = new QuickFindDAOImpl();
    @Override
    public int findID(String email) throws SmileException {

        return find.findID(email);
    }
    @Override
    public int findID(int acc_num, String account_type) throws SmileException{
        return find.findID(acc_num, account_type);
    }

    @Override
    public List<Account> findAccounts(String account_type, int customer_id) throws SmileException{
        return find.findAccounts(account_type,customer_id);
    }

    @Override
    public Account findAccounts(String account_type, int customer_id, int acc_num) throws SmileException{
        return find.findAccounts(account_type,customer_id,acc_num);
    }
    @Override
    public List<Transactions> findTransactions(String filter_spec, int filter) throws SmileException{
            if(filter<1 || filter >7){
                throw new SmileException("Invalid Selection!");
            }
            if((filter==4 || filter==5) && !(Integer.parseInt(filter_spec)>0) ){
                throw new SmileException("PLEASE ENTER A NUMBER GREATER THAN 0");
            }
/*
            if(filter==6 && !(filter_spec.matches("[a-zA-Z]{2,15} [a-zA-Z]{2,14}"))){
                throw new SmileException("THIS NAME IS NOT VALID");
                }

 */

        return find.findTransactions(filter_spec,filter);
    }
}
