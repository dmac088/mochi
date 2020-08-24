package io.nzbee.entity.bag;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.nzbee.entity.product.Product;

@Table(name = "bag_item", schema = "mochi")
public class BagItem {

	@ManyToOne
	private Bag bag;
	
	@Column(name="prd_id")
	private Product product;
	
	@Column(name="qty")
	private double quantity;
	
	public BagItem(Product p) {
		this.product = p;
	}
	
	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
}
