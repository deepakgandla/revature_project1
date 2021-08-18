package com.app.service.delete;

import com.app.exception.BusinessException;
import com.app.model.Customer;

public interface CartItemsDeleteService {
	public int cartItemsDelete(Customer customer) throws BusinessException;
}
