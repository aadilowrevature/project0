package com.smile.bank.functions.dao.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

import com.smile.bank.dao.dbutil.PostgresConnection;
import com.smile.bank.exception.SmileException;
import com.smile.bank.functions.dao.QuickFindDAO;
import com.smile.bank.log.SmileLog;
import com.smile.bank.model.Account;
import com.smile.bank.model.Customer;
import com.smile.bank.model.Transactions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class QuickFindDAOImpl implements QuickFindDAO {
    SmileLog smile = new SmileLog();

    @Override
    public int findID(String email) throws SmileException {

        int ID = 0;

        try (Connection connection = PostgresConnection.getConnection()) {
            String qry = "select customer_id from bank_schema.customer_creds where email =?";
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(qry);
            preparedStatement.setString(1, email);

            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            ID = rs.getInt(1);

        } catch (ClassNotFoundException | SQLException e) {
            smile.eventFail(e);
            e.printStackTrace();
            throw new SmileException("FUBAR");
        }
        return ID;
    }

    @Override
    public int findID(int acc_num, String account_type) throws SmileException {

        int ID = 0;
        String qry = null;
        try (Connection connection = PostgresConnection.getConnection()) {
            if (account_type.equals("checking")) {
                qry = "select customer_id from bank_schema.checking where acc_num =?";
            }
            if (account_type.equals("savings")) {
                qry = "select customer_id from bank_schema.savings where acc_num =?";
            }
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(qry);
            preparedStatement.setInt(1, acc_num);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                ID = rs.getInt("customer_id");
            } else {
                throw new SmileException("No Customer With Account Number " + acc_num);
            }

        } catch (ClassNotFoundException | SQLException e) {
            smile.eventFail(e);
            e.printStackTrace();
            throw new SmileException("FUBAR");
        }
        return ID;
    }

    @Override
    public List<Account> findAccounts(String account_type, int customer_id) throws SmileException {
        List<Account> accountList = new ArrayList<>();
        try (Connection connection = PostgresConnection.getConnection()) {
            String sql = null;
            if (account_type.equals("checking")) {
                sql = "select balance, acc_num,account_status from bank_schema.checking where customer_id=? order by balance";
            }
            if (account_type.equals("savings")) {
                sql = "select balance, acc_num,account_status from bank_schema.savings where customer_id=? order by balance";
            }
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, customer_id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Account account = new Account();

                account.setBalance(resultSet.getDouble("balance"));
                account.setCustomer_id(customer_id);
                account.setAcc_num(resultSet.getInt("acc_num"));
                account.setAccount_status(resultSet.getString("account_status"));
                account.setAccount_type(account_type);

                accountList.add(account);
            }
            if (accountList.size() == 0) {
                throw new SmileException("");
            }
        } catch (SQLException | ClassNotFoundException e) {
            smile.message(e.getMessage());
            throw new SmileException("Internal error");
        }
        return accountList;
    }

    @Override
    public Account findAccounts(String account_type, int customer_id, int acc_num) throws SmileException {
        Account account = new Account();
        try (Connection connection = PostgresConnection.getConnection()) {
            String sql = null;
            if (account_type.equals("checking")) {
                sql = "select balance, customer_id, account_status from bank_schema.checking where acc_num=? order by balance";
            }
            if (account_type.equals("savings")) {
                sql = "select balance, customer_id,account_status from bank_schema.savings where acc_num=? order by balance";
            }
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, acc_num);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                account.setBalance(resultSet.getDouble("balance"));
                account.setCustomer_id(resultSet.getInt("customer_id"));
                account.setAcc_num(acc_num);
                account.setAccount_status(resultSet.getString("account_status"));
                account.setAccount_type(account_type);
            } else {
                throw new SmileException("No " + account_type + " account found with account number= " + acc_num);
            }
        } catch (SQLException | ClassNotFoundException e) {
            smile.message(e.getMessage());
            throw new SmileException("Internal error");
        }
        return account;
    }

    @Override
    public List<Transactions> findTransactions(String filter_spec, int filter) throws SmileException {
        List<Transactions> transactionsList = new ArrayList<>();
        try (Connection connection = PostgresConnection.getConnection()) {
            String sql = null;

            if (filter == 1) // all transactions
            {
                sql = "select * from bank_schema.transactions order by transaction_num ";
            }
            if (filter == 2) //filter by date
            {
                sql="select * from bank_schema.transactions where timestamp between ? and now()";
            }
            if (filter == 3) //by type (withdraw or deposit or transfer: i/o)
            {
                sql = "select * from bank_schema.transactions where transaction_type = ?";
            }
            if (filter == 4) //by customer id
            {
                sql = "select * from bank_schema.transactions where customer_id=?";
            }
            if (filter == 5) // by account number
            {
                sql = "select * from bank_schema.transactions where acc_num=?";
            }
            if (filter == 6) //filter by customer name
            {
                sql = "select c.name, c.customer_id, t.acc_num, t.account_type, t.transaction_type, t.transaction_amount, " +
                        "t.transaction_num, t.timestamp from bank_schema.customers c left join bank_schema.transactions t " +
                        "on t.customer_id =c.customer_id where name like ? order by c.name";
            }
//**********
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            if (filter == 1) {

            }
            if (filter == 2) {




                    try {
                        Date filter_date = java.sql.Date.valueOf(filter_spec);

                        preparedStatement.setDate(1, filter_date);
                    } catch(IllegalArgumentException e){
                        smile.message("Invalid Date Entered");
                    }



            }
            if (filter == 3) {
                preparedStatement.setString(1, filter_spec);
            }
            if (filter == 4) {
                preparedStatement.setInt(1, Integer.parseInt(filter_spec));
            }
            if (filter == 5) {
                preparedStatement.setInt(1, Integer.parseInt(filter_spec));
            }
            if (filter == 6) {
                preparedStatement.setString(1, filter_spec + '%');
            }

            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                Transactions transactions = new Transactions();
                if (filter == 6) {
                    transactions.setName(resultSet.getString("name"));
                }
                transactions.setTimestamp(resultSet.getString("timestamp"));
                transactions.setCustomer_id(resultSet.getInt("customer_id"));
                transactions.setAcc_num(resultSet.getInt("acc_num"));
                transactions.setAmount(resultSet.getDouble("transaction_amount"));
                transactions.setAccount_type(resultSet.getString("account_type"));
                transactions.setTransaction_type(resultSet.getString("transaction_type"));
                transactions.setTransaction_num(resultSet.getInt("transaction_num"));

                transactionsList.add(transactions);
            }
            if (transactionsList.size() == 0) {
                throw new SmileException("");
            }
        } catch (SQLException | ClassNotFoundException e) {
            smile.message(e.getMessage());
            throw new SmileException("Internal error");
        }

        return transactionsList;

    }
}
