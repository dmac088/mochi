package io.nzbee.domain.category;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;

import io.nzbee.domain.product.Product;

@JsonTypeName("productcategory")
public class ProductCategory extends Category {
	
	private Long categoryLevel;
	
	private Long childCategoryCount;
	
	@JsonIgnore
	private List<Product> products;
	
	private String parentCode;
	
	public ProductCategory(	String categoryCode,
							String categoryDesc,
							boolean isHierarchical,
							Long level,
							Long objectCount,
							String parentCode,
							Long childCount,
							String locale) {
		
		super(categoryCode, 
			  categoryDesc, 
			  locale,
			  objectCount);
		
		this.categoryLevel = level;
		this.childCategoryCount = childCount;
		this.parentCode = parentCode;
		this.categoryType = this.getClass().getSimpleName().toString().toLowerCase();
		this.products = new ArrayList<Product>();
	}
	
	public ProductCategory(	String categoryCode,
							String categoryDesc,
							Long   level,
							String parentCode,
							String locale) {

		super(	categoryCode, 
				categoryDesc,
				locale);
		
		this.categoryLevel = level;
		this.parentCode = parentCode;
		this.categoryType = this.getClass().getSimpleName().toString().toLowerCase();
		this.products = new ArrayList<Product>();
	}

	public void addProduct(Product product) {
		this.getProducts().add(product);
	}
	
	public void removeProduct(Product product) {
		this.getProducts().remove(product);
	}
	
	public List<Product> getProducts() {
		return products;
	}

	public String getParentCode() {
		return parentCode;
	}
	
	public Long getChildCount() {
		return childCategoryCount;
	}
	
	public Long getCategoryLevel() {
		return this.categoryLevel;
	}
	
}
