package com.app.service.login.impl;

import com.app.dao.login.EmployeeLoginDAO;
import com.app.dao.login.impl.EmployeeLoginDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Employee;
import com.app.service.login.EmployeeLoginService;

public class EmployeeLoginServiceImpl implements EmployeeLoginService {

	@Override
	public Employee employeeLogin(String email, String password) throws BusinessException {
		// TODO Auto-generated method stub
		Employee employee = new Employee();
		if(email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$")) {
			//code to dao
			EmployeeLoginDAO employeeLoginDAO = new EmployeeLoginDAOImpl();
			employee = employeeLoginDAO.employeeLogin(email, password);
		}
		return employee;
	}

}
