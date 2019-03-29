package io.javabrains.springbootstarter.services;

import java.util.Date;
import java.util.List;


public class ProductDTO {

	private Long productId;

	private String productUPC;
	
	private Date productCreateDt;

	private String productDesc;
	
	private String brandDesc;

	private double productRrp;
	
	private String productImage;
	
	private Long productPreview;
	
	private String lclCd;
	
	private List<ProductCategoryDTO> categories;


	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
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

	public double getProductRrp() {
		return productRrp;
	}

	public void setProductRrp(double productRrp) {
		this.productRrp = productRrp;
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

	public Long getProductPreview() {
		return productPreview;
	}

	public void setProductPreview(Long productPreview) {
		this.productPreview = productPreview;
	}

	public void setLclCd(String lclCd) {
		this.lclCd = lclCd;
	}
	
	public List<ProductCategoryDTO> getCategories() {
		return categories;
	}

	public void setCategories(List<ProductCategoryDTO> categories) {
		this.categories = categories;
	}
	
	public String getBrandDesc() {
		return brandDesc;
	}

	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc;
	}

	@Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ProductDto [productId=").append(productId)
        		.append(", productUPC=").append(productUPC)
        		.append(", productCreateDt=").append(productCreateDt)
        		.append(", productDesc=").append(productDesc)
        		.append(", brandDesc=").append(brandDesc)
        		.append(", productRrp=").append(productRrp)
                .append(", ProductImage=").append(productImage)
                .append(", PreviewFlag=").append(productPreview)
                .append(", lclCd=").append(lclCd);
        return builder.toString();
    }

}