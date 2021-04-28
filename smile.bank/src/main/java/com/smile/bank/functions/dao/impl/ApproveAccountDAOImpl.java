package com.smile.bank.functions.dao.impl;

import com.smile.bank.dao.dbutil.PostgresConnection;
import com.smile.bank.exception.SmileException;
import com.smile.bank.functions.dao.ApproveAccountDAO;
import com.smile.bank.log.SmileLog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ApproveAccountDAOImpl implements ApproveAccountDAO {
    SmileLog smile = new SmileLog();

    @Override
    public int approveAccount(int ID, String account_type) throws SmileException {
        int c = 0;
        try (Connection connection = PostgresConnection.getConnection()) {
            String qry = null;

            if (account_type.equals("checking")) {
                qry = "update bank_schema.checking set account_status = ? where acc_num =?";
            }
            if (account_type.equals("savings")) {
                qry = "update bank_schema.savings set account_status = ? where acc_num =?";
            }

            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(qry);
            preparedStatement.setString(1, "Approved");
            preparedStatement.setInt(2, ID);

            c = preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            smile.eventFail(e);
            e.printStackTrace();
            throw new SmileException("FUBAR");
        }
        return c;
    }

    @Override
    public int denyAccount(int ID, String account_type) throws SmileException {
        int c = 0;
        try (Connection connection = PostgresConnection.getConnection()) {
            String qry = null;
            if (account_type.equals("checking")) {
                qry = "delete from bank_schema.checking where acc_num =?";
            }
            if (account_type.equals("savings")) {
                qry = "delete from bank_schema.savings where acc_num =?";
            }

            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(qry);
            preparedStatement.setInt(1, ID);

            c = preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            smile.eventFail(e);
            e.printStackTrace();
            throw new SmileException("FUBAR");
        }
        return c;
    }

    @Override
    public int searchAccount(String account_type) throws SmileException {
        int acc_num = 0;
        int customer_id = 0;
        double balance = 0;
        int ch = 0;

        Scanner scanner = new Scanner(System.in);

        try (Connection connection = PostgresConnection.getConnection()) {
            String qry = null;
            if (account_type.equals("checking")) {
                qry = "select acc_num,balance,customer_id from bank_schema.checking where account_status =?";
            }
            if (account_type.equals("savings")) {
                qry = "select acc_num,balance,customer_id  from bank_schema.savings where account_status =?";
            }
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(qry);
            preparedStatement.setString(1, "Pending");

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                acc_num = rs.getInt(1);
                balance = rs.getDouble(2);
                customer_id = rs.getInt(3);

                try { //probably should move this....
                    smile.message("Thank you for working today!");
                    smile.message("Hourly Wage: $1.50 per hour");
                    smile.message("");
                    smile.message("Entering Work Mode. Approve or Deny Account?");
                    smile.message("Checking Account ID: " + acc_num + " Balance: " + balance + " Customer ID: " + customer_id);
                    smile.message("1) Approve");
                    smile.message("2) Deny");
                    smile.message("3) Exit Work Mode");

                    ch = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {

                }
                if (ch == 1) {
                    approveAccount(acc_num, account_type);
                    ch = 0;
                } else if (ch == 2) {
                    denyAccount(acc_num, account_type);
                    ch = 0;
                } else if (ch == 3) {

                    smile.message("Terminating Work Mode");
                    smile.message("");
                }
                else{
                    smile.message("Invalid Entry: Terminating Work Mode");
                    smile.message("");
                }
            } else {
                smile.message("No Work Found!");
                smile.message("");
            }
        } catch (ClassNotFoundException | SQLException e) {
            smile.eventFail(e);
            e.printStackTrace();
            throw new SmileException("FUBAR");
        }

        return 0;
    }
}
