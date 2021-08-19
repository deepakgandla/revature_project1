package com.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.app.dao.search.OrderItemSearchDAO;
import com.app.exception.BusinessException;
import com.app.model.Customer;
import com.app.model.Employee;
import com.app.model.Order;
import com.app.model.Product;
import com.app.service.create.CreateCartService;
import com.app.service.create.CustomerCreateService;
import com.app.service.create.OrderCreateService;
import com.app.service.create.ProductCreateService;
import com.app.service.create.impl.CreateCartServiceImpl;
import com.app.service.create.impl.CustomerCreateServiceImpl;
import com.app.service.create.impl.OrderCreateServiceImpl;
import com.app.service.create.impl.ProductCreateServiceImpl;
import com.app.service.login.CustomerLoginService;
import com.app.service.login.EmployeeLoginService;
import com.app.service.login.impl.CustomerServiceLoginImpl;
import com.app.service.login.impl.EmployeeLoginServiceImpl;
import com.app.service.search.CartItemSearchService;
import com.app.service.search.CustomerSearchService;
import com.app.service.search.OrderItemSearchService;
import com.app.service.search.ProductSearchService;
import com.app.service.search.impl.CartItemSearchServiceImpl;
import com.app.service.search.impl.CustomerSearchServiceImpl;
import com.app.service.search.impl.OrderItemSearchServiceImpl;
import com.app.service.search.impl.ProductSearchServiceImpl;
import com.app.service.update.OrderStatusUpdateService;
import com.app.service.update.impl.OrderStatusUpdateServiceImpl;

