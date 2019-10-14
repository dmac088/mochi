package io.nzbee.domain.category;

import java.util.List;

import io.nzbee.domain.product.Product;

public class ProductCategory extends Category {


	public ProductCategory() {
		super();
	}

	private List<Product> products;
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return super.getCategoryCode();
	}
	
	@Override
	public String getDesc() {
		// TODO Auto-generated method stub
		return super.getCategoryDesc();
	}

	
}
