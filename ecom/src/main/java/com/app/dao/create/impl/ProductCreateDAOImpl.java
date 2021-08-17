package com.app.dao.create.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.app.dao.create.ProductCreateDAO;
import com.app.dqo.dbutil.MySqlConnection;
import com.app.exception.BusinessException;
import com.app.model.Product;

public class ProductCreateDAOImpl implements ProductCreateDAO {

	@Override
	public int createProduct(Product product) throws BusinessException {
		// TODO Auto-generated method stub
		int c = 0;
		try(Connection connection = MySqlConnection.getConnection()){
			String sql = "insert into product(name, category, price) values(?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, product.getName());
			preparedStatement.setString(2, product.getCategory());
			preparedStatement.setFloat(3, product.getPrice());
			c = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			throw new BusinessException("Something went wrong contact admin");
		}
		return c;
	}
}
