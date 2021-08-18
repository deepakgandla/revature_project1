package com.app.dao.search.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.dao.search.CartItemSearchDAO;
import com.app.dqo.dbutil.MySqlConnection;
import com.app.exception.BusinessException;
import com.app.model.Customer;
import com.app.model.Product;

public class CartItemSearchDAOImpl implements CartItemSearchDAO {

	@Override
	public List<Product> cartItemSearhService(Customer customer) throws BusinessException {
		// TODO Auto-generated method stub
		List<Product> products = new ArrayList<>();
		try(Connection connection = MySqlConnection.getConnection()){
			String sql = "select product.id as Pid, product.name as pName, product.price as pPrice  from product where product.id in(select productid from cart where customerid=?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customer.getId());
			ResultSet result = preparedStatement.executeQuery();
			while(result.next()) {
				Product product = new Product();
				product.setId(result.getInt("Pid"));
				product.setName(result.getString("pName"));
				product.setPrice(result.getFloat("pPrice"));
				products.add(product);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		    throw new BusinessException("Something went wrong contact admin");
		}
		return products;
	}

}
