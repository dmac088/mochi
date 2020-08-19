package io.nzbee.domain.product;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonTypeName;

import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.department.Department;


@JsonTypeName("accessories")
public class Accessories extends Product {

	public Accessories(	 String productUPC,
					   	 Date   productCreateDt,
					   	 String productStatus,
					   	 String productDesc,
					   	 String productLongDesc,
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
			  productLongDesc,
			  productRetail,
			  productMarkdown,
			  productImage,
			  lclCd,
			  currency,
			  brand,
			  department,
			  category);
		
		this.productType = this.getClass().getSimpleName().toString().toLowerCase();
	}

}
