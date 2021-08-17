package com.app.service.create.impl;

import com.app.dao.create.CustomerCreateDAO;
import com.app.dao.create.impl.CustomerCreateDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Customer;
import com.app.service.create.CustomerCreateService;

public class CustomerCreateServiceImpl implements CustomerCreateService {

	@Override
	public int createCustomer(Customer customer) throws BusinessException {
		// TODO Auto-generated method stub
		int c = 0;
		if(customer.getFirst_name().matches("[a-zA-z]+") && customer.getLast_name().matches("[a-zA-z]+") && customer.getEmail().matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$") && customer.getPassword().length()>1) {
		    //code to DAO	
			CustomerCreateDAO customerCreateDAO = new CustomerCreateDAOImpl();
			c = customerCreateDAO.createCustomer(customer);
		}else {
			throw new BusinessException("Invalid Inputs");
		}
		return c;
	}

}
