package io.nzbee.domain.category;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.nzbee.SpringContext;
import io.nzbee.domain.IService;
import io.nzbee.domain.product.Product;

public class ProductCategory extends Category {


	public ProductCategory() {
		super();
	}

	@JsonIgnore
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
	
	@Override
	public boolean isHierarchical() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Long getLevel() {
		// TODO Auto-generated method stub
		return super.getCategoryLevel();
	}
	
}
