package com.app.customertest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.app.exception.BusinessException;
import com.app.service.login.CustomerLoginService;
import com.app.service.login.impl.CustomerServiceLoginImpl;

class CustomerTest {
    CustomerLoginService customeloginservice = new CustomerServiceLoginImpl();
	@Test
	void test() throws BusinessException {
		assertEquals("praneeth", customeloginservice.customerLogin("praneeth@gmail.com", "praneeth12").getFirst_name());
	}

}
