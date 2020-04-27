package io.nzbee.domain.category;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;

import io.nzbee.domain.product.Product;

@JsonTypeName("productcategory")
public class ProductCategory extends Category {
	
	public ProductCategory(	String categoryCode,
							String categoryDesc,
							boolean isHierarchical,
							Long level,
							int objectCount,
							String parentCode,
							String locale, 
							String currency) {
		
		super(categoryCode, 
			  categoryDesc,
			  level, 
			  locale, 
			  currency,
			  parentCode,
			  objectCount
			  );
		
		this.categoryType = this.getClass().getSimpleName().toString().toLowerCase();
		this.products = new ArrayList<Product>();
	}
	
	@JsonIgnore
	private List<Product> products;
	
	private String parentCode;
	
	public void addProduct(Product product) {
		this.getProducts().add(product);
	}
	
	public void removeProduct(Product product) {
		this.getProducts().remove(product);
	}
	
	public List<Product> getProducts() {
		return products;
	}
	
	@Override
	public String getCode() {
		return super.getCategoryCode();
	}
	
	@Override
	public String getDesc() {
		return super.getCategoryDesc();
	}
	
	@Override
	public boolean isHierarchical() {
		return true;
	}

	@Override
	public Long getLevel() {
		return super.getCategoryLevel();
	}

	public String getParentCode() {
		return parentCode;
	}
	
}
