package com.app.dao.search.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.dao.search.OrderItemSearchDAO;
import com.app.dqo.dbutil.MySqlConnection;
import com.app.exception.BusinessException;
import com.app.model.Customer;
import com.app.model.Order;
import com.app.model.Product;

public class OrderItemSearchDAOImpl implements OrderItemSearchDAO {

	@Override
	public List<Order> orderItemSeach(Customer customer) throws BusinessException {
		// TODO Auto-generated method stub
		List<Order> orders = new ArrayList<>();
		try(Connection connection = MySqlConnection.getConnection()){
			String sql = "SELECT * FROM ecom.orders, ecom.product where product.id =productid and customerid =?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, customer.getId());
			ResultSet result = preparedStatement.executeQuery();
			while(result.next()) {
				Order order = new Order();
				Product product = new Product();
				product.setName(result.getString("name"));
				order.setCustomer(customer);
				order.setProduct(product);
				order.setStatus(result.getString("status"));
				order.setId(result.getInt("id"));
				orders.add(order);
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			throw new BusinessException("Internal Error Occured");
		}
		return orders;
	}
	
}
