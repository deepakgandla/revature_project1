package com.app.dao.create.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.app.dao.create.CreateCartDAO;
import com.app.dqo.dbutil.MySqlConnection;
import com.app.exception.BusinessException;
import com.app.model.Customer;
import com.app.model.Product;

public class CreateCartDAOImpl implements CreateCartDAO {

	@Override
	public int createCart(Product product, Customer customer) throws BusinessException {
		// TODO Auto-generated method stub
		int c = 0;
		try(Connection connection = MySqlConnection.getConnection()){
			String sql = "insert into cart(customerid, productid) values(?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setInt(1, customer.getId());
	        preparedStatement.setInt(2, product.getId());
	        c = preparedStatement.executeUpdate();
	        
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			throw new BusinessException("Some Internal Error Occured contact admin");
		}
		return c;
	}

}
