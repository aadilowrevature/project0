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

	/*
			String qry2="insert into bank_schema.transactions (customer_id, transaction_type,account_type,transaction_amount)"
					+ "values(?,?,?,?)";
*/
			PreparedStatement p1 = null;
			//PreparedStatement p2 = null;
			p1 = connection.prepareStatement(qry);
			//p2=connection.prepareStatement(qry2);



			//Set auto-commit to false
			//connection.setAutoCommit(false);
			
			p1.setDouble(1, amount);
			p1.setInt(2, acc_num);

/*
			p2.setInt(1,customer_id);
			p2.setString(2,"Withdrawl");
			p2.setString(3,account_type);
			p2.setDouble(4,amount);
*/
			c=p1.executeUpdate();
			//p2.executeUpdate();


		} catch (ClassNotFoundException | SQLException e) {
			smile.eventFail(e);
			e.printStackTrace();
			throw new SmileException("FUBAR");
		}
		return c;
	}

	@Override
	public int depositAcc(int customer_id, int acc_num, String account_type, double balance, double amount)
			throws SmileException {
		int c = 0;
		try (Connection connection = PostgresConnection.getConnection()) {
			String qry = null;

			if (account_type.equals("checking")) {
				qry = "update bank_schema.checking set balance = balance-? where acc_num =?";
			}
			if (account_type.equals("savings")) {
				qry = "update bank_schema.savings set balance = balance+? where acc_num =?";
			}
			String qry2="insert into bank.schema.transactions (customer_id, transaction_type,account_type,transaction_amount)"
					+ "values(?,?,?,?)";
			PreparedStatement preparedStatement = null;
			PreparedStatement p2 = null;
			preparedStatement = connection.prepareStatement(qry);
			p2=connection.prepareStatement(qry2);
			
			//Set auto-commit to false
			connection.setAutoCommit(false);
			
			preparedStatement.setDouble(1, amount);
			preparedStatement.setInt(2, acc_num);
			
			p2.setInt(1,customer_id);
			p2.setString(2,"Deposit");
			p2.setString(3,account_type);
			p2.setDouble(4,amount);

			preparedStatement.addBatch();
			p2.addBatch();
			
			preparedStatement.executeBatch();
			p2.executeBatch();
			

		} catch (ClassNotFoundException | SQLException e) {
			smile.eventFail(e);
			e.printStackTrace();
			throw new SmileException("FUBAR");
		}
		return c;
	}

	@Override
	public int transferAcc(int customer_id, int acc_num, String account_type, int target_acc_num,
			String target_account_type, double balance, double amount) throws SmileException {
		int c = 0;
		try (Connection connection = PostgresConnection.getConnection()) {
			String qry = null;

			if (account_type.equals("checking")) {
				qry = "update bank_schema.checking set balance = balance-? where acc_num =?";
			}
			if (account_type.equals("savings")) {
				qry = "update bank_schema.savings set balance = balance-? where acc_num =?";
			}
			String qry2="insert into bank.schema.transactions (customer_id, transaction_type,account_type,transaction_amount)"
					+ "values(?,?,?,?)";
			PreparedStatement preparedStatement = null;
			PreparedStatement p2 = null;
			preparedStatement = connection.prepareStatement(qry);
			p2=connection.prepareStatement(qry2);
			
			//Set auto-commit to false
			connection.setAutoCommit(false);
			
			preparedStatement.setDouble(1, amount);
			preparedStatement.setInt(2, acc_num);
			
			p2.setInt(1,customer_id);
			p2.setString(2,"Transfer");
			p2.setString(3,account_type);
			p2.setDouble(4,amount);

			preparedStatement.addBatch();
			p2.addBatch();
			
			preparedStatement.executeBatch();
			p2.executeBatch();
			

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
		// TODO Auto-generated method stub
		return 0;
	}

}
