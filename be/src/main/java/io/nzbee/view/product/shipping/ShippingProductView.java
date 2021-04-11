package io.nzbee.view.product.shipping;

public class ShippingProductView  {
	
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

