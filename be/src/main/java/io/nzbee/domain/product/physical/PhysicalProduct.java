package io.nzbee.domain.product.physical;

import java.time.LocalDateTime;
import java.util.List;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.department.Department;
import io.nzbee.domain.product.Product;
import io.nzbee.domain.promotion.Promotion;

public class PhysicalProduct extends Product {

	private boolean inStock;
	
	private Double weight;
	
	public PhysicalProduct(  String productUPC,
							 LocalDateTime   productCreateDt,
						   	 String productStatus,
						   	 String productDesc,
						   	 String productLongDesc,
						   	 Double productRetail,
						   	 Double productMarkdown,
						   	 String productImage,
						   	 String lclCd,
						   	 String currency,
						   	 Boolean inStock,
						   	 Double weight,
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
			  department,
			  productCategories,
			  productPromotions);
		
		this.productType = this.getClass().getSimpleName().toString().toLowerCase();
		this.inStock 	 = inStock;
		this.weight 	 = weight;
		this.productType = this.getClass().getSimpleName().toString().toLowerCase();
		
	}
	
	public boolean isInStock() {
		return inStock;
	}

	public Double getWeight() {
		return weight;
	}

}
