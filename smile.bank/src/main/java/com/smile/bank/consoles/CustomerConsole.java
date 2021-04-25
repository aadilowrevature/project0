package com.smile.bank.consoles;

import com.smile.bank.exception.SmileException;
import com.smile.bank.functions.Functions;
import com.smile.bank.functions.dao.OpenAccountDAO;
import com.smile.bank.functions.dao.impl.OpenAccountDAOImpl;
import com.smile.bank.log.SmileLog;

import java.util.Scanner;

public class CustomerConsole {
	OpenAccountDAO runn = new OpenAccountDAOImpl();

	public void customerMainConsole(String email) {
		Scanner scanner = new Scanner(System.in);
		SmileLog smile = new SmileLog();
		Functions run= new Functions();
		int ID=0;
		try {
			ID = runn.quickfindID(email);
			smile.message("ID is  "+ID);
		} catch (SmileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int ch = 0;
		do {
			try {
				smile.message("User: "+email);
				smile.customerConsole();
				ch = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
			}
			switch (ch) {

			case 1:
				// Open checking or savings account
				run.OpenAccountMethod(email);
				ch = 0;
				break;
			case 2:
				// Withdraw
				//run.WithdrawMethod();
				smile.message("WIP");
				ch = 0;
				break;
			case 3:
				// Deposit
				//run.DepositMethod();
				smile.message("WIP");
				ch = 0;
				break;
			case 4:
				// Transfer
				//run.TransferMethod();
				smile.message("WIP");
				ch = 0;
				break;
			case 5:
				// Send Money
				//run.SendMoneyMethod();
				smile.message("WIP");
				ch = 0;
				break;
			case 6:
				//view balance
				run.ViewAccountCustomer(ID);
				ch = 0;
				break;
			case 7:
				// Logout
				smile.message("Logging out...");
				smile.message("");
				break;
			default:
				smile.error();
				break;
			}
		} while (ch != 7);
	}
}
