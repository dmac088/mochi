package io.nzbee.domain.product;

import java.util.Date;

import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.department.Department;

public class Jewellery extends Product {

	public Jewellery(String productUPC,
				   	 Date   productCreateDt,
				   	 String productStatus,
				   	 String productDesc,
				   	 Double productRetail,
				   	 Double productMarkdown,
				   	 String productImage,
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
		  category
		  );
	}

}
