package com.app.service.create.impl;

import com.app.dao.create.OrderCreateDAO;
import com.app.dao.create.impl.OrderCreateDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Customer;
import com.app.service.create.OrderCreateService;

public class OrderCreateServiceImpl implements OrderCreateService {

	@Override
	public int createOrder(Customer customer) throws BusinessException {
		// TODO Auto-generated method stub
		int c=0;
		if(customer.getId()!=0) {
			//code to dao
			OrderCreateDAO orderCreateService = new OrderCreateDAOImpl();
			c = orderCreateService.createOrder(customer);
		}
		return c;
	}
}
