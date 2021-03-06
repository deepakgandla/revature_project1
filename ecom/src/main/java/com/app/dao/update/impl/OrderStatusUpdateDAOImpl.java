package com.app.dao.update.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.app.dao.update.OrderStatusUpdateDAO;
import com.app.dqo.dbutil.MySqlConnection;
import com.app.exception.BusinessException;
import com.app.model.Customer;

public class OrderStatusUpdateDAOImpl implements OrderStatusUpdateDAO {

	@Override
	public int orderStatusUpdateShipped(int orderId) throws BusinessException {
		// TODO Auto-generated method stub
		int c =0;
		try(Connection connection = MySqlConnection.getConnection()){
			String orderStatusShipped = "Shipped";
			String orderStatusOrdered = "Ordered";
			String sql = "update orders set status=? where id=? and status=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, orderStatusShipped);
			preparedStatement.setInt(2, orderId);
			preparedStatement.setString(3, orderStatusOrdered);
			c = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			throw new BusinessException("Something went wrong");
		}
		return c;
	}

	@Override
	public int orderStatusUpdateRecieved(int orderId, Customer customer) throws BusinessException {
		// TODO Auto-generated method stub
		int c = 0;
		try(Connection connection = MySqlConnection.getConnection()){
	        String orderStatusReceived = "Received";
	        String orderStatusShipped = "Shipped";
			String sql = "update orders set status=? where id=? and status=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, orderStatusReceived);
			preparedStatement.setInt(2, orderId);
			preparedStatement.setString(3, orderStatusShipped);
			c = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			throw new BusinessException("Something went wrong");
		}
		return c;
	}

}
