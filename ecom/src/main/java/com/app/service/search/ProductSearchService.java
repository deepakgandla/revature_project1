package com.app.service.search;

import com.app.exception.BusinessException;

import java.util.List;

import com.app.model.Product;
public interface ProductSearchService {
	public List<Product> searchProduct(String name) throws BusinessException;
	public List<Product> searchProduct() throws BusinessException;
}
