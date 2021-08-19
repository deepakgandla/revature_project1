package com.app.service.update.impl;

import com.app.dao.update.OrderStatusUpdateDAO;
import com.app.dao.update.impl.OrderStatusUpdateDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Customer;
import com.app.service.update.OrderStatusUpdateService;

public class OrderStatusUpdateServiceImpl implements OrderStatusUpdateService {

	@Override
	public int orderStatusUpdateShipped(int orderId) throws BusinessException {
		// TODO Auto-generated method stub
		int c =0;
		if(orderId>0 && orderId<1000) {
			//code to dao
			OrderStatusUpdateDAO orderStatusUpdateDAO = new OrderStatusUpdateDAOImpl();
			c = orderStatusUpdateDAO.orderStatusUpdateShipped(orderId);
		}
		return c;
	}

	@Override
	public int orderStatusUpdateRecieved(int orderId, Customer customer) throws BusinessException {
		// TODO Auto-generated method stub
		int c =0;
		if(orderId>0 && orderId<1000) {
			//code to dao
			OrderStatusUpdateDAO orderStatusUpdateDAO = new OrderStatusUpdateDAOImpl();
			c = orderStatusUpdateDAO.orderStatusUpdateRecieved(orderId, customer);
		}
		return c;
	}

}
