package io.nzbee.domain.product;

import java.util.Date;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.department.Department;

public class Jewellery extends Product {

	public Jewellery(String productUPC,
				   	 Date   productCreateDt,
				   	 String productDesc,
				   	 Double productRetail,
				   	 Double productMarkdown,
				   	 String productImage,
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
	}

}
