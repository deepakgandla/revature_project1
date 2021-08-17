package com.app.dao.create;

import com.app.exception.BusinessException;
import com.app.model.Product;

public interface ProductCreateDAO {
	public int createProduct(Product product) throws BusinessException;
}
