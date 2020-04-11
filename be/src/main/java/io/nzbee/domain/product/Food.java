package io.nzbee.domain.product;

import java.util.Date;
import io.nzbee.domain.brand.Brand;
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
			   	Department department) {
		
		super(productUPC,
			  productCreateDt,
			  productDesc,
			  productRetail,
			  productMarkdown,
			  productImage,
			  displayCategories,
			  lclCd,
			  currency,
			  brand,
			  department);
		
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
