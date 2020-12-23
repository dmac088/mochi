package io.nzbee.domain.product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.department.Department;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.domain.tag.Tag;

@JsonTypeInfo(
	    use = JsonTypeInfo.Id.NAME,
	    include = JsonTypeInfo.As.PROPERTY,
	    property="type")
@JsonSubTypes( {@JsonSubTypes.Type(value = PhysicalProduct.class, 	name = "accessories")})
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
	
	private boolean inStock;
	
	protected String productType;

	@JsonIgnore
	private List<ProductCategory> categories;
	
	@JsonIgnore
	private List<Promotion> promotions;

	@JsonIgnore
	private Department department;
	
	private Brand brand;
	
	@JsonIgnore
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
				   	boolean			inStock,
				   	Brand 			brand,
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
					this.brand = brand;
					this.department = department;
					this.tags = new ArrayList<Tag>();
					this.categories = productCategories;
					this.promotions	= productPromotions;
					this.productStatus = productStatus;
					this.productLongDesc = productLongDesc;
					this.inStock = inStock;
	}
	
	public boolean isInStock() {
		return inStock;
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

	public Brand getBrand() {
		return brand;
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