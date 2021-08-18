package com.app.dao.search.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.dao.search.ProductSearchDAO;
import com.app.dqo.dbutil.MySqlConnection;
import com.app.exception.BusinessException;
import com.app.model.Product;

public class ProductSearchDAOImpl implements ProductSearchDAO {

	@Override
	public List<Product> searchProduct(String name) throws BusinessException {
		// TODO Auto-generated method stub
		List<Product> products = new ArrayList<Product>();
		try(Connection connection = MySqlConnection.getConnection()){
			String sql = "select id, name, category, price from product where name=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			ResultSet result = preparedStatement.executeQuery();
			while(result.next()) {
				Product product = new Product(result.getString("name"), result.getString("category"), result.getFloat("price"));
				product.setId(result.getInt("id"));
				products.add(product);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			throw new BusinessException("Something went wrong contact admin");
		}
		return products;
	}
   
	@Override
	public List<Product> searchProduct() throws BusinessException{
		List<Product> products = new ArrayList<>();
		try(Connection connection = MySqlConnection.getConnection()){
			String sql = "select * from product";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet result = preparedStatement.executeQuery();
			while(result.next()) {
				Product product = new Product(result.getString("name"), result.getString("category"), result.getShort("price"));
				product.setId(result.getInt("id"));
				products.add(product);
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
		
	}
	
	
}
