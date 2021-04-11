package io.nzbee.view.product.shipping;

import com.fasterxml.jackson.annotation.JsonTypeName;

import io.nzbee.view.product.ProductView;

//the DTO exposes the bits or the slice of the domain model that we want to expose to the 
//application UI, stitching the UI directly over the domain model results in verbosity in the 
//domain model in terms of Jackson annotations (i.e. Ignore that we typically want to avoid)

@JsonTypeName("shippingproduct")
public class ShippingProductView {
	
	private ProductView productView;
	
	private String shippingDestinationCode;
	
	private String shippingDestinationDesc;
	
	private String shippingTypeCode;
	
	private String shippingTypeDesc;
	
	private Double weightLimit;
    
    private Double weightFrom;
    
    private Double weightTo;
	
    public ShippingProductView(	 ProductView productView, 
							     String shippingDestinationCode,
								 String shippingDestinationDesc,
								 String shippingTypeCode,								
								 String shippingTypeDesc,
								 Double weightLimit,
							     Double weightFrom,
							     Double weightTo
							   ) {
		super();
		this.productView = productView;
		this.shippingDestinationCode = shippingDestinationCode;
		this.shippingDestinationDesc = shippingDestinationDesc;
		this.shippingTypeCode		 = shippingTypeCode;
		this.shippingTypeDesc		 = shippingTypeDesc;
		this.weightLimit			 = weightLimit;
		this.weightFrom				 = weightFrom;
		this.weightTo				 = weightTo;
	}

	public ProductView getProductView() {
		return productView;
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

	public Double getWeightLimit() {
		return weightLimit;
	}

	public Double getWeightFrom() {
		return weightFrom;
	}

	public Double getWeightTo() {
		return weightTo;
	}
    
}

