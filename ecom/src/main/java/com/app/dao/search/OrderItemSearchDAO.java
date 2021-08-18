package com.app.dao.search;

import java.util.List;

import com.app.exception.BusinessException;
import com.app.model.Customer;
import com.app.model.Order;

public interface OrderItemSearchDAO {
	public List<Order> orderItemSeach(Customer customer) throws BusinessException;
	
}
