package io.javabrains.springbootstarter.services;

import java.util.Date;

//this is the grand daddy DTO
/*---------------------------testing DTO JSON---------------------------
{
	"userName":"dmac654331",
	"password":"magic7653",
	"enabled":true,
	"givenName":"Ronald",
	"familyName":"McDonald"
}
*/

//@JsonTypeName(value = "customer")
//@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class ProductDTO {

	private Long productId;

	private String productUPC;
	
	private Date productCreateDt;

	private String productDesc;
	
	private double productRrp;
	
	private String ProductImage;
	
	private String lclCd;
	
	
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
		return ProductImage;
	}

	public void setProductImage(String productImage) {
		ProductImage = productImage;
	}

	public String getLclCd() {
		return lclCd;
	}

	public void setLclCd(String lclCd) {
		this.lclCd = lclCd;
	}

	@Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ProductDto [productId=").append(productId).append(", productUPC=").append(productUPC).append(", productCreateDt=").append(productCreateDt).append(", productDesc=").append(productDesc).append(", productRrp=").append(productRrp)
                .append(", ProductImage=").append(ProductImage).append(", lclCd=").append(lclCd);
        return builder.toString();
    }

}