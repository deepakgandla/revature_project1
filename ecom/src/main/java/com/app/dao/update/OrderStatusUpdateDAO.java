package com.app.dao.update;

import com.app.exception.BusinessException;
import com.app.model.Customer;

public interface OrderStatusUpdateDAO {
	public int orderStatusUpdateShipped(int orderId) throws BusinessException;
	public int orderStatusUpdateRecieved(int orderId, Customer customer) throws BusinessException;
}
