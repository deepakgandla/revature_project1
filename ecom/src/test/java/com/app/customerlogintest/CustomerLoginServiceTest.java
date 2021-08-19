package com.app.customerlogintest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.app.exception.BusinessException;
import com.app.service.login.CustomerLoginService;
import com.app.service.login.impl.CustomerServiceLoginImpl;

class CustomerLoginServiceTest {
    CustomerLoginService customerLoginService  = new CustomerServiceLoginImpl();
	@Test
	void test() throws BusinessException {
		assertEquals("praneeth", customerLoginService.customerLogin("praneeth@gmail.com", "praneeth12").getFirst_name());
	}

}
