package io.javabrains.springbootstarter.orderline;

import javax.persistence.Entity;

import io.javabrains.springbootstarter.product.Product;
import io.javabrains.springbootstarter.thing.Thing;

//@Entity
public class OrderLine extends Thing {

	private Product product;
	private int quantity;
	
	public OrderLine() {
		super();
	}
	
	public OrderLine(int id) {
		super(id);
	}
	
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
