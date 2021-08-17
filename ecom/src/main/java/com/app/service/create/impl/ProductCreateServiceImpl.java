package com.app.service.create.impl;

import com.app.dao.create.ProductCreateDAO;
import com.app.dao.create.impl.ProductCreateDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Product;
import com.app.service.create.ProductCreateService;

public class ProductCreateServiceImpl implements ProductCreateService {

	@Override
	public int createProduct(Product product) throws BusinessException {
		// TODO Auto-generated method stub
		int c = 0;
		if(product.getName().matches("[a-zA-z]+") && product.getCategory().matches("[a-zA-z]+")) {
			//code to dao
			ProductCreateDAO productCreateDAO = new ProductCreateDAOImpl();
			c = productCreateDAO.createProduct(product);
		}else {
			throw new BusinessException("Enter valid details");
		}
		return c;
	}
	

}
