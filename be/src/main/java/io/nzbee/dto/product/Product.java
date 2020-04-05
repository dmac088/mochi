package io.nzbee.dto.product;

import java.util.Date;
import java.util.Objects;
import io.nzbee.dto.IDto;

public class Product implements IDto {

	private String productUPC;
	
	private Date productCreateDt;

	private String productDesc;
	
	private String departmentCode;
	
	private String departmentDesc;
	
	private String BrandCode;

	private String brandDesc;

	private String productStatusCode;
	
	private String productStatusDesc;

	private Double productRetail;
	
	private Double productMarkdown;
	
	private String productImage;
	
	private String displayCategories;

	private String lclCd;
	
	private String currency;
	
	public Product() {
	
	}

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return this.getProductUPC();
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return this.getClass().getSimpleName();
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

	public String getProductStatusCode() {
		return productStatusCode;
	}

	public void setProductStatusCode(String productStatusCode) {
		this.productStatusCode = productStatusCode;
	}

	public String getProductStatusDesc() {
		return productStatusDesc;
	}

	public void setProductStatusDesc(String productStatusDesc) {
		this.productStatusDesc = productStatusDesc;
	}
	
	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	
	public String getDepartmentDesc() {
		return departmentDesc;
	}
	
	public String getBrandCode() {
		return BrandCode;
	}

	public void setBrandCode(String brandCode) {
		BrandCode = brandCode;
	}
	
	public String getBrandDesc() {
		return brandDesc;
	}

	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc;
	}

	public void setDepartmentDesc(String departmentDesc) {
		this.departmentDesc = departmentDesc;
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
	
	
	public String getDisplayCategories() {
		return displayCategories;
	}

	public void setDisplayCategories(String displayCategories) {
		this.displayCategories = displayCategories;
	}


	public String getLclCd() {
		return lclCd;
	}

	public void setLclCd(String lclCd) {
		this.lclCd = lclCd;
	}
	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public boolean equals(Object o) {
		 if (this == o) return true;
	     if (o == null || getClass() != o.getClass()) return false;
	     Product pcDto = (Product) o;
	     return this.getCode() == pcDto.getCode();
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getCode());
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