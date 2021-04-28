package com.smile.bank.functions.dao.impl;

import com.smile.bank.dao.dbutil.PostgresConnection;
import com.smile.bank.exception.SmileException;
import com.smile.bank.functions.dao.QuickFindDAO;
import com.smile.bank.log.SmileLog;
import com.smile.bank.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            if(rs.next()) {
                ID = rs.getInt("customer_id");
            }
            else
            {
                throw new SmileException("No Customer With Account Number "+ acc_num);
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
                sql = "select balance, acc_num,account_status from bank_schema.checking where customer_id=?";
            }
            if (account_type.equals("savings")) {
                sql = "select balance, acc_num,account_status from bank_schema.savings where customer_id=?";
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
}
