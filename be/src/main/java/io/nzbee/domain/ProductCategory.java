package io.nzbee.domain;

import java.util.List;

public class ProductCategory extends Category {

	private List<Product> products;
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
