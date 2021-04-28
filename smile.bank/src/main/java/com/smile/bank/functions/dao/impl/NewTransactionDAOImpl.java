package com.smile.bank.functions.dao.impl;

import com.smile.bank.dao.dbutil.PostgresConnection;
import com.smile.bank.exception.SmileException;
import com.smile.bank.functions.dao.NewTransactionDAO;
import com.smile.bank.log.SmileLog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NewTransactionDAOImpl implements NewTransactionDAO {
    SmileLog smile = new SmileLog();

    @Override
    public int withdrawAcc(int customer_id, int acc_num, String account_type, double balance, double amount)
            throws SmileException {

        int c = 0;
        try (Connection connection = PostgresConnection.getConnection()) {
            String qry = null;

            if (account_type.equals("checking")) {
                qry = "update bank_schema.checking set balance = balance-? where acc_num =?";
            }
            if (account_type.equals("savings")) {
                qry = "update bank_schema.savings set balance = balance-? where acc_num =?";
            }

            PreparedStatement p1 = null;

            p1 = connection.prepareStatement(qry);

            p1.setDouble(1, amount);
            p1.setInt(2, acc_num);

            c = p1.executeUpdate();

            String qry2 = "insert into bank_schema.transactions (customer_id, transaction_type,account_type,transaction_amount,acc_num)"
                    + "values(?,?,?,?,?)";
            PreparedStatement p2 = null;
            p2 = connection.prepareStatement(qry2);
            p2.setInt(1, customer_id);
            p2.setString(2, "Withdrawl");
            p2.setString(3, account_type);
            p2.setDouble(4, amount);
            p2.setInt(5, acc_num);
            c = p2.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            smile.eventFail(e);
            e.printStackTrace();
            throw new SmileException("FUBAR");
        }
        return c;
    }

    @Override
    public int depositAcc(int customer_id, int acc_num, String account_type,  double amount)
            throws SmileException {
        int c = 0;
        try (Connection connection = PostgresConnection.getConnection()) {
            String qry = null;

            if (account_type.equals("checking")) {
                qry = "update bank_schema.checking set balance = balance+? where acc_num =?";
            }
            if (account_type.equals("savings")) {
                qry = "update bank_schema.savings set balance = balance+? where acc_num =?";
            }
            PreparedStatement p1 = null;

            p1 = connection.prepareStatement(qry);
            p1.setDouble(1, amount);
            p1.setInt(2, acc_num);
            p1.executeUpdate();

            String qry2 = "insert into bank_schema.transactions (customer_id, transaction_type,account_type,transaction_amount,acc_num)"
                    + "values(?,?,?,?,?)";
            PreparedStatement p2 = null;
            p2 = connection.prepareStatement(qry2);
            p2.setInt(1, customer_id);
            p2.setString(2, "Deposit");
            p2.setString(3, account_type);
            p2.setDouble(4, amount);
            p2.setInt(5, acc_num);
            c = p2.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            smile.eventFail(e);
            e.printStackTrace();
            throw new SmileException("FUBAR");
        }
        return c;
    }

    @Override
    public int sendMoney(int target_customer_id, int target_acc_num, String target_account_type, int customer_id,
                         int acc_num, String account_type, double balance, double amount) throws SmileException {
        int c = 0;
        try (Connection connection = PostgresConnection.getConnection()) {
            String qry = null;
            //outbound
            if (account_type.equals("checking")) {
                qry = "update bank_schema.checking set balance = balance-? where acc_num =?";
            }
            if (account_type.equals("savings")) {
                qry = "update bank_schema.savings set balance = balance-? where acc_num =?";
            }
            PreparedStatement p1 = null;
            p1 = connection.prepareStatement(qry);


            p1.setDouble(1, amount);
            p1.setInt(2, acc_num);
            p1.executeUpdate();


            //inbound
            if (target_account_type.equals("checking")) {
                qry = "update bank_schema.checking set balance = balance+? where acc_num =?";
            }
            if (target_account_type.equals("savings")) {
                qry = "update bank_schema.savings set balance = balance+? where acc_num =?";
            }

            p1 = connection.prepareStatement(qry);
            p1.setDouble(1, amount);
            p1.setInt(2, target_acc_num);
            p1.executeUpdate();
            //transaction
            String qry2 = "insert into bank_schema.transactions (customer_id, transaction_type,account_type,transaction_amount,acc_num)"
                    + "values(?,?,?,?,?)";

            PreparedStatement p2 = null;
            p2 = connection.prepareStatement(qry2);
            p2.setInt(1, customer_id);
            p2.setString(2, "Transfer: Outbound ");
            p2.setString(3, account_type);
            p2.setDouble(4, amount);
            p2.setInt(5, acc_num);
            c = p2.executeUpdate();

            p2 = connection.prepareStatement(qry2);
            p2.setInt(1, target_customer_id);
            p2.setString(2, "Transfer: Inbound ");
            p2.setString(3, target_account_type);
            p2.setDouble(4, amount);
            p2.setInt(5, target_acc_num);
            c = p2.executeUpdate();


        } catch (ClassNotFoundException | SQLException e) {
            smile.eventFail(e);
            e.printStackTrace();
            throw new SmileException("FUBAR");
        }
        return c;
    }

}
