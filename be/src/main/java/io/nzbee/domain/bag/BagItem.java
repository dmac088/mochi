package io.nzbee.domain.bag;

import io.nzbee.domain.product.Product;

public class BagItem {

	private Bag bag;
	private Product product;
	private int qty;
	
	public BagItem(Bag bag, Product p, int qty) {
		this.bag = bag;
		this.product = p;
		this.qty = qty;
	}

	public Bag getBag() {
		return bag;
	}

	public Product getProduct() {
		return product;
	}

	public int getQty() {
		return qty;
	}

}
