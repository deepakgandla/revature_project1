package com.app.service.create;

import com.app.exception.BusinessException;
import com.app.model.Product;

public interface ProductCreateService {
	public int createProduct(Product product) throws BusinessException;
}
