package com.smile.bank.functions.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.smile.bank.dao.dbutil.PostgresConnection;
import com.smile.bank.exception.SmileException;
import com.smile.bank.functions.dao.ApproveAccountDAO;
import com.smile.bank.log.SmileLog;
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
		int ID = 0;
		int ch = 0;

		Scanner scanner = new Scanner(System.in);

		try (Connection connection = PostgresConnection.getConnection()) {
			String qry = null;
			if (account_type.equals("checking")) {
				qry = "select acc_num from bank_schema.checking where account_status =?";
			}
			if (account_type.equals("savings")) {
				qry = "select acc_num from bank_schema.savings where account_status =?";
			}
			PreparedStatement preparedStatement = null;
			preparedStatement = connection.prepareStatement(qry);
			preparedStatement.setString(1, "Pending");

			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				ID = rs.getInt(1);

				try {
					smile.message("Entering Work Mode. Approve or Deny Account?");
					smile.message("Checking Account ID: " + ID);
					smile.message("1) Approve");
					smile.message("2) Deny");
					smile.message("3) Exit Work Mode");

					ch = Integer.parseInt(scanner.nextLine());
				} catch (NumberFormatException e) {

				}
				if (ch == 1) {
					approveAccount(ID, account_type);
					ch = 0;
				} else if (ch == 2) {
					denyAccount(ID, account_type);
					ch = 0;
				} else if (ch == 0) {
					// do nothing
				} else {
					smile.message("Invalid Entry");
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
