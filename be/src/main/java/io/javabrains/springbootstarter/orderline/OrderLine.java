package io.javabrains.springbootstarter.orderline;



import io.javabrains.springbootstarter.product.Product;

//@Entity
public class OrderLine {

	private Product product;
	private int quantity;
	
	public OrderLine() {
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
