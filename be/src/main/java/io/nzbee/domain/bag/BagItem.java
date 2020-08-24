package io.nzbee.domain.bag;

import io.nzbee.domain.product.Product;

public class BagItem {

	private Bag bag;
	private Product product;
	private double qty;
	private BagStatus bagStatus;
	
	public BagItem(Bag bag, Product p, int qty) {
		this.bag = bag;
		this.product = p;
		this.qty = qty;
		bagStatus = BagStatus.NEW;
	}

	public Bag getBag() {
		return bag;
	}

	public Product getProduct() {
		return product;
	}

	public double getQty() {
		return qty;
	}

	public BagStatus getBagStatus() {
		return bagStatus;
	}

}
