package com.smile.bank.customer.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.smile.bank.customer.dao.RegisterCustomerDAO;
import com.smile.bank.exception.SmileException;
import com.smile.bank.log.SmileLog;
import com.smile.bank.model.Customer;
import com.smile.bank.model.Customer_Creds;



import com.smile.bank.dao.dbutil.PostgresConnection;

public class RegisterCustomerDAOImpl implements RegisterCustomerDAO {
	
	int ID = 0;
	SmileLog smile= new SmileLog();
	
	@Override
	public int createCustomer(Customer customer) throws SmileException {

		int c = 0;
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "insert into bank_schema.customers(name,ssn) values (?,?)";
			PreparedStatement preparedStatement = null;
			preparedStatement = connection.prepareStatement(sql, preparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, customer.getName());
			preparedStatement.setString(2, customer.getSsn());
			c = preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();

			rs.next();
			ID = rs.getInt(3);
			customer.setCustomerid(ID);

		} catch (ClassNotFoundException | SQLException e) {
			smile.eventFail(e);
			throw new SmileException("FUBAR");

		}

		return c;
	}

	@Override
	public int createCustomerCreds(Customer_Creds customercreds) throws SmileException {

		int c = 0;
		
		PreparedStatement preparedStatement = null;

		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "insert into bank_schema.customer_creds(email,password, customer_id) values (?,?,?)";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, customercreds.getEmail());
			preparedStatement.setString(2, customercreds.getPassword());
			preparedStatement.setInt(3, customercreds.getCustomerid());
			c = preparedStatement.executeUpdate();
			

		} catch (ClassNotFoundException | SQLException e) {
			smile.eventFail(e);
			throw new SmileException("FUBAR"); //shouldn't even get called
		} 

		return c;
	}

	@Override
	public int purge(int purgeme,int id) throws SmileException {
		int c = 0;
		if (purgeme == 1) {
			try (Connection connection = PostgresConnection.getConnection()) {
				PreparedStatement purge = null;
				purge = connection.prepareStatement("delete from bank_schema.customers where customer_id=?");
				purge.setInt(1, id);
				c = purge.executeUpdate();
				
			} catch (ClassNotFoundException | SQLException e) {
				
				smile.message("catch 22 for customer_id: " +ID);	
			}
			return c;
		} else {
			return c;
		}
	}

}
