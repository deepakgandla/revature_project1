package com.app.dao.delete;

import com.app.exception.BusinessException;
import com.app.model.Customer;

public interface CartItemsDeleteDAO {
	public int cartItemsDelete(Customer customer) throws BusinessException;
}
