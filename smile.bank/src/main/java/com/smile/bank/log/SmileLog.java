package com.smile.bank.log;

import org.apache.log4j.Logger;

public class SmileLog {

	private static Logger log = Logger.getLogger(SmileLog.class);

	public void hello() {

		log.info("Hello! Welcome to Smile Bank!");

	}

	public void consoleMain() {
		log.info("");
		log.info("Please Login to your Account or Register!");
		log.info("");
		log.info("1) Employee Login");
		log.info("2) Customer Login");
		log.info("3) Register for an Account");
		log.info("4) Exit App");
		log.info("");
		log.info("Type and enter the number for your selection");
	}

	public void loginConsole() {
		log.info("Your Account Creation was successful! Would you like to login now?");
		log.info("1) Yes");
		log.info("2) No");
	}

	public void customerConsole() {
		log.info("Welcome! Please make a selection");
		log.info("");
		log.info("1) Open a new Checkings or Savings Account");
		log.info("2) Withdrawl");
		log.info("3) Deposit");
		log.info("4) Send Money");
		log.info("5) View Balance");
		log.info("6) Logout");
	}

	public void employeeConsole() {
		log.info("Welcome! Please make a selection");
		log.info("");
		log.info("1) Work: Approve or Deny New Accounts");
		log.info("2) View Account by ID");
		log.info("3) View Logs");
		log.info("4) Logout");
	}

	public void openAcc() {
		log.info("Would You Like to Open a Checking or Savings Account?");
		log.info("Due to COVID-19, all accounts must be Manually Approved by Employee");
		log.info("1) Checking");
		log.info("2) Savings");
		log.info("3) Go Back");
	}

	public void register(int i) {
		if (i == 1) {
			log.info(
					"Thank you for choosing Smile Bank! Please enter the requested information to create your account");
			log.info("");
			log.info("Enter your FULL NAME: ");
		}
		if (i == 2) {
			log.info("Enter your SSN (9 digit number): ");
		}
		if (i == 3) {
			log.info("Enter your Email: ");
		}
		if (i == 4) {

			log.info("Create a Password. Your password must be 8-16 characters long");

		}
		if (i == 5) {
			log.info("");
			log.info("Thank you! Your login will be your Email and Password");
		}
	}

	public void select() {
		log.info("");
		log.info("Awaiting Your Response:");
	}

	public void error() {

		log.error("Error, Invalid Selction. Please Try Again: ");
	}

	public void error2() {

		log.error("Error, Invalid Entry. ");
		log.info("");
	}

	public void testcase() {
		log.info("This case is still in construction");
	}

	public void goodbye() {
		log.info("Thank You for using Smile Bank! Goodbye!");
	}

	public void customerMade() {
		log.info("Customer Creation Success!");
	}

	public void eventFail(Exception e) {

		log.info("Reason Failed: " + e.getMessage());
		log.info("");
	}

	public void message(String message) { 
		log.info(message);
	}

	public void warn(String warn) {
		log.warn(warn);
	}
}
