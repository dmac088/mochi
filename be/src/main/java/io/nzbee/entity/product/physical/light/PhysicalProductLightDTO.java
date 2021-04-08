package io.nzbee.entity.product.physical.light;

import java.io.Serializable;
import java.math.BigDecimal;

public class PhysicalProductLightDTO implements Serializable {

	private static final long serialVersionUID = -8575411581964316295L;

	private String 		productupc; 
	
	private String 		productdesc; 
	
	private String 		branddesc;
	
	private BigDecimal 	retailprice; 
	
	private BigDecimal 	markdownprice; 
	
	private Boolean 	instock;
	
	private String 		productimage;

	public PhysicalProductLightDTO(String 		productUPC, 
								   String 	 	productDesc,
								   String 	 	brandDesc,
								   BigDecimal 	retailPrice,
								   BigDecimal 	markdownPrice, 
								   Boolean 	 	inStock, 
								   String	 	productImage) {
		super();
		this.productupc 	= productUPC;
		this.productdesc 	= productDesc;
		this.retailprice 	= retailPrice;
		this.markdownprice 	= markdownPrice;
		this.branddesc		= brandDesc;
		this.instock 		= inStock;
		this.productimage 	= productImage;
	}
	
	public PhysicalProductLightDTO() {
		super();
	}

	public String getProductupc() {
		return productupc;
	}

	public void setProductupc(String productupc) {
		this.productupc = productupc;
	}

	public String getProductdesc() {
		return productdesc;
	}

	public void setProductdesc(String productdesc) {
		this.productdesc = productdesc;
	}

	public String getBranddesc() {
		return branddesc;
	}

	public void setBranddesc(String branddesc) {
		this.branddesc = branddesc;
	}

	public BigDecimal getRetailprice() {
		return retailprice;
	}

	public void setRetailprice(BigDecimal retailprice) {
		this.retailprice = retailprice;
	}

	public BigDecimal getMarkdownprice() {
		return markdownprice;
	}

	public void setMarkdownprice(BigDecimal markdownprice) {
		this.markdownprice = markdownprice;
	}

	public Boolean getInstock() {
		return instock;
	}

	public void setInstock(Boolean instock) {
		this.instock = instock;
	}

	public String getProductimage() {
		return productimage;
	}

	public void setProductimage(String productimage) {
		this.productimage = productimage;
	}	

}
