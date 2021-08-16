package com.app.dao.login.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import com.app.dao.login.EmployeeLoginDAO;
import com.app.dqo.dbutil.MySqlConnection;
import com.app.exception.BusinessException;
import com.app.model.Employee;
import com.mysql.cj.xdevapi.PreparableStatement;

public class EmployeeLoginDAOImpl implements EmployeeLoginDAO {

	@Override
	public Employee employeeLogin(String email, String password) throws BusinessException {
		// TODO Auto-generated method stub
		Employee employee = new Employee();
		try(Connection connection = MySqlConnection.getConnection()){
			String sql = "select id, first_name, last_name, email, password from employee where email=? and password=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			ResultSet result = preparedStatement.executeQuery();
			if(result.next()) {
				employee.setId(result.getInt("id"));
				employee.setFirst_name(result.getString("first_name"));
				employee.setLast_name(result.getString("last_name"));
				employee.setEmail(result.getString("email"));
				employee.setPassword(result.getString("password"));
				
			}
		}catch(ClassNotFoundException|SQLException e) {
			throw new BusinessException("Some Errore Occured");
		}
		return employee;
	}

}
