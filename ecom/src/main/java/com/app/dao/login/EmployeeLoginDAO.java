package com.app.dao.login;

import com.app.exception.BusinessException;
import com.app.model.Employee;

public interface EmployeeLoginDAO {
	public Employee employeeLogin(String email, String password) throws BusinessException;
}
