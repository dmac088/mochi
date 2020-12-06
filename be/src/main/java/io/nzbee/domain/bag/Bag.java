package io.nzbee.domain.bag;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import io.nzbee.domain.customer.Customer;
import io.nzbee.domain.product.Product;

public class Bag {
	
	private List<BagItem> bagItems = new ArrayList<>();
	
	private Customer customer;
	
	private BagIssues bagIssues = new BagIssues();

	public Bag(Customer customer) {
		this.customer = customer;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public List<BagItem> getBagItems() {
		return bagItems;
	}
	
	public void addItem(Product p, int qty) {
		System.out.println("adding quantity " + qty + " for product " + p.getProductUPC());
		
		Optional<BagItem> obi = this.getBagItems().stream()
		.filter(bi -> bi.getProduct().getProductUPC().equals(p.getProductUPC()))
		.findAny();
		
		System.out.println("bagItem found = " + obi.isPresent());
		
		if(obi.isPresent()) {
			System.out.println("addToQuantity");
			obi.get().addToQuantity(qty);
		} else {
			System.out.println("adding bag item");
			this.getBagItems().add(new BagItem(this, p, qty));
		}
		
	}
	
	public void addItem(BagItem bi) {
		this.addItem(bi.getProduct(), bi.getQuantity());
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
		bagItem.setBagItemStatus("PND01");
	}
	
	public BagIssues getBagIssues() {
		return bagIssues;
	}

	public boolean hasIssues() {
		return bagIssues.hasIssues();
	}
}
