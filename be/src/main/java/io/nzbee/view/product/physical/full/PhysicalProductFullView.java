package io.nzbee.view.product.physical.full;

import io.nzbee.view.product.ProductView;

public class PhysicalProductFullView {

	private ProductView productView;
	
	private boolean inStock;
		
	private Double weight;

	public PhysicalProductFullView(ProductView productView, 
								   boolean inStock, 
								   Double weight) {
		super();
		this.productView = productView;
		this.inStock = inStock;
		this.weight = weight;
	}

	public String getProductUPC() {
		return productView.getProductUPC();
	}

	public String getProductDesc() {
		return productView.getProductDesc();
	}

	public Double getProductRetail() {
		return productView.getProductRetail();
	}
	public Double getProductMarkdown() {
		return productView.getProductMarkdown();
	}

	public String getProductType() {
		return productView.getProductType();
	}

	public String getBrandDesc() {
		return productView.getBrandDesc();
	}
	
	public boolean isInStock() {
		return inStock;
	}

	public Double getWeight() {
		return weight;
	}

	public ProductView getProductView() {
		return productView;
	}
	
	
}

