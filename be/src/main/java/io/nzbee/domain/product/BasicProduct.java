package io.nzbee.domain.product;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.department.Department;


@JsonTypeName("product")
public class BasicProduct extends Product {

	public BasicProduct(	 String productUPC,
						 LocalDateTime   productCreateDt,
					   	 String productStatus,
					   	 String productDesc,
					   	 String productLongDesc,
					   	 Double productRetail,
					   	 Double productMarkdown,
					   	 String productImage,
					   	 String lclCd,
					   	 String currency,
					   	 boolean inStock,
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
			  inStock,
			  brand,
			  department,
			  category);
		
		this.productType = this.getClass().getSimpleName().toString().toLowerCase();
	}

}
