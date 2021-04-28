package com.smile.bank.functions.dao.impl;

import com.smile.bank.dao.dbutil.PostgresConnection;
import com.smile.bank.exception.SmileException;
import com.smile.bank.functions.dao.OpenAccountDAO;
import com.smile.bank.log.SmileLog;
import com.smile.bank.model.OpenAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OpenAccountDAOImpl implements OpenAccountDAO {
	SmileLog smile = new SmileLog();

	@Override
	public int openChecking(OpenAccount open) throws SmileException {

		int c = 0;
		int customer_id=open.getCustomer_id();
		double balance=open.getBalance();
		int acc_num=0;

		try (Connection connection = PostgresConnection.getConnection()) {
			String qry = "insert into bank_schema.checking(balance,customer_id,account_status) values (?,?,?)";
			PreparedStatement preparedStatement = null;
			preparedStatement = connection.prepareStatement(qry);
			preparedStatement.setDouble(1, balance);
			preparedStatement.setInt(2, customer_id);
			preparedStatement.setString(3, "Pending");
			c = preparedStatement.executeUpdate();


		} catch (ClassNotFoundException | SQLException e) {
			smile.eventFail(e);
			e.printStackTrace();
			throw new SmileException("FUBAR");
		}
		return c;
	}

	@Override
	public int openSavings(OpenAccount open) throws SmileException {
		int c = 0;
		try (Connection connection = PostgresConnection.getConnection()) {
			String qry = "insert into bank_schema.savings(balance,customer_id,account_status) values (?,?,?)";
			PreparedStatement preparedStatement = null;
			preparedStatement = connection.prepareStatement(qry);
			preparedStatement.setDouble(1, open.getBalance());
			preparedStatement.setInt(2, open.getCustomer_id());
			preparedStatement.setString(3, "Pending");
			c = preparedStatement.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			smile.eventFail(e);
			e.printStackTrace();
			throw new SmileException("FUBAR");
		}
		return c;
	}



}
