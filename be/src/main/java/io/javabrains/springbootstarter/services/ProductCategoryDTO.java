package io.javabrains.springbootstarter.services;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.javabrains.springbootstarter.domain.ProductCategory;


public class ProductCategoryDTO {
	
	private Long categoryId;
	
	private String categoryCode;
	
	private String categoryDesc;

	private Long categoryLevel;

	private String lclCd;
	
	@JsonIgnore
	private ProductCategoryDTO parent;	

	private Long childCategoryCount;

	private Long productCount; 
	
	private List<ProductCategoryDTO> children;
	
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
	
	public Long getCategoryLevel() {
		return categoryLevel;
	}

	public void setCategoryLevel(Long categoryLevel) {
		this.categoryLevel = categoryLevel;
	}

	public String getLclCd() {
		return lclCd;
	}

	public void setLclCd(String lclCd) {
		this.lclCd = lclCd;
	}

	public ProductCategoryDTO getParent() {
		return parent;
	}

	public void setParent(ProductCategoryDTO parent) {
		this.parent = parent;
	}
	
	public List<ProductCategoryDTO> getChildren() {
		return children;
	}

	public void setChildren(List<ProductCategoryDTO> children) {
		this.children = children;
	}
	
	public Long getChildCategoryCount() {
		return childCategoryCount;
	}

	public void setChildCategoryCount(Long childCategoryCount) {
		this.childCategoryCount = childCategoryCount;
	}
	
	public Long getProductCount() {
		return productCount;
	}

	public void setProductCount(Long productCount) {
		this.productCount = productCount;
	}

	@Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("CategoryDto [categoryId=").append(categoryId).append(", categoryCode=").append(categoryCode).append(", categoryDesc=").append(categoryDesc).append(", lclCd=").append(lclCd);
        return builder.toString();
    }
}