package com.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.app.exception.BusinessException;
import com.app.model.Customer;
import com.app.model.Employee;
import com.app.model.Product;
import com.app.service.create.CreateCartService;
import com.app.service.create.CustomerCreateService;
import com.app.service.create.ProductCreateService;
import com.app.service.create.impl.CreateCartServiceImpl;
import com.app.service.create.impl.CustomerCreateServiceImpl;
import com.app.service.create.impl.ProductCreateServiceImpl;
import com.app.service.login.CustomerLoginService;
import com.app.service.login.EmployeeLoginService;
import com.app.service.login.impl.CustomerServiceLoginImpl;
import com.app.service.login.impl.EmployeeLoginServiceImpl;
import com.app.service.search.CartItemSearchService;
import com.app.service.search.ProductSearchService;
import com.app.service.search.impl.CartItemSearchServiceImpl;
import com.app.service.search.impl.ProductSearchServiceImpl;

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
									int customerChoice =0;
									log.info("================================");
									log.info("Login Successfull             ||");
									log.info("Welcome user " + customer.getFirst_name() + "           ||");
									log.info("================================");
									do {
										log.info("1)Shop");
										log.info("2)My Orders");
										log.info("3)My Cart");
										log.info("4)Search Products");
										log.info("5)My Profile");
										log.info("6)Exit");
										log.info("Enter your choice (1-5)");
										try {
											customerChoice = Integer.parseInt(scanner.nextLine());
											}catch(NumberFormatException e) {}
										switch(customerChoice) {
										case 1:
											log.info("Products");
											//code to service
											break;
										case 2:
											log.info("Under Construction");
											break;
										case 3:
											log.info("CART");
											log.info("============================");
											log.info("||Name                Price||");
											CartItemSearchService cartItemSearchService = new CartItemSearchServiceImpl();
											List<Product> cartItems = cartItemSearchService.cartItemSearhService(customer);
											float totalCartValue = 0;
											if(cartItems.size()>=1) {
												for(Product product: cartItems) {
													totalCartValue += product.getPrice();
													log.info("|| "+product.getName()+"                 " +product.getPrice()+" ||");
												}

												log.info("============================");
												log.info("                  total: " + totalCartValue);
											}else {
												log.info("Your cart is empty");
											}
			
											break;
										case 4:
											log.info("Enter Product Name");
											String producName = scanner.nextLine();
											ProductSearchService productSearchService = new ProductSearchServiceImpl();
											List<Product> products = new ArrayList<Product>();
											products =productSearchService.searchProduct(producName);
											if(products.size()>0) {
												int index = 1;
												for(Product product: products) {
													log.info(index + ") " + product);
												}
												int addtoCart = 0;
												do {
													log.info("0)Back");
													log.info("1)Add to Cart");
													try {
														addtoCart = Integer.parseInt(scanner.nextLine());
														
													}catch(NumberFormatException e) {
														log.error(e.getMessage());
													}
													switch(addtoCart) {
													case 1:
														log.info("Enter the choice(1-" + products.size() + ")");
														index = 1;
														for(Product product: products) {
															log.info(index + ") " + product);
														}
														int addToCartPoductId = Integer.parseInt(scanner.nextLine());
														
														index = 1;
													    CreateCartService createCartService = new CreateCartServiceImpl();
													    int c = 0;
														for(Product product: products) {
															if(index == addToCartPoductId);{
															c = createCartService.createCart(product, customer);
															break;
															}
														}
													    if(c==1) {
													    	log.info("Product added to Cart");
													    }else {
													    	throw new BusinessException("Something went wrong");
													    }
														break;
													}
												}while(addtoCart!=0);
												
											}else {
												log.info("No items found");
											}
											break;
										case 5:
											log.info("Profile");
											log.info("=====================================================");
											log.info("ID:    " +customer.getId());
											log.info("Name:    " + customer.getFirst_name() + " " + customer.getLast_name());
											log.info("Email:    " + customer.getEmail());
											log.info("======================================================");
											break;
										}
									}while(customerChoice!=6);
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
						int employeeAction =0;
						
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
								do {
								log.info("1)Add product");
								log.info("2)Update Order Status");
								log.info("3)Exit");
								try {
								employeeAction = Integer.parseInt(scanner.nextLine());
								}catch(NumberFormatException e) {
									log.error("Enter Number");
								}
								switch(employeeAction) {
								case 1:
									log.info("Enter Product Details");
									log.info("Enter Product Name");
									String productName = scanner.nextLine(); 
									log.info("Enter Category");
									String productCategory = scanner.nextLine();
									log.info("Enter Price");
									float productPrice = Float.parseFloat(scanner.nextLine());
									Product product = new Product(productName, productCategory, productPrice);
									//Code to Service
									ProductCreateService productCreateService = new ProductCreateServiceImpl();
									if(productCreateService.createProduct(product) == 1) {
										log.info("Product added");
									}else {
										throw new BusinessException("Something went wrong");
									}
									break;
								case 2:
									log.info("Undr con");
									break;
					
								}
								}while(employeeAction!=3);
							}
							
						
							else {
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
				log.info("Register Account page");
				log.info("Enter First Name");
				String customerRegisterFirstName = scanner.nextLine();
				log.info("Enter Last Name");
				String customerRegisterLastName = scanner.nextLine();
				log.info("Enter Email");
				String customerRegisterEmail = scanner.nextLine();
				log.info("Enter Password");
				String customerRegisterPassword = scanner.nextLine();
				Customer customer = new Customer(customerRegisterFirstName,customerRegisterLastName,customerRegisterEmail,customerRegisterPassword);
				
				//code to service
				int c = 0;
				CustomerCreateService customerCreateService = new CustomerCreateServiceImpl();
				try {
					c = customerCreateService.createCustomer(customer);
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					log.error(e.getMessage());
				}
				if(c==1) {
					log.info("accont created");
				}
				
				break;
			}
		}while(entryChoice!=3);
	}
}
