package io.nzbee.view.product.physical.light;

import io.nzbee.view.product.ProductView;

public class PhysicalProductLightView extends ProductView {

	private boolean inStock;
	
	private String productImage;

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}
	
}
