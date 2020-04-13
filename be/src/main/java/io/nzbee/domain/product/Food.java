package io.nzbee.domain.product;

import java.util.Date;
import java.util.List;

import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.department.Department;


public class Food extends Product {

	private String countryOfOrigin;
	
	private Date expiryDate;
	
	
	public Food(String productUPC,
			   	Date productCreateDt,
			   	String productDesc,
			   	Double productRetail,
			   	Double productMarkdown,
			   	String productImage,
			   	String countryOfOrigin,
			   	Date   expiryDate,
			   	String displayCategories,
			   	String lclCd,
			   	String currency,
			   	Brand brand,
			   	Department department,
			   	List<ProductCategory> categories) {
		
		super(productUPC,
			  productCreateDt,
			  productDesc,
			  productRetail,
			  productMarkdown,
			  productImage,
			  lclCd,
			  currency,
			  brand,
			  department,
			  categories);
		
		this.countryOfOrigin = countryOfOrigin;
		this.expiryDate = expiryDate;
		
	}


	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}


	public Date getExpiryDate() {
		return expiryDate;
	}
	
}
