package com.smile.bank.consoles;

import com.smile.bank.functions.Functions;
import com.smile.bank.log.SmileLog;

import java.util.Scanner;

public class EmployeeConsole {
	SmileLog smile = new SmileLog();
	Functions run = new Functions();
	
	public void employeeMainConsole() {
		Scanner scanner = new Scanner(System.in);
		
		int ch = 0;
		do {
			try {
				smile.employeeConsole();
				ch = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
			}
			switch (ch) {
			case 1:
				// approve accounts
				run.AccountApprovalMethod();
				ch = 0;
				break;
			case 2:
				// view accounts
				run.ViewAccountMethod();
				ch = 0;
				break;
			case 3:
				// view transaction logs
				run.ViewTransactionLog();
				ch = 0;
				break;
			case 4:
				smile.message("Logging out...");
				smile.message("");
				break;
			default:
				smile.error();
				break;
			}
		} while (ch != 4);
	}
}
