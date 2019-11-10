package io.nzbee.dto.category.product;

import io.nzbee.dto.category.Category;

public class ProductCategory extends Category  {

	private String parentCode;

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}	
	
}
