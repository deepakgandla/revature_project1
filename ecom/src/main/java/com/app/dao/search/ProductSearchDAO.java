package com.app.dao.search;

import java.util.List;

import com.app.exception.BusinessException;
import com.app.model.Product;

public interface ProductSearchDAO {
	public List<Product> searchProduct(String name) throws BusinessException;
	public List<Product> searchProduct() throws BusinessException;
}
