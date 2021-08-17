package com.app.dao.create.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.app.dao.create.CustomerCreateDAO;
import com.app.dqo.dbutil.MySqlConnection;
import com.app.exception.BusinessException;
import com.app.model.Customer;

public class CustomerCreateDAOImpl implements CustomerCreateDAO {

	@Override
	public int createCustomer(Customer customer) throws BusinessException {
		// TODO Auto-generated method stub
		int c = 0;
		try(Connection connection = MySqlConnection.getConnection()){
			String sql = "insert into customer(first_name, last_name, email, password) values(?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, customer.getFirst_name());
			preparedStatement.setString(2, customer.getLast_name());
			preparedStatement.setString(3, customer.getEmail());
			preparedStatement.setString(4, customer.getPassword());
			c = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			throw new BusinessException("Something went wrong contact admin");
		}
		return c;
	}

}
