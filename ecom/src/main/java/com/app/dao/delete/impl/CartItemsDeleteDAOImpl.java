package com.app.dao.delete.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.app.dao.delete.CartItemsDeleteDAO;
import com.app.dqo.dbutil.MySqlConnection;
import com.app.exception.BusinessException;
import com.app.model.Customer;

public class CartItemsDeleteDAOImpl implements CartItemsDeleteDAO {
	@Override
	public int cartItemsDelete(Customer customer) throws BusinessException{
		int c=0;
		try(Connection connection = MySqlConnection.getConnection()){
			String sql = "delete from cart where cart.customerid=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customer.getId());
			c = preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			throw new BusinessException("Cannot delete the items at the moment");
		}
		return c;
	}
	
}
