package com.app.service.create;
import com.app.exception.BusinessException;
import com.app.model.*;
public interface CreateCartService {
	public int createCart(Product product, Customer customer) throws BusinessException;
}
