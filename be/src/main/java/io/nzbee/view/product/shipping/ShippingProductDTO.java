package io.nzbee.view.product.shipping;

import io.nzbee.view.product.ProductDTO;

//the DTO exposes the bits or the slice of the domain model that we want to expose to the 
//application UI, stitching the UI directly over the domain model results in verbosity in the 
//domain model in terms of Jackson annotations (i.e. Ignore that we typically want to avoid)

public class ShippingProductDTO extends ProductDTO {
	
	private String shippingDestinationCode;
	
	private String shippingDestinationDesc;
	
	private String shippingTypeCode;
	
	private String shippingTypeDesc;
	
	private Double weightLimit;
    
    private Double weightFrom;
    
    private Double weightTo;
	
	public String getShippingDestinationCode() {
		return shippingDestinationCode;
	}

	public void setShippingDestinationCode(String destinationCode) {
		this.shippingDestinationCode = destinationCode;
	}

	public String getShippingDestinationDesc() {
		return shippingDestinationDesc;
	}

	public void setShippingDestinationDesc(String destinationDesc) {
		this.shippingDestinationDesc = destinationDesc;
	}

	public String getShippingTypeCode() {
		return shippingTypeCode;
	}

	public void setShippingTypeCode(String shippingTypeCode) {
		this.shippingTypeCode = shippingTypeCode;
	}

	public String getShippingTypeDesc() {
		return shippingTypeDesc;
	}

	public void setShippingTypeDesc(String shippingTypeDesc) {
		this.shippingTypeDesc = shippingTypeDesc;
	}

	public Double getWeightLimit() {
		return weightLimit;
	}

	public void setWeightLimit(Double weightLimit) {
		this.weightLimit = weightLimit;
	}

	public Double getWeightFrom() {
		return weightFrom;
	}

	public void setWeightFrom(Double weightFrom) {
		this.weightFrom = weightFrom;
	}

	public Double getWeightTo() {
		return weightTo;
	}

	public void setWeightTo(Double weightTo) {
		this.weightTo = weightTo;
	}

}

