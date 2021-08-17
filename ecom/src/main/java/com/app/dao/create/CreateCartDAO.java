package com.app.dao.create;

import com.app.exception.BusinessException;
import com.app.model.Customer;
import com.app.model.Product;

public interface CreateCartDAO {
	public int createCart(Product product, Customer customer) throws BusinessException;
}
