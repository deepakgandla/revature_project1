package com.app.service.update;

import com.app.exception.BusinessException;
import com.app.model.Customer;

public interface OrderStatusUpdateService {
	public int orderStatusUpdateShipped(int orderId) throws BusinessException;
	public int orderStatusUpdateRecieved(int orderId, Customer customer) throws BusinessException;
}
