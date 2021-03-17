package io.nzbee.view.product;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import io.nzbee.domain.product.physical.PhysicalProduct;
import io.nzbee.domain.product.shipping.ShippingProduct;

@JsonTypeInfo(
	    use = JsonTypeInfo.Id.NAME,
	    include = JsonTypeInfo.As.PROPERTY,
	    property="type")
@JsonSubTypes( {@JsonSubTypes.Type(value = PhysicalProduct.class, 	name = "physicalproduct"),
				@JsonSubTypes.Type(value = ShippingProduct.class, 	name = "shippingproduct")} )
public abstract class ProductDTO {

	protected String productUPC;

	protected String productDesc;
	
	protected String productLongDesc;
	
	protected Double productRetail;
	
	protected Double productMarkdown;
	
	protected String productType;
	
	protected String brandCode;
	
	protected String brandDesc;
	
	protected String locale;
	
	protected String currency;
	
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

	public String getProductLongDesc() {
		return productLongDesc;
	}

	public void setProductLongDesc(String productLongDesc) {
		this.productLongDesc = productLongDesc;
	}

	public Double getProductRetail() {
		return productRetail;
	}

	public void setProductRetail(Double productRetail) {
		this.productRetail = productRetail;
	}

	public Double getProductMarkdown() {
		return productMarkdown;
	}

	public void setProductMarkdown(Double productMarkdown) {
		this.productMarkdown = productMarkdown;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getBrandDesc() {
		return brandDesc;
	}

	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
