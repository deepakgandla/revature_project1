package com.app.model;

public class Order {
	private int id;
	private Customer customer;
	private Product product;
	private String status;
	public Order(Customer customer, Product product, String status) {
		super();
		this.customer = customer;
		this.product = product;
		this.status = status;
	}
	public Order() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", customer=" + customer + ", product=" + product + ", status=" + status + "]";
	}
	
}
