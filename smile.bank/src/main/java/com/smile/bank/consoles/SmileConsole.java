package com.smile.bank.consoles;

import java.util.Scanner;

import com.smile.bank.log.SmileLog;
import com.smile.bank.login.Login;
import com.smile.bank.register.Register;

public class SmileConsole {

	public void consoleMain() {
		Scanner scanner = new Scanner(System.in);
		int ch = 0;

		Register newUser = new Register();
		SmileLog smile = new SmileLog();
		Login user = new Login();
		do {
			try {
				smile.consoleMain();
				smile.select();
				ch = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {

			}

			switch (ch) {

			case 1:
				// employee login

				user.EmployeeLoginMethod();
				ch = 0; // so hitting multiple returns give error default case
				break;

			case 2:
				// customer login

				user.CustomerLoginMethod();
				ch = 0;
				break;

			case 3:
				newUser.registerMethod();
				ch = 0;
				break;
			case 4:
				// end program, fall through to Main
				break;
			default:
				smile.error();
				break;
			}
		} while (ch != 4);
	}
}
