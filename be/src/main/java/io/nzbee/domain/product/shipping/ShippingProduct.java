package io.nzbee.domain.product.shipping;

import java.time.LocalDateTime;
import java.util.List;
import io.nzbee.domain.product.Product;
import io.nzbee.domain.promotion.Promotion;


public class ShippingProduct extends Product {
	 
    private String shippingDestinationCode;
    
    private String shippingDestinationDesc;
    
    private String shippingTypeCode;
    
    private String shippingTypeDesc;
   
    private Double weightLimit;
    
    private Double weightFrom;
    
    private Double weightTo;
    
	public ShippingProduct(	String productUPC, 
							LocalDateTime productCreateDt, 
							String productStatus, 
							String productDesc,
							String productLongDesc, 
							Double productRetail, 
							Double productMarkdown, 
							String productImage, 
							String lclCd,
							String currency, 
							boolean inStock,
							List<Promotion> productPromotions,
							Double weightLimit,
							Double weightFrom,
							Double weightTo,
							String shippingDestinationCode,
							String shippingDestinationDesc,
							String shippingTypeCode,
							String shippingTypeDesc
							) {
		super(	productUPC, 
				productCreateDt, 
				productStatus, 
				productDesc, 
				productLongDesc,
				productRetail, 
				productMarkdown,
				productImage, 
				lclCd, 
				currency,
				productPromotions);
		
		this.weightLimit 				= 	weightLimit;
		this.weightFrom 				=	weightFrom;
		this.weightTo					= 	weightTo;
		this.shippingDestinationCode	= 	shippingDestinationCode;
		this.shippingDestinationDesc	= 	shippingDestinationDesc;
		this.shippingTypeCode			=	shippingTypeCode;
		this.shippingTypeDesc			=	shippingTypeDesc;
	}

	public Double getWeightLimit() {
		return weightLimit;
	}

	public Double getWeightFrom() {
		return weightFrom;
	}

	public Double getWeightTo() {
		return weightTo;
	}

	public String getShippingDestinationCode() {
		return shippingDestinationCode;
	}

	public String getShippingDestinationDesc() {
		return shippingDestinationDesc;
	}

	public String getShippingTypeCode() {
		return shippingTypeCode;
	}

	public String getShippingTypeDesc() {
		return shippingTypeDesc;
	}


}
