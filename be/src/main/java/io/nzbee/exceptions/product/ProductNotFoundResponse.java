package io.nzbee.exceptions.product;

public class ProductNotFoundResponse {

	private String ProductNotFound;

	public ProductNotFoundResponse(String ProductNotFound) {
		this.setProductNotFound(ProductNotFound);
	}

	public String getProductNotFound() {
		return ProductNotFound;
	}

	public void setProductNotFound(String ProductNotFound) {
		this.ProductNotFound = ProductNotFound;
	}
	
}
