package io.nzbee.domain.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;

import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.category.ProductCategory;

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
	
	@JsonIgnore
	private List<ProductCategory> categories;
	
	private List<Brand> brands;
	
	public Product() {
	
	}
	
	public Product(	String productUPC,
				   	Date productCreateDt,
				   	String productDesc,
				   	Double productRetail,
				   	Double productMarkdown,
				   	String productImage,
				   	String displayCategories,
				   	String lclCd,
				   	String currency) {
		
					this.productUPC = productUPC;
					this.productCreateDt = productCreateDt;
					this.productDesc = productDesc;
					this.productRetail = productRetail;
					this.productMarkdown = productMarkdown;
					this.productImage = productImage;
					this.displayCategories = displayCategories;
					this.lclCd = lclCd;
					this.currency = currency;
					this.categories = new ArrayList<ProductCategory>();
	}
	
	public void addCategory(ProductCategory productCategory) {
		this.getCategories().add(productCategory);
	}
	
	public void removeCategory(ProductCategory productCategory) {
		this.getCategories().remove(productCategory);
	}
	
	public void addBrand(Brand brand) {
		this.getBrands().add(brand);
	}
	

	public void removeBrand(Brand brand) {
		this.getBrands().remove(brand);
	}
	
	public List<Brand> getBrands() {
		return brands;
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

	public List<ProductCategory> getCategories() {
		return categories;
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