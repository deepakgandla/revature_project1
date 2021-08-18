package com.app.service.search.impl;

import java.util.ArrayList;
import java.util.List;

import com.app.dao.search.OrderItemSearchDAO;
import com.app.dao.search.impl.OrderItemSearchDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.*;
import com.app.service.search.OrderItemSearchService;

public class OrderItemSearchServiceImpl implements OrderItemSearchService {
   @Override
   public List<Order> orderItemSeach(Customer customer) throws BusinessException{
	   List<Order> orders = new ArrayList<>();
	   if(customer.getId()>0 && customer.getId()<100) {
		   //code to dao
		   OrderItemSearchDAO orderItemSearchDAO = new OrderItemSearchDAOImpl();
		   orders = orderItemSearchDAO.orderItemSeach(customer);
	   }
	   return orders;
   }
}
