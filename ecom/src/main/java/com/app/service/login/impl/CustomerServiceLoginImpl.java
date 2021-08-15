package com.app.service.login.impl;

import com.app.dao.login.CustomerLoginDAO;
import com.app.dao.login.impl.CustomerLoginDAOImpl;
import com.app.exception.BusinessException;
import com.app.service.login.CustomerLoginService;
import com.app.model.Customer;
public class CustomerServiceLoginImpl implements CustomerLoginService {

	@Override
	public Customer customerLogin(String email, String password) throws BusinessException {
		Customer customer = null;
		if(email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$")) {
			CustomerLoginDAO customerLoginDAO = new CustomerLoginDAOImpl();
			customer = customerLoginDAO.customerLogin(email, password);
		}else {
			throw new BusinessException("Invalid email id");
		}
		return customer;
	}

}
