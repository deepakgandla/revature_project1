package com.app.service.search.impl;

import java.util.ArrayList;
import java.util.List;

import com.app.dao.search.ProductSearchDAO;
import com.app.dao.search.impl.ProductSearchDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Product;
import com.app.service.search.ProductSearchService;

public class ProductSearchServiceImpl implements ProductSearchService {

	@Override
	public List<Product> searchProduct(String name) throws BusinessException {
		// TODO Auto-generated method stub
		List<Product> products = new ArrayList<>();
	    if(name.length()>=1){
			
	    	ProductSearchDAO productSearchDAO = new ProductSearchDAOImpl();
	    	products = productSearchDAO.searchProduct(name);
		}else {
			throw new BusinessException("Invalid");
		}
		return products;
	}

}