public class Main {
	private static Logger log = Logger.getLogger(Main.class);
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		log.info("Welcome to Deepak's shopping command line app");
		log.info("===============================================\n");
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
									log.info("================================\n");
									do {
										log.info("1)Shop");
										log.info("2)My Orders");
										log.info("3)My Cart");
										log.info("4)Search Products");
										log.info("5)My Profile");
										log.info("6)Logout");
										log.info("Enter your choice (1-6)");
										try {
											customerChoice = Integer.parseInt(scanner.nextLine());
											}catch(NumberFormatException e) {}
										switch(customerChoice) {
										case 1:
											log.info("Availabe Products"); 
											log.info("=====================================================");
										    log.info("id             Name            Category       price");
										    log.info("=======================================================");
										    ProductSearchService productSearchService = new ProductSearchServiceImpl();
										    List<Product> availableProducts = new ArrayList<>();
										    availableProducts = productSearchService.searchProduct();
										    int numberofProductsAdded = 0;
										    if(availableProducts.size()>0) {
										    	for(Product product: availableProducts) {
										    		log.info(product.getId() + "            " + product.getName()+"         "+product.getCategory()+"         "+product.getPrice());

										    	}
										    	log.info("======================================================\n");
										    	int addToCartProductId = 0;
										    	
										    	do {
										    	log.info("Enter Product id to add to cart | Enter 0 to go back");
										        try {
										        	addToCartProductId = Integer.parseInt(scanner.nextLine());
										        	
										        }catch(NumberFormatException e) {}
										        if(addToCartProductId>0) {
										        	numberofProductsAdded++;
										        	log.info(numberofProductsAdded +" item/s selected so far");
										        }
										        CreateCartService craeteCartService = new CreateCartServiceImpl();
										        int index = 1;
										        
										        for(Product product: availableProducts) {
										        	if(index == addToCartProductId) {
										        		craeteCartService.createCart(product, customer);
										        	}
										        	index++;
										        }
										    	}while(addToCartProductId!=0);
										    }else {
										    	throw new BusinessException("oops something went wrong");
										    }
										    log.info(numberofProductsAdded + " product/s added to your cart");
											break;
										case 2:
											
											List<Order> orderedItems = new ArrayList<>();
											OrderItemSearchService orderItemSearchService = new OrderItemSearchServiceImpl();
											orderedItems = orderItemSearchService.orderItemSeach(customer);
											if(orderedItems.size()>0) {
												log.info("Orders");
												log.info("=======================================================");
												log.info("||OrderID                 Item                   Status||");
												log.info("=======================================================");
												for(Order order: orderedItems) {
								
													log.info("#"+order.getId()+"                     "+order.getProduct().getName()+"                       "+order.getStatus());
												}
												log.info("=========================================================");
											}else {
												log.info("You don't have any orders");
											}
											int updateOrderAsRecieved = 0;
											
											do {
												log.info("1)Update Order Status to Received");
												log.info("2)Back");
												try {
													updateOrderAsRecieved = Integer.parseInt(scanner.nextLine());
												}catch(NumberFormatException e) {}
											    switch(updateOrderAsRecieved) {
											    case 1:
											    	log.info("Enter Order id");
											    	int orderId = 0;
											    	try {
											    		orderId = Integer.parseInt(scanner.nextLine());
											    	}catch(NumberFormatException e) {}
											    	OrderStatusUpdateService orderStatusUpdateService = new OrderStatusUpdateServiceImpl();
											    	if(orderStatusUpdateService.orderStatusUpdateRecieved(orderId, customer)==1) {
											    		log.info("Order with " + orderId + "marked as received");
											    	}else {
											    		log.info("Can not find order id " + orderId + " in your orders with status shipped");
											    	}
											    	break;
											    	
											    }
											}while(updateOrderAsRecieved!=2);
											break;
										case 3:
											CartItemSearchService cartItemSearchService = new CartItemSearchServiceImpl();
											List<Product> cartItems = cartItemSearchService.cartItemSearhService(customer);
											float totalCartValue = 0;
											if(cartItems.size()>=1) {
												log.info("CART");
												log.info("============================");
												log.info("||Name                Price||");
												for(Product product: cartItems) {
													totalCartValue += product.getPrice();
													log.info("|| "+product.getName()+"                 " +product.getPrice()+" ||");
												}

												log.info("============================");
												log.info("                  total: " + totalCartValue);
												int buyCartItems = 0;
												do {
													log.info("1)Make Order");
													log.info("2)Go back");
													try {
														buyCartItems = Integer.parseInt(scanner.nextLine());
													}catch(NumberFormatException e) {}
													switch(buyCartItems) {
													case 1:
														log.info("Order Items");
														//Code to Service
														OrderCreateService orderCreateService = new OrderCreateServiceImpl();
														int numberOfItemsAddedToCart = orderCreateService.createOrder(customer);
														log.info("Number of Items Order: " + numberOfItemsAddedToCart);
														break;
													}
												}while(buyCartItems!=2);
											}else {
												log.info("Your cart is empty");
											}
			
											break;
										case 4:
											log.info("Enter Product Name");
											String producName = scanner.nextLine();
											productSearchService = new ProductSearchServiceImpl();
											List<Product> products = new ArrayList<Product>();
											products =productSearchService.searchProduct(producName);
											if(products.size()>0) {
												int index = 1;
												log.info("====================");
												log.info("#     name     price");
												log.info("=====================");
												for(Product product: products) {
									
													log.info(index + ")    " + product.getName() + "    "+product.getPrice());
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
															log.info(index + ") " +product.getName());
														}
														int addToCartPoductId =0;
														try {
														addToCartPoductId = Integer.parseInt(scanner.nextLine());
														}catch(NumberFormatException e) {}
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
								
								log.error(e.getMessage());
							}
							
						}while(userType!=3);
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
								log.info("Welcome Admin " + employee.getFirst_name() + "\n");
								do {
								log.info("1)Add product");
								log.info("2)Update Order Status to Shipped");
								log.info("3)Search Customer");
								log.info("4)Logout");
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
									log.info("Enter the Product ID: ");
									int updateOrderStatusProductId = 0;
									//code to service
									OrderStatusUpdateService orderStatusUpdateService= new OrderStatusUpdateServiceImpl();
									try {
										updateOrderStatusProductId = Integer.parseInt(scanner.nextLine());
									}catch(NumberFormatException e) {}              
									if(orderStatusUpdateService.orderStatusUpdateShipped(updateOrderStatusProductId)==1) {
										log.info("Status Update to Shipped with Order id " +updateOrderStatusProductId);
										
									}else {
										log.info("Cannot update the Status");                     
									}
									
									break;
								case 3:
									int searchCustomerBy = 0;
									do {
									log.info("1)Search Player By Name");
									log.info("2)Search Player By Email");
									log.info("3)Search Player By Id");
									log.info("4)Search Player By Order Id");
									log.info("5)Back");
					                try {
					                	searchCustomerBy = Integer.parseInt(scanner.nextLine());
					                }catch(NumberFormatException e) {}
					                switch(searchCustomerBy) {
					                case 1:
					                	log.info("Enter Customer Name");
					                	String customerName = scanner.nextLine();
				                        //code to service   
					                	List<Customer> customers = new ArrayList<>();
					                	CustomerSearchService customerSearchService = new CustomerSearchServiceImpl();
					                	try {
					                		customers = customerSearchService.searchCustomerByName(customerName);
					                	}catch(BusinessException e) {
					                		log.error(e.getMessage());
					                	}
				                        
					                	if(customers.size()>0) {
					                		log.info("id     first_name     last_name         email");
					                		for(Customer foundCustomer : customers) {
					                			log.info(foundCustomer.getId()+"        "+foundCustomer.getFirst_name()+"          "+foundCustomer.getLast_name()+"        "+foundCustomer.getEmail());
					                		}
					                		log.info("\n");
					                	}else {
					                		log.info("No customer found with name " +customerName);
					                	}
					                	break;
					                case 2:
					                	log.info("Enter Cutomer Email");
					                	String customerEmail = scanner.nextLine();
					                	Customer foundCustomerByEmail = null;
					                	customerSearchService = new CustomerSearchServiceImpl();
					                	try {
					                	foundCustomerByEmail = customerSearchService.searchCustomerByEmail(customerEmail);
					                	}catch(BusinessException e) {
					                		log.error(e.getMessage());
					                	}
					                	if(foundCustomerByEmail!=null) {
					                		log.info("id     first_name     last_name         email");
					                		log.info(foundCustomerByEmail.getId()+"     "+foundCustomerByEmail.getFirst_name()+"     "+foundCustomerByEmail.getLast_name()+"     "+foundCustomerByEmail.getEmail()+"\n");
					                	}else {
					                		log.info("No customer found with email " +customerEmail);
					                	}
					                	break;
					                case 3:
					                	log.info("Enter Player Id");
					                	int customerId = 0;
					                	try {
					                		customerId = Integer.parseInt(scanner.nextLine());
					                	}catch(NumberFormatException e) {
					                		log.error(e.getMessage());
					                	}
					                	customerSearchService = new CustomerSearchServiceImpl();
					                	Customer foundCustomerById = null;
					                	try {
						                	foundCustomerById = customerSearchService.searchCustomerById(customerId);
						                	}catch(BusinessException e) {}
						                	if(foundCustomerById!=null) {
						                		log.info("id     first_name     last_name         email");
						                		log.info(foundCustomerById.getId() + "     " + foundCustomerById.getFirst_name() + "     " + foundCustomerById.getLast_name() + "       " + foundCustomerById.getEmail()+"\n");
						                	}else {
						                		log.info("No customer found with id " +customerId);
						                	}
					                	break;
					                case 4:
					                	log.info("Enter Order Id");
					                	int orderId = 0;
					                	try {
					                		orderId = Integer.parseInt(scanner.nextLine());
					                	}catch(NumberFormatException e) {}
					                	Customer foundCustomerByOrderId = null;
					                	customerSearchService = new CustomerSearchServiceImpl();
					                	try {
						                	foundCustomerByOrderId = customerSearchService.searchCustomerByOrderId(orderId);
						                	}catch(BusinessException e) {
						                		log.error(e.getMessage());
						                	}
						                	if(foundCustomerByOrderId!=null) {
						                		log.info("id     first_name     last_name         email");
						                		log.info(foundCustomerByOrderId.getId() + "     " + foundCustomerByOrderId.getFirst_name() + "        " + foundCustomerByOrderId.getLast_name()+ "        " + foundCustomerByOrderId.getEmail()+"\n");
						                	}else {
						                		log.info("No customer found with order id " +orderId);
						                	}
					                	break;
					              
					                	
					                }
									}while(searchCustomerBy!=5);                               
					               
					                
								}
								}while(employeeAction!=4);
							}
							
						
							else {
								log.info("Email and password didn't match");
							}
						} catch (BusinessException e) {
							// TODO Auto-generated catch block
							log.error(e.getMessage());
						}
						
						break;
						default:
							log.info("Enter valid number");
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
					log.error(e.getMessage());
				}
				if(c==1) {
					log.info("accont created");
				}
				
				break; 
             
			}
		
		}while(entryChoice!=3);
		log.info("Thank You Bye");
	}
}
