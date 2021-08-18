package com.app.service.search.impl;

import java.util.ArrayList;
import java.util.List;

import com.app.dao.search.CartItemSearchDAO;
import com.app.dao.search.impl.CartItemSearchDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Customer;
import com.app.model.Product;
import com.app.service.search.CartItemSearchService;

public class CartItemSearchServiceImpl implements CartItemSearchService {

	@Override
	public List<Product> cartItemSearhService(Customer customer) throws BusinessException {
		// TODO Auto-generated method stub
		List<Product> products = new ArrayList<>();
		if(customer.getId()>1 && customer.getId()<100) {
			//code to dao
			CartItemSearchDAO cartItemSearchDAO = new CartItemSearchDAOImpl();
			products = cartItemSearchDAO.cartItemSearhService(customer);
		}else {
			throw new BusinessException("Invalid Request");
		}
		return products;
	}

}
