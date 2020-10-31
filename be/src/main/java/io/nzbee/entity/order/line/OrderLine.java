package io.nzbee.entity.order.line;

import io.nzbee.entity.product.ProductEntity;

//@Entity
public class OrderLine {

	private ProductEntity product;
	private int quantity;
	
	public OrderLine() {
		super();
	}
	
	public ProductEntity getProduct() {
		return this.product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
