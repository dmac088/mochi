package io.nzbee.entity.order.line.retail;

import io.nzbee.entity.product.ProductEntity;

//@Entity
public class OrderLineRetail {

	private ProductEntity product;
	private int quantity;
	
	public OrderLineRetail() {
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
