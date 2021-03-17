package io.nzbee.view.product.physical;

import com.fasterxml.jackson.annotation.JsonTypeName;

import io.nzbee.view.product.ProductDTO;

//the DTO exposes the bits or the slice of the domain model that we want to expose to the 
//application UI, stitching the UI directly over the domain model results in verbosity in the 
//domain model in terms of Jackson annotations (i.e. Ignore that we typically want to avoid)

@JsonTypeName("physicalproduct")
public class PhysicalProductDTO extends ProductDTO {
	
	private boolean inStock;

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}
	
}

