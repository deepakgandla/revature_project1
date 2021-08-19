package com.app.service.search.impl;

import java.util.ArrayList;
import java.util.List;

import com.app.dao.search.CustomerSearchDAO;
import com.app.dao.search.impl.CustomerSearchDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Customer;
import com.app.service.search.CustomerSearchService;

public class CustomerSearchServiceImpl implements CustomerSearchService {

	@Override
	public List<Customer> searchCustomerByName(String name) throws BusinessException {
		// TODO Auto-generated method stub
		List<Customer> customers = new ArrayList<>();
		if(name.matches("[a-zA-Z]+")) {
			CustomerSearchDAO customerSearchDAO = new CustomerSearchDAOImpl();
			
			customers = customerSearchDAO.searchCustomerByName(name);
		}else {
			throw new BusinessException("Invalid Search Request");
		}
		return customers;
	}

	@Override
	public Customer searchCustomerByEmail(String email) throws BusinessException {
		// TODO Auto-generated method stub
		Customer customer = null;
		if(email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$")) {
			CustomerSearchDAO customerSearchDAO = new CustomerSearchDAOImpl();
		    customer = customerSearchDAO.searchCustomerByEmail(email);
		}else {
			throw new BusinessException("Invalid Email");
		}
		return customer;
	}

	@Override
	public Customer searchCustomerById(int id) throws BusinessException {
		// TODO Auto-generated method stub
		Customer customer = null;
		if(id>0 && id<100) {
			CustomerSearchDAO customerSearchDAO = new CustomerSearchDAOImpl();
		    customer = customerSearchDAO.searchCustomerById(id);
		}else {
			throw new BusinessException("Invalid Search Request");
		}
		return customer;
	}

	@Override
	public Customer searchCustomerByOrderId(int orderId) throws BusinessException {
		// TODO Auto-generated method stub
		Customer customer = new Customer();
		if(orderId>0 && orderId<1000) {
			CustomerSearchDAO customerSearchDAO = new CustomerSearchDAOImpl();
		    customer = customerSearchDAO.searchCustomerByOrderId(orderId);
		}else {
			throw new BusinessException("No orders found");
		}
		return customer;
	}

}
