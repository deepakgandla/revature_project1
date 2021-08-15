package com.app;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.app.exception.BusinessException;
import com.app.model.Customer;
import com.app.service.login.CustomerLoginService;
import com.app.service.login.impl.CustomerServiceLoginImpl;

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
								if(customer!=null) {
									log.info("Login Successfull");
									log.info("Welcome user " + customer.getFirst_name());
								}
							} catch (BusinessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}while(false);
						break;
					case 2:
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
