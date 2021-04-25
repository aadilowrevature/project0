package com.smile.bank.register;

import java.util.Scanner;

import com.smile.bank.log.SmileLog;

import com.smile.bank.model.Customer;
import com.smile.bank.model.Customer_Creds;

import com.smile.bank.customer.service.RegisterCustomerService;
import com.smile.bank.customer.service.impl.RegisterCustomerServiceImpl;
import com.smile.bank.exception.SmileException;
import com.smile.bank.consoles.FirstLoginConsole;

public class Register {
	
	int ID = 0;

	public void registerMethod() {
		Scanner scanner = new Scanner(System.in);
		
		Customer newCustomer = new Customer();
		Customer_Creds newCreds = new Customer_Creds();
		RegisterCustomerService regcustServ = new RegisterCustomerServiceImpl();
		SmileLog smile = new SmileLog();
		FirstLoginConsole login = new FirstLoginConsole();
		
		int fail = 0;
		int success = 0;

		while (fail == 0 && success < 2) {
			smile.register(1); // Name
			newCustomer.setName(scanner.nextLine());

			smile.register(2); // SSN
			newCustomer.setSsn(scanner.nextLine());

			try {
				if (regcustServ.createCustomer(newCustomer) == 1) {
					smile.customerMade();
					success++;
				}
			} catch (SmileException e) {
				smile.message("Customer Creation Failed!");
				smile.eventFail(e);
				fail = 1;
				// good here since if this fails, customer creds wont be added

				if (fail == 1) {
					break;
				}

			}
			ID = newCustomer.getCustomerid();
			smile.register(3); // EMAIL
			newCreds.setEmail(scanner.nextLine());

			smile.register(4); // Password
			newCreds.setPassword(scanner.nextLine());
			newCreds.setCustomerid(ID);

			try {
				if (regcustServ.createCustomerCreds(newCreds) == 1) {
					smile.message("Credentials Created!");
					smile.message("");
					success++;
					if (success == 2) {
						break;
					}

				}
			} catch (SmileException e) {
				smile.message("Credential Creation Failed!");
				smile.eventFail(e);
				fail = 1;
				// problem here is that customer info is saved but credentials are not
				// this solves that by deleting customer by id from table

				if (fail == 1) {
					try {
						regcustServ.purge(1, ID);

					} catch (SmileException e1) {
						e1.printStackTrace();
					}
					break;
				}
			}
		}

		if (success == 2) {
			
			login.firstLoginConsole();
			//success=0;
			// logins if success account made, else falls through back to main
		}
	}
}
