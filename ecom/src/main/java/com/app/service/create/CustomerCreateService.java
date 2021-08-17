package com.app.service.create;

import com.app.exception.BusinessException;
import com.app.model.Customer;
public interface CustomerCreateService {
	public int createCustomer(Customer customer) throws BusinessException;
}
