package io.nzbee.domain.bag;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.nzbee.domain.product.Product;

public class BagItem {

	@JsonBackReference
	private Bag bag;
	private Product product;
	private int quantity;
	
	public BagItem(Bag bag, 
			  	   Product p, 
			  	   int quantity) {
		this.bag = bag;
		this.product = p;
		this.quantity = quantity;
	}

	public Bag getBag() {
		return bag;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

}
