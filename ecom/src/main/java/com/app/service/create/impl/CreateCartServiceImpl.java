package com.app.service.create.impl;

import com.app.dao.create.CreateCartDAO;
import com.app.dao.create.impl.CreateCartDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Customer;
import com.app.model.Product;
import com.app.service.create.CreateCartService;

public class CreateCartServiceImpl implements CreateCartService {

	@Override
	public int createCart(Product product, Customer customer) throws BusinessException {
		// TODO Auto-generated method stub
		int c = 0;
		if(product.getId()!=0 && customer.getId()!=0) {
			CreateCartDAO createCartDAO = new CreateCartDAOImpl();
			c = createCartDAO.createCart(product, customer);
		}else {
			 throw new BusinessException("Invalid Request");
		}
		return c;
	}
	
}
