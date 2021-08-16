package com.app.service.login;

import com.app.exception.BusinessException;
import com.app.model.Employee;

public interface EmployeeLoginService {
	public Employee employeeLogin(String email, String password) throws BusinessException;
}
