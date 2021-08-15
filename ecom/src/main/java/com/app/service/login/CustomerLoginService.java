package com.app.service.login;

import com.app.exception.BusinessException;
import com.app.model.Customer;

public interface CustomerLoginService {
	public Customer customerLogin(String email, String password) throws BusinessException;
}
