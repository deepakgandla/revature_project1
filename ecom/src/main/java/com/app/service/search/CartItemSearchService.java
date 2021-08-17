package com.app.service.search;

import java.util.List;

import com.app.exception.BusinessException;
import com.app.model.Customer;
import com.app.model.Product;

public interface CartItemSearchService {
	public List<Product> cartItemSearhService(Customer customer) throws BusinessException;
}
