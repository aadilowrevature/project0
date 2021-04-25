package com.smile.bank.employee.dao;

import com.smile.bank.exception.SmileException;
import com.smile.bank.model.UserLogin;

public interface EmployeeLoginDAO {
	public int employeeLogin(UserLogin login) throws SmileException;
}
