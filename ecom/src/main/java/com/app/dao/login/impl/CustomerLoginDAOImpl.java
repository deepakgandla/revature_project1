package com.app.dao.login.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.app.dao.login.CustomerLoginDAO;
import com.app.dqo.dbutil.MySqlConnection;
import com.app.exception.BusinessException;
import com.app.model.*;
public class CustomerLoginDAOImpl implements CustomerLoginDAO {
    private static Logger log = Logger.getLogger(CustomerLoginDAOImpl.class);
	@Override
	public Customer customerLogin(String email, String password) throws BusinessException {
		Customer customer = new Customer();
		try(Connection connection = MySqlConnection.getConnection()){
			String sql = "select id, first_name, last_name, email, password from customer where email=? and password=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				customer.setId(resultSet.getInt("id"));
				customer.setFirst_name(resultSet.getString("first_name"));
				customer.setLast_name(resultSet.getString("last_name"));
				customer.setEmail(resultSet.getString("email"));
				customer.setPassword(resultSet.getString("password"));
			}
		}catch(ClassNotFoundException|SQLException e) {
			log.error(e);
			throw new BusinessException("Some Internal Error Occured contact Sys admin");
		}
		return customer;
	}

}
