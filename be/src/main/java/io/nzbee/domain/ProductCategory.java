package io.nzbee.domain;

import java.util.List;

public class ProductCategory extends Category {

	private List<Product> products;
	
	private Long productCount;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public Long getCount() {
		return productCount;
	}

	public void setCount(Long productCount) {
		this.productCount = productCount;
	}
}
