package io.nzbee.entity.order;

import io.nzbee.entity.product.Product;

//@Entity
public class RetailOrderLine {

	private Product product;
	private int quantity;
	
	public RetailOrderLine() {
		super();
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
