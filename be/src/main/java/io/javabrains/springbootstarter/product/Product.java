package io.javabrains.springbootstarter.product;

import io.javabrains.springbootstarter.thing.Thing;

public class Product /*extends Thing*/ {
	
	private double rrp;
	private String ProductName;

	public Product (){
		super();
	}

	public Product (int id){
		//super(id);
	}
	
	public double getRrp() {
		return this.rrp;
	}
	
	public void setRrp(double rrp) {
		this.rrp = rrp;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}
}
