package io.nzbee.domain.product;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonTypeName;

import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.department.Department;

@JsonTypeName("food")
public class Food extends Product {

	private String countryOfOrigin;
	
	private Date expiryDate;
	
	
	public Food(String productUPC,
			   	Date productCreateDt,
			   	String productStatus,
			   	String productDesc,
			   	Double productRetail,
			   	Double productMarkdown,
			   	String productImage,
			   	String countryOfOrigin,
			   	Date   expiryDate,
			   	String lclCd,
			   	String currency,
			   	Brand brand,
			   	Department department,
			   	ProductCategory category) {
		
		super(productUPC,
			  productCreateDt,
			  productStatus,
			  productDesc,
			  productRetail,
			  productMarkdown,
			  productImage,
			  lclCd,
			  currency,
			  brand,
			  department,
			  category);
		
		this.countryOfOrigin = countryOfOrigin;
		this.expiryDate = expiryDate;
		this.productType = this.getClass().getSimpleName().toString().toLowerCase();
		
	}


	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}


	public Date getExpiryDate() {
		return expiryDate;
	}
	
}
