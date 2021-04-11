package io.nzbee.view.product.physical.full;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.nzbee.view.product.ProductView;

@JsonTypeName("physicalproduct")
public class PhysicalProductFullView extends ProductView {
	
	private boolean inStock;
		
	private Double weight;

	public PhysicalProductFullView(ProductView productView, 
								   boolean inStock, 
								   Double weight) {
		super();
		this.inStock = inStock;
		this.weight = weight;
	}

	
	public boolean isInStock() {
		return inStock;
	}

	public Double getWeight() {
		return weight;
	}
	
}

