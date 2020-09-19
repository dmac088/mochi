package io.nzbee.domain.bag;

import io.nzbee.domain.product.Product;

public class BagItem {

	private Bag bag;
	private Product product;
	private int quantity;
	private String locale;
	private String currency;
	
	public BagItem(Bag bag, 
			  	   Product p, 
			  	   int quantity) {
		this.bag = bag;
		this.product = p;
		this.quantity = quantity;
		this.locale = p.getLclCd();
		this.currency = p.getCurrency();
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
	
	public void addToQuantity(int quantity) {
		this.quantity += quantity;
	}

	public String getLocale() {
		return this.locale;
	}

	public String getCurrency() {
		return this.currency;
	}
	
}