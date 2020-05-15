package io.nzbee.domain.category;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("layoutcategory")
public class LayoutCategory extends Category {

	private Long orderNumber;
	
	public LayoutCategory(String categoryCode, 
						  String categoryDesc, 
						  Long categoryLevel, 
						  String lclCd, 
						  String currency,
						  int objectCount,
						  Long orderNumber) {
		
		super(	categoryCode, 
				categoryDesc, 
				categoryLevel, 
				lclCd, 
				currency, 
				objectCount);
		
		this.orderNumber = orderNumber;
	}

	public Long getOrderNumber() {
		return orderNumber;
	}

}
