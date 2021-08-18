package com.app.dao.create;

import com.app.exception.BusinessException;
import com.app.model.Customer;

public interface OrderCreateDAO {
	public int createOrder(Customer customer) throws BusinessException;
}
