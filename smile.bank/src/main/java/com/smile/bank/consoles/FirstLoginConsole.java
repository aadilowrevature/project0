package com.smile.bank.consoles;

import com.smile.bank.log.SmileLog;
import com.smile.bank.login.Login;

import java.util.Scanner;

public class FirstLoginConsole {

	public void firstLoginConsole() {
		int ch = 0;
		int runonce=0;
		Login user= new Login();
		SmileLog smile= new SmileLog();
		Scanner scanner = new Scanner(System.in);
		
		do {
			try {
				smile.loginConsole();
				ch = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {

			}
			switch (ch) {
			case 1:
				// login to main console
				user.CustomerLoginMethod();
				runonce++;
				ch = 0;
				break;

			case 2:
				// fall through to main console
				smile.message("Returning to Main Menu");
				break;

			default:
				smile.error();
				break;

			}

		} while (ch != 2 && runonce==0);

	}
}
