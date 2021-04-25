package com.smile.bank.employee.service.impl;

import com.smile.bank.customer.service.impl.CustomerValidation;
import com.smile.bank.employee.dao.EmployeeLoginDAO;
import com.smile.bank.employee.dao.impl.EmployeLoginDAOImpl;
import com.smile.bank.employee.service.EmployeeLoginService;
import com.smile.bank.exception.SmileException;
import com.smile.bank.model.UserLogin;

public class EmployeeLoginServiceImpl implements EmployeeLoginService {
	private EmployeeLoginDAO employeelogindao = new EmployeLoginDAOImpl();

	@Override
	public int employeeLogin(UserLogin login) throws SmileException {
		if (!CustomerValidation.isValidEmail(login.getEmail())) {
			throw new SmileException(login.getEmail() + " is not a valid Email.");
		}
		if (!CustomerValidation.isValidPassword(login.getPassword())) {
			throw new SmileException(login.getPassword() + " is not a valid Password.");
		}
		return employeelogindao.employeeLogin(login);
	}

}
