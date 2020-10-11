package io.nzbee.domain.bag;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import io.nzbee.domain.customer.Customer;
import io.nzbee.domain.product.Product;

public class Bag {
	
	private Set<BagItem> bagItems;
	
	private Customer customer;
	
	private BagIssues bagIssues = new BagIssues();

	public Bag(	Customer customer) {
		this.customer 		= customer;
		this.bagItems 		= new HashSet<BagItem>();
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public Set<BagItem> getBagItems() {
		return bagItems;
	}
	
	public void addItem(Product p, int qty) {
		Optional<BagItem> obi = this.getBagItems().stream()
		.filter(bi -> bi.getProduct().getProductUPC().equals(p.getProductUPC()))
		.findAny();
		
		BagItem bi = new BagItem(this, p, qty);
		
		if(obi.isPresent()) {
			bi = obi.get();
			bi.addToQuantity(qty);
		} else {
			this.getBagItems().add(bi);
		}
	}
	
	public void removeItem(BagItem bi) {
		this.getBagItems().remove(bi);
	}
	
	public int getTotalItems() {
		return this.getBagItems().size();
	}
	
	public int getTotalQuantity() {
		return this.getBagItems().stream().mapToInt(BagItem::getQuantity).sum();
	}
	
	public Double getTotalAmount() {
		return this.getBagItems().stream().mapToDouble(bi -> bi.getQuantity() * bi.getProduct().getProductMarkdown()).sum();
	}
	
	public void logItemError(String key, BagItem bagItem) {
		bagIssues.logItemError(key, bagItem);
		bagItems.add(bagItem);
		bagItem.setBagItemStatus("PND01");
	}
	
	public BagIssues getBagIssues() {
		return bagIssues;
	}

	public boolean hasIssues() {
		return bagIssues.hasIssues();
	}
}
