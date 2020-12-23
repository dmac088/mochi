package io.nzbee.domain.product;

import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.department.Department;
import io.nzbee.domain.promotion.Promotion;


@JsonTypeName("product")
public class PhysicalProduct extends Product {

	public PhysicalProduct( String productUPC,
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
					   	 List<ProductCategory> productCategories,
					   	 List<Promotion> productPromotions) {
	
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
			  productCategories,
			  productPromotions);
		
		this.productType = this.getClass().getSimpleName().toString().toLowerCase();
	}

}
