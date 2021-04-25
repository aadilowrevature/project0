package com.smile.bank.login;

import java.util.Scanner;

import com.smile.bank.consoles.CustomerConsole;
import com.smile.bank.consoles.EmployeeConsole;
import com.smile.bank.customer.service.CustomerLoginService;
import com.smile.bank.customer.service.impl.CustomerLoginServiceImpl;
import com.smile.bank.employee.service.EmployeeLoginService;
import com.smile.bank.employee.service.impl.EmployeeLoginServiceImpl;
import com.smile.bank.exception.SmileException;
import com.smile.bank.log.SmileLog;
import com.smile.bank.model.UserLogin;

public class Login {
	SmileLog smile = new SmileLog();
	Scanner scanner = new Scanner(System.in);

	public void CustomerLoginMethod() {

		UserLogin newLogin = new UserLogin();
		CustomerLoginService loginService = new CustomerLoginServiceImpl();
		CustomerConsole auth = new CustomerConsole();
		
		smile.message("Please Login with your Credentials");
		smile.message("");

		smile.message("Enter your Email: ");
		newLogin.setEmail(scanner.nextLine());

		smile.message("Enter your Password: ");
		newLogin.setPassword(scanner.nextLine());

		try {
			if (loginService.customerLogin(newLogin) == 1) {
				smile.message("Login Success!");
				String email=newLogin.getEmail();
				
				auth.customerMainConsole(email);
				
			} else {
				smile.warn("Login Failed! Incorrect Email or Password");
			}

		} catch (SmileException e) {
			smile.message("Login Failed!");
			smile.eventFail(e);
		}
	}

	public void EmployeeLoginMethod() {
		UserLogin newLogin = new UserLogin();
		EmployeeLoginService loginService = new EmployeeLoginServiceImpl();
		EmployeeConsole auth = new EmployeeConsole();
		
		smile.message("Please Login with your Credentials");
		smile.message("");

		smile.message("Enter your Email: ");
		newLogin.setEmail(scanner.nextLine());

		smile.message("Enter your Password: ");
		newLogin.setPassword(scanner.nextLine());

		try {
			if ((loginService.employeeLogin(newLogin)) == 1) {
				smile.message("Login Success!");
				auth.employeeMainConsole();
				
			} else {
				smile.warn("Login Failed! Incorrect Email or Password");
			}

		} catch (SmileException e) {
			smile.message("Login Failed!");
			smile.eventFail(e);
		}

	}
}
