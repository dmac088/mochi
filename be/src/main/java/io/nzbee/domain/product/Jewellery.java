package io.nzbee.domain.product;

import java.util.Date;
import java.util.List;

import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.department.Department;

public class Jewellery extends Product {

	public Jewellery(String productUPC,
				   	 Date   productCreateDt,
				   	 String productDesc,
				   	 Double productRetail,
				   	 Double productMarkdown,
				   	 String productImage,
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
	}

}
