package com.smile.bank.employee.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.smile.bank.dao.dbutil.PostgresConnection;
import com.smile.bank.employee.dao.EmployeeLoginDAO;
import com.smile.bank.exception.SmileException;
import com.smile.bank.log.SmileLog;
import com.smile.bank.model.UserLogin;

public class EmployeLoginDAOImpl implements EmployeeLoginDAO {
	SmileLog smile = new SmileLog();
	int success = 0;
	int c = 0;

	@Override
	public int employeeLogin(UserLogin login) throws SmileException {
		ResultSet rs;

		String email = null;
		String password = null;

		try (Connection connection = PostgresConnection.getConnection()) {
			String qry = "select * from bank_schema.employee_creds where email=? and password = ?";
			PreparedStatement preparedStatement = null;
			preparedStatement = connection.prepareStatement(qry);
			preparedStatement.setString(1, login.getEmail());
			preparedStatement.setString(2, login.getPassword());
			rs = preparedStatement.executeQuery();

			if (rs.next()) {
				email = rs.getString(3);
				password = rs.getString(2);
				if (email.equals(login.getEmail())) {
					success++;
				}
				if (password.equals(login.getPassword())) {
					success++;
				}
				if (success == 2) {
					c = 1;
				}
			}
			if (success < 2) {
				c = 0;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			smile.eventFail(e);
			e.printStackTrace();
			c = 0;
			throw new SmileException("FUBAR");
		}

		return c;
	}

}
