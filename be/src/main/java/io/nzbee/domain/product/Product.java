package io.nzbee.domain.product;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("productcategory")
public class Product {

	private String productUPC;
	
	private Date productCreateDt;

	private String productDesc;
	
	private Double productRetail;
	
	private Double productMarkdown;
	
	private String productImage;
	
	private String displayCategories;

	private String lclCd;
	
	private String currency;
	
	public Product() {
	
	}
	
	public Product(String productUPC,
				   Date productCreateDt,
				   String productDesc,
				   Double productRetail,
				   Double productMarkdown,
				   String productImage,
				   String lclCd,
				   String currency) {
		
		this.productUPC = productUPC;
		this.productCreateDt = productCreateDt;
		this.productDesc = productDesc;
		this.productRetail = productRetail;
		this.productMarkdown = productMarkdown;
		this.productImage = productImage;
		this.lclCd = lclCd;
		this.currency = currency;
	}
	
	public String getProductUPC() {
		return productUPC;
	}

	public Date getProductCreateDt() {
		return productCreateDt;
	}

	public String getProductDesc() {
		return productDesc;
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

	public String getDisplayCategories() {
		return displayCategories;
	}

	public String getLclCd() {
		return lclCd;
	}
	
	public String getCurrency() {
		return currency;
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