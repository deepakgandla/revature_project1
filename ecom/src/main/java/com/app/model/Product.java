package com.app.model;

public class Product {
	private int id;
	private String name;
	private String category;
	private float price;
	public Product() {
		// TODO Auto-generated constructor stub
	}
	public Product(String name, String category, float price) {
		super();
		this.name = name;
		this.category = category;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", category=" + category + ", price=" + price + "]";
	}
	
}
