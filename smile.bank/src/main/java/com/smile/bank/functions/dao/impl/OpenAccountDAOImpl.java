package com.smile.bank.functions.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.smile.bank.dao.dbutil.PostgresConnection;
import com.smile.bank.exception.SmileException;
import com.smile.bank.functions.dao.OpenAccountDAO;
import com.smile.bank.log.SmileLog;
import com.smile.bank.model.OpenAccount;

public class OpenAccountDAOImpl implements OpenAccountDAO {
	SmileLog smile = new SmileLog();
	int ID=0;
	@Override
	public int openChecking(OpenAccount open) throws SmileException {
		int c = 0;
		try (Connection connection = PostgresConnection.getConnection()) {
			String qry = "insert into bank_schema.checking(balance,customer_id,account_status) values (?,?,?)";
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

	@Override
	public int quickfindID(String email) throws SmileException {
		
		try (Connection connection = PostgresConnection.getConnection()) {
			String qry = "select customer_id from bank_schema.customer_creds where email =?";
			PreparedStatement preparedStatement = null;
			preparedStatement = connection.prepareStatement(qry);
			preparedStatement.setString(1, email);

			ResultSet rs= preparedStatement.executeQuery();
			rs.next();
			ID = rs.getInt(1);
			
		} catch (ClassNotFoundException | SQLException e) {
			smile.eventFail(e);
			e.printStackTrace();
			throw new SmileException("FUBAR");
		}
		return ID;
	}

}
