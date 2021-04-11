package io.nzbee.domain.product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.department.Department;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.domain.tag.Tag;

public abstract class Product {

	private String productUPC;
	
	private String productStatus;
	
	private LocalDateTime productCreateDt;

	private String productDesc;
	
	private String productLongDesc;
	
	private Double productRetail;
	
	private Double productMarkdown;
	
	private String productImage;
	
	private String lclCd;
	
	private String currency;
	
	protected String productType;

	private List<ProductCategory> categories;
	
	private List<Promotion> promotions;

	private Department department;
	
	private List<Tag> tags;
	
	public Product(	String 			productUPC,
					LocalDateTime 	productCreateDt,
				   	String 			productStatus,
				   	String 			productDesc,
				   	String 			productLongDesc,
				   	Double 			productRetail,
				   	Double 			productMarkdown,
				   	String 			productImage,
				   	String 			lclCd,
				   	String 			currency,
				   	Department 		department,
				   	List<ProductCategory> productCategories,
				   	List<Promotion> productPromotions) {
		
					this.productUPC = productUPC;
					this.productCreateDt = productCreateDt;
					this.productDesc = productDesc;
					this.productRetail = productRetail;
					this.productMarkdown = productMarkdown;
					this.productImage = productImage;
					this.lclCd = lclCd;
					this.currency = currency;
					this.department = department;
					this.tags = new ArrayList<Tag>();
					this.categories = productCategories;
					this.promotions	= productPromotions;
					this.productStatus = productStatus;
					this.productLongDesc = productLongDesc;
	}

	public String getProductStatus() {
		return productStatus;
	}
	
	public List<ProductCategory> getCategories() {
		return categories;
	}

	public Department getDepartment() {
		return department;
	}
	
	public String getProductUPC() {
		return productUPC;
	}

	public LocalDateTime getProductCreateDt() {
		return productCreateDt;
	}

	public String getProductDesc() {
		return productDesc;
	}
	
	public String getProductLongDesc() {
		return productLongDesc;
	}

	public double getProductRetail() {
		return productRetail;
	}

	public double getProductMarkdown() {
		return productMarkdown;
	}

	public String getProductImage() {
		return productImage;
	}

	public String getLclCd() {
		return lclCd;
	}
	
	public String getCurrency() {
		return currency;
	}

	public String getProductType() {
		return productType;
	}
	
	public void addTag(Tag t) {
		tags.add(t);
	}
	
	public List<Tag> getTags() {
		return tags;
	}

	public List<Promotion> getPromotions() {
		return promotions;
	}
	
	public void addPromotion(Promotion promotion) {
		this.promotions.add(promotion);
	}

	@Override
	public boolean equals(Object o) {
		 if (this == o) return true;
	     if (o == null || getClass() != o.getClass()) return false;
	     Product pcDto = (Product) o;
	     return this.productUPC == pcDto.productUPC;
	}

	@Override
	public int hashCode() {
		return Objects.hash(productUPC);
	}

	@Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Product [productUPC=").append(productUPC)
        		.append(", productCreateDt=").append(productCreateDt)
        		.append(", productDesc=").append(productDesc)
        		.append(", productRetail=").append(productRetail)
        		.append(", productMarkdown=").append(productMarkdown)
                .append(", ProductImage=").append(productImage)
                .append(", lclCd=").append(lclCd);
        return builder.toString();
    }


}