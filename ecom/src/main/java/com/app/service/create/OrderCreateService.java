package com.app.service.create;

import com.app.exception.BusinessException;
import com.app.model.*;
public interface OrderCreateService {
	public int createOrder(Customer customer) throws BusinessException;
}
