package com.app.dao.search.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.dao.search.CustomerSearchDAO;
import com.app.dqo.dbutil.MySqlConnection;
import com.app.exception.BusinessException;
import com.app.model.Customer;

public class CustomerSearchDAOImpl implements CustomerSearchDAO {

	@Override
	public List<Customer> searchCustomerByName(String name) throws BusinessException {
		// TODO Auto-generated method stub
		List<Customer> customers = new ArrayList<>();
		try(Connection connection = MySqlConnection.getConnection()){
			String sql = "select * from customer where customer.first_name=? or last_name=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, name);
			ResultSet result = preparedStatement.executeQuery();
			while(result.next()) {
				Customer customer = new Customer();
				customer.setId(result.getInt("id"));
				customer.setFirst_name(result.getString("first_name"));
				customer.setLast_name(result.getString("last_name"));
				customer.setEmail(result.getString("email"));
				customers.add(customer);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			throw new BusinessException("Internal Error Occured");
		}
		return customers;
	}

	@Override
	public Customer searchCustomerByEmail(String email) throws BusinessException {
		// TODO Auto-generated method stub
		Customer customer = null;
		try(Connection connection = MySqlConnection.getConnection()){
			String sql = "select * from customer where customer.email=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, email);
			ResultSet result = preparedStatement.executeQuery();
			if(result.next()) {
				customer = new Customer();
				customer.setId(result.getInt("id"));
				customer.setFirst_name(result.getString("first_name"));
				customer.setLast_name(result.getString("last_name"));
				customer.setEmail(result.getString("email"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			throw new BusinessException("Internal Error");
		}
		return customer;
	}

	

	@Override
	public Customer searchCustomerByOrderId(int orderId) throws BusinessException {
		// TODO Auto-generated method stub
		Customer customer = null;
		try(Connection connection = MySqlConnection.getConnection()){
			String sql = "select * from customer where customer.id = (select customerid from orders where id=?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, orderId);
			ResultSet result = preparedStatement.executeQuery();
			if(result.next()) {
				customer = new Customer();
				customer.setId(result.getInt("id"));
				customer.setFirst_name(result.getString("first_name"));
				customer.setLast_name(result.getString("last_name"));
				customer.setEmail(result.getString("email"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			throw new BusinessException("Internal Error");
		}
		return customer;
	}

	@Override
	public Customer searchCustomerById(int id) throws BusinessException {
		// TODO Auto-generated method stub
		Customer customer = null;
		try(Connection connection = MySqlConnection.getConnection()){
			String sql = "select * from customer where customer.id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			if(result.next()) {
				customer = new Customer();
				customer.setId(result.getInt("id"));
				customer.setFirst_name(result.getString("first_name"));
				customer.setLast_name(result.getString("last_name"));
				customer.setEmail(result.getString("email"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			throw new BusinessException("Internal Error");
		}
		return customer;
	}

}
