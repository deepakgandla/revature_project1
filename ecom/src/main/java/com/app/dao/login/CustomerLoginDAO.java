package com.app.dao.login;

import com.app.exception.BusinessException;
import com.app.model.*;
public interface CustomerLoginDAO {
	public Customer customerLogin(String email, String password) throws BusinessException;
}
