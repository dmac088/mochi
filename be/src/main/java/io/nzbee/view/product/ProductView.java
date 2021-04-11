package io.nzbee.view.product;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.nzbee.view.product.physical.full.PhysicalProductFullView;
import io.nzbee.view.product.shipping.ShippingProductView;

@JsonTypeInfo(
	    use 		= JsonTypeInfo.Id.NAME,
	    include 	= JsonTypeInfo.As.PROPERTY,
	    property	="type")
@JsonSubTypes( {@JsonSubTypes.Type(value = PhysicalProductFullView.class, 	name = "physicalproduct"),
				@JsonSubTypes.Type(value = ShippingProductView.class, 		name = "shippingproduct")})
public class ProductView {

	private String productUPC;

	private String productDesc;
	
	private BigDecimal productRetail;
	
	private BigDecimal productMarkdown;
	
	private String productType;
	
	private String brandDesc;

	public String getProductUPC() {
		return productUPC;
	}

	public void setProductUPC(String productUPC) {
		this.productUPC = productUPC;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public BigDecimal getProductRetail() {
		return productRetail;
	}

	public void setProductRetail(BigDecimal productRetail) {
		this.productRetail = productRetail;
	}

	public BigDecimal getProductMarkdown() {
		return productMarkdown;
	}

	public void setProductMarkdown(BigDecimal productMarkdown) {
		this.productMarkdown = productMarkdown;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getBrandDesc() {
		return brandDesc;
	}

	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc;
	}

}
