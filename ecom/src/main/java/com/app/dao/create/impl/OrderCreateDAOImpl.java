package com.app.dao.create.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.dao.create.OrderCreateDAO;
import com.app.dqo.dbutil.MySqlConnection;
import com.app.exception.BusinessException;
import com.app.model.Customer;
import com.app.model.Product;
import com.app.service.delete.CartItemsDeleteService;
import com.app.service.delete.impl.CartItemsDeleteImpl;
import com.app.service.search.CartItemSearchService;
import com.app.service.search.impl.CartItemSearchServiceImpl;

public class OrderCreateDAOImpl implements OrderCreateDAO {

	@Override
	public int createOrder(Customer customer) throws BusinessException {
		// TODO Auto-generated method stub
		int c = 0;
		List<Product> products = new ArrayList<>();
		try(Connection connection = MySqlConnection.getConnection()){
			CartItemSearchService cartItemSearchService = new CartItemSearchServiceImpl();
			products = cartItemSearchService.cartItemSearhService(customer);
			String sql = "insert into orders(customerid, productid) values(?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			for(Product product: products) {
				preparedStatement.setInt(1, customer.getId());
				preparedStatement.setInt(2, product.getId());
				c+= preparedStatement.executeUpdate();
			}
			if(c==products.size()) {
				CartItemsDeleteService cartItemsDeleteService = new CartItemsDeleteImpl();
				cartItemsDeleteService.cartItemsDelete(customer);
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
			throw new BusinessException("Something went wrong");
		}
		return c;
	}
	
	

}
