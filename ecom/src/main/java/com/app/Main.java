package com.app;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.app.exception.BusinessException;
import com.app.model.Customer;
import com.app.model.Employee;
import com.app.service.login.CustomerLoginService;
import com.app.service.login.EmployeeLoginService;
import com.app.service.login.impl.CustomerServiceLoginImpl;
import com.app.service.login.impl.EmployeeLoginServiceImpl;

public class Main {
	private static Logger log = Logger.getLogger(Main.class);
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		log.info("Welcome to Deepak's shopping command line app");
		int entryChoice = 0;
		do {
			log.info("1)Login");
			log.info("2)Don't have an account signup");
			log.info("3)Close");
			try {
				entryChoice = Integer.parseInt(scanner.nextLine());
			}catch(NumberFormatException e) {}
			switch(entryChoice) {
			case 1:
				int userType = 0;
				do {
					log.info("1)Customer");
					log.info("2)Employee");
					log.info("3)Main");
					try {
						userType = Integer.parseInt(scanner.nextLine());
					}catch(NumberFormatException e) {}
					switch(userType) {
					case 1:
						Customer customer = null;
						do {
							log.info("welcome to login page");
							log.info("Enter email id");
							String customerEmail = scanner.nextLine();
							log.info("Enter Password");
							String customerPassword = scanner.nextLine();
							CustomerLoginService customerLoginService = new CustomerServiceLoginImpl();
							try {
								customer = customerLoginService.customerLogin(customerEmail, customerPassword);
								if(customer.getFirst_name()!=null) {
									log.info("Login Successfull");
									log.info("Welcome user " + customer.getFirst_name());
								}else {
									log.info("Email and password didn't match");
								}
							} catch (BusinessException e) {
								// TODO Auto-generated catch block
								log.error(e.getMessage());
							}
							
						}while(false);
						break;
					case 2:
						log.info("Welcome to employee login page");
						log.info("Enter Email");
						String employeeEmail = scanner.nextLine();
						log.info("Enter Password");
						String employeePassword = scanner.nextLine();
						//Code to EmployeeServieLogin
						EmployeeLoginService employeeLoginService = new EmployeeLoginServiceImpl();
						try {
							Employee employee = employeeLoginService.employeeLogin(employeeEmail, employeePassword);
							if(employee.getFirst_name()!=null) {
								log.info("Welcome Admin " + employee.getFirst_name());
							}else {
								log.info("Email and password didn't match");
							}
						} catch (BusinessException e) {
							// TODO Auto-generated catch block
							log.error(e.getMessage());
						}
						
						break;
					}
				}while(userType!=3);
				break;
			case 2:
				break;
			}
		}while(entryChoice!=3);
	}
}
