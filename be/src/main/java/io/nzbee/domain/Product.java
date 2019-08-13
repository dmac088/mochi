package io.nzbee.domain;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;


public class Product {

	private String productUPC;
	
	private Date productCreateDt;

	private String productDesc;
	
	private Optional<Brand> brand;
	
	private double productRetail;
	
	private double productMarkdown;
	
	private String productImage;
	
	private String lclCd;
	
	private String currency;
	
	private String primaryCategoryPath; 

	public String getProductUPC() {
		return productUPC;
	}

	public void setProductUPC(String productUPC) {
		this.productUPC = productUPC;
	}

	public Date getProductCreateDt() {
		return productCreateDt;
	}

	public void setProductCreateDt(Date productCreateDt) {
		this.productCreateDt = productCreateDt;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public double getProductRetail() {
		return productRetail;
	}

	public void setProductRetail(double productRetail) {
		this.productRetail = productRetail;
	}

	public double getProductMarkdown() {
		return productMarkdown;
	}

	public void setProductMarkdown(double productMarkdown) {
		this.productMarkdown = productMarkdown;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getLclCd() {
		return lclCd;
	}

	public void setLclCd(String lclCd) {
		this.lclCd = lclCd;
	}
	
	public String getPrimaryCategoryPath() {
		return primaryCategoryPath;
	}

	public void setPrimaryCategoryPath(String primaryCategoryPath) {
		this.primaryCategoryPath = primaryCategoryPath;
	}
	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public Optional<Brand> getBrand() {
		return brand;
	}

	public void setBrand(Optional<Brand> brand) {
		this.brand = brand;
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