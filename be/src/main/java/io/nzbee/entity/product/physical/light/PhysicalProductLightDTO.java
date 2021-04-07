package io.nzbee.entity.product.physical.light;

import java.io.Serializable;

public class PhysicalProductLightDTO implements Serializable {

	private static final long serialVersionUID = -8575411581964316295L;

	private String 		productUPC; 
	
	private String 		productDesc; 
	
	private String 		brandDesc;
	
	private Double 		retailPrice; 
	
	private Double 		markdownPrice; 
	
	private Boolean 	inStock;
	
	private String 		productImage;

	public PhysicalProductLightDTO(String 	 productUPC, 
								   String 	 productDesc,
								   String 	 brandDesc,
								   Double 	 retailPrice,
								   Double 	 markdownPrice, 
								   Boolean 	 inStock, 
								   String	 productImage) {
		super();
		this.productUPC 	= productUPC;
		this.productDesc 	= productDesc;
		this.retailPrice 	= retailPrice;
		this.markdownPrice 	= markdownPrice;
		this.brandDesc		= brandDesc;
		this.inStock 		= inStock;
		this.productImage 	= productImage;
	}

	public String getProductUPC() {
		return productUPC;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public String getBrandDesc() {
		return brandDesc;
	}

	public Double getRetailPrice() {
		return retailPrice;
	}

	public Double getMarkdownPrice() {
		return markdownPrice;
	}

	public Boolean getInStock() { 
		return inStock;
	}

	public String getProductImage() {
		return productImage;
	}

}
