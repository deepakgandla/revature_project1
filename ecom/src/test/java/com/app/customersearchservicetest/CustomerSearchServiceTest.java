package com.app.customersearchservicetest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.app.exception.BusinessException;
import com.app.service.search.CustomerSearchService;
import com.app.service.search.impl.CustomerSearchServiceImpl;

class CustomerSearchServiceTest {
    CustomerSearchService customersearchService = new CustomerSearchServiceImpl();
 
	@Test
	void customerSearchByNameTest() throws BusinessException {
		assertEquals(1, customersearchService.searchCustomerByName("deepak").size());
	}
	
	@Test
	void customerSearchByIdTest() throws BusinessException{
		assertEquals("deepak", customersearchService.searchCustomerById(1).getFirst_name());
		assertEquals("praneeth", customersearchService.searchCustomerById(4).getFirst_name());
	}
	
	@Test
	void customerSearchByEmail() throws BusinessException{
		assertEquals(1, customersearchService.searchCustomerByEmail("deepakgandla1@gmail.com").getId());
	}
	
	@Test
	void customerSearchByOrderId() throws BusinessException{
		assertEquals(4, customersearchService.searchCustomerByOrderId(1).getId());
	}
	

}
