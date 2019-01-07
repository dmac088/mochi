package io.javabrains.springbootstarter.services;

import java.util.List;
import io.javabrains.springbootstarter.domain.ProductCategory;


public class ProductCategoryDTO {
	
	private Long categoryId;
	
	private String categoryCode;
	
	private String categoryDesc;
	
	private String lclCd;
	
	private ProductCategory parent;	

	private List<ProductCategory> children;	
	
	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	public String getLclCd() {
		return lclCd;
	}

	public void setLclCd(String lclCd) {
		this.lclCd = lclCd;
	}

	public ProductCategory getParent() {
		return parent;
	}

	public void setParent(ProductCategory parent) {
		this.parent = parent;
	}
	
	public List<ProductCategory> getChildren() {
		return children;
	}

	public void setChildren(List<ProductCategory> children) {
		this.children = children;
	}

	@Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("CategoryDto [categoryId=").append(categoryId).append(", categoryCode=").append(categoryCode).append(", categoryDesc=").append(categoryDesc).append(", lclCd=").append(lclCd);
        return builder.toString();
    }
}