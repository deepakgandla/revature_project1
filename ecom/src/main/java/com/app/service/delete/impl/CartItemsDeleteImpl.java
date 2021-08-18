package com.app.service.delete.impl;

import com.app.dao.delete.CartItemsDeleteDAO;
import com.app.dao.delete.impl.CartItemsDeleteDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Customer;
import com.app.service.delete.CartItemsDeleteService;

public class CartItemsDeleteImpl implements CartItemsDeleteService {

	@Override
	public int cartItemsDelete(Customer customer) throws BusinessException {
		// TODO Auto-generated method stub
		int c = 0;
		if(customer.getId()>0 && customer.getId()<100){
			//code to DAO
			CartItemsDeleteDAO cartItemsDeleteDAO = new CartItemsDeleteDAOImpl();
			c = cartItemsDeleteDAO.cartItemsDelete(customer);
		}else {
			throw new BusinessException("Invalid Request");
		}
		return c;
	}
	

}
