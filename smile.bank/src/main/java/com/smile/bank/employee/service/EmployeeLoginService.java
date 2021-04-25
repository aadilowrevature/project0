package com.smile.bank.employee.service;

import com.smile.bank.exception.SmileException;
import com.smile.bank.model.UserLogin;

public interface EmployeeLoginService {

	public int employeeLogin(UserLogin login) throws SmileException;
}
